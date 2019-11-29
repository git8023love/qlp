package com.qlp.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qlp.cloud.common.security.component.QlpAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;


@JsonSerialize(using = QlpAuth2ExceptionSerializer.class)
public class ServerErrorException extends QlpAuth2Exception {
    public ServerErrorException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "server_error";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
