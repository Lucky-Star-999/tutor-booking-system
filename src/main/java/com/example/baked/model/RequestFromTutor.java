package com.example.baked.model;

public class RequestFromTutor {
    private String request_id;
    private String tutor_id;
    private String student_id;
    private String class_id;

    public RequestFromTutor() {
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getTutor_id() {
        return this.tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getClass_id() {
        return this.class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

}
