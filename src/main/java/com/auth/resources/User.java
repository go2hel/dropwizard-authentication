package com.auth.resources;

import com.auth.core.Roles;

import java.security.Principal;

public class User implements Principal {
    private String name;
    private Roles role;
    private String id;
    private String password;

    public User(){}

    public User(String name) {
        this.name = name;
        this.role = Roles.USER;
    }

    public User(String id, String name, String password, String role) {
        this.name = name;
        this.role = Roles.valueOf(role);
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role.getRole();
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
