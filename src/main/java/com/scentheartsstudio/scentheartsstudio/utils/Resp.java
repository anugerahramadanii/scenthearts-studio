package com.scentheartsstudio.scentheartsstudio.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// properti yang memiliki nilai null tidak akan disertakan dalam serialisasi JSON.
@JsonInclude(Include.NON_NULL)

// <> generic class
//generic class(Resp) with parameter generic type(T)
// Parameter tipe generik T digunakan untuk menunjukkan bahwa kelas Resp dapat menangani berbagai jenis data.
// misalnya, Resp<String> untuk menangani data bertipe String, atau Resp<Integer> untuk menangani data bertipe Integer
public class Resp<T> {

    private Integer code = 200;
    private String message = "OK";
    private T data;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
