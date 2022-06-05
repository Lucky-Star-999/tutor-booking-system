package com.example.baked.model;

public class Birth {
    private int day;
    private int month;
    private int year;

    public Birth() {
    }


    public Birth(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }


    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return getDay() + "/" + getMonth() + "/" +
            getYear();
    }


}
