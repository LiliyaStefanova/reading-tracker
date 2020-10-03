package org.elstere.reading.tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BookTrackerServiceException extends RuntimeException {

    public BookTrackerServiceException(String message){
        super(message);
    }


}
