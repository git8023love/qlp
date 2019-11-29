package com.qlp.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qlp.cloud.common.security.component.QlpAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = QlpAuth2ExceptionSerializer.class)
public class QlpAuth2Exception extends OAuth2Exception {
    @Getter
    private String errorCode;

    public QlpAuth2Exception(String msg) {
        super(msg);
    }

    public QlpAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
