package com.auth.db;

import com.auth.core.SQLQueries;
import com.auth.resources.PasswordNotSetException;
import com.auth.resources.User;
import com.auth.resources.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static volatile UserDao instance = null;

    private UserDao(){}

    public static UserDao getInstance() {
        if(instance==null){
            synchronized (UserDao.class){
                instance = new UserDao();
            }
        }
        return instance;
    }

    public User getDetails(String id) throws UserNotFoundException {
        Connection connection = DBUtil.getConnection();
        User user = new User(id);
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_DETAILS);

            statement.setString(1,id);

            ResultSet set = statement.executeQuery();

            while (set.next()){
                user = new User(set.getString("id"),set.getString("name"),set.getString("password"),
                        set.getString("role"));
            }

        }catch (Exception e){
            throw new UserNotFoundException(id);
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
        return user;
    }

    public void setPassword(String id, String password) throws PasswordNotSetException{
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.SET_PASSWORD);

            statement.setString(1,password);
            statement.setString(2,id);

            statement.executeUpdate();

        }catch (Exception e){
            throw new PasswordNotSetException(id);
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
    }
}
