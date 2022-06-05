package com.example.baked.controller;

import com.example.baked.model.Class;
import com.example.baked.model.Student;
import com.example.baked.model.StudentAuthentication;
import com.example.baked.model.Tutor;
import com.example.baked.model.TutorAuthentication;
import com.example.baked.service.ClassService;
import com.example.baked.service.StudentService;
import com.example.baked.service.TutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class GlobalController {
    @Autowired
    private StudentService studentService = new StudentService();

    @Autowired
    private TutorService tutorService = new TutorService();

    @Autowired
    private ClassService classService = new ClassService();

    //////////////////////////////////// Tutors /////////////////////////////
    @GetMapping(value = "global/getAllTutors")
    @ResponseBody
    public List<Tutor> getAllTutors() {
        return tutorService.getAllTutors();
    }

    @GetMapping(value = "global/getAllTutorsAuthentication")
    @ResponseBody
    public List<TutorAuthentication> getAllTutorsAuthentication() {
        return tutorService.getAllTutorsAuthentication();
    }

    @GetMapping(value = "global/getTutor/{id}")
    @ResponseBody
    public Tutor findTutorByID(@PathVariable(value = "id") String id) {
        return tutorService.findTutorByID(id);
    }

    //////////////////////////////////// Students /////////////////////////////
    @GetMapping(value = "global/getAllStudents")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "global/getAllStudentsID")
    @ResponseBody
    public List<String> getAllStudentsID() {
        return studentService.getAllStudentsID();
    }

    @GetMapping(value = "global/getAllStudentsAuthentication")
    @ResponseBody
    public List<StudentAuthentication> getAllStudentsAuthentication() {
        return studentService.getAllStudentsAuthentication();
    }

    //////////////////////////////////// Classes /////////////////////////////
    @GetMapping(value = "global/getAllClasses")
    @ResponseBody
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }


}
