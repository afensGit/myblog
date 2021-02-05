package com.bin.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客类型实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    private Long id;
    private String typename;

}
