package com.qlp.cloud.provider.oa.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlp.cloud.provider.oa.book.entity.BookAttr;
import com.qlp.cloud.provider.oa.book.mapper.BookAttrMapper;
import com.qlp.cloud.provider.oa.book.service.BookAttrService;
import org.springframework.stereotype.Service;

@Service
public class BookAttrServiceImpl extends ServiceImpl<BookAttrMapper, BookAttr> implements BookAttrService {
}
