package com.radionov.jrbot.app;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by Andrey Radionov
 */
public class JRBotMain {
    public static void main(String[] args) {
        int port = Integer.parseInt(System.getenv("PORT"));
        System.out.println(port);
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(port).build();
        JRBotApplication config = new JRBotApplication();
        JettyHttpContainerFactory.createServer(baseUri, config);
    }
}
