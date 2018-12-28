package com.elster.booktracker.utils.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class BaseResource {

    public <T> Response successResponse(T payload) {
        return Response.ok(payload).build();
    }

    public <T> Response optionalResponse(Optional<T> payload){
        if(payload.isPresent()){
            return this.successResponse(payload.get());
        }
        return notFoundResponse();
    }

    public Response notFoundResponse() {
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    public <T> Response badRequestResponse(T error) {

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
