package com.example.baked.controller;

import java.util.*;

import com.example.baked.repository.CustomTutorRepo;
import com.example.baked.service.ClassService;
import com.example.baked.service.StudentService;
import com.example.baked.service.TutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "tutor", "student" })
public class ProjectController {
    @Autowired
    private StudentService studentService = new StudentService();

    @Autowired
    private TutorService tutorService = new TutorService();

    @Autowired
    private ClassService classService = new ClassService();

    @GetMapping(value = "/")
    public String initIndex(Model model) {
        return tutorService.initIndex(model);
    }

    //////////////////// Tutor //////////////////////

    @PostMapping(value = "/tutor-login")
    public String tutorLogin(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            Model model) {
        return tutorService.tutorLogin(username, password, model);
    }

    @GetMapping(value = "/tutor-home")
    public String tutorHome(Model model) {
        return tutorService.tutorHome(model);
    }

    @GetMapping(value = "/tutor-logout")
    public String tutorLogout(Model model) {
        return tutorService.tutorLogout(model);
    }

    @GetMapping(value = "/tutor-request")
    public String tutorRequest(Model model) {
        return tutorService.tutorRequest(model);
    }

    @GetMapping(value = "/tutor-class")
    public String tutorClass(Model model) {
        return tutorService.tutorClass(model);
    }

    @GetMapping(value = "/tutor-profile")
    public String tutorProfile(Model model) {
        return tutorService.tutorProfile(model);
    }

    @GetMapping(value = "/add-free-time")
    public String addFreeTime(
            @RequestParam(value = "start_time", required = false) String start_time,
            @RequestParam(value = "end_time", required = false) String end_time,
            @RequestParam(value = "day", required = false) String day,
            Model model) {
        return tutorService.addFreeTime(start_time, end_time, day, model);
    }

    @PostMapping(value = "/delete-free-time")
    public String deleteFreeTime(
            @RequestParam(value = "period_id", required = false) String period_id,
            Model model) {
        return tutorService.deleteFreeTime(period_id, model);
    }

    @PostMapping(value = "/tutor-accept-request")
    public String acceptRequestFromStudent(
            @RequestParam(value = "request_id", required = false) String request_id,
            Model model) {
        return tutorService.acceptRequestFromStudent(request_id, model);
    }

    @PostMapping(value = "/tutor-delete-request")
    public String deleteRequestFromStudent(
            @RequestParam(value = "request_id", required = false) String request_id,
            Model model) {
        return tutorService.deleteRequestFromStudent(request_id, model);
    }

    @PostMapping(value = "/tutor-class-detail")
    public String tutorClassDetail(
            @RequestParam(value = "class_id", required = false) String class_id,
            Model model) {
        return tutorService.tutorClassDetail(class_id, model);
    }

    @PostMapping(value = "/tutor-request-detail")
    public String tutorRequestDetail(
            @RequestParam(value = "request_id", required = false) String request_id,
            Model model) {
        return tutorService.tutorRequestDetail(request_id, model);
    }

    @GetMapping(value = "/tutor-update")
    public String tutorUpdate(Model model) {
        return tutorService.tutorUpdate(model);
    }

    @PostMapping(value = "/tutor-update-submit")
    public String tutorUpdateSubmit(Model model, @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name, @RequestParam(required = false) String gender,
            @RequestParam(required = false) String day, @RequestParam(required = false) String month,
            @RequestParam(required = false) String year, @RequestParam(required = false) String province_city,
            @RequestParam(required = false) String ward_district, @RequestParam(required = false) String home_number,
            @RequestParam(required = false) String emails, @RequestParam(required = false) String phones,
            @RequestParam(required = false) String job, @RequestParam(required = false) String graduated_school,
            @RequestParam(required = false) String major, @RequestParam(required = false) String qualification,
            @RequestParam(required = false) String graduated_year, @RequestParam(required = false) String grades,
            @RequestParam(required = false) String subjects,
            @RequestParam(required = false) String minimum_salary_requirement,
            @RequestParam(required = false) String about, @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        return tutorService.tutorUpdateSubmit(model, first_name, last_name, gender, day, month, year, province_city,
                ward_district, home_number, emails, phones, job, graduated_school, major, qualification, graduated_year,
                grades, subjects, minimum_salary_requirement, about, username, password);
    }

