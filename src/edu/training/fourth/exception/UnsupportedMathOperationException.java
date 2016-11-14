package edu.training.fourth.exception;

/**
 * Created by Roman on 14.11.2016.
 */
public class UnsupportedMathOperationException extends Exception {
    public UnsupportedMathOperationException() {
    }

    public UnsupportedMathOperationException(String message) {
        super(message);
    }

    public UnsupportedMathOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedMathOperationException(Throwable cause) {
        super(cause);
    }

    public UnsupportedMathOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
