package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeConfig {

    public static final ZoneId ZONE = ZoneId.of("Asia/Tokyo");

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZONE);
    }
}
