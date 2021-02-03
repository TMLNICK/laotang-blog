package com.personblog.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "分类实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    @ApiModelProperty(value = "分类id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "博客集合")
    private List<Blog> blogs = new ArrayList<>();

}
