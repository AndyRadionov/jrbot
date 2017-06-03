package com.radionov.jrbot.controller;

import com.google.gson.Gson;

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
    private static Set<String> messages = ConcurrentHashMap.newKeySet();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveMessage(String body) {
        messages.add(body);
        return Response
                .status(Response.Status.OK)
                .entity("{}")
                .build();
    }

    @GET
    @Path("/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogs(@PathParam("id") int id) {
        return Response
                .ok()
                .entity(gson.toJson(messages))
                .build();
    }
}
