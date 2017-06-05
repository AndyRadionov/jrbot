package com.radionov.jrbot.app;

import com.radionov.jrbot.service.MessageService;
import com.radionov.jrbot.service.MessageServiceImpl;
import com.radionov.jrbot.service.messageprocessor.*;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author Andrey Radionov
 */
public class JRBotBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(JerseyClientFactory.class).to(Client.class).in(Singleton.class);

        bind(HelpMessageProcessorImpl.class).to(MessageProcessor.class)
                .named("helpMessageProcessor").in(Singleton.class);
        bind(JokeMessageProcessorImpl.class).to(MessageProcessor.class)
                .named("jokeMessageProcessor").in(Singleton.class);
        bind(TextMessageProcessorImpl.class).to(MessageProcessor.class)
                .named("textMessageProcessor").in(Singleton.class);
        bind(TimeMessageProcessorImpl.class).to(MessageProcessor.class)
                .named("timeMessageProcessor").in(Singleton.class);
        bind(WeatherMessageProcessorImpl.class).to(MessageProcessor.class)
                .named("weatherMessageProcessor").in(Singleton.class);

        bind(MessageServiceImpl.class).to(MessageService.class).in(Singleton.class);
    }

    private static class JerseyClientFactory implements Factory<Client> {
        @Override
        public Client provide() {
            return ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        }

        @Override
        public void dispose(Client client) {
        }
    }
}
