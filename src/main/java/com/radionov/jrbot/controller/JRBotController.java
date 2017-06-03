package com.radionov.jrbot.controller;

import com.google.gson.Gson;
import com.radionov.jrbot.dto.RequestMessageDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Andrey Radionov
 */
@Path("/")
public class JRBotController {
    private static Gson gson = new Gson();
    private static Set<RequestMessageDTO> messages = ConcurrentHashMap.newKeySet();
    private static final String APP_ID = "e24b7c45-0e41-47cc-b72d-8da0ed575d9d";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveMessage(String body) {
        RequestMessageDTO requestDTO = gson.fromJson(body, RequestMessageDTO.class);
        messages.add(requestDTO);
        return Response
                .status(Response.Status.OK)
                .entity("{}")
                .build();
    }

    @GET
    @Path("/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogs() {
        return Response
                .ok()
                .entity(gson.toJson(messages))
                .build();
    }
}
