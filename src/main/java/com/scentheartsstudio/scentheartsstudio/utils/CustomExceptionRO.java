package com.scentheartsstudio.scentheartsstudio.utils;

import feign.FeignException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomExceptionRO extends FeignException {
    private Integer code;
    private String description;

    // Konstruktor dengan status code dan description
    public CustomExceptionRO(Integer code, String description) {
        // Memanggil konstruktor parent (FeignException) dengan status code dan pesan
        super(code, description);
        this.code = code;
        this.description = description;
    }
}
