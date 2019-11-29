package com.qlp.cloud.provider.oa.book.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.oa.book.entity.Book;
import com.qlp.cloud.provider.oa.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    /**
     * 简单分页查询
     * @return
     */
    @GetMapping("/page")
    public R<IPage<Book>> getBookPage(Page<Book> page, Book book) {
        return new R<>(bookService.getBookPage(page,book));
    }

    @PostMapping("/save")
    public R save(@RequestBody Map<String, Object> map) {
        bookService.save(map);
        return new R<>();
    }

}
