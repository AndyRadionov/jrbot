package com.radionov.jrbot.service.messageprocessor;

/**
 * @author Andrey Radionov
 */
public class HelpMessageProcessorImpl implements MessageProcessor {
    @Override
    public String processMessage(String message) {
        return ", -h/--help - помощь\n -t/--time время";
    }
}
