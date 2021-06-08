package com.auth.db;

import com.auth.core.SQLQueries;
import com.auth.resources.User;
import com.auth.resources.UserNotAddedException;
import com.auth.resources.UserNotDeletedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static volatile AdminDao instance = null;

    private AdminDao(){}

    public static AdminDao getInstance() {
        if(instance==null){
            synchronized (AdminDao.class){
                instance = new AdminDao();
            }
        }
        return instance;
    }

    public void addUser(User user) throws UserNotAddedException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.ADD_USER);

            statement.setString(1,user.getId());
            statement.setString(2,user.getName());
            statement.setString(3,user.getPassword());
            statement.setString(4,user.getRole());

            statement.executeUpdate();

        }catch (Exception e){
            throw new UserNotAddedException(user.getId());
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
    }

    public void deleteUser(String id) throws UserNotDeletedException{
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.DELETE_USER);

            statement.setString(1,id);

            statement.executeUpdate();
        }catch (Exception e){
            throw new UserNotDeletedException(id);
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
    }

    public List<User> getAll(){
        Connection connection = DBUtil.getConnection();
        List<User> users = new ArrayList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.GET_ALL);

            ResultSet set = statement.executeQuery();

            while (set.next()){
                users.add( new User(set.getString("id"),set.getString("name"),set.getString("password"),
                           set.getString("role")));
            }

            return users;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
        return users;
    }
}
