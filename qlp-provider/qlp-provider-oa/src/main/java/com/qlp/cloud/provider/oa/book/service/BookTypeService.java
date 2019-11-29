package com.qlp.cloud.provider.oa.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qlp.cloud.provider.oa.book.entity.BookType;

public interface BookTypeService extends IService<BookType> {
    /**
     * 保存书籍类型
     *
     * @param id ID
     * @param name 类型名称
     * @param type 属性归类 1：男生，2：女生
     */
    void save(Long id, String name, Integer type);
}
