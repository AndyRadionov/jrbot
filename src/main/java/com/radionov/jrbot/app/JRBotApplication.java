package com.radionov.jrbot.app;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Andrey Radionov
 */
public class JRBotApplication extends ResourceConfig {

    public JRBotApplication() {
        packages("com.radionov.jrbot");
    }
}
