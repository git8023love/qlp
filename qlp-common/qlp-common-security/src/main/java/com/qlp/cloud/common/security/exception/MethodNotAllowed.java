package com.qlp.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qlp.cloud.common.security.component.QlpAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

@JsonSerialize(using = QlpAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends QlpAuth2Exception {
    public MethodNotAllowed(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "method_not_allowed";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.METHOD_NOT_ALLOWED.value();
    }
}
