package com.codegym.view;

import com.codegym.controller.UserManagement;
import com.codegym.model.User;

import java.util.Scanner;

public class UserMenu {
    Scanner scanner = new Scanner(System.in);
    UserManagement userManagement = UserManagement.getInstance();
    public void run(String username) {
        int choice = -1;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.println("User đăng đăng nhập là ");
                    System.out.println(username);
                    break;
                }
                case 2: {
                    System.out.println("Hiển thị danh sách User đã có");
                    userManagement.displayAll();
                    break;
                }
                case 3: {
                    doRegister();
                    break;
                }
                case 4: {
                    updatepassword(userManagement);
                    break;
                }
                case 5: {
                    removeUser(userManagement);
                    break;
                }
                case 6: {
                    searchUser(userManagement);
                    break;
                }

            }

        } while (choice != 0);

    }

    private void menu() {
        System.out.println("1. Hiển thị User đang đăng nhập");
        System.out.println("2. Hiển thị danh sách User đã có");
        System.out.println("3. Thêm User mới");
        System.out.println("4. Cập nhật password");
        System.out.println("5. Xóa User");
        System.out.println("6. Tìm kiếm User");
        System.out.println("0. Quay lại");
    }

    private void doRegister() {
        System.out.println("Đăng ký tài khoản mới!");
        User user = createUser();
        userManagement.register(user);
    }

    private User createUser() {
        String username = inputUsername();
        String password = inputPassword();
        User user = new User(username, password);
        return user;
    }

    private String inputPassword() {
        String password;
        do {
            System.out.println("Nhập mật khẩu (6-12 ký tự):");
            password = scanner.nextLine();
            if (password.length() < 6) {
                System.err.println("Mật khẩu phải có ít nhất 6 ký tự!");
            } else if (password.length() > 12) {
                System.err.println("Mật khẩu chỉ được phép tối đa 12 ký tự!");
            }
        } while (password.length() < 6 || password.length() > 12);
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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (username.length() < 6 || username.length() > 12 || userManagement.checkUsernameExist(username));
        return username;
    }

    private void updatepassword(UserManagement userManagement) {
        System.out.println("Đổi password");
        System.out.println("Nhập tên username cần đổi password");
        String username = scanner.nextLine();
        int index = userManagement.findusername(username);
        if (index != -1) {
            System.out.println("Nhập password mới");
            String password = scanner.nextLine();
            User user = new User(username, password);
            userManagement.updateByName(username, user);
            System.out.println("Đổi thành công!");
        } else {
            System.out.println("Cập nhật bị lỗi do không tồn tại Username cần tìm!");
        }
    }

    private void removeUser(UserManagement userManagement) {
        System.out.println("Xóa User");
        System.out.println("Nhập username cần xóa");
        String username = scanner.nextLine();
        boolean isDeleted = userManagement.deleteByName(username);
        if (isDeleted) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa lỗi do tên nhân viên không tồn tại!");
        }
    }

    private void searchUser(UserManagement userManagement) {
        System.out.println("Tìm kiếm User");
        System.out.println("Nhập username cần tìm");
        String username = scanner.nextLine();
        int index = userManagement.findusername(username);
        if (index != -1) {
            System.out.println("User cần tìm: " + userManagement.getByName(username));
        } else {
            System.out.println("Không tìm thấy");
        }
    }
}
