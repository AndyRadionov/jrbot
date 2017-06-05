package com.radionov.jrbot.service.messageprocessor;

import com.radionov.jrbot.app.JRBotConfig;
import com.radionov.jrbot.dto.WeatherResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

/**
 * @author Andrey Radionov
 */
public class WeatherMessageProcessorImpl implements MessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherMessageProcessorImpl.class);
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/find?q=%s&units=metric&appid=%s";
    @Inject Client client;

    @Override
    public String processMessage(String message) {
        LOGGER.debug("WeatherMessageProcessor - processMessage {}", message);
        String city;
        if (message.startsWith("-w")) city = message.substring(2).trim();
        else if (message.startsWith("--weather")) city = message.substring(9).trim();
        else return "Ошибка в названии города. \n  Пример: --weather London";

        WeatherResponseDTO weatherResponseDTO =
                client.target(String.format(WEATHER_URL, city, JRBotConfig.WEATHER_ID))
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .get(WeatherResponseDTO.class);
        return ", " + weatherResponseDTO;
    }
}
