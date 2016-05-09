package com.excilys.cdb.model.exception;

public class JDBCException extends RuntimeException {

    /**
     * Default RuntimeException constructor.
     */
    public JDBCException() {
        super();
    }

    /**
     * Default RuntimeException constructor.
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public JDBCException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Default RuntimeException constructor.
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).
     */
    public JDBCException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Default RuntimeException constructor.
     * @param message the detail message
     */
    public JDBCException(String message) {
        super(message);
    }

    /**
     * Default RuntimeException constructor.
     * @param cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).
     */
    public JDBCException(Throwable cause) {
        super(cause);
    }

}