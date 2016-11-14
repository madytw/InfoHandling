package edu.training.fourth.exception;

/**
 * Created by Roman on 02.11.2016.
 */
public class SymbolUnsupportedOperationException extends Exception {
    public SymbolUnsupportedOperationException() {
    }

    public SymbolUnsupportedOperationException(String message) {
        super(message);
    }

    public SymbolUnsupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SymbolUnsupportedOperationException(Throwable cause) {
        super(cause);
    }

    public SymbolUnsupportedOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
