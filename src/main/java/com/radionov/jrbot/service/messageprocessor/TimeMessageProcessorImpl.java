package com.radionov.jrbot.service.messageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Andrey Radionov
 */
public class TimeMessageProcessorImpl implements MessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeMessageProcessorImpl.class);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @Override
    public String processMessage(String message) {
        LOGGER.debug("TimeMessageProcessor - processMessage {}", message);
        ZoneId msc = ZoneId.of("Europe/Moscow");
        ZonedDateTime zdt = ZonedDateTime.now(msc);
        return ", сейчас " + zdt.format(DATE_FORMAT) + " (МСК)";
    }
}
