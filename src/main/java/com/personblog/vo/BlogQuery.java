package com.personblog.vo;


import com.personblog.entity.Type;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "后台查询博客列表")
public class BlogQuery {

    private Long id;

    private String title;

    private Date updateTime;

    private Boolean recommend;

    private Boolean published;

    private Long typeId;

    private Type type;
}
