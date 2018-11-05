package by.bsuir.gurinov.controller;

import by.bsuir.gurinov.model.User;
import by.bsuir.gurinov.service.UserService;

public class UserController {

    private UserService userService;
    private User user;

    public User getUser() {
        return user;
    }

    public UserController() {
        userService = new UserService();
    }

    /**
     * Sign in.
     */
    public boolean signIn(String username, String password){
        if(userService.isValidUser(username, password)){
            user = userService.getByName(username);
            return true;
        }
        return false;
    }

    /**
     * Registration new user.
     * @param username
     * @param password
     */
    public boolean signUp(String username, String password){
        if(userService.add(new User(username, password, "user"))){
            user = userService.getByName(username);
            return true;
        }
        return false;
    }

    /**
     * sign out.
     */
    public void signOut(){
        user = null;
    }
}