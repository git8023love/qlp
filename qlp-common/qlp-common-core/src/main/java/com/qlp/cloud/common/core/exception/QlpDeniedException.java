package com.qlp.cloud.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * 403 授权拒绝
 */
@NoArgsConstructor
public class QlpDeniedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public QlpDeniedException(String message) {
        super(message);
    }

    public QlpDeniedException(Throwable cause) {
        super(cause);
    }

    public QlpDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public QlpDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
