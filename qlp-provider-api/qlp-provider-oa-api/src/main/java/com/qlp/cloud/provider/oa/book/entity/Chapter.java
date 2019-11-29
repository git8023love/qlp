package com.qlp.cloud.provider.oa.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_chapter_0")
public class Chapter extends Model<Chapter> {

    private static final long serialVersionUID = 108315445057928871L;

    @TableId
    private Long id;

    private Long bookId;

    private Integer chapterId;

    private String name;

    private String content;

    private Integer wordNum;

    private String nextUrl;

    private String prevUrl;
}
