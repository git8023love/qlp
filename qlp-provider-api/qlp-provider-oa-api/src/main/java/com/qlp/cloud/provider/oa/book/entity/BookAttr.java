package com.qlp.cloud.provider.oa.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true) // 生成Equals和Hash的实现方法
@TableName("t_book_attr")
public class BookAttr extends Model<BookAttr> {

    private static final long serialVersionUID = -785549174365499233L;

    @TableId
    private Integer id;

    private Integer sortId;

    private String name;

    /**
     * 类型：1：状态，2：字数，3：品质， 4：更新时间，5：标签
     */
    private Integer type;

    private LocalDateTime createTime;
}
