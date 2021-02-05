package com.bin.myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    private Long id;
    private String tagName;
}
