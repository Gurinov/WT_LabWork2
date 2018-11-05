package by.bsuir.gurinov.service;

import by.bsuir.gurinov.dao.UserDao;
import by.bsuir.gurinov.model.User;

import java.util.ArrayList;

public class UserService implements EntityService<User>{

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    /**
     * @return All users
     */
    @Override
    public ArrayList<User> getAll() {
        return userDao.getAll();
    }

    /**
     * @param name user name
     * @return user by name
     */
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    /**
     * Add new user
     * @param obj user
     */
    @Override
    public boolean add(User obj) {
        if((obj != null) && (!userDao.getAll().contains(obj))){
            userDao.add(obj);
            return true;
        }
        return false;
    }
    /**
     * Delete user
     */
    public boolean delete(String name) {
        userDao.delete(userDao.getByName(name));
        return true;
    }

    public boolean isValidUser(String username, String password) {
        if (userDao.getByName(username) == null){
            return false;
        }
        return userDao.getByName(username).getPassword().equals(password);
    }
}
