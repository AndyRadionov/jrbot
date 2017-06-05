package com.radionov.jrbot.service.messageprocessor;

import com.radionov.jrbot.dto.JokeResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

/**
 * @author Andrey Radionov
 */
public class JokeMessageProcessorImpl implements MessageProcessor {
    private static final String JOKE_URL = "http://rzhunemogu.ru/RandJSON.aspx?CType=1";
    @Inject Client client;

    @Override
    public String processMessage(String message) {
        JokeResponseDTO jokeResponseDTO =
                client.target(JOKE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get(JokeResponseDTO.class);
        return ",\n  " + jokeResponseDTO.getContent();
    }
}
