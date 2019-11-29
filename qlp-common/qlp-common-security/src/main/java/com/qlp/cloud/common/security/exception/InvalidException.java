package com.qlp.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qlp.cloud.common.security.component.QlpAuth2ExceptionSerializer;

@JsonSerialize(using = QlpAuth2ExceptionSerializer.class)
public class InvalidException extends QlpAuth2Exception {
    public InvalidException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "invalid_exception";
    }

    @Override
    public int getHttpErrorCode() {
        return 426;
    }
}
