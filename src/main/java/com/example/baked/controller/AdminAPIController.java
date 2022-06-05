package com.example.baked.controller;

import java.util.*;

import com.example.baked.model.Class;
import com.example.baked.model.Period;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Student;
import com.example.baked.model.StudentAuthentication;
import com.example.baked.model.Tutor;
import com.example.baked.model.TutorAuthentication;
import com.example.baked.requestBody.ClassAddRequestBody;
import com.example.baked.requestBody.ClassRequestBody;
import com.example.baked.requestBody.PeriodAddRequestBody;
import com.example.baked.requestBody.PeriodDeleteRequestBody;
import com.example.baked.requestBody.PeriodRequestBody;
import com.example.baked.requestBody.RequestFromStudentAddRequestBody;
import com.example.baked.requestBody.RequestFromStudentRequestBody;
import com.example.baked.requestBody.StudentAddRequestBody;
import com.example.baked.requestBody.StudentAuthenticationAddRequestBody;
import com.example.baked.requestBody.StudentAuthenticationRequestBody;
import com.example.baked.requestBody.StudentRequestBody;
import com.example.baked.requestBody.TutorAddRequestBody;
import com.example.baked.requestBody.TutorAuthenticationAddRequestBody;
import com.example.baked.requestBody.TutorAuthenticationRequestBody;
import com.example.baked.requestBody.TutorRequestBody;
import com.example.baked.service.AdminAPIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminAPIController {

    /////////////////// Import services //////////////
    @Autowired
    private AdminAPIService adminAPIService = new AdminAPIService();

    /////////////////// API Read /////////////////
    // Read All
    @GetMapping(value = "/api/tutors")
    @ResponseBody
    public List<Tutor> apiGetAllTutors() {
        return adminAPIService.apiGetAllTutors();
    }

    @GetMapping(value = "/api/students")
    @ResponseBody
    public List<Student> apiGetAllStudents() {
        return adminAPIService.apiGetAllStudents();
    }

    @GetMapping(value = "/api/tutors-authentication")
    @ResponseBody
    public List<TutorAuthentication> apiGetAllTutorsAuthentication() {
        return adminAPIService.apiGetAllTutorsAuthentication();
    }

    @GetMapping(value = "/api/students-authentication")
    @ResponseBody
    public List<StudentAuthentication> apiGetAllStudentsAuthentication() {
        return adminAPIService.apiGetAllStudentsAuthentication();
    }

    @GetMapping(value = "/api/classes")
    @ResponseBody
    public List<Class> apiGetAllClasses() {
        return adminAPIService.apiGetAllClasses();
    }

    @GetMapping(value = "/api/periods")
    @ResponseBody
    public List<Period> apiGetAllPeriods() {
        return adminAPIService.apiGetAllPeriods();
    }

    @GetMapping(value = "/api/requests-from-students")
    @ResponseBody
    public List<RequestFromStudent> apiGetAllRequestsFromStudents() {
        return adminAPIService.apiGetAllRequestsFromStudents();
    }

    // Read by ID itself
    /*
     * @GetMapping(value = "/api/tutor/{tutor_id}")
     * 
     * @ResponseBody
     * public Tutor apiGetTutorByTutorId(@PathVariable String tutor_id) {
     * return ;
     * }
     */

    @GetMapping(value = "/api/tutor/{tutor_id}")
    @ResponseBody
    public Tutor apiGetTutorByTutorId(@PathVariable String tutor_id) {
        return adminAPIService.apiGetTutorByTutorId(tutor_id);
    }

    @GetMapping(value = "/api/student/{student_id}")
    @ResponseBody
    public Student apiGetStudentByStudentId(@PathVariable String student_id) {
        return adminAPIService.apiGetStudentByStudentId(student_id);
    }

    @GetMapping(value = "/api/tutor-authentication/{tutor_id}")
    @ResponseBody
    public TutorAuthentication apiGetTutorAuthenticationByTutorId(@PathVariable String tutor_id) {
        return adminAPIService.apiGetTutorAuthenticationByTutorId(tutor_id);
    }

    @GetMapping(value = "/api/tutor-authentication-by-username/{username}")
    @ResponseBody
    public TutorAuthentication apiGetTutorAuthenticationByUsername(@PathVariable String username) {
        return adminAPIService.apiGetTutorAuthenticationByUsername(username);
    }

    @GetMapping(value = "/api/student-authentication/{student_id}")
    @ResponseBody
    public StudentAuthentication apiGetStudentAuthenticationByStudentId(@PathVariable String student_id) {
        return adminAPIService.apiGetStudentAuthenticationByStudentId(student_id);
    }

    @GetMapping(value = "/api/student-authentication-by-username/{username}")
    @ResponseBody
    public StudentAuthentication apiGetStudentAuthenticationByUsername(@PathVariable String username) {
        return adminAPIService.apiGetStudentAuthenticationByUsername(username);
    }

    @GetMapping(value = "/api/class/{class_id}")
    @ResponseBody
    public Class apiGetClassByClassId(@PathVariable String class_id) {
        return adminAPIService.apiGetClassByClassId(class_id);
    }

    @GetMapping(value = "/api/period/{period_id}")
    @ResponseBody
    public Period apiGetPeriodByPeriodId(@PathVariable String period_id) {
        return adminAPIService.apiGetPeriodByPeriodId(period_id);
    }

    @GetMapping(value = "/api/request-from-student/{request_id}")
    @ResponseBody
    public RequestFromStudent apiGetRequestFromStudentByRequestFromStudentId(@PathVariable String request_id) {
        return adminAPIService.apiGetRequestFromStudentByRequestFromStudentId(request_id);
    }

    // Filters
    /*
     * @GetMapping(value = "/api/filter-for-tutors")
     * 
     * @ResponseBody
     * public List<Tutor> filterForTutors(
     * 
     * @RequestParam String tutor_id,
     * 
     * @RequestParam String fullname,
     * 
     * @RequestParam String province_city,
     * 
     * @RequestParam String ward_district) {
     * return adminAPIService.filterForTutors(tutor_id, fullname, province_city,
     * ward_district);
     * }
     */

    // Filters
    @PostMapping(value = "/api/filter-for-tutors")
    @ResponseBody
    public List<Tutor> filterForTutors(
            @RequestBody RequestTutor requestTutor) {
        return adminAPIService.filterForTutors(requestTutor.tutor_id, requestTutor.fullname, requestTutor.province_city,
                requestTutor.ward_district);
    }

    /*
     * @GetMapping(value = "/api/filter-for-students")
     * 
     * @ResponseBody
     * public List<Student> filterForStudents(
     * 
     * @RequestParam String student_id,
     * 
     * @RequestParam String fullname,
     * 
     * @RequestParam String province_city,
     * 
     * @RequestParam String ward_district) {
     * return adminAPIService.filterForStudents(student_id, fullname, province_city,
     * ward_district);
     * }
     */

    @PostMapping(value = "/api/filter-for-students")
    @ResponseBody
    public List<Student> filterForStudents(
            @RequestBody StudentRequestBody studentRequestBody) {
        return adminAPIService.filterForStudents(studentRequestBody.student_id, studentRequestBody.fullname,
                studentRequestBody.province_city, studentRequestBody.ward_district);
    }

    /*@GetMapping(value = "/api/filter-for-tutors-authentication")
    @ResponseBody
    public List<TutorAuthentication> filterForTutorsAuthentication(
            @RequestParam String tutor_id,
            @RequestParam String username) {
        return adminAPIService.filterForTutorsAuthentication(tutor_id, username);
    }*/

    @PostMapping(value = "/api/filter-for-tutors-authentication")
    @ResponseBody
    public List<TutorAuthentication> filterForTutorsAuthentication(
            @RequestBody TutorAuthenticationRequestBody tutorAuthenticationRequestBody) {
        return adminAPIService.filterForTutorsAuthentication(tutorAuthenticationRequestBody.tutor_id, tutorAuthenticationRequestBody.username);
    }

    /*@GetMapping(value = "/api/filter-for-students-authentication")
    @ResponseBody
    public List<StudentAuthentication> filterForStudentsAuthentication(
            @RequestParam String student_id,
            @RequestParam String username) {
        return adminAPIService.filterForStudentsAuthentication(student_id, username);
    }*/

    @PostMapping(value = "/api/filter-for-students-authentication")
    @ResponseBody
    public List<StudentAuthentication> filterForStudentsAuthentication(
            @RequestBody StudentAuthenticationRequestBody studentAuthenticationRequestBody) {
        return adminAPIService.filterForStudentsAuthentication(studentAuthenticationRequestBody.student_id, studentAuthenticationRequestBody.username);
    }

    /*@GetMapping(value = "/api/filter-for-classes")
    @ResponseBody
    public List<Class> filterForClasses(
            @RequestParam String class_id,
            @RequestParam String tutor_id,
            @RequestParam String student_id) {
        return adminAPIService.filterForClasses(class_id, tutor_id, student_id);
    }*/

    @PostMapping(value = "/api/filter-for-classes")
    @ResponseBody
    public List<Class> filterForClasses(
            @RequestBody ClassRequestBody classRequestBody) {
        return adminAPIService.filterForClasses(classRequestBody.class_id, classRequestBody.tutor_id, classRequestBody.student_id);
    }

    /*@GetMapping(value = "/api/filter-for-requests-from-students")
    @ResponseBody
    public List<RequestFromStudent> filterForRequestsFromStudents(
            @RequestParam String request_id,
            @RequestParam String tutor_id,
            @RequestParam String student_id) {
        return adminAPIService.filterForRequestsFromStudents(request_id, tutor_id, student_id);
    }*/

    @PostMapping(value = "/api/filter-for-requests-from-students")
    @ResponseBody
    public List<RequestFromStudent> filterForRequestsFromStudents(
            @RequestBody RequestFromStudentRequestBody requestFromStudentRequestBody) {
        return adminAPIService.filterForRequestsFromStudents(requestFromStudentRequestBody.request_id, requestFromStudentRequestBody.tutor_id, requestFromStudentRequestBody.student_id);
    }

    /*@GetMapping(value = "/api/filter-for-periods")
    @ResponseBody
    public List<Period> filterForPeriods(
            @RequestParam String period_id,
            @RequestParam String start_time,
            @RequestParam String end_time,
            @RequestParam String day) {
        return adminAPIService.filterForPeriods(period_id, start_time, end_time, day);
    }*/

    @PostMapping(value = "/api/filter-for-periods")
    @ResponseBody
    public List<Period> filterForPeriods(
            @RequestBody PeriodRequestBody periodRequestBody) {
        return adminAPIService.filterForPeriods(periodRequestBody.period_id, periodRequestBody.start_time, periodRequestBody.end_time, periodRequestBody.day);
    }

    /////////////////// API Delete /////////////////
    // Delete by id itself or username (for Authentication)
    /*@DeleteMapping(value = "/api/delete/tutor")
    @ResponseBody
    public String apiDeleteTutorByTutorId(@RequestParam String tutor_id) {
        return adminAPIService.apiDeleteTutorByTutorId(tutor_id);
    }*/

    @PostMapping(value = "/api/delete/tutor")
    @ResponseBody
    public String apiDeleteTutorByTutorId(@RequestBody TutorRequestBody tutor) {
        return adminAPIService.apiDeleteTutorByTutorId(tutor.tutor_id);
    }
    
    /*@DeleteMapping(value = "/api/delete/student")
    @ResponseBody
    public String apiDeleteStudentByStudentId(@RequestParam String student_id) {
        return adminAPIService.apiDeleteStudentByStudentId(student_id);
    }*/

    @PostMapping(value = "/api/delete/student")
    @ResponseBody
    public String apiDeleteStudentByStudentId(@RequestBody StudentRequestBody student) {
        return adminAPIService.apiDeleteStudentByStudentId(student.student_id);
    }

    /*@DeleteMapping(value = "/api/delete/tutor-authentication")
    @ResponseBody
    public String apiDeleteTutorAuthenticationByTutorId(@RequestParam String tutor_id) {
        return adminAPIService.apiDeleteTutorAuthenticationByTutorId(tutor_id);
    }*/

    @PostMapping(value = "/api/delete/tutor-authentication")
    @ResponseBody
    public String apiDeleteTutorAuthenticationByTutorId(@RequestBody TutorAuthenticationRequestBody tutor) {
        return adminAPIService.apiDeleteTutorAuthenticationByTutorId(tutor.tutor_id);
    }

    @DeleteMapping(value = "/api/delete/tutor-authentication-by-username")
    @ResponseBody
    public String apiDeleteTutorAuthenticationByUsername(@RequestBody String username) {
        return adminAPIService.apiDeleteTutorAuthenticationByUsername(username);
    }

    /*@DeleteMapping(value = "/api/delete/student-authentication")
    @ResponseBody
    public String apiDeleteStudentAuthenticationByStudentId(@RequestParam String student_id) {
        return adminAPIService.apiDeleteStudentAuthenticationByStudentId(student_id);
    }*/

    @PostMapping(value = "/api/delete/student-authentication")
    @ResponseBody
    public String apiDeleteStudentAuthenticationByStudentId(@RequestBody StudentAuthenticationRequestBody student) {
        return adminAPIService.apiDeleteStudentAuthenticationByStudentId(student.student_id);
    }

    @DeleteMapping(value = "/api/delete/student-authentication-by-username")
    @ResponseBody
    public String apiDeleteStudentAuthenticationByUsername(@RequestBody String username) {
        return adminAPIService.apiDeleteStudentAuthenticationByUsername(username);
    }

    /*@DeleteMapping(value = "/api/delete/class")
    @ResponseBody
    public String apiDeleteClassByClassId(@RequestParam String class_id) {
        return adminAPIService.apiDeleteClassByClassId(class_id);
    }*/

    @PostMapping(value = "/api/delete/class")
    @ResponseBody
    public String apiDeleteClassByClassId(@RequestBody ClassRequestBody class1) {
        System.out.println(class1.class_id);
        return adminAPIService.apiDeleteClassByClassId(class1.class_id);
    }

    /*@DeleteMapping(value = "/api/delete/period")
    @ResponseBody
    public String apiDeletePeriodByPeriodId(@RequestParam String period_id) {
        return adminAPIService.apiDeletePeriodByPeriodId(period_id);
    }*/

    @PostMapping(value = "/api/delete/period")
    @ResponseBody
    public String apiDeletePeriodByPeriodId(@RequestBody PeriodRequestBody period) {
        return adminAPIService.apiDeletePeriodByPeriodId(period.period_id);
    }

    /*@DeleteMapping(value = "/api/delete/request-from-student")
    @ResponseBody
    public String apiDeleteRequestFromStudentByRequestFromStudentId(@RequestParam String request_id) {
        return adminAPIService.apiDeleteRequestFromStudentByRequestFromStudentId(request_id);
    }*/

    @PostMapping(value = "/api/delete/request-from-student")
    @ResponseBody
    public String apiDeleteRequestFromStudentByRequestFromStudentId(@RequestBody RequestFromStudentRequestBody request) {
        return adminAPIService.apiDeleteRequestFromStudentByRequestFromStudentId(request.request_id);
    }

    /////////////////// API Add /////////////////
    @PostMapping(value = "/api/add/tutor")
    @ResponseBody
    public Tutor apiAddTutor(@RequestBody TutorAddRequestBody tutorAddRequestBody) {
        return adminAPIService.apiAddTutor(tutorAddRequestBody);
    }

    @PostMapping(value = "/api/add/student")
    @ResponseBody
    public Student apiAddStudent(@RequestBody StudentAddRequestBody studentAddRequestBody ) {
        return adminAPIService.apiAddStudent(studentAddRequestBody);
    }

    @PostMapping(value = "/api/add/tutor-authentication")
    @ResponseBody
    public TutorAuthentication apiAddTutorAuthentication(@RequestBody TutorAuthenticationAddRequestBody tutorAuthenticationAddRequestBody) {
        return adminAPIService.apiAddTutorAuthentication(tutorAuthenticationAddRequestBody);
    }

    @PostMapping(value = "/api/add/student-authentication")
    @ResponseBody
    public StudentAuthentication apiAddStudentAuthentication(@RequestBody StudentAuthenticationAddRequestBody studentAuthenticationAddRequestBody) {
        return adminAPIService.apiAddStudentAuthentication(studentAuthenticationAddRequestBody);
    }

    @PostMapping(value = "/api/add/class")
    @ResponseBody
    public Class apiAddClass(@RequestBody ClassAddRequestBody classAddRequestBody) {
        return adminAPIService.apiAddClass(classAddRequestBody);
    }

    @PostMapping(value = "/api/add/request-from-student")
    @ResponseBody
    public RequestFromStudent apiAddRequestFromStudent(@RequestBody RequestFromStudentAddRequestBody requestFromStudentAddRequestBody) {
        return adminAPIService.apiAddRequestFromStudent(requestFromStudentAddRequestBody);
    }

    @PostMapping(value = "/api/add/period")
    @ResponseBody
    public Period apiAddPeriod(@RequestBody PeriodAddRequestBody periodAddRequestBody) {
        return adminAPIService.apiAddPeriod(periodAddRequestBody);
    }
}
