package com.radionov.jrbot.endpoint;

import com.radionov.jrbot.dto.MessageRequestDTO;
import com.radionov.jrbot.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Andrey Radionov
 */
@Path("/")
public class JRBotEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(JRBotEndpoint.class);
    @Inject private MessageService messageService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveMessage(MessageRequestDTO messageRequestDTO) {
        LOGGER.debug("ENDPOINT message", messageRequestDTO);

        messageService.processMessage(messageRequestDTO);
        return Response.ok().build();
    }
}
