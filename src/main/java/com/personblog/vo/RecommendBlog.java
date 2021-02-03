package com.personblog.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "博客首页是否推荐展示vo")
public class RecommendBlog {

    private Long id;

    private String title;

    private boolean recommend;
}