package com.codegym.view;

import com.codegym.controller.DiligenceManagement;
import com.codegym.controller.StaffManagement;

import java.util.Scanner;

public class SalaryMenu {
    public static final int SALARY_FULLTIME = 12000000;
    public static final int SALARY_PARTTIME = 5500000;
    public static final int DAYOFF_FULLTIME = 200000;
    public static final int DAYOFF_PARTTIME = 100000;
    public static final int LATETIME_PARTTIME = 25000;
    public static final int LATETIME_FULLTIME = 25000;

    public static int salary = 0;
    DiligenceManagement diligenceManagement = DiligenceManagement.getInstance();
    StaffManagement staffManagement = StaffManagement.getInstance();
    Scanner scanner = new Scanner(System.in);

    public void run() {

        int choice = -1;
        do {
            menu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Tra cứu tiền lương theo ID");
                    System.out.println("Nhập ID");
                    String id = scanner.nextLine();
                    int index = staffManagement.findById(id);
                    showSalaryStaff(index);

                    break;
                }
                case 2: {
                    for (int i = 0; i < staffManagement.size(); i++) {
                        showSalaryStaff(i);
                    }
                    break;
                }
            }
        } while (choice != 0);


    }

    private void showSalaryStaff(int index) {
        int dayOff = diligenceManagement.showDayOff(index);
        int lateTime = diligenceManagement.showLateTime(index);
        if (staffManagement.isFullTime(index) && staffManagement.isOn(index)) {
            salary = SALARY_FULLTIME - dayOff * DAYOFF_FULLTIME - lateTime * LATETIME_FULLTIME;
            System.out.println(staffManagement.displayStaff(index) + ", lương " + salary);
        } else if (staffManagement.isFullTime(index) == false && staffManagement.isOn(index) == true) {
            salary = SALARY_PARTTIME - dayOff * DAYOFF_PARTTIME - lateTime * LATETIME_PARTTIME;
            System.out.println(staffManagement.displayStaff(index) + ", lương " + salary);
        } if (staffManagement.isOn(index) == false) {
            System.out.println(staffManagement.displayStaff(index));
        }

    }


    private void menu() {
        System.out.println("---Quản lý tiền lương---");
        System.out.println("1. Tra cứu tiền lương theo ID");
        System.out.println("2. Hiển thị toàn bộ lương nhân viên");
        System.out.println("0. Thoát");
        System.out.println("Nhập lựa chọn của bạn");
    }
}
