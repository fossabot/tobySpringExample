package com.example.tobyExample;

public class DaoFactory {

    public UserDao userDao() {

        UserDao userDao =
                new UserDao(new SimpleConnectionMaker());

        return userDao;

    }
}
