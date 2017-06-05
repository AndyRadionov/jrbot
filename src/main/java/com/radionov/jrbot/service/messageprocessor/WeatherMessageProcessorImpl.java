package com.radionov.jrbot.service.messageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrey Radionov
 */
public class WeatherMessageProcessorImpl implements MessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherMessageProcessorImpl.class);

    @Override
    public String processMessage(String message) {
        LOGGER.debug("WeatherMessageProcessor - processMessage {}", message);
        return null;
    }
}
