package com.haha.business.audiobook.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("audiobook")
public class Audiobook {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;  // 必有
    private String traditionalName;
    private String url;  // 书url, 必有
    private int typeid;
    private String type;
    private int countryid;
    private String country;
    private int processid;
    private String process;
    private int websiteid;
    private String website;
    private String alias;
    private String author;
    private String reader;
    private String brief;
    private int isForAdult;
    private double score;
    private int totalChapter;
    private String coverPhotoUrl;  // 封面图片url
    private String coverPhotoSavepath;  // 封面图片保存路径
    private int hits;
    private Date releaseDate;
    private Date lastUpdateDate;
    private String updateToChapter;  // 更新至
    private int spiderStatus;
    private int downloadStatus;  // 下载状态

}
