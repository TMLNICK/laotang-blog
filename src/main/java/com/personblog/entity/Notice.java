package com.personblog.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "通知实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @ApiModelProperty(value = "通知id")
    private Long id;

    @ApiModelProperty(value = "通知内容")
    private String content;


}
