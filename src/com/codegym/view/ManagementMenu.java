package com.codegym.view;

import com.codegym.controller.UserManagement;

import java.util.Scanner;

public class ManagementMenu {
    public static Scanner scanner = new Scanner(System.in);
    UserManagement userManagement = UserManagement.getInstance();
    public void run(String name, String username) {
        UserMenu userMenu = new UserMenu();
        StaffMenu staffMenu = new StaffMenu();
        DiligenceMenu diligenceMenu = new DiligenceMenu();
        SalaryMenu salaryMenu = new SalaryMenu();

        if (username.equals("admin")) {
            System.out.println("Đăng nhập thành công !!!");
            int choiceAdmin = -1;
            do {
                System.out.println("---Xin chào Admin---");
                System.out.println("1. Quản lý tài khoản đăng nhập");
                menu();
                choiceAdmin = scanner.nextInt();
                switch (choiceAdmin) {
                    case 1: {
                        userMenu.run();
                        break;
                    }
                    case 2: {
                        staffMenu.run();
                        break;
                    }
                    case 3: {
                        diligenceMenu.run();
                        break;
                    }
                    case 4: {
                        salaryMenu.run();
                        break;
                    }
                    case 0: {
                        System.err.println("Đã đăng xuất !!!");
                        break;
                    }
                    default: {
                        System.err.println("Nhập sai, mời nhập lại");
                    }
                }
            } while (choiceAdmin != 0);
        } else {
            System.out.println("Đăng nhập thành công !!!");
            int choiceMenu = -1;
            do {
                System.out.println("---Xin chào " + name + "---");
                System.out.println("1. Đổi mật khẩu");
                menu();
                choiceMenu = scanner.nextInt();
                switch (choiceMenu) {
                    case 1: {
                        userMenu.updatePassword(userManagement,username);
                        break;
                    }
                    case 2: {
                        staffMenu.run();
                        break;
                    }
                    case 3: {
                        diligenceMenu.run();
                        break;
                    }
                    case 4: {
                        salaryMenu.run();
                        break;
                    }
                    case 0: {
                        System.err.println("Đăng xuất !!!\n");
                        break;
                    }
                    default: {
                        System.err.println("Nhập sai, mời nhập lại\n");
                    }
                }
            } while (choiceMenu != 0);
        }
    }

    private void menu() {
        System.out.println("2. Quản lý nhân viên");
        System.out.println("3. Quản lý chuyên cần");
        System.out.println("4. Quản lý tiền lương nhân viên");
        System.out.println("0. Đăng xuất");
        System.out.println("Nhập lựa chọn của bạn:");
    }

}
