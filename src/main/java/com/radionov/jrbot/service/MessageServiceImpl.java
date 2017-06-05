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
import java.util.stream.Stream;

/**
 * @author Andrey Radionov
 */
public class MessageServiceImpl implements MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    private static final String SERVICE_URL = "%sv3/conversations/%s/activities/%s";
    private static final String TOKEN_URL = "https://login.microsoftonline.com/botframework.com/oauth2/v2.0/token";
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

        MessageResponseDTO responseDTO = new MessageResponseDTO(messageRequestDTO, responseMsg);

        String url = String.format(SERVICE_URL,
                messageRequestDTO.getServiceUrl(),
                messageRequestDTO.getConversation().getId(),
                messageRequestDTO.getId());

        client.target(url)
                .request()
                .header("Authorization", "Bearer " + getToken().getAccessToken())
                .header("content-type", "application/json; charset=utf8")
                .post(Entity.entity(responseDTO, MediaType.APPLICATION_JSON));
    }

    private MessageProcessor getMessageProcessor(String msg) {
        LOGGER.debug("getMessageProcessor for message {}", msg);
        if (isMsgStartWith(msg, "-h", "--help", "help")) return helpMessageProcessor;
        if (msg.startsWith("-")) {
            if (isMsgStartWith(msg,"-j", "--joke")) return jokeMessageProcessor;
            if (isMsgStartWith(msg,"-t", "--time")) return timeMessageProcessor;
            if (isMsgStartWith(msg,"-w", "--weather")) return weatherMessageProcessor;
        }
        return textMessageProcessor;
    }

    private boolean isMsgStartWith(String msg, String... args) {
        return Stream.of(args).anyMatch(msg::startsWith);
    }

    private TokenResponseDTO getToken() {
        LOGGER.debug("getToken");
        Form form = new Form()
                .param("client_id", JRBotConfig.APP_ID)
                .param("client_secret", JRBotConfig.CLIENT_SECRET)
                .param("grant_type", "client_credentials")
                .param("scope", "https://api.botframework.com/.default");

        return client.target(TOKEN_URL)
                .request(MediaType.MULTIPART_FORM_DATA)
                .post(Entity.form(form), TokenResponseDTO.class);
    }
}
