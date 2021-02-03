package com.personblog.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "后台搜索博客的vo")
public class SearchBlog {
    private String title;
    private Long typeId;
}
