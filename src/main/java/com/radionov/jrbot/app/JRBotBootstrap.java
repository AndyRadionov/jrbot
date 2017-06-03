package com.radionov.jrbot.app;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by Andrey Radionov
 */
public class JRBotBootstrap {
    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8888).build();
        JRBotApplication config = new JRBotApplication();
        JettyHttpContainerFactory.createServer(baseUri, config);
    }
}
