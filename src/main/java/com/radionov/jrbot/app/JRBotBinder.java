package com.radionov.jrbot.app;

import com.radionov.jrbot.service.MessageService;
import com.radionov.jrbot.service.MessageServiceImpl;
import com.radionov.jrbot.service.messageprocessor.JokeMessageProcessorImpl;
import com.radionov.jrbot.service.messageprocessor.MessageProcessor;
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
