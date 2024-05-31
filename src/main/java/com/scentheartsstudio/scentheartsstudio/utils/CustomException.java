package com.scentheartsstudio.scentheartsstudio.utils;

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

}
