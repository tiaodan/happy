package com.haha.business.audiobook.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("country_test")
public class Country {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
}
