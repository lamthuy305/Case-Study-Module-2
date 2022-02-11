package com.codegym.view;

import com.codegym.controller.DiligenceManagement;
import com.codegym.controller.StaffManagement;
import com.codegym.model.Diligence;
import com.codegym.model.Staff;

import java.util.Scanner;

public class StaffMenu {
    public Scanner scanner = new Scanner(System.in);
    DiligenceManagement diligenceManagement = DiligenceManagement.getInstance();
    StaffManagement staffManagement = StaffManagement.getInstance();

    public void run() {

        int choiceStaffMenu = -1;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choiceStaffMenu = scanner.nextInt();
            scanner.nextLine();
            switch (choiceStaffMenu) {
                case 1: {
                    showAllStaff(staffManagement);
                    break;
                }
                case 2: {
                    showCreateStaff(staffManagement);
                    break;
                }
                case 3: {
                    showUpdateStaff(staffManagement);
                    break;
                }
                case 4: {
                    showDeleteStaff(staffManagement);
                    break;
                }
                case 5: {
                    searchStaff(staffManagement);
                    break;
                }
                case 6: {
                    int choiceSort = -1;
                    do {
                        menuSort();
                        System.out.println("Nhập lựa chọn của bạn");
                        choiceSort = scanner.nextInt();
                        switch (choiceSort) {
                            case 1: {
                                System.out.println("Danh sách nhân viên full time");
                                staffManagement.fullTimeStaff();
                                break;
                            }
                            case 2: {
                                System.out.println("Danh sách nhân viên part time");
                                staffManagement.partTimeStaff();
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
                    } while (choiceSort != 0);
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
        } while (choiceStaffMenu != 0);
    }


    private void menuSort() {
        System.out.println("Phân loại nhân viên");
        System.out.println("1. Nhân viên full time");
        System.out.println("2. Nhân viên part time");
        System.out.println("0. Quay lại");
    }


    private void searchStaff(StaffManagement staffManagement) {
        System.out.println("Tìm kiếm nhân viên");
        System.out.println("Nhập ID nhân viên cần tìm");
        String id = scanner.nextLine();
        int index = staffManagement.findStaffById(id);
        if (index != -1) {
            System.out.println("Thông tin nhân viên cần tìm: " + staffManagement.getStaffById(id));
        } else {
            System.err.println("Không tìm được do ID không tồn tại!!!");
        }
    }


    private void showDeleteStaff(StaffManagement staffManagement) {
        System.out.println("Xóa thông tin nhân viên");
        System.out.println("Nhập ID nhân viên cần xóa");
        String id = scanner.nextLine();
        int index = staffManagement.findStaffById(id);
        if (index != -1) {
            staffManagement.deleteStaff(index);
            diligenceManagement.deleteDiligence(index);
            System.out.println("Xóa thành công!");
        } else {
            System.err.println("Xóa lỗi do ID nhân viên không tồn tại!!!");
        }
    }


    private void showUpdateStaff(StaffManagement staffManagement) {
        System.out.println("Chỉnh sửa thông tin nhân viên");
        System.out.println("Nhập ID nhân viên cần chỉnh sửa");
        String id = scanner.nextLine();
        int index = staffManagement.findStaffById(id);
        if (index != -1) {
            Staff staff = inputStaffInfo();
            staffManagement.updateStaffById(id, staff);
            diligenceManagement.updateDiligence(index, staff);
            System.out.println("Cập nhật thành công!");
        } else {
            System.err.println("Cập nhật bị lỗi do không tồn tại ID cần tìm!");
        }
    }


    private void showCreateStaff(StaffManagement staffManagement) {
        System.out.println("Thêm nhân viên");
        Staff staff = inputStaffInfo();
        staffManagement.addNewStaff(staff);
        Diligence diligence = new Diligence(staff);
        diligenceManagement.addNew(diligence);
    }


    private void showAllStaff(StaffManagement staffManagement) {
        int size = staffManagement.size();
        if (size == 0) {
            System.out.println("Danh sách rỗng");
        } else {
            System.out.println("Danh sách học viên");
            staffManagement.displayStaffAll();
        }
    }


    private Staff inputStaffInfo() {
        System.out.println("Nhập thông tin nhân viên");
        String id;
        do {
            do {
                System.out.println("Nhập ID nhân viên gồm 8-14 ký tự theo quy tắc(tên + họ, tên đệm viết tắt + năm sinh): ");
                id = scanner.nextLine();
                if (staffManagement.checkIDStaff(id)) {
                    System.err.println("ID đã tồn tại, cần thêm số 1 - 9 vào cuối");
                }
            }while (staffManagement.checkIDStaff(id));

            if (staffManagement.isID(id) == false) {
                System.err.println("ID chưa đúng định dạng");
            }
        } while (staffManagement.isID(id) == false);
        System.out.println("Nhập tên nhân viên:");
        String name = scanner.nextLine();
        String phone;
        do {
            System.out.println("Nhập số điện thoại nhân viên");
            phone = scanner.nextLine();
            if (phone.length() != 10) {
                System.out.println("Số điện thoại phải có 10 số");
            }
        } while (phone.length() != 10);
        System.out.println("Nhập quê quán nhân viên:");
        String hometown = scanner.nextLine();
        System.out.println("Có làm việc fulltime không(true/false):");
        boolean fulltime = scanner.nextBoolean();
        Staff staff = new Staff(id, name, phone, hometown, fulltime);
        return staff;
    }


    private void menu() {
        System.out.println("1. Hiển thị danh sách nhân viên");
        System.out.println("2. Thêm nhân viên mới");
        System.out.println("3. Cập nhật nhân viên");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Phân loại nhân viên");
        System.out.println("0. Quay lại");
    }
}
