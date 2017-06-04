package com.radionov.jrbot.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author Andrey Radionov
 */
public class JRBotConfig {
    public static final String APP_ID;
    public static final String CLIENT_SECRET;
    public static final String WEATHER_ID;

    static {
        Config config = ConfigFactory.load();
        APP_ID = config.getString("jrbot.app_id");
        CLIENT_SECRET = config.getString("jrbot.client_secret");
        WEATHER_ID = config.getString("jrbot.weather_id");
    }
}
