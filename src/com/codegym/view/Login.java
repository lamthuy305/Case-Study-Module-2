package com.codegym.view;

import com.codegym.controller.UserManagement;

import java.util.Scanner;


public class Login{
    public Scanner scanner = new Scanner(System.in);
    UserManagement userManagement = UserManagement.getInstance();
    private ManagementMenu managementMenu = new ManagementMenu();


    public void run() {
        System.out.println("---Ứng dụng quản lý nhân viên---");
        doLogin();
    }


    private void doLogin() {
        boolean isLogin = false;
        do {
            System.out.println("---Đăng nhập---");
            System.out.println("Nhập username:");
            String username = scanner.nextLine();
            System.out.println("Nhập password:");
            String password = scanner.nextLine();
            isLogin = userManagement.checkUserLogin(username, password);
            if (isLogin) {
                if (username.equals("admin")) {
                    String name = "Admin";
                    managementMenu.run(name, username);
                } else {
                    int index = userManagement.findUserByUserName(username);
                    String name = userManagement.findName(index);
                    managementMenu.run(name, username);
                }
            } else {
                System.err.println("Username hoặc password không đúng!\n");
            }
        }while (!isLogin);
    }
}
