package com.elster.booktracker.utils.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class BaseResource {

    public <T> Response successResponse(T payload) {
        //FIXME the CORS request policy is too permissive
        return
                Response.ok(payload)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public <T> Response optionalResponse(Optional<T> payload){
        if(payload.isPresent()){
            return this.successResponse(payload.get());
        }
        return notFoundResponse();
    }

    public Response notFoundResponse() {
        //FIXME the CORS request policy is too permissive
        return Response
                .status(Response.Status.NOT_FOUND)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public <T> Response badRequestResponse(T error) {
        //FIXME the CORS request policy is too permissive
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .header("Access-Control-Allow-Origin", "*")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
