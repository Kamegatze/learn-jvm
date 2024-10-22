package com.kamegatze.learnjvm.servicies.authentication.exceptions;

public class NotEqualsPasswordAndRetryPasswordException extends RuntimeException {
    public NotEqualsPasswordAndRetryPasswordException() {
    }

    public NotEqualsPasswordAndRetryPasswordException(String message) {
        super(message);
    }

    public NotEqualsPasswordAndRetryPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEqualsPasswordAndRetryPasswordException(Throwable cause) {
        super(cause);
    }

    public NotEqualsPasswordAndRetryPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
