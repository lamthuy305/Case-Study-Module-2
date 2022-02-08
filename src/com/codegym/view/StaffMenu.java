package com.codegym.view;

import com.codegym.controller.StaffManagement;
import com.codegym.model.Staff;

import java.util.Scanner;

public class StaffMenu {
    public Scanner scanner = new Scanner(System.in);

    public void run() {
        StaffManagement staffManagement = StaffManagement.getInstance();
        int choice = -1;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
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
                    salaryStaff(staffManagement);
                    break;
                }
                case 6: {
                    searchStaff(staffManagement);
                    break;
                }
                case 7: {
                    int choiceSort = -1;
                    do {
                        menuSort();
                        System.out.println("Nhập lựa chọn của bạn");
                        choiceSort = scanner.nextInt();
                        switch (choiceSort) {
                            case 1: {
                                System.out.println("Danh sách nhân viên full time");
                                staffManagement.fulltimeStaff();
                                break;
                            }
                            case 2: {
                                System.out.println("Danh sách nhân viên part time");
                                staffManagement.parttimeStaff();
                                break;
                            }
                        }
                    } while (choiceSort != 0);
                    break;
                }
            }
        } while (choice != 0);
    }

    private void menuSort() {
        System.out.println("Phân loại nhân viên");
        System.out.println("1. Nhân viên full time");
        System.out.println("2. Nhân viên part time");
        System.out.println("0. Quay lại");
    }

    private void searchStaff(StaffManagement staffManagement) {
        System.out.println("Tìm kiếm nhân viên");
        System.out.println("Nhập tên nhân viên cần tìm");
        String name = scanner.nextLine();
        int index = staffManagement.findStaffByName(name);
        if (index != -1) {
            System.out.println("Thông tin nhân viên cần tìm: " + staffManagement.getByName(name));
        } else {
            System.out.println("Không tìm thấy");
        }
    }


    private void salaryStaff(StaffManagement staffManagement) {
        System.out.println("Tính tiền lương nhân viên");
        staffManagement.salary();
    }


    private void showDeleteStaff(StaffManagement staffManagement) {
        System.out.println("Xóa thông tin nhân viên");
        System.out.println("Nhập tên nhân viên cần xóa");
        String name = scanner.nextLine();
        boolean isDeleted = staffManagement.deleteByName(name);
        if (isDeleted) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa lỗi do tên nhân viên không tồn tại!");
        }
    }

    private void showUpdateStaff(StaffManagement staffManagement) {
        System.out.println("Chỉnh sửa thông tin nhân viên");
        System.out.println("Nhập tên nhân viên cần chỉnh sửa thông tin");
        String name = scanner.nextLine();
        int index = staffManagement.findStaffByName(name);
        if (index != -1) {
            Staff staff = inputStaffInfo();
            staffManagement.updateByName(name, staff);
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật bị lỗi do không tồn tại nhân viên cần tìm!");
        }
    }

    private void showCreateStaff(StaffManagement staffManagement) {
        System.out.println("Thêm học viên");
        Staff staff = inputStaffInfo();
        staffManagement.addNew(staff);
    }

    private void showAllStaff(StaffManagement staffManagement) {
        int size = staffManagement.size();
        if (size == 0) {
            System.out.println("Danh sách rỗng");
        } else {
            System.out.println("Danh sách học viên");
            staffManagement.displayAll();
        }
    }

    private Staff inputStaffInfo() {
        System.out.println("Nhập mã nhân viên:");
        String id = scanner.nextLine();
        System.out.println("Nhập tên nhân viên:");
        String name = scanner.nextLine();
        System.out.println("Nhập số điện thoại nhân viên");
        int phone = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập quê quán nhân viên:");
        String hometown = scanner.nextLine();
        System.out.println("Có làm việc fulltime không:");
        boolean fulltime = scanner.nextBoolean();
        System.out.println("Nhập trạng thái làm việc:");
        boolean on = scanner.nextBoolean();
        Staff staff = new Staff(id, name, phone, hometown, fulltime, on);
        return staff;
    }

    private void menu() {
        System.out.println("1. Hiển thị danh sách nhân viên");
        System.out.println("2. Thêm nhân viên mới");
        System.out.println("3. Cập nhật nhân viên");
        System.out.println("4. Xóa");
        System.out.println("5. Tính lương nhân viên");
        System.out.println("6. Tìm kiếm");
        System.out.println("7. Phân loại nhân viên");
        System.out.println("0. Quay lại");
    }
}
