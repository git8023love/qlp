package com.qlp.cloud.provider.quartz.task.enterprise;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component("enterpriseTask")
public class EnterpriseTask {
    public static String params = "{name:\"法定代表人\",registeredCapital:\"注册资本\",paidInCapital:\"实缴资本\",status:\"经营状态\",createDate:\"成立日期\",code:\"统一社会信用代码\",number:\"纳税人识别号\",registrationNumber:\"注册号\",OrganizationCode:\"组织机构代码\",type:\"企业类型\",industry:\"所属行业\",approvalDate:\"核准日期\",registrationAuthority:\"登记机关\",district:\"所属地区\",enName:\"英文名\",usedName:\"曾用名\",participantsNumber:\"参保人数\",staffSize:\"人员规模\",operatingDate:\"营业期限\",address:\"企业地址\",address:\"经营范围\"}";

    public static String params1 = "{\"法定代表人\":\"name\",\"注册资本\":\"registeredCapital\",\"实缴资本\":\"paidInCapital\",\"经营状态\":\"status\",\"成立日期\":\"createDate\",\"统一社会信用代码\":\"code\",\"纳税人识别号\":\"number\",\"注册号\":\"registrationNumber\",\"组织机构代码\":\"organizationCode\",\"企业类型\":\"type\",\"所属行业\":\"industry\",\"核准日期\":\"approvalDate\",\"登记机关\":\"registrationAuthority\",\"所属地区\":\"district\",\"英文名\":\"enName\",\"曾用名\":\"usedName\",\"参保人数\":\"participantsNumber\",\"人员规模\":\"staffSize\",\"营业期限\":\"operatingDate\",\"企业地址\":\"address\",\"经营范围\":\"businessScope\"}";

//    public static void main(String[] args) {
//        try {
//            EnterpriseTask enterpriseTask = new EnterpriseTask();
//            enterpriseTask.getDocument();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void getDocument() throws IOException {
        String url = "https://www.qichacha.com/search?key=深圳微跳动网络科技有限公司";
        String cookie = "QCCSESSID=687bn8rbm1eig75795ksg96ln7; zg_did=%7B%22did%22%3A%20%2216dce97ecc39-011f1d5eb49cb-4c312373-384000-16dce97ecc4f8%22%7D; zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f=%7B%22sid%22%3A%201571134304792%2C%22updated%22%3A%201571135239977%2C%22info%22%3A%201571129126092%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22%22%2C%22zs%22%3A%200%2C%22sc%22%3A%200%2C%22cuid%22%3A%20%221ea86f54c6861f2e1309149e727df845%22%7D; UM_distinctid=16dce97ed27111-045af1fe4719c78-4c312373-384000-16dce97ed284e7; CNZZDATA1254842228=469874692-1571128678-https%253A%252F%252Fwww.baidu.com%252F%7C1571134078; Hm_lvt_3456bee468c83cc63fb5147f119f1075=1571129127; Hm_lpvt_3456bee468c83cc63fb5147f119f1075=1571135239; hasShow=1; _uab_collina=157112912718432440471613; acw_tc=7169ab1a15711291252758760ed31dc7d917540d923833a13fabd41332";
        Map<String, String> cookieMap = getCookieMap(cookie);
        Document document = Jsoup.connect(url).cookies(cookieMap).timeout(3000).get();
        Element element = document.body();
        Elements tbody = element.select("tbody[id=search-result]");
        for (Element el : tbody) {
            Elements elements = el.children();
            for (Element ele : elements) {
                String href = ele.select("td a[class=ma_h1]").attr("href");
                url = "https://www.qichacha.com" + href;
                Document details = Jsoup.connect(url).cookies(cookieMap).timeout(3000).get();
                Elements infos = details.select("section[id=Cominfo] table[class=ntable] tbody");
                for (Element info : infos) {
                    Elements infoAttr = info.children();
                    Map<String, String> map = new HashMap<>();
                    boolean flag = true;
                    for (Element attr : infoAttr) {
                        JSONObject json = JSONUtil.parseObj(params1);
                        if(json.get(attr.children().eq(0).text()) != null) {
                            String name = "";
                            if(attr.children().eq(0).text().equals("法定代表人")) {
                                name = attr.children().eq(1).select("div[class=bpen] a h2").text();
                            } else {
                                name = attr.children().eq(1).text();
                            }
                            map.put(json.get(attr.children().eq(0).text()).toString(), name);
                        }
                        if(attr.children().size() > 2) {
                            if (json.get(attr.children().eq(2).text()) != null) {
                                String name = attr.children().eq(3).text();
                                map.put(json.get(attr.children().eq(2).text()).toString(), name);
                            }
                        }
                        if(attr.children().eq(0).text().equals("经营范围")) {
                            break;
                        }
                    }
                    System.out.println(JSONUtil.toJsonStr(map));
                    flag = false;
                    if(!flag) {
                        continue;
                    }
                }
            }
        }
    }

    private Map<String, String> getCookieMap(String cookie) {
        Map<String, String> map = new HashMap<>();
        Arrays.asList(cookie.split(";")).stream().forEach(attr -> map.put(attr.split("=")[0], attr.split("=")[1]));
        return map;
    }
}
