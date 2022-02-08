package com.codegym.view;

import java.util.Scanner;

public class ManagementMenu {
    public static Scanner scanner = new Scanner(System.in);

    public void run(String username) {
        UserMenu userMenu = new UserMenu();
        StaffMenu staffMenu = new StaffMenu();

        int choice = -1;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    userMenu.run(username);
                    break;
                }
                case 2: {
                    staffMenu.run();
                    break;
                }
            }
        } while (choice != 0);
    }

    private void menu() {
        System.out.println("1. Quản lý tài khoản đăng nhập");
        System.out.println("2. Quản lý nhân viên");
        System.out.println("0. Đăng xuất");
    }

}
