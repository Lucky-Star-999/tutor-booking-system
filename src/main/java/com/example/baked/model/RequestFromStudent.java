package com.example.baked.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "RequestFromStudent")
public class RequestFromStudent {

    @Id
    private String id;
    private String request_id;
    private String tutor_id;
    private String student_id;
    private String grade;
    private List<String> subjects;
    private Address address;
    private int salary;
    private String requirement;
    private List<String> periods;

    public RequestFromStudent() {
    }

    public RequestFromStudent(String request_id, String tutor_id,
            String student_id, String grade, List<String> subjects,
            Address address, int salary, String requirement, List<String> periods) {
        this.request_id = request_id;
        this.tutor_id = tutor_id;
        this.student_id = student_id;
        this.grade = grade;
        this.subjects = subjects;
        this.address = address;
        this.salary = salary;
        this.requirement = requirement;
        this.periods = periods;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<String> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRequirement() {
        return this.requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public List<String> getPeriods() {
        return this.periods;
    }

    public void setPeriods(List<String> periods) {
        this.periods = periods;
    }

    public String beautifyList(List<String> list) {
        return list.toString().replaceAll("(^\\[|\\]$)", "");
    }




    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", request_id='" + getRequest_id() + "'" +
            ", tutor_id='" + getTutor_id() + "'" +
            ", student_id='" + getStudent_id() + "'" +
            ", grade='" + getGrade() + "'" +
            ", subjects='" + getSubjects() + "'" +
            ", address='" + getAddress() + "'" +
            ", salary='" + getSalary() + "'" +
            ", requirement='" + getRequirement() + "'" +
            ", periods='" + getPeriods() + "'" +
            "}";
    }
    

}