    @GetMapping(value = "/tutor-register")
    public String tutorRegister() {
        return tutorService.tutorRegister();
    }

    @PostMapping(value = "/tutor-register-submit")
    public String tutorRegisterSubmit(Model model, @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name, @RequestParam(required = false) String gender,
            @RequestParam(required = false) String day, @RequestParam(required = false) String month,
            @RequestParam(required = false) String year, @RequestParam(required = false) String province_city,
            @RequestParam(required = false) String ward_district, @RequestParam(required = false) String home_number,
            @RequestParam(required = false) String emails, @RequestParam(required = false) String phones,
            @RequestParam(required = false) String job, @RequestParam(required = false) String graduated_school,
            @RequestParam(required = false) String major, @RequestParam(required = false) String qualification,
            @RequestParam(required = false) String graduated_year, @RequestParam(required = false) String grades,
            @RequestParam(required = false) String subjects,
            @RequestParam(required = false) String minimum_salary_requirement,
            @RequestParam(required = false) String about, @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        return tutorService.tutorRegisterSubmit(model, first_name, last_name, gender, day, month, year, province_city,
                ward_district, home_number, emails, phones, job, graduated_school, major, qualification, graduated_year,
                grades, subjects, minimum_salary_requirement, about, username, password);
    }

    ////////////////////////// Student ////////////////////
    @PostMapping(value = "/student-login")
    public String studentLogin(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            Model model) {
        return studentService.studentLogin(username, password, model);
    }

    @GetMapping(value = "/student-home")
    public String studentHome(Model model) {
        return studentService.studentHome(model);
    }

    @PostMapping(value = "/student-detail-tutor")
    public String getStudentDetailTutor(
            @RequestParam(value = "tutorId", required = false) String tutorId,
            Model model) {
        return studentService.getStudentDetailTutor(model, tutorId);
    }

