package com.auth.resources;

public class PasswordNotSetException extends Exception{
    private String id;

    public PasswordNotSetException(String id){
        this.id = id;
    }

    public String getMessage(){
        return "Password not updated for username: " + id;
    }
}
