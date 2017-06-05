package com.radionov.jrbot.service;

import com.radionov.jrbot.app.JRBotConfig;
import com.radionov.jrbot.dto.MessageRequestDTO;
import com.radionov.jrbot.dto.MessageResponseDTO;
import com.radionov.jrbot.dto.TokenResponseDTO;
import com.radionov.jrbot.service.messageprocessor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * @author Andrey Radionov
 */
public class MessageServiceImpl implements MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    private static final MessageProcessor JOKE_MESSAGE_PROCESSOR = new JokeMessageProcessorImpl();
    private static final MessageProcessor HELP_MESSAGE_PROCESSOR = new HelpMessageProcessorImpl();
    private static final MessageProcessor TEXT_MESSAGE_PROCESSOR = new TextMessageProcessorImpl();
    private static final MessageProcessor TIME_MESSAGE_PROCESSOR = new TimeMessageProcessorImpl();
    private static final MessageProcessor WEATHER_MESSAGE_PROCESSOR = new WeatherMessageProcessorImpl();
    @Inject private Client client;

    public void processMessage(MessageRequestDTO messageRequestDTO) {
        LOGGER.debug("MessageService processMessage {}", messageRequestDTO);
        String lowerRequestMsg = messageRequestDTO.getText().toLowerCase();

        MessageProcessor messageProcessor = getMessageProcessor(lowerRequestMsg);
        String processedMsg = messageProcessor.processMessage(lowerRequestMsg);
        String responseMsg = messageRequestDTO.getFrom().getName() + processedMsg;

        MessageResponseDTO responseDTO = new MessageResponseDTO(messageRequestDTO, JRBotConfig.APP_ID, responseMsg);

        String url = String.format("%s/v3/conversations/%s/activities/",
                messageRequestDTO.getServiceUrl(), messageRequestDTO.getConversation().getId());
        LOGGER.debug("++++ responeMSG {}", responseDTO);
        LOGGER.debug("=== status {}", client.target(url)
                .request()
                .header("Authorization", "Bearer " + getToken().getAccessToken())
                .header("content-type", "application/json; charset=utf8")
                .post(Entity.entity(responseDTO, MediaType.APPLICATION_JSON))
        .getStatusInfo());
    }

    private MessageProcessor getMessageProcessor(String s) {
        if (s.startsWith("-")) {
            if (s.startsWith("-h") || s.startsWith("--help")) return HELP_MESSAGE_PROCESSOR;
            if (s.startsWith("-t") || s.startsWith("--time")) return TIME_MESSAGE_PROCESSOR;
            if (s.startsWith("-w") || s.startsWith("--weather")) return WEATHER_MESSAGE_PROCESSOR;
        }
        if (s.startsWith("help")) return HELP_MESSAGE_PROCESSOR;
        if (s.contains("joke") || s.contains("анекдот")) return JOKE_MESSAGE_PROCESSOR;
        return TEXT_MESSAGE_PROCESSOR;
    }

    private TokenResponseDTO getToken() {
        Form form = new Form();
        form.param("client_id", JRBotConfig.APP_ID);
        form.param("client_secret", JRBotConfig.CLIENT_SECRET);
        form.param("grant_type", "client_credentials");
        form.param("scope", "https://api.botframework.com/.default");

        return client.target("https://login.microsoftonline.com/botframework.com/oauth2/v2.0/token")
                .request(MediaType.MULTIPART_FORM_DATA)
                .post(Entity.form(form), TokenResponseDTO.class);
    }
}
