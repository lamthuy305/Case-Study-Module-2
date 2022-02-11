package com.codegym.model;

import java.io.Serializable;

public class Staff implements Serializable {

    private String id;

    private String name;

    private String phone;

    private String hometown;

    private boolean fulltime;

    public Staff() {
    }

    public Staff(String id, String name, String phone, String hometown, boolean fulltime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.hometown = hometown;
        this.fulltime = fulltime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }


    @Override
    public String toString() {
        if (fulltime) return "ID: "+this.id + ", Họ tên: " + this.name + ", SĐT: " + this.phone + ", quê quán: " + this.hometown + ", làm việc fulltime";
        return "ID: "+this.id + ", Họ tên: " + this.name + ", SĐT: " + this.phone + ", quê quán: " + this.hometown + ", làm việc parttime";
    }
}
