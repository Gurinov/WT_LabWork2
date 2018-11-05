package by.bsuir.gurinov.dao;

import by.bsuir.gurinov.Cipher;
import by.bsuir.gurinov.Serialization;
import by.bsuir.gurinov.model.User;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class UserDao implements EntityDao<User> {

    private ArrayList<User> users = new ArrayList<User>();
    private Serialization<User> serialization = new Serialization<>();

    public UserDao() {
        users = serialization.deSerialize(new TypeToken<ArrayList<User>>() {}.getType(), "src/main/java/by/bsuir/gurinov/files/users.txt");
        for (User user: users) {
            user.setPassword(Cipher.decrypt(user.getPassword()));
        }
    }

    /**
     * @return All users
     */
    @Override
    public ArrayList<User> getAll() {
        return users;
    }

    /**
     * @param name user name
     * @return user by name
     */
    public User getByName(String name) {
        for (User user: users) {
            if (user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }

    /**
     * Add new user
     * @param obj user
     */
    @Override
    public void add(User obj) {
        users.add(obj);
        for (User user: users) {
            user.setPassword(Cipher.encrypt(user.getPassword()));
        }
        serialization.serialize(users, "src/main/java/by/bsuir/gurinov/files/users.txt");
    }

    /**
     * Delete user
     * @param obj user
     */
    @Override
    public boolean delete(User obj) {
        if(users.contains(obj)){
            users.remove(obj);
            return true;
        }
        return false;
    }
}
