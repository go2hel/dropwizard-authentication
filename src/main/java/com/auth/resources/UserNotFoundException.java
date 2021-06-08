package com.auth.resources;

public class UserNotFoundException extends Exception{

    private String id;

    public UserNotFoundException(String id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "user with id " + id + " not found";
    }
}
