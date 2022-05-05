package com.haha.business.audiobook.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chapter_test")
public class Chapter {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private int chapterNum;
    private String name;
    private String traditionalName;
    private String url;
    private String mediaUrl;
    private int audiobookid;
    private String audiobookname;
    private String releaseDate;
    private String lastUpdateDate;
    private int spiderStatus;
    private int downloadStatus;

}
