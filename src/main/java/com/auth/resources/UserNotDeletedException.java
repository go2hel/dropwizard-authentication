package com.auth.resources;

public class UserNotDeletedException extends Exception{

    private String id;

    public UserNotDeletedException(String id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "user with id " + id + " not deleted";
    }
}
