package com.haha.common.domain;

import lombok.Data;

import java.io.Serializable;

// 只要这个类,可能要进行信息传递,就序列化
@Data
public class QueryRequest implements Serializable {
    private static final long serialVersionUID = -8730105947631834455L;

    private Long current;  // 当前第几页
    private Long size;  // 每页显示几个数据
}
