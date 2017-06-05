package com.radionov.jrbot.service.messageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrey Radionov
 */
public class TextMessageProcessorImpl implements MessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageProcessorImpl.class);

    @Override
    public String processMessage(String message) {
        LOGGER.debug("TextMessageProcessor - processMessage {}", message);
        if (message.contains("hi") || message.contains("hello") || message.contains("привет")) {
            return ", привет! (wave)";
        } else {
            return ", --help - справка";
        }
    }
}
