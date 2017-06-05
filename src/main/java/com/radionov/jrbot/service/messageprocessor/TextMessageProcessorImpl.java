package com.radionov.jrbot.service.messageprocessor;

/**
 * @author Andrey Radionov
 */
public class TextMessageProcessorImpl implements MessageProcessor {
    @Override
    public String processMessage(String message) {
        if (message.contains("hi") || message.contains("hello") || message.contains("привет")) {
            return ", привет! (wave)";
        } else {
            return ", help - справка";
        }
    }
}
