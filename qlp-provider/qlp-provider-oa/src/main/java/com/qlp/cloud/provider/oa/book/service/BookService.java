package com.qlp.cloud.provider.oa.book.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qlp.cloud.provider.oa.book.entity.Book;

import java.util.Map;

public interface BookService extends IService<Book> {

    /**
     * 书籍表简单分页查询
     * @param book 书籍表
     * @return
     */
    IPage<Book> getBookPage(Page<Book> page, Book book);

    /**
     * 保存书籍信息
     * @param map
     */
    void save(Map<String, Object> map);
}
