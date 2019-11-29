package com.qlp.cloud.provider.oa.book.controller;

import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.oa.book.entity.BookAttr;
import com.qlp.cloud.provider.oa.book.service.BookAttrService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/attr")
public class BookAttrController {
    private final BookAttrService bookAttrService;

    /**
     * 查询所有书籍属性
     */
    @GetMapping("/list")
    public R<List<BookAttr>> list() {
        return new R<>(bookAttrService.list());
    }

    @GetMapping("/list/{name}")
    public R<Collection<BookAttr>> findAttrByName(@PathVariable String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        return new R<>(bookAttrService.listByMap(map));
    }
}
