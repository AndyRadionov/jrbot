package com.radionov.jrbot.endpoint;

import com.radionov.jrbot.dto.MessageRequestDTO;
import com.radionov.jrbot.dto.UserData;
import com.radionov.jrbot.service.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Andrey Radionov
 */
@Path("/")
public class JRBotEndpoint {
    private MessageService messageService;

    @Inject
    public JRBotEndpoint(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveMessage(MessageRequestDTO messageRequestDTO) {
        System.out.println("====Inside receiveMsg");
        messageService.processMessage(messageRequestDTO);
        return Response.ok().build();
    }


    @GET
    @Path("/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogs() {
        return Response.ok().entity(new UserData()).build();
    }
}
