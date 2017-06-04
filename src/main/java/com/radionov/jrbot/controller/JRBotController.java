package com.radionov.jrbot.controller;

import com.google.gson.Gson;
import com.radionov.jrbot.dto.MessageRequestDTO;
import com.radionov.jrbot.dto.MessageResponseDTO;
import com.radionov.jrbot.dto.TokenRequestDTO;
import com.radionov.jrbot.dto.TokenResponseDTO;

import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Andrey Radionov
 */
@Path("/")
public class JRBotController {
    private static final Gson GSON = new Gson();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static final String APP_ID = "e24b7c45-0e41-47cc-b72d-8da0ed575d9d";
    private static final String CLIENT_SECRET = "3UqPrQm1eS4kg1vzkJ5VUMt";
    private static Set<MessageRequestDTO> messages = ConcurrentHashMap.newKeySet();
    private Client client = ClientBuilder.newBuilder().build();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveMessage(String body) {
        System.out.println("body = " + body);
        MessageRequestDTO messageRequestDTO = GSON.fromJson(body, MessageRequestDTO.class);
        messages.add(messageRequestDTO);

        processMessage(messageRequestDTO);
        return Response
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogs() {
        return Response
                .ok()
                .entity(GSON.toJson(messages))
                .build();
    }

    private void processMessage(MessageRequestDTO messageRequestDTO) {
        String lowerMsg = messageRequestDTO.getText().toLowerCase();
        if (lowerMsg.startsWith("jrbot")) {
            String responseMsg = messageRequestDTO.getFrom().getName() + ", ";
            if (lowerMsg.contains("time") || lowerMsg.contains("date") || lowerMsg.contains("время") || lowerMsg.contains("дата")) {
                responseMsg += "сейчас " + DATE_FORMAT.format(new Date());
            } else if (lowerMsg.contains("hi") || lowerMsg.contains("hello") || lowerMsg.contains("привет")) {
                responseMsg += "привет! (wave)";
            } else {
                responseMsg += "Что хотел?";
            }
            System.out.println("+++++++++" + responseMsg);
            MessageResponseDTO responseDTO = new MessageResponseDTO(messageRequestDTO, APP_ID, responseMsg);

            String url = String.format("%s/v3/conversations/%s/activities/",
                    messageRequestDTO.getServiceUrl(), messageRequestDTO.getConversation().getId());

            TokenResponseDTO token = getToken();
            client.target(url)
                    .request()
                    .header("Authorization", "Bearer " + token.getAccessToken())
                    .header("content-type", "application/json; charset=utf8")
                    .buildPost(Entity.entity(GSON.toJson(responseDTO), MediaType.APPLICATION_JSON))
                    .invoke();
        }
    }

    private TokenResponseDTO getToken() {
        TokenRequestDTO tokenRequestDTO = new TokenRequestDTO(APP_ID, CLIENT_SECRET,
                "client_credentials", "https://api.botframework.com/.default");

        return client.target("https://login.microsoftonline.com/common/oauth2/v2.0/token")
                .request(MediaType.APPLICATION_JSON)
                .buildPost(Entity.entity(GSON.toJson(tokenRequestDTO), MediaType.APPLICATION_JSON))
                .invoke(TokenResponseDTO.class);
    }
}
