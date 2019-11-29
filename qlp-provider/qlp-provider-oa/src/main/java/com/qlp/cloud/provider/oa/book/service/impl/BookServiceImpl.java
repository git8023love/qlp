package com.qlp.cloud.provider.oa.book.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlp.cloud.provider.oa.book.entity.Book;
import com.qlp.cloud.provider.oa.book.mapper.BookMapper;
import com.qlp.cloud.provider.oa.book.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public IPage<Book> getBookPage(Page<Book> page, Book book) {
        return baseMapper.getBookPage(page, book);
    }

    @Override
    public void save(Map<String, Object> map) {
        Book book = this.getById(map.get("id").toString());
        if (null == book) {
            book = new Book();
            book.setCreateTime(LocalDateTime.now());
        }
        book.setId(Long.valueOf(map.get("id").toString()));
        book.setName(map.get("name").toString());
        book.setAuthor(map.get("author").toString());
        book.setAuthorId(Integer.valueOf(map.get("authorId").toString()));
        book.setUrl(map.get("url").toString());
        book.setIntro(map.get("intro").toString());
        book.setType(Integer.valueOf(map.get("typeId").toString()));
        book.setSubType(Integer.valueOf(map.get("subTypeId").toString()));
        book.setCategory(Integer.valueOf(map.get("category").toString()));
        book.setReadUrl(map.get("readUrl").toString());
        book.setWord(map.get("word").toString());
        book.setRate(Double.valueOf(map.get("rate").toString()));
        book.setSexType(Integer.valueOf(map.get("sexType").toString()));
        book.setTags(map.get("tags").toString());
        book.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(book);
    }
}
