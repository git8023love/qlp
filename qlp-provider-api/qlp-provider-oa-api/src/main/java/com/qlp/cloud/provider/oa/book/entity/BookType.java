package com.qlp.cloud.provider.oa.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_book_type")
public class BookType extends Model<BookType> {

    /**
     * 分类ID
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 状态 0：无效，1:有效
     */
    private Integer status = 1;

    /**
     * 父ID
     */
    private Long parentId = 0l;

    /**
     * 父分类名称
     */
    @TableField(exist=false)
    private String parentName;

    /**
     * 属性归类 1：男生，2：女生
     */
    private Integer type;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
