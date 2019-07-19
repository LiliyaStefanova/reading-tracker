package org.elstere.booktrkr.model;

import java.util.UUID;

public class Reading {

    private  UUID id;

    private String title;

    private ReadingType type;

    private Medium medium;

    //TODO enum or db table
    private String language;

    //TODO make this a DB entity
    private String publisher;

    private String edition;



    public Reading(){

    }

    public Reading(String title, ReadingType type, Medium medium, String language, String publisher, String edition){
        this.title = title;
        this.type = type;
        this.medium = medium;
        this.language = language;
        this.publisher = publisher;
        this.edition = edition;
    }

    public UUID getId(){
        return this.id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public ReadingType getType(){
        return type;
    }

    public void setType(ReadingType type){
        this.type = type;
    }

    public Medium getMedium(){
        return medium;
    }

    public void setMedium(Medium medium){
        this.medium = medium;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getEdition(){
        return edition;
    }

    public void setEdition(String edition){
        this.edition = edition;
    }

    public enum Medium {

        LETTERS("letters"),
        AUDIO("audio");

        private String medium;

        Medium(String medium) {
            this.medium = medium;
        }

        static Medium valueFrom(String medium) {
            for (Medium value : values()) {
                if (medium.equals(value.medium)) {
                    return value;
                }
            }
            throw new IllegalArgumentException(
                    "'" + medium + "' is not a supported medium");
        }
    }
}
