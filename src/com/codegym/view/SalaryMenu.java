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
    public static final int BONUSMONEY_FULLTIME = 1000000;
    public static final int BONUSMONEY_PARTTIME = 500000;
    public static int salary = 0;
    DiligenceManagement diligenceManagement = DiligenceManagement.getInstance();
    StaffManagement staffManagement = StaffManagement.getInstance();
    Scanner scanner = new Scanner(System.in);

    public void run() {

        int choiceSalaryMenu = -1;
        do {
            menu();
            choiceSalaryMenu = scanner.nextInt();
            scanner.nextLine();
            switch (choiceSalaryMenu) {
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
                default:{
                    System.out.println("Nhập sai, mời nhập lại");
                }
            }
        } while (choiceSalaryMenu != 0);
    }


    private void showSalaryStaff(int index) {
        salary = 0;
        int dayOff = diligenceManagement.showDayOff(index);
        int lateTime = diligenceManagement.showLateTime(index);
        if (staffManagement.isFullTime(index) && staffManagement.isOn(index)) {
            setSalaryFulltime(dayOff, lateTime, index);
        } else if (staffManagement.isFullTime(index) == false && staffManagement.isOn(index) == true) {
            setSalaryParttime(dayOff, lateTime, index);
        }
        if (staffManagement.isOn(index) == false) {
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

    public void setSalaryParttime(int dayOff, int lateTime, int index) {
        if (dayOff < 2 && lateTime < 3) {
            salary += BONUSMONEY_PARTTIME;   //nghỉ dưới 2 ngày và đi muộn dưới 3 lần thì đc thưởng chuyên cần
        }
        if (dayOff > 0) {
            dayOff--;           //Mỗi tháng đc nghỉ 1 ngày
        }
        salary += SALARY_PARTTIME - dayOff * DAYOFF_PARTTIME - lateTime * LATETIME_PARTTIME;
        System.out.println(staffManagement.displayStaff(index) + ", lương " + salary);
    }

    public void setSalaryFulltime(int dayOff, int lateTime, int index) {
        if (dayOff < 2 && lateTime < 3) {
            salary += BONUSMONEY_FULLTIME;
        }
        if (dayOff > 0) {
            dayOff--;
        }
        salary += SALARY_FULLTIME - dayOff * DAYOFF_FULLTIME - lateTime * LATETIME_FULLTIME;
        System.out.println(staffManagement.displayStaff(index) + ", lương " + salary);
    }
}
