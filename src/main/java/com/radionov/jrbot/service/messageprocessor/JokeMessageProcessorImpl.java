package com.radionov.jrbot.service.messageprocessor;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * @author Andrey Radionov
 */
public class JokeMessageProcessorImpl implements MessageProcessor {
    private static final String JOKE_URL = "http://rzhunemogu.ru/RandJSON.aspx?CType=1";
    private Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    @Override
    public String processMessage(String message) {
        String jokeResponse =
                client.target(JOKE_URL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        if (jokeResponse == null) return ", кочились анекдоты.";
        return ",\n  " + jokeResponse.substring(12, jokeResponse.length() - 2);
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
