package com.codegym.model;

import java.io.Serializable;

public class Staff implements Serializable {

    private String id;

    private String name;

    private int phone;

    private String hometown;

    private boolean fulltime;

    private boolean on;

    public Staff() {
    }

    public Staff(String id, String name, int phone, String hometown, boolean fulltime, boolean on) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.hometown = hometown;
        this.fulltime = fulltime;
        this.on = on;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public String toString() {
        if (fulltime && on) return this.id + ", " + this.name + ", " + this.phone + ", " + this.hometown+", fulltime, còn làm việc";
        if (fulltime == false && on == true) return this.id + ", " + this.name + ", " + this.phone + ", " + this.hometown+", parttime, còn làm việc";
        if (fulltime == false && on == false) return this.id + ", " + this.name + ", " + this.phone + ", " + this.hometown+", parttime, đã nghỉ việc";
        return this.id + ", " + this.name + ", " + this.phone + ", " + this.hometown+", fulltime, đã nghỉ việc";
    }
}
