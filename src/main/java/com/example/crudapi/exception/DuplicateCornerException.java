package com.example.crudapi.exception;

public class DuplicateCornerException extends RuntimeException {

    public DuplicateCornerException() {
        super();
    }

    public DuplicateCornerException(String message) {
        super(message);
    }

    public DuplicateCornerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateCornerException(Throwable cause) {
        super(cause);
    }
    
}
