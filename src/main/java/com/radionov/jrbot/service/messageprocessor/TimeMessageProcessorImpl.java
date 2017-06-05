package com.radionov.jrbot.service.messageprocessor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Andrey Radionov
 */
public class TimeMessageProcessorImpl implements MessageProcessor {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @Override
    public String processMessage(String message) {
        ZoneId msc = ZoneId.of("Europe/Moscow");
        ZonedDateTime zdt = ZonedDateTime.now(msc);
        return ", сейчас " + zdt.format(DATE_FORMAT) + " (МСК)";
    }
}
