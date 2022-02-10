package com.codegym.controller;

import com.codegym.model.Diligence;
import com.codegym.model.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DiligenceManagement implements ReadFile, WriteFile {

    private static DiligenceManagement diligenceManagement;

    StaffManagement staffManagement = StaffManagement.getInstance();

    private List<Diligence> diligences = new ArrayList<>();


    private DiligenceManagement() {
        File file = new File("diligence.txt");
        if (file.exists()) {
            try {
                readFile("diligence.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public static DiligenceManagement getInstance() {
        if (diligenceManagement == null) {
            diligenceManagement = new DiligenceManagement();
        }
        return diligenceManagement;
    }

    public int size() {
        return diligenceManagement.size();
    }


    public void addDayOff(int index) {

        int count = diligences.get(index).getDayOff();
        count++;
        diligences.get(index).setDayOff(count);
        System.out.println("Đã thêm 1 ngày nghỉ");
        showDiligence(index);
        try {
            writeFile("diligence.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLateTime(int index) {
        int count = diligences.get(index).getLateTime();
        count++;
        diligences.get(index).setLateTime(count);
        System.out.println("Đã thêm 1 lần đi muộn");
        showDiligence(index);
        try {
            writeFile("diligence.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int findById(String id) {
        return staffManagement.findById(id);
    }


    public void removeDayOff(int index) {
        int count = diligences.get(index).getDayOff();
        if (count > 0) {
            count--;
            diligences.get(index).setDayOff(count);
            System.out.println("Đã giảm 1 ngày nghỉ");
            showDiligence(index);
            try {
                writeFile("diligence.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Không thể giảm khi số ngày nghỉ đã bằng 0");
        }
    }


    public void removeLateTime(int index) {
        int count = diligences.get(index).getLateTime();
        if (count > 0) {
            count--;
            diligences.get(index).setLateTime(count);
            System.out.println("Đã giảm 1 lần đi muộn");
            showDiligence(index);
            try {
                writeFile("diligence.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Không thể giảm khi số lần đi muộn đã bằng 0");
        }
    }

    public void resetDiligence() {
        for (int i = 0; i < diligences.size(); i++) {
            diligences.get(i).setDayOff(0);
            diligences.get(i).setLateTime(0);
            try {
                writeFile("diligence.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateDiligence(int index, Staff staff) {
        diligences.get(index).setStaff(staff);
        try {
            writeFile("diligence.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteDiligence(int index) {
        diligences.remove(index);
        try {
            writeFile("diligence.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int showDayOff(int index) {
        return diligences.get(index).getDayOff();
    }

    public int showLateTime(int index) {
        return diligences.get(index).getLateTime();
    }

    public void showDiligence(int index) {
        System.out.println(diligences.get(index));
    }


    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.diligences = (List<Diligence>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.diligences);
    }


    public void addNew(Diligence diligence) {
        diligences.add(diligence);
        try {
            writeFile("diligence.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
