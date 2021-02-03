package com.personblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "统计实体类")
public class Count {

    @ApiModelProperty(value = "统计id")
    private Long id;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
