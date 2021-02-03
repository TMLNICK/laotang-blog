package com.personblog.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "标签实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @ApiModelProperty(value = "标签id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "博客集合")
    private List<Blog> blogs = new ArrayList<>();

}
