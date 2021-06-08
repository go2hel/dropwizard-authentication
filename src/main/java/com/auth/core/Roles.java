package com.auth.core;

public enum Roles {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    Roles(String role) {
        this.role = role;;
    }

    public String getRole(){
        return this.role;
    }

}
