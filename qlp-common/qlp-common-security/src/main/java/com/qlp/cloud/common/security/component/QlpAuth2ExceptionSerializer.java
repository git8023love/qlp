package com.qlp.cloud.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.qlp.cloud.common.core.constant.CommonConstants;
import com.qlp.cloud.common.security.exception.QlpAuth2Exception;
import lombok.SneakyThrows;

/**
 * OAuth2 异常格式化
 */
public class QlpAuth2ExceptionSerializer extends StdSerializer<QlpAuth2Exception> {
    public QlpAuth2ExceptionSerializer() {
        super(QlpAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(QlpAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject();
        gen.writeObjectField("code", CommonConstants.FAIL);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getErrorCode());
        gen.writeEndObject();
    }
}
