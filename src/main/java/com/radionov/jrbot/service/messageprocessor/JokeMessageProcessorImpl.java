package com.radionov.jrbot.service.messageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

/**
 * @author Andrey Radionov
 */
public class JokeMessageProcessorImpl implements MessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(JokeMessageProcessorImpl.class);
    private static final String JOKE_URL = "http://rzhunemogu.ru/RandJSON.aspx?CType=1";
    @Inject private Client client;

    @Override
    public String processMessage(String message) {
        LOGGER.debug("JokeMessageProcessor - processMessage {}", message);
        String jokeResponse =
                client.target(JOKE_URL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        if (jokeResponse == null) return ", кочились анекдоты.";
        return ", анекдот:  \n" + jokeResponse.substring(12, jokeResponse.length() - 2);
    }
}
