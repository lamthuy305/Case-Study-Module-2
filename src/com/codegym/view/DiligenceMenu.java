package com.codegym.view;

import com.codegym.controller.DiligenceManagement;

import java.util.Scanner;

public class DiligenceMenu {
    Scanner scanner = new Scanner(System.in);
    DiligenceManagement diligenceManagement = DiligenceManagement.getInstance();

    public void run() {

        System.out.println("---Diligence Management---");
        System.out.println("Nhập ID");
        String id = scanner.nextLine();
        int index = diligenceManagement.findById(id);
        diligenceManagement.showDeligence(index);

        int choice = -1;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    diligenceManagement.addDayOff(index);
                    break;
                }
            }
        } while (choice != 0);


    }


    private void menu() {

        System.out.println("1. Thêm lần nghỉ  ");
        System.out.println("2. Xóa lần nghỉ");
        System.out.println("3. Thêm lần đi muộn  ");
        System.out.println("4. Xóa lần đi muộn");
        System.out.println("0. Thoát");
    }

}
