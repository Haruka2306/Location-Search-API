package com.example.crudapi.exception;

public class NoCornerFoundException extends RuntimeException {
    private int code;

    public NoCornerFoundException(int code, String message) {
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
