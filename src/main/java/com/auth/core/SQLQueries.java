package com.auth.core;

public class SQLQueries {

    public static final String SET_PASSWORD = "update users set password=? where id=?";
    public static final String GET_DETAILS = "select * from users where id=?";
    public static final String ADD_USER = "insert into users (id,name,password,role) values (?,?,?,?)";
    public static final String DELETE_USER = "delete from users where id=?";
    public static final String GET_ALL = "select * from users";
}
