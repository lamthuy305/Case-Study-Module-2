package com.codegym.controller;

import com.codegym.model.Diligence;
import com.codegym.model.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DiligenceManagement implements GeneralManagement<Diligence>, ReadFile, WriteFile {

    private static DiligenceManagement diligenceManagement;

    StaffManagement staffManagement = StaffManagement.getInstance();

    private List<Diligence> diligences = new ArrayList<>();


    private DiligenceManagement() {

        File file = new File("diligence.txt");
        writeToFileDiligence();

        if (file.exists()) {
            try {
                readFile("diligence.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeToFileDiligence() {
        for (int i = 0; i < staffManagement.size(); i++) {
            Staff staff = staffManagement.displayStaff(i);
            Diligence diligence = new Diligence(staff);
            addNew(diligence);
        }
    }

    public static DiligenceManagement getInstance() {
        if (diligenceManagement == null) {
            diligenceManagement = new DiligenceManagement();
        }
        return diligenceManagement;
    }

    public void addDayOff(int index) {

        int count = diligences.get(index).getDayOff();
        System.out.println("Số lần nghỉ cũ " + count);
        count++;
        System.out.println("Số lần nghỉ mới " + count);

        diligences.get(index).setDayOff(count);
        System.out.println("Đã thêm xong");

        showDeligence(index);
        try {
            writeFile("diligence.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeligence(int index) {
        System.out.println(diligences.get(index));
    }

    public int size() {
        return diligenceManagement.size();
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


    @Override
    public void displayAll() {
        for (Diligence diligence : diligences) {
            System.out.println(diligence);
        }
    }


    @Override
    public void addNew(Diligence diligence) {
        diligences.add(diligence);
        try {
            writeFile("diligence.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateByName(String name, Diligence diligence) {

    }

    @Override
    public boolean deleteByName(String name) {
        return false;
    }

    @Override
    public Diligence getByName(String name) {
        return null;
    }

    public int findById(String id) {
        return staffManagement.findById(id);
    }

}
