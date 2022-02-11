package com.codegym.controller;

import com.codegym.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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


    public String findName(int index) {
        return users.get(index).getName();
    }

    public String findPassword(int index){
        return users.get(index).getPassword();
    }

    public int findUserByUserName(String username) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void addNewUser(User user) {
        this.users.add(user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean checkUsernameExist(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) return true;
        }
        return false;
    }

    public boolean checkUserLogin(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) return true;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())) return true;
        }
        return false;
    }


    public void displayUserAll() {
        for (User user : users) {
            System.out.println(user);
        }
    }


    public void updateUserByUserName(String username, User user) {
        int index = findUserByUserName(username);
        users.set(index, user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean deleteUserByUserName(String username) {
        int index = findUserByUserName(username);
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


    public User getUserByUserName(String username) {
        int index = findUserByUserName(username);
        return users.get(index);
    }

    public boolean isPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$";
        if (Pattern.matches(regex, password)) return true;
        return false;
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
}
