package com.example.baked.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Period")
public class Period {

    @Id
    private String id;
    private String period_id;
    private String tutor_id;
    private String student_id;
    private int start_time;
    private int end_time;
    private int day;

    public Period() {
    }


    public Period(String period_id, String tutor_id, int start_time, int end_time, int day) {
        this.period_id = period_id;
        this.tutor_id = tutor_id;
        this.student_id = "";
        this.start_time = start_time;
        this.end_time = end_time;
        this.day = day;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeriod_id() {
        return this.period_id;
    }

    public void setPeriod_id(String period_id) {
        this.period_id = period_id;
    }

    public String getTutor_id() {
        return this.tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public int getStart_time() {
        return this.start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }


    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
    

    @Override
    public String toString() {
        if (getDay() < 8) {
            return timeToString(getStart_time()) +
            " - " + timeToString(getEnd_time()) + " " +
            "(T" + getDay() + ")";
        } else {
            return timeToString(getStart_time()) +
            " - " + timeToString(getEnd_time()) + " " +
            "(CN)";
        }
        
    }

    public String timeToString(int time){
        if (time%100 < 10){
            return (time - time%100)/100 + ":0" + (time%100);
        }
        return (time - time%100)/100 + ":" + (time%100);
    }

    public boolean isConflictWith(Period another_period) {
        if (this.day == another_period.day) {
            if (this.start_time == another_period.start_time) {
                return true;
            }
            if (this.end_time == another_period.end_time) {
                return true;
            }
            if (this.start_time < another_period.start_time && this.end_time > another_period.start_time) {
                return true;
            }
            if (this.start_time > another_period.start_time && this.start_time < another_period.end_time) {
                return true;
            }
            if (this.start_time > another_period.start_time && this.end_time < another_period.end_time) {
                return true;
            }
            if (this.start_time < another_period.start_time && this.end_time > another_period.end_time) {
                return true;
            }
        }
        return false;
    }

}
