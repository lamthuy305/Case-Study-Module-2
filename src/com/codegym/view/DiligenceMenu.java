package com.codegym.view;

import com.codegym.controller.DiligenceManagement;

import java.util.Scanner;

public class DiligenceMenu {
    Scanner scanner = new Scanner(System.in);
    DiligenceManagement diligenceManagement = DiligenceManagement.getInstance();


    public void run() {
        System.out.println("---Diligence Management---");
        int choiceReset = -1;
        do {
            menu();
            choiceReset = scanner.nextInt();
            switch (choiceReset) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("Nhập ID");
                    String id = scanner.nextLine();
                    int index = diligenceManagement.findById(id);
                    if (index != -1) {
                        diligenceManagement.showDiligence(index);
                        int choiceDiligenceMenu = -1;
                        do {
                            menuDiligence();
                            System.out.println("Nhập lựa chọn của bạn");
                            choiceDiligenceMenu = scanner.nextInt();
                            scanner.nextLine();

                            switch (choiceDiligenceMenu) {
                                case 1: {
                                    diligenceManagement.addDayOff(index);
                                    break;
                                }
                                case 2: {
                                    diligenceManagement.removeDayOff(index);
                                    break;
                                }
                                case 3: {
                                    diligenceManagement.addLateTime(index);
                                    break;
                                }
                                case 4: {
                                    diligenceManagement.removeLateTime(index);
                                    break;
                                }
                                case 0: {
                                    System.err.println("Quay lại !!! \n");
                                    break;
                                }
                                default:{
                                    System.err.println("Nhập sai, mời nhập lại \n");
                                }
                            }
                        } while (choiceDiligenceMenu != 0);
                    } else {
                        System.err.println("ID nhập vào không tồn tại");
                    }
                    break;
                }
                case 2: {
                    diligenceManagement.resetDiligence();
                    System.out.println("Đã reset !!!");
                    break;
                }
                case 0: {
                    System.err.println("Quay lại !!!\n");
                    break;
                }
                default:{
                    System.err.println("Nhập sai, mời nhập lại\n");
                }
            }
        } while (choiceReset != 0);
    }

    private void menu() {
        System.out.println("1. Quản lý trong tháng");
        System.out.println("2. Reset sang tháng mới");
        System.out.println("0. Thoát");
        System.out.println("Nhập lựa chọn");
    }


    private void menuDiligence() {
        System.out.println("1. Thêm lần nghỉ  ");
        System.out.println("2. Xóa lần nghỉ");
        System.out.println("3. Thêm lần đi muộn  ");
        System.out.println("4. Xóa lần đi muộn");
        System.out.println("0. Thoát");
    }
}
