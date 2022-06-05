package com.example.baked.model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Tutor")
public class Tutor {

    @Id
    private String id;

    @Field("tutor_id")
    private String tutor_id;

    @Field("username")
    private String username;

    @Field("password")
    private String password;
    private FullName fullname;
    private String gender;
    private Birth date_of_birth;
    private Address address;
    private List<String> emails;
    private List<String> phones;
    private String job;
    private String graduated_school;
    private String major;
    private String qualification;
    private int graduated_year;
    private List<String> grades;
    private List<String> subjects;
    private int minimum_salary_requirement;
    private List<String> teaching_classes;
    private String about;

    // Addition
    private List<String> periods;

    public Tutor() {
    }

    public Tutor(
            String tutor_id, FullName fullname,
            String gender, Birth date_of_birth, Address address, List<String> emails,
            List<String> phones, String job, String graduated_school, String major,
            String qualification, int graduated_year, List<String> grades,
            List<String> subjects, int minimum_salary_requirement, String about) {
        this.tutor_id = tutor_id;
        this.fullname = fullname;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.emails = emails;
        this.phones = phones;
        this.job = job;
        this.graduated_school = graduated_school;
        this.major = major;
        this.qualification = qualification;
        this.graduated_year = graduated_year;
        this.grades = grades;
        this.subjects = subjects;
        this.minimum_salary_requirement = minimum_salary_requirement;
        this.teaching_classes = new ArrayList<>();
        this.about = about;
        this.periods = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTutor_id() {
        return this.tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
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

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGraduated_school() {
        return this.graduated_school;
    }

    public void setGraduated_school(String graduated_school) {
        this.graduated_school = graduated_school;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getQualification() {
        return this.qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getGraduated_year() {
        return this.graduated_year;
    }

    public void setGraduated_year(int graduated_year) {
        this.graduated_year = graduated_year;
    }

    public List<String> getGrades() {
        return this.grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    public List<String> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public int getMinimum_salary_requirement() {
        return this.minimum_salary_requirement;
    }

    public void setMinimum_salary_requirement(int minimum_salary_requirement) {
        this.minimum_salary_requirement = minimum_salary_requirement;
    }

    public List<String> getTeaching_classes() {
        return this.teaching_classes;
    }

    public void setTeaching_classes(List<String> teaching_classes) {
        this.teaching_classes = teaching_classes;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String about) {
        this.about = about;
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
            ", tutor_id='" + getTutor_id() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", gender='" + getGender() + "'" +
            ", date_of_birth='" + getDate_of_birth() + "'" +
            ", address='" + getAddress() + "'" +
            ", emails='" + getEmails() + "'" +
            ", phones='" + getPhones() + "'" +
            ", job='" + getJob() + "'" +
            ", graduated_school='" + getGraduated_school() + "'" +
            ", major='" + getMajor() + "'" +
            ", qualification='" + getQualification() + "'" +
            ", graduated_year='" + getGraduated_year() + "'" +
            ", grades='" + getGrades() + "'" +
            ", subjects='" + getSubjects() + "'" +
            ", minimum_salary_requirement='" + getMinimum_salary_requirement() + "'" +
            ", teaching_classes='" + getTeaching_classes() + "'" +
            ", about='" + getAbout() + "'" +
            ", periods='" + getPeriods() + "'" +
            "}";
    }


}
