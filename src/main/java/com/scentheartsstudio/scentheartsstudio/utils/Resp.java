package com.scentheartsstudio.scentheartsstudio.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Resp<T> {
    private Integer code = 200;
    private String message = "OK";
    private T data;
}
