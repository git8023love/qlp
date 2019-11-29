package com.qlp.cloud.provider.oa.book.controller;

import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.oa.book.entity.BookType;
import com.qlp.cloud.provider.oa.book.service.BookTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/bookType")
public class BookTypeController {

    private final BookTypeService bookTypeService;

    /**
     * 按属性归类查询书籍分类集合
     *
     * @param type 属性归类 1：男生，2：女生
     * @return
     */
    @GetMapping("/list/{type}")
    public R<Collection<BookType>> listBookType (@PathVariable Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", type);
        return new R<>(bookTypeService.listByMap(map));
    }

    /**
     * 保存书籍类型
     *
     * @param id ID
     * @param name 类型名称
     * @param type 属性归类 1：男生，2：女生
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("type") Integer type) {
        bookTypeService.save(id, name, type);
        return new R<>();
    }
}
