package com.codegym.controller;

import com.codegym.model.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StaffManagement implements ReadFile, WriteFile {

    private static StaffManagement staffManagement;
    private List<Staff> staffs = new ArrayList<>();


    private StaffManagement() {
        File file = new File("staff.txt");
        if (file.exists()) {
            try {
                readFile("staff.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static StaffManagement getInstance() {
        if (staffManagement == null) {
            staffManagement = new StaffManagement();
        }
        return staffManagement;
    }

    public int size() {
        return staffs.size();
    }


    public void fullTimeStaff() {
        for (Staff staff : staffs) {
            if (staff.isFulltime()) {
                System.out.println(staff);
            }
        }
    }

    public void partTimeStaff() {
        for (Staff staff : staffs) {
            if (staff.isFulltime() == false) {
                System.out.println(staff);
            }
        }
    }


    public int findStaffById(String id) {
        int index = -1;
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void deleteStaff(int index) {
        this.staffs.remove(index);
        try {
            writeFile("staff.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Staff displayStaff(int index) {
        return staffs.get(index);
    }


    public void displayStaffAll() {
        for (Staff staff : staffs) {
            System.out.println(staff);
        }
    }


    public void addNewStaff(Staff staff) {
        staffs.add(staff);
        try {
            writeFile("staff.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateStaffById(String id, Staff staff) {
        int index = findStaffById(id);
        this.staffs.set(index, staff);
        try {
            writeFile("staff.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Staff getStaffById(String id) {
        int index = findStaffById(id);
        return staffs.get(index);
    }


    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (staffs.get(i).getId().equals(id)) {
                return i;
            }
        }
        return index;
    }

    public boolean isFullTime(int index) {
        if (staffs.get(index).isFulltime()) {
            return true;
        }
        return false;
    }


    public boolean isID(String id) {
        String regex = "^(?=[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,9}(19|20)\\d{2,3}$";
        if (Pattern.matches(regex, id)) return true;
        return false;
    }


    public boolean checkIDStaff(String id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (id.equals(staffs.get(i).getId())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.staffs = (List<Staff>) ois.readObject();
    }


    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.staffs);
    }
}
