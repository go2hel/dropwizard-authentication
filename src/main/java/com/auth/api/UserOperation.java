package com.auth.api;

import com.auth.db.UserDao;
import com.auth.resources.PasswordNotSetException;
import com.auth.resources.User;
import com.auth.resources.UserNotFoundException;

public class UserOperation {

    private static volatile UserOperation instance = null;

    private UserOperation(){}

    public static UserOperation getInstance() {
        if(instance==null){
            instance = new UserOperation();
        }
        return instance;
    }

    public void setPassword(String id, String password) throws PasswordNotSetException{
        try {
            UserDao.getInstance().setPassword(id,password);
        } catch (Exception e) {
            throw e;
        }
    }

    public User getDetails(String id) throws UserNotFoundException{
        try {
            User user = UserDao.getInstance().getDetails(id);
            return user;
        }catch (Exception e){
            throw e;
        }
    }
}
