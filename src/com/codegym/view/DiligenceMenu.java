package com.codegym.view;

import com.codegym.controller.DiligenceManagement;
import com.codegym.controller.StaffManagement;
import com.codegym.model.Diligence;
import com.codegym.model.Staff;

import java.util.Scanner;

public class DiligenceMenu {
    Scanner scanner = new Scanner(System.in);
    DiligenceManagement diligenceManagement = DiligenceManagement.getInstance();
    StaffManagement staffManagement = StaffManagement.getInstance();

    public void run() {

        writeToFileDiligence();

        System.out.println("---Diligence Management---");
        System.out.println("Nhập ID");
        String id = scanner.nextLine();
        int index = staffManagement.findById(id);
        diligenceManagement.showDeligence(index);

        int choice = -1;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    diligenceManagement.addDayOff(id);
                    break;
                }
            }
        } while (choice != 0);


    }

    private void writeToFileDiligence() {
        for (int i = 0; i < staffManagement.size(); i++) {
            Staff staff = staffManagement.displayStaff(i);
            Diligence diligence = new Diligence(staff);
            diligenceManagement.addNew(diligence);
        }
    }

    private void menu() {

        System.out.println("1. Thêm lần nghỉ  ");
        System.out.println("2. Xóa lần nghỉ");
        System.out.println("3. Thêm lần đi muộn  ");
        System.out.println("4. Xóa lần đi muộn");
        System.out.println("0. Thoát");
    }


    public void addNew(Staff staff) {
        Diligence diligence = new Diligence();
        diligence.setStaff(staff);
    }
}
