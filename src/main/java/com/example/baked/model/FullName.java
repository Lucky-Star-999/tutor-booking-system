package com.example.baked.model;

public class FullName {
    private String first_name;
    private String last_name;

    public FullName() {
    }


    public FullName(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }


    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    @Override
    public String toString() {
        return getLast_name() + " " + getFirst_name();
    }


}
