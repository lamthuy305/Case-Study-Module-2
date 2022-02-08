//package com.codegym.view;
//
//import com.codegym.controller.UserManagement;
//import com.codegym.model.User;
//
//import java.util.Scanner;
//
//public class ClassLogin {
//    public Scanner scanner = new Scanner(System.in);
//    private UserManagement userManagement = new UserManagement();
//    private ManagementMenu managementMenu = new ManagementMenu();
//
//    public void run() {
//        int choice = -1;
//        do {
//            System.out.println("---Ứng dụng quản lý nhân viên---");
//            menu();
//            System.out.println("Nhập lựa chọn của bạn");
//            choice = scanner.nextInt();
//            scanner.nextLine();
//            switch (choice) {
//                case 1: {
//                    doLogin();
//                    break;
//                }
//                case 2: {
//                    doRegister();
//                    break;
//                }
//            }
//        } while (choice != 0);
//    }
//
//    private void doLogin() {
//        System.out.println("Nhập username:");
//        String username = scanner.nextLine();
//        System.out.println("Nhập password:");
//        String password = scanner.nextLine();
//        boolean isLogin = userManagement.checkUserLogin(username, password);
//        if (isLogin) {
//            System.out.println("Đăng nhập thành công!");
//            managementMenu.run();
//        } else {
//            System.err.println("Username hoặc password không đúng!");
//        }
//    }
//
//    private void doRegister() {
//        System.out.println("Đăng ký tài khoản mới!");
//        User user = createUser();
//        userManagement.register(user);
//    }
//
//    private User createUser() {
//        String username = inputUsername();
//        String password = inputPassword();
//        User user = new User(username, password);
//        return user;
//    }
//
//    private String inputPassword() {
//        String password;
//        do {
//            System.out.println("Nhập mật khẩu (6-12 ký tự):");
//            password = scanner.nextLine();
//            if (password.length() < 6) {
//                System.err.println("Mật khẩu phải có ít nhất 6 ký tự!");
//            } else if (password.length() > 12) {
//                System.err.println("Mật khẩu chỉ được phép tối đa 12 ký tự!");
//            }
//        } while (password.length() < 6 || password.length() > 12);
//        return password;
//    }
//
//    private String inputUsername() {
//        String username;
//        do {
//            System.out.println("Nhập tên tài khoản (6-12 ký tự):");
//            username = scanner.nextLine();
//            if (username.length() < 6) {
//                System.err.println("Tài khoản phải có ít nhất 6 ký tự!");
//            } else if (username.length() > 12) {
//                System.err.println("Tài khoản chỉ được phép tối đa 12 ký tự!");
//            } else if (userManagement.checkUsernameExist(username)) {
//                System.err.println("Tài khoản này đã được đăng ký!");
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } while (username.length() < 6 || username.length() > 12 || userManagement.checkUsernameExist(username));
//        return username;
//    }
//
//    private void menu() {
//        System.out.println("1. Đăng nhập");
//        System.out.println("2. Đăng ký");
//        System.out.println("0. Thoát");
//    }
//}
