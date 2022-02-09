package com.codegym.model;

import java.io.Serializable;

public class Diligence implements Serializable {
    private Staff staff;
    private int dayOff = 0;
    private int lateTime = 0;

    public Diligence() {
    }

    public Diligence(Staff staff) {
        this.staff = staff;
    }

    public Diligence(Staff staff, int dayOff, int lateTime) {
        this.staff = staff;
        this.dayOff = dayOff;
        this.lateTime = lateTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public int getDayOff() {
        return dayOff;
    }

    public void setDayOff(int dayOff) {
        this.dayOff = dayOff;
    }

    public int getLateTime() {
        return lateTime;
    }

    public void setLateTime(int lateTime) {
        this.lateTime = lateTime;
    }

    @Override
    public String toString() {
        return staff + ", số ngày nghỉ = " + dayOff + ", số lần đi muộn = " + lateTime;
    }
}
