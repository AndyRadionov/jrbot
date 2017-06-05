package com.radionov.jrbot.service.messageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrey Radionov
 */
public class HelpMessageProcessorImpl implements MessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpMessageProcessorImpl.class);

    @Override
    public String processMessage(String message) {
        LOGGER.debug("HelpMessageProcessor - processMessage {}", message);
        return ",  \n" +
                "-h/--help - помощь  \n" +
                "-j/--joke - анекдот  \n" +
                "-t/--time - время  \n" +
                "-w/--weather <city> - погода";
    }
}
