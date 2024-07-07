package service;

import dao.UserDao;
import entity.User;

public class UserService {
    public boolean register(String username,String password){
        User user = new User(username,password);
        UserDao userDao = new UserDao();
        boolean u = userDao.select(username);
        if(u){
            userDao.addUser(user);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean login(String username,String password){
        UserDao userDao = new UserDao();
        boolean flag = userDao.select(username,password);
        return flag;
    }
}
