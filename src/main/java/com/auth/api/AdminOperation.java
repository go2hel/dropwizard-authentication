package com.auth.api;

import com.auth.db.AdminDao;
import com.auth.resources.User;
import com.auth.resources.UserNotAddedException;
import com.auth.resources.UserNotDeletedException;

import java.util.List;

public class AdminOperation {

    private static volatile AdminOperation instance = null;

    private AdminOperation(){}

    public static AdminOperation getInstance() {
        if(instance==null){
            synchronized (AdminOperation.class){
                instance = new AdminOperation();
            }
        }
        return instance;
    }

    public void addUser(User user) throws UserNotAddedException{
        try {
            AdminDao.getInstance().addUser(user);
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteUser(String id) throws UserNotDeletedException{
        try {
            AdminDao.getInstance().deleteUser(id);
        }catch (Exception e){
            throw e;
        }
    }

    public List<User> getAll(){
        return AdminDao.getInstance().getAll();
    }
}
