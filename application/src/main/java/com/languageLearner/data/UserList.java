package com.languageLearner.data;

import java.util.ArrayList;

public class UserList {

    private static UserList instance;
    private ArrayList<User> users;

    private UserList() {
        users = new ArrayList<>();
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void clearUsers() {
        users.clear();
    }
}