    @PostMapping(value = "/student-request-tutor")
    public String studentRequestTutor(
            @RequestParam(value = "province_city", required = false) String province_city,
            @RequestParam(value = "ward_district", required = false) String ward_district,
            @RequestParam(value = "home_number", required = false) String home_number,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "grade", required = false) String grade,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "period", required = false) List<String> period,
            @RequestParam(value = "salary", required = false) int salary,
            @RequestParam(value = "requirement", required = false) String requirement,
            @RequestParam(value = "tutor_id", required = false) String tutor_id,
            Model model) {
        return studentService.studentRequestTutor(province_city, ward_district,
                home_number, phone, email, grade, subject, period, salary, requirement, tutor_id, model);
    }

    @GetMapping(value = "/student-request")
    public String studentRequest(Model model) {
        return studentService.studentRequest(model);
    }

    @GetMapping(value = "/student-logout")
    public String studentLogout(Model model) {
        return studentService.studentLogout(model);
    }

    @PostMapping(value = "/student-class-detail")
    public String studentClassDetail(
            @RequestParam(value = "class_id", required = false) String class_id,
            Model model) {
        return studentService.studentClassDetail(class_id, model);
    }

    @GetMapping(value = "/student-class")
    public String studentClass(Model model) {
        return studentService.studentClass(model);
    }

    @GetMapping(value = "/student-profile")
    public String studentProfile(Model model) {
        return studentService.studentProfile(model);
    }

    @PostMapping(value = "/student-request-detail")
    public String studentRequestDetail(
            @RequestParam(value = "request_id", required = false) String request_id,
            Model model) {
        return studentService.studentRequestDetail(request_id, model);
    }

    @GetMapping(value = "/student-update")
    public String studentUpdate(Model model) {
        return studentService.studentUpdate(model);
    }

    @GetMapping(value = "/student-register")
    public String studentRegister() {
        return studentService.studentRegister();
    }

    @PostMapping(value = "/student-update-submit")
    public String studentUpdateSubmit(Model model, @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name, @RequestParam(required = false) String gender,
            @RequestParam(required = false) String day, @RequestParam(required = false) String month,
            @RequestParam(required = false) String year, @RequestParam(required = false) String province_city,
            @RequestParam(required = false) String ward_district, @RequestParam(required = false) String home_number,
            @RequestParam(required = false) String emails, @RequestParam(required = false) String phones,
            @RequestParam(required = false) String parent_first_name,
            @RequestParam(required = false) String parent_last_name,
            @RequestParam(required = false) String about, @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        return studentService.studentUpdateSubmit(model, first_name, last_name, gender, day, month, year, province_city,
                ward_district, home_number, emails, phones, parent_first_name, parent_last_name, about, username,
                password);
    }

    @PostMapping(value = "/student-register-submit")
    public String studentRegisterSubmit(Model model, @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name, @RequestParam(required = false) String gender,
            @RequestParam(required = false) String day, @RequestParam(required = false) String month,
            @RequestParam(required = false) String year, @RequestParam(required = false) String province_city,
            @RequestParam(required = false) String ward_district, @RequestParam(required = false) String home_number,
            @RequestParam(required = false) String emails, @RequestParam(required = false) String phones,
            @RequestParam(required = false) String parent_first_name,
            @RequestParam(required = false) String parent_last_name,
            @RequestParam(required = false) String about, @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        return studentService.studentRegisterSubmit(model, first_name, last_name, gender, day, month, year,
                province_city,
                ward_district, home_number, emails, phones, parent_first_name, parent_last_name, about, username,
                password);
    }

    @PostMapping(value = "/student-tutor-filter")
    public String studentTutorFilter(
            Model model,
            @RequestParam(value = "", required = false) String tutor_id,
            @RequestParam(value = "", required = false) String subject,
            @RequestParam(value = "", required = false) String grade,
            @RequestParam(value = "", required = false) String province_city,
            @RequestParam(value = "", required = false) String ward_district,
            @RequestParam(value = "", required = false) String qualification) {
        return studentService.studentTutorFilter(model, tutor_id, subject, grade, province_city, ward_district,
                qualification);
    }


    @PostMapping(value = "/student-delete-request")
    public String studentDeleteRequest(@RequestParam(required = false) String request_id) {
        return studentService.studentDeleteRequest(request_id);
    }
    

    //////////////////////// Global //////////////////
    @PostMapping(value = "/global-detail-tutor")
    public String getGlobalDetailTutor(
            @RequestParam(value = "tutorId", required = false) String tutorId,
            Model model) {
        return tutorService.getGlobalDetailTutor(model, tutorId);
    }

    @PostMapping(value = "/global-detail-class")
    public String getGlobalClassDetail(
            @RequestParam(value = "class_id", required = false) String class_id,
            Model model) {
        return classService.getGlobalClassDetail(model, class_id);
    }

    @GetMapping(value = "/admin")
    public String getAdmin(Model model) {
        return "admin.html";
    }

    @PostMapping(value = "/search")
    public String getTutorOnMainSearch(@RequestParam(defaultValue = "", required = false) String city, @RequestParam(defaultValue = "", required = false) String subject, @RequestParam(defaultValue = "", required = false) String grade, Model model) {
        return tutorService.getTutorOnMainSearch(city, subject, grade, model);
    }

    @PostMapping(value = "/search-text")
    public String getTutorOnText(@RequestParam(defaultValue = "", required = false) String name, Model model) {
        return tutorService.getTutorOnText(name, model);
    }

    @GetMapping(value = "/search-subject+{subject}")
    public String getTutorOnSubject(@PathVariable String subject, Model model) {
        return tutorService.getTutorOnSubject(subject, model);
    }
}
