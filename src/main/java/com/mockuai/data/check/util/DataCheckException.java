package com.mockuai.data.check.util;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-19 23:23
 */
public class DataCheckException extends RuntimeException {
    private static final long serialVersionUID = -2153361411667197679L;

    public DataCheckException() {
        super();
    }

    public DataCheckException(String message) {
        super(message);
    }

    public DataCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataCheckException(Throwable cause) {
        super(cause);
    }
}
