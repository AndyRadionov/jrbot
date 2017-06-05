package com.radionov.jrbot.app;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by Andrey Radionov
 */
public class JRBotMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(JRBotMain.class);

    public static void main(String[] args) {
        LOGGER.debug("JRBotMain - starting server");
        int port = Integer.parseInt(System.getenv("PORT"));
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(port).build();
        JRBotApplication config = new JRBotApplication();
        JettyHttpContainerFactory.createServer(baseUri, config);
    }
}
