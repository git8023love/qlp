package com.qlp.cloud.provider.quartz.task.book;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qlp.cloud.common.core.constant.SecurityConstants;
import com.qlp.cloud.common.core.util.HttpUtils;
import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.quartz.feign.RemoteBookAttrService;
import com.qlp.cloud.provider.quartz.feign.RemoteBookService;
import com.qlp.cloud.provider.quartz.feign.RemoteBookTypeService;
import lombok.AllArgsConstructor;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component("novelTask")
public class NovelTask {

    private final RemoteBookService remoteBookService;
    private final RemoteBookAttrService remoteBookAttrService;
    private final RemoteBookTypeService remoteBookTypeService;

    public static String cookie;
    public static Integer pageSize = 20;

    public void getBookInfo(String params){
        qiDianNovel(params);
    }

    private void qiDianNovel(String param) {
        try {
            Integer type = ("M".equals(param) ? 2 : 1);
            R result = remoteBookTypeService.listBookType(type, SecurityConstants.FROM_IN);
            if (result.getCode() == 0) {
                Object data = JSONUtil.wrap(result.getData(), true);
                JSONArray array = JSONUtil.parseArray(data);
                for (Object obj : array) {
                    JSONObject bookType = JSONUtil.parseObj(obj);
                    Integer id = bookType.getInt("id");
                    Integer parentId = bookType.getInt("parentId");
                    String path = "";
                    path = getOriginalPath(param, path);
                    if (parentId > 0) {
                        path += "&chanId="+ parentId + "&subCateId=" + id;
                    } else {
                        path += "&chanId="+ id;
                    }

                    R attr = remoteBookAttrService.list(SecurityConstants.FROM_IN);
                    if (attr.getCode() == 0) {
                        Object attrs = JSONUtil.wrap(attr.getData(), true);
                        JSONArray attrArray = JSONUtil.parseArray(attrs);
                        for (Object attrObj : attrArray) {
                            JSONObject bookAttr = JSONUtil.parseObj(attrObj);
                            String tempPath = path;
                            tempPath = createPath(bookAttr, tempPath);
                            tempPath += "&page=";
                            requestContent(param, tempPath);
                        }
                    }

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestContent(String param, String tempPath) throws Exception {
        Integer totalNum = getTotalNum(tempPath+1);
        Integer pages = totalNum / pageSize == 0  ? totalNum / pageSize :  (totalNum / pageSize) + 1;

        Integer page = 0;
        boolean flag = page < pages ? true : false;

        while (flag) {
            String url = tempPath;
            page += 1;
            flag = page < pages ? true : false;
            url = url + page;
            System.out.println(url);
            flag = getContent(url, param);
        }
    }
    private boolean getContent(String url, String param) throws Exception {

        Document document = getDocument(url);
        Elements aEl = document.select("div[class=all-book-list] div[class=book-img-text] ul[class=all-img-list cf]");
        if (aEl.size() == 0) {
            return false;
        }
        for (Element e: aEl) {
            Elements elements = e.children();
            for (int i=0; i<20; i++) {
                Elements dataRids = elements.eq(i); // <li data-rid ="?">

                for (Element dataRid: dataRids) {
                    Elements dataRidChild = dataRid.children();
                    Elements bookMidInfos = dataRidChild.eq(1); // <div class="book-mid-info

                    for (Element bookMidInfo: bookMidInfos) {
                        Elements bookInfo = bookMidInfo.select("h4 a");
                        String bookLink = bookInfo.attr("href");
                        String bid = bookInfo.attr("data-bid");
                        String bookName = bookInfo.text();

                        Elements authorInfo = bookMidInfo.select("p[class=author] a[class=name]");
                        String author = authorInfo.text();

                        String typeName = bookMidInfo.select("p[class=author] a").eq(1).text();

                        Elements subType = bookMidInfo.select("p[class=author] a[class=go-sub-type]");
                        String typeId = subType.attr("data-typeid");
                        String subTypeId = subType.attr("data-subtypeid");
                        String subTypeName = subType.text();

                        String _category = bookMidInfo.select("p[class=author] span").text();
                        Integer category = 0;
                        if ("连载".equals(_category)) {
                            category = 0;
                        } else {
                            category = 1;
                        }

                        // 简介
                        String intro = bookMidInfo.select("p[class=intro]").text();

                        // 保存类型
                        Integer sexType = 0;
                        if ("F".equals(param)) {
                            sexType = 1;
                        } else if ("M".equals(param)){
                            sexType = 2;
                        }

                        Long id = null;
                        String name = "";
                        Integer type = sexType;
                        id = Long.valueOf(typeId);
                        name = typeName;
                        remoteBookTypeService.save(id, name, type, SecurityConstants.FROM_IN);
                        id = Long.valueOf(subTypeId);
                        name = subTypeName;
                        remoteBookTypeService.save(id, name, type, SecurityConstants.FROM_IN);

                        String readUrl = "", word = "";
                        StringBuffer tags = new StringBuffer();
                        Integer authorId = 0;
                        try {
                            Document document_real = getDocument("https:" + bookLink); //请求书本阅读链接
                            Elements readElement = document_real.select("div[class=book-info] p").eq(3).select("a").eq(0);
                            readUrl = readElement.attr("href");

                            // 累积字数
                            Elements wordElement = document_real.select("div[class=info-wrap] ul[class=work-state cf] li").eq(1).select("em");
                            word = wordElement.text();
                            // 作者ID author-info
                            Elements authorElement = document_real.select("div[class=author-info] div[class=author-photo]");
                            authorId = Integer.valueOf(authorElement.attr("data-authorid").equals("") ? "0" : authorElement.attr("data-authorid").toString());
                            // 标签
                            Elements tagsElement = document_real.select("div[class=book-state] p[class=tag-wrap] a[class=tags]");
                            for (Element tag : tagsElement) {
                                name = tag.text();
                                R attr = remoteBookAttrService.findAttrByName(name, SecurityConstants.FROM_IN);
                                if (attr.getCode() == 0) {
                                    Object attrs = JSONUtil.wrap(attr.getData(), true);
                                    JSONArray attrArray = JSONUtil.parseArray(attrs);
                                    for (Object attrObj : attrArray) {
                                        JSONObject bookAttr = JSONUtil.parseObj(attrObj);
                                        tags.append(bookAttr.get("id") + ",");
                                    }
                                }
                            }
                            if (null != tags) {
                                tags = tags.deleteCharAt(tags.length() -1);
                            }
                        } catch (Exception e1) {
                        }

                        // 评分
                        /*Elements rateElement = document_real.select("div[id=commentWrap] div[class=j_getData hidden] h4");
                        String score1 = rateElement.select("cite[id=score1]").text();
                        String score2 = rateElement.select("cite[id=score1]").text();
                        Double rate = Double.valueOf(score1 + "." + score2);*/
                        Double rate = 0.0;
                        try {
                            String data = HttpUtils.get("https://book.qidian.com/ajax/comment/index?_csrfToken="+cookie+"&bookId="+bid+"&pageSize=15", null, 3, "UTF-8");
                            JSONObject json = new JSONObject(data);
                            if (0 == json.getInt("code")) {
                                rate = json.getJSONObject("data").getDouble("rate");
                            }
                        } catch (Exception e1){
                        }
                        // 保存书信息
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", bid);
                        map.put("name", bookName);
                        map.put("author", author);
                        map.put("authorId", authorId);
                        map.put("url", "https:" + bookLink);
                        map.put("intro", intro);
                        map.put("typeId", typeId);
                        map.put("subTypeId", subTypeId);
                        map.put("category", category);
                        map.put("readUrl","https:" + readUrl);
                        map.put("word", word);
                        map.put("rate", rate);
                        map.put("sexType", sexType);
                        map.put("tags", tags);
                        remoteBookService.save(map, SecurityConstants.FROM_IN);
                    }
                }
            }
        }
        return true;
    }

    private Integer getTotalNum(String url) throws IOException {
        Integer totalNum = 0;
        Document document = getDocument(url);
        Elements aEl = document.getElementById("sort-count").selectFirst("div").getElementsByTag("span");
        for (Element e: aEl) {
            totalNum = Integer.valueOf(e.text());
        }
        return totalNum;
    }

    private Document getDocument(String url) throws IOException{
        // 1.获取httpClient对象
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        cookie = cookieStore.getCookies().get(0).getValue();

        String html = EntityUtils.toString(response.getEntity(), "utf-8");
        Document document = Jsoup.parse(html);
        return document;
    }

    private String createPath(JSONObject bookAttr, String tempPath) {
        Object sortId = "";
        if (null != bookAttr.get("sortId")) {
            sortId = bookAttr.get("sortId");
        }
        if (bookAttr.getInt("type") == 1) {
            tempPath += "&action="+ sortId;
        } else if(bookAttr.getInt("type") == 2) {
            tempPath += "&size="+ sortId;
        } else if(bookAttr.getInt("type") == 3) {
            tempPath += "&sign="+ sortId;
        } else if(bookAttr.getInt("type") == 4) {
            tempPath += "&update="+ sortId;
        } else if(bookAttr.getInt("type") == 5) {
            tempPath += "&tag="+ sortId;
        }
        return tempPath;
    }

    public String getOriginalPath(String param, String path) {
        if ("F".equals(param)) {
            path += "https://www.qidian.com/all?orderId=&vip=hidden&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=1";
        } else if ("M".equals(param)) {
            path += "https://www.qidian.com/mm/free/all?orderId=&vip=hidden&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=1";
        }
        return path;
    }
}
