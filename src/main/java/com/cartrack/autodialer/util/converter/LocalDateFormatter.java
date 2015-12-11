package com.cartrack.autodialer.util.converter;

import com.cartrack.autodialer.util.TimeUtil;
import org.springframework.format.Formatter;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * GKislin
 * 15.04.2015.
 */
public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return TimeUtil.parseLocalDate(text, null);
    }

    @Override
    public String print(LocalDate lt, Locale locale) {
        return lt.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
