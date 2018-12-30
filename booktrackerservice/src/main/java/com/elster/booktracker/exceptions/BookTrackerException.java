package com.elster.booktracker.exceptions;

public class BookTrackerException extends Throwable {

    private int code;

    public BookTrackerException(){
        this(500);
    }

    public BookTrackerException(int code){
        this(code, "Error processing request", null);
    }

    public BookTrackerException(int code, String message){
        this(code, message, null);
    }

    public BookTrackerException(int code, String message, Throwable throwable){

        super(message, throwable);
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }
}
