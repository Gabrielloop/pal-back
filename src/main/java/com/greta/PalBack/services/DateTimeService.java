package com.greta.PalBack.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateTimeService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public String getCurrentDateTime() {
        return LocalDateTime.now().format(FORMATTER);
    }

    public LocalDateTime getCurrentDateTimeObject() {
        return LocalDateTime.now();
    }
}
