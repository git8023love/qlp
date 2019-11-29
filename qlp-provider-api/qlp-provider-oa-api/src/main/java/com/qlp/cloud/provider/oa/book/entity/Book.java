package com.qlp.cloud.provider.oa.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 书籍表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_book")
public class Book extends Model<Book> {

    private static final long serialVersionUID=1L;

    /**
    *
    */
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
    * 书名
    */
    private String name;
    /**
    * 作者Id
    */
    private Integer authorId;
    /**
    * 作者
    */
    private String author;
    /**
    * 地址
    */
    private String url;
    /**
    * 简介
    */
    private String intro;
    /**
    * 大类
    */
    private Integer type;
    /**
    * 小类
    */
    private Integer subType;
    /**
    * 1：连载，2：完结
    */
    private Integer category;
    /**
    * 状态 0：无效，1：有效
    */
    private Integer status;
    /**
    * 评分
    */
    private Double rate;
    /**
    * 阅读地址
    */
    private String readUrl;
    /**
    * 累积字数
    */
    private String word;
    /**
    * 总字数
    */
    private Integer wordNum;
    /**
    * 属性归类 1：男生，2：女生
    */
    private Integer sexType;
    /**
    * 标签
    */
    private String tags;
    /**
    *
    */
    private LocalDateTime createTime;
    /**
    *
    */
    private LocalDateTime updateTime;
    }
