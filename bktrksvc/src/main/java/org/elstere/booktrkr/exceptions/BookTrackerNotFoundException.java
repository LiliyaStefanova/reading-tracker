package org.elstere.booktrkr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookTrackerNotFoundException extends RuntimeException {

    public BookTrackerNotFoundException(String message){
        super(message);
    }
}
