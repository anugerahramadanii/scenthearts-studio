package com.scentheartsstudio.scentheartsstudio.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends Exception {
    private Integer code;
    private String message;

    //constructor
    // nama constructor sama dengan nama class
    public CustomException(Integer code, String message) {
        // memanggil constructor parent (superclass), yaitu Exception
        super();
        this.code = code;
        this.message = message;
    }
}
