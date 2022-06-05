package com.example.baked.model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Student")
public class Student {
    @Id
    private String id;
    private String student_id;
    private String username;
    private String password;
    private FullName fullname;
    private String gender;
    private Birth date_of_birth;
    private Address address;
    private FullName parent_name;
    private List<String> emails;
    private List<String> phones;
    private List<String> classes;
    private String about;

    public Student() {
    }

    public Student(String student_id, FullName fullname, String gender, Birth date_of_birth, Address address,
            FullName parent_name, List<String> emails, List<String> phones, String about) {
        this.student_id = student_id;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.parent_name = parent_name;
        this.emails = emails;
        this.phones = phones;
        this.about = about;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FullName getFullname() {
        return this.fullname;
    }

    public void setFullname(FullName fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Birth getDate_of_birth() {
        return this.date_of_birth;
    }

    public void setDate_of_birth(Birth date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public FullName getParent_name() {
        return this.parent_name;
    }

    public void setParent_name(FullName parent_name) {
        this.parent_name = parent_name;
    }

    public List<String> getEmails() {
        return this.emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhones() {
        return this.phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getClasses() {
        return this.classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String beautifyList(List<String> list) {
        return list.toString().replaceAll("(^\\[|\\]$)", "");
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", student_id='" + getStudent_id() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", gender='" + getGender() + "'" +
            ", date_of_birth='" + getDate_of_birth() + "'" +
            ", address='" + getAddress() + "'" +
            ", parent_name='" + getParent_name() + "'" +
            ", emails='" + getEmails() + "'" +
            ", phones='" + getPhones() + "'" +
            ", classes='" + getClasses() + "'" +
            ", about='" + getAbout() + "'" +
            "}";
    }


}
