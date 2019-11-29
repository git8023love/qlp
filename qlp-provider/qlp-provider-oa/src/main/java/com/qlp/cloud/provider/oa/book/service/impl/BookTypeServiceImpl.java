package com.qlp.cloud.provider.oa.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlp.cloud.provider.oa.book.entity.BookType;
import com.qlp.cloud.provider.oa.book.mapper.BookTypeMapper;
import com.qlp.cloud.provider.oa.book.service.BookTypeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {
    @Override
    public void save(Long id, String name, Integer type) {
        BookType bookType = this.getById(id);
        if (null == bookType) {
            bookType = new BookType();
            bookType.setId(id);
            bookType.setName(name);
            bookType.setType(type);
            bookType.setCreateTime(LocalDateTime.now());
            bookType.setUpdateTime(LocalDateTime.now());
            this.save(bookType);
        }
    }
}
