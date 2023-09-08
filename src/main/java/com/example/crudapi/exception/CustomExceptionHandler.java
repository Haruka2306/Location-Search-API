package com.example.crudapi.exception;

public class CustomExceptionHandler extends RuntimeException {
    private int code;

    public CustomExceptionHandler(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
