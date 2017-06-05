package com.radionov.jrbot.service;

import com.radionov.jrbot.app.JRBotConfig;
import com.radionov.jrbot.dto.MessageRequestDTO;
import com.radionov.jrbot.dto.MessageResponseDTO;
import com.radionov.jrbot.dto.TokenResponseDTO;
import com.radionov.jrbot.service.messageprocessor.MessageProcessor;
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
    @Inject @Named("jokeMessageProcessor") private MessageProcessor jokeMessageProcessor;
    @Inject @Named("helpMessageProcessor") private MessageProcessor helpMessageProcessor;
    @Inject @Named("textMessageProcessor") private MessageProcessor textMessageProcessor;
    @Inject @Named("timeMessageProcessor") private MessageProcessor timeMessageProcessor;
    @Inject @Named("weatherMessageProcessor") private MessageProcessor weatherMessageProcessor;
    @Inject private Client client;

    public void processMessage(MessageRequestDTO messageRequestDTO) {
        LOGGER.debug("MessageService processMessage {}", messageRequestDTO);
        String lowerRequestMsg = messageRequestDTO.getText().toLowerCase();

        MessageProcessor messageProcessor = getMessageProcessor(lowerRequestMsg);
        String processedMsg = messageProcessor.processMessage(lowerRequestMsg);
        String responseMsg = messageRequestDTO.getFrom().getName() + processedMsg;

        MessageResponseDTO responseDTO = new MessageResponseDTO(messageRequestDTO, JRBotConfig.APP_ID, responseMsg);

        String url = String.format("%sv3/conversations/%s/activities/%s",
                messageRequestDTO.getServiceUrl(),
                messageRequestDTO.getConversation().getId(),
                messageRequestDTO.getId());
        String token = getToken().getAccessToken();

        client.target(url)
                .request()
                .header("Authorization", "Bearer " + token)
                .header("content-type", "application/json; charset=utf8")
                .post(Entity.entity(responseDTO, MediaType.APPLICATION_JSON));
    }

    private MessageProcessor getMessageProcessor(String msg) {
        LOGGER.debug("getMessageProcessor for message {}", msg);
        if (msg.startsWith("-")) {
            if (msg.startsWith("-h") || msg.startsWith("--help")) return helpMessageProcessor;
            if (msg.startsWith("-t") || msg.startsWith("--time")) return timeMessageProcessor;
            if (msg.startsWith("-w") || msg.startsWith("--weather")) return weatherMessageProcessor;
        }
        if (msg.startsWith("help")) return helpMessageProcessor;
        if (msg.contains("joke") || msg.contains("анекдот")) return jokeMessageProcessor;
        return textMessageProcessor;
    }

    private TokenResponseDTO getToken() {
        LOGGER.debug("getToken");
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
