package com.auth.resources;

public class UserNotAddedException extends Exception{

    private String id;

    public UserNotAddedException(String id){
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "user with id " + id + " not added";
    }
}
