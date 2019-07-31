package com.example.springboot.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class LocalDateTimeSerializerConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            log.info("加载定制化的 LocalDateTime JSON解析: {}", pattern);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(formatter);
            builder.serializerByType(LocalDateTime.class, serializer);
            LocalDateTimeDeserializer deSerializer = new LocalDateTimeDeserializer(formatter) {
                @Override
                public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
                    if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                        long millis = parser.getValueAsLong() / 1000L;
                        return LocalDateTime.ofInstant(Instant.ofEpochSecond(millis), JSON.defaultTimeZone.toZoneId());
                    }
                    return super.deserialize(parser, context);
                }
            };
            builder.deserializerByType(LocalDateTime.class, deSerializer);
            builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        };
    }
}
