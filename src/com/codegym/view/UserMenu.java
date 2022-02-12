package com.codegym.view;

import com.codegym.controller.UserManagement;
import com.codegym.model.User;

import java.util.Scanner;

public class UserMenu {
    Scanner scanner = new Scanner(System.in);
    UserManagement userManagement = UserManagement.getInstance();

    public void run() {
        int choiceUserMenu = -1;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choiceUserMenu = scanner.nextInt();
            scanner.nextLine();
            switch (choiceUserMenu) {
                case 1: {
                    System.out.println("Hiển thị danh sách User đã có");
                    userManagement.displayUserAll();
                    break;
                }
                case 2: {
                    doRegister();
                    break;
                }
                case 3: {
                    int choicePassword = -1;
                    do {
                        System.out.println("Đổi password");
                        System.out.println("1. Đổi password tài khoản admin");
                        System.out.println("2. Đổi password theo username");
                        System.out.println("0. Quay lại");
                        System.out.println("Nhập lựa chọn của bạn");
                        choicePassword = scanner.nextInt();
                        scanner.nextLine();
                        switch (choicePassword) {
                            case 1: {
                                String newPassword;
                                do {
                                    System.out.println("Nhập password mới cho tài khoản admin");
                                    newPassword = scanner.nextLine();
                                    if (userManagement.checkPasswordAdmin(newPassword)){
                                        System.err.println("Password mới không được trùng với password cũ \n");
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }while (userManagement.checkPasswordAdmin(newPassword));
                                userManagement.updateUserAdmin(newPassword);
                                System.out.println("Đổi password tài khoản Admin thành công");
                                break;
                            }
                            case 2: {
                                System.out.println("Nhập tên username cần đổi password");
                                String username = scanner.nextLine();
                                updatePassword(userManagement, username);
                                break;
                            }
                            case 0: {
                                System.err.println("Quay lại\n");
                                break;
                            }
                            default: {
                                System.err.println("Nhập sai, mời nhập lại\n");
                            }
                        }
                    } while (choicePassword != 0);
                    break;
                }
                case 4: {
                    removeUser(userManagement);
                    break;
                }
                case 5: {
                    searchUser(userManagement);
                    break;
                }
                case 0: {
                    System.err.println("Quay lại !!!\n");
                    break;
                }
                default: {
                    System.err.println("Nhập sai, mời nhập lại\n");
                }
            }

        } while (choiceUserMenu != 0);

    }


    private void menu() {
        System.out.println("1. Hiển thị danh sách User đã có");
        System.out.println("2. Thêm User mới");
        System.out.println("3. Cập nhật Password");
        System.out.println("4. Xóa User");
        System.out.println("5. Tìm kiếm User");
        System.out.println("0. Quay lại");
    }


    private void doRegister() {
        System.out.println("Đăng ký tài khoản mới!");
        User user = createUser();
        userManagement.addNewUser(user);
    }


    private User createUser() {
        System.out.println("Nhập họ tên");
        String name = scanner.nextLine();
        String username = inputUsername();
        String password = inputPassword();
        User user = new User(name, username, password);
        return user;
    }


    private String inputPassword() {
        String password;
        do {
            System.out.println("Nhập mật khẩu (6-12 ký tự, bao gồm ít nhất 1 chữ cái, ít nhất 1 số):");
            password = scanner.nextLine();
            if (!userManagement.isPassword(password)) {
                System.err.println("Mật khẩu phải từ 6-12 ký tự, bao gồm ít nhất 1 chữ cái, ít nhất 1 số");
            }
        } while (!userManagement.isPassword(password));
        return password;
    }


    private String inputUsername() {
        String username;
        do {
            System.out.println("Nhập tên tài khoản (6-12 ký tự):");
            username = scanner.nextLine();
            if (username.length() < 6) {
                System.err.println("Tài khoản phải có ít nhất 6 ký tự!");
            } else if (username.length() > 12) {
                System.err.println("Tài khoản chỉ được phép tối đa 12 ký tự!");
            } else if (userManagement.checkUsernameExist(username)) {
                System.err.println("Tài khoản này đã được đăng ký!");
            }
        } while (username.length() < 6 || username.length() > 12 || userManagement.checkUsernameExist(username));
        return username;
    }


    public void updatePassword(UserManagement userManagement, String username) {
        System.out.println("User --- " + userManagement.getUserByUserName(username));
        int index = userManagement.findUserByUserName(username);
        if (index != -1) {
            String name = userManagement.findName(index);
            String passwordOld = userManagement.findPassword(index);
            String password;
            do {
                do {
                    System.out.println("Nhập password mới(6-12 ký tự, bao gồm ít nhất 1 chữ cái, ít nhất 1 số): ");
                    password = scanner.nextLine();
                    if (password.equals(passwordOld)) {
                        System.err.println("Mật khẩu mới không được giống mật khẩu cũ \n");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } while (password.equals(passwordOld));
                if (!userManagement.isPassword(password)) {
                    System.err.println("Mật khẩu phải từ 6-12 ký tự, bao gồm ít nhất 1 chữ cái, ít nhất 1 số");
                }
            } while (!userManagement.isPassword(password));
            User user = new User(name, username, password);
            userManagement.updateUserByUserName(username, user);
            System.out.println("Đổi thành công!");
        } else {
            System.err.println("Cập nhật bị lỗi do không tồn tại Username cần tìm!");
        }
    }


    private void removeUser(UserManagement userManagement) {
        System.out.println("Xóa User");
        System.out.println("Nhập username cần xóa");
        String username = scanner.nextLine();
        boolean isDeleted = userManagement.deleteUserByUserName(username);
        if (isDeleted) {
            System.out.println("Xóa thành công!");
        } else {
            System.err.println("Xóa lỗi do User không tồn tại!");
        }
    }


    private void searchUser(UserManagement userManagement) {
        System.out.println("Tìm kiếm User");
        System.out.println("Nhập username cần tìm");
        String username = scanner.nextLine();
        int index = userManagement.findUserByUserName(username);
        if (index != -1) {
            System.out.println("User cần tìm: " + userManagement.getUserByUserName(username));
        } else {
            System.err.println("Không tìm thấy do User không tồn tại");
        }
    }
}
