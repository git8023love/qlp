package com.qlp.cloud.provider.oa.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlp.cloud.provider.oa.book.entity.Book;
import org.apache.ibatis.annotations.Param;

public interface BookMapper extends BaseMapper<Book> {

    /**
     * 书籍表简单分页查询
     * @param book 书籍表
     * @return
     */
    IPage<Book> getBookPage(Page page, @Param("book") Book book);
}
