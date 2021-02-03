package com.personblog.vo;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "博客和标签的vo")
public class BlogAndTag {

    private Long tagId;

    private Long blogId;
}
