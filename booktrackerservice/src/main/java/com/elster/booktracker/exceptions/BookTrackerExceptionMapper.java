package com.elster.booktracker.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BookTrackerExceptionMapper implements ExceptionMapper<BookTrackerException> {

    private final static Logger LOG = LoggerFactory.getLogger(BookTrackerExceptionMapper.class);
    @Override
    public Response toResponse(BookTrackerException e) {
        LOG.debug("Error executing ", e);
        return Response.status(e.getCode())
                .entity(e.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
