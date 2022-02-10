package com.codegym.controller;

import com.codegym.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement implements ReadFile, WriteFile {

    private static UserManagement userManagement;

    private List<User> users = new ArrayList<>();

    private UserManagement() {
        File file = new File("user.txt");
        if (file.exists()) {
            try {
                readFile("user.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static UserManagement getInstance() {
        if (userManagement == null) {
            userManagement = new UserManagement();
        }
        return userManagement;
    }

    public String findname(int index) {
        return users.get(index).getName();
    }

    public int findusername(String username) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void register(User user) {
        this.users.add(user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.users = (List<User>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.users);
    }

    public boolean checkUsernameExist(String username) {
        boolean isExisted = false;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }

    public boolean checkUserLogin(String username, String password) {
        boolean isLogin = false;
        if (username.equals("admin") && password.equals("admin")) return true;

        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())) {
                isLogin = true;
                break;
            }
        }
        return isLogin;
    }


    public void displayAll() {
        for (User user : users) {
            System.out.println(user);
        }
    }


    public void updateByName(String name, User user) {
        int index = findusername(name);
        users.set(index, user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean deleteByName(String name) {
        int index = findusername(name);
        if (index != -1) {
            this.users.remove(index);
            try {
                writeFile("user.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }


    public User getByName(String username) {
        int index = findusername(username);
        return users.get(index);
    }
}
