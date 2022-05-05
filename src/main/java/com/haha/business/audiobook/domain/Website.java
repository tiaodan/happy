package com.haha.business.audiobook.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("website_test")
public class Website {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private String url;
}
