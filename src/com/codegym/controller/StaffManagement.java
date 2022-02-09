package com.codegym.controller;

import com.codegym.model.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffManagement implements GeneralManagement<Staff>, ReadFile, WriteFile {
    public static final int SALARYFULLTIME = 9000000;
    public static final int SALARYPARTTIME = 4000000;

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


    public void fulltimeStaff() {
        for (Staff staff : staffs) {
            if (staff.isFulltime()) {
                System.out.println(staff);
            }
        }
    }

    public void parttimeStaff() {
        for (Staff staff : staffs) {
            if (staff.isFulltime() == false) {
                System.out.println(staff);
            }
        }
    }

    public int size() {
        return staffs.size();
    }


    public int findStaffByName(String name) {
        int index = -1;
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Staff displayStaff(int index){
        return staffs.get(index);
    }

    @Override
    public void displayAll() {
        for (Staff staff : staffs) {
            System.out.println(staff);
        }
    }

    @Override
    public void addNew(Staff staff) {
        staffs.add(staff);
        try {
            writeFile("staff.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateByName(String name, Staff staff) {
        int index = findStaffByName(name);
        this.staffs.set(index, staff);
    }

    @Override
    public boolean deleteByName(String name) {
        int index = findStaffByName(name);
        if (index != -1) {
            this.staffs.remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Staff getByName(String name) {
        int index = findStaffByName(name);
        return staffs.get(index);
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


    public void salary() {
        for (Staff staff : staffs) {
            if (staff.isFulltime() && staff.isOn()) {
                System.out.println(staff + ", lương" + SALARYFULLTIME);

            } else if (staff.isFulltime() == false && staff.isOn() == true) {
                System.out.println(staff + ", lương"+ SALARYPARTTIME);

            } else {
                System.out.println(staff + ", lương 0");
            }

        }
    }

    public int findById(String id){
    int index = -1;
        for (int i = 0; i < size(); i++) {
           if (staffs.get(i).getId().equals(id)){
               return i;
           }
        }
        return index;
    }
}
