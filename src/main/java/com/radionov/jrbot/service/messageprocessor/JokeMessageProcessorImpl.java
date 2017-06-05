package com.radionov.jrbot.service.messageprocessor;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author Andrey Radionov
 */
public class JokeMessageProcessorImpl implements MessageProcessor {
    private static final String JOKE_URL = "http://rzhunemogu.ru/RandJSON.aspx?CType=1";
    @Inject Client client;

    @Override
    public String processMessage(String message) {
        String jokeResponse =
                client.target(JOKE_URL)
                .request("application/json; charset=utf8")
                .get(String.class);
        return ",\n  " + jokeResponse.substring(12, jokeResponse.length() - 2);
    }
}
