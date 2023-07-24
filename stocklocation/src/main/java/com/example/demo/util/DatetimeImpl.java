package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatetimeImpl implements Datetime {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
