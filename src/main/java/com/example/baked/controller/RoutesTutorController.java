package com.example.baked.controller;

import java.util.*;

import com.example.baked.model.Class;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Tutor;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.RequestFromStudentRepository;
import com.example.baked.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
// Store the object student in the session
@SessionAttributes("tutor")
public class RoutesTutorController {

    //////////////////////////////////////// Repositories
    @Autowired
    private TutorRepository tutorRepository;

    //@Autowired
    //private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private RequestFromStudentRepository requestFromStudentRepository;

    @GetMapping(value = "/tutor/login")
    public String getLogin() {
        return "tutor/login.html";
    }

    @RequestMapping(value = "/tutor/home", method = RequestMethod.GET)
    public String getHome(Model model, @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @ModelAttribute Tutor tutor) {
        // Bring the tutor who logging in to model under the name "student"
        model.addAttribute("tutor", tutorRepository.getTutorByUsername(username));

        // Retrieve all classes from database
        List<Class> classes = new ArrayList<>(classRepository.findAll());

        // Declare list of requests from students
        List<RequestFromStudent> requests_from_students = null; 

        // Declare list of classes which the tutor is teaching
        List<Class> classes_teaching = null;

        // Student does not exist
        if (model.getAttribute("tutor") != null) {
            Tutor tutor_temp = (Tutor) model.getAttribute("tutor");

            // Retrieve all classes which the tutor is teaching from database
            classes_teaching = new ArrayList<>(classRepository.getClassByTutorID(tutor_temp.getTutor_id()));

            // Retrieve all requests from students from database
            requests_from_students = new ArrayList<>(requestFromStudentRepository.getRequestByTutorID(tutor_temp.getTutor_id()));
        }

        // Bring list of classes which the tutor is teaching to model under the name
        // "classes_teaching"
        model.addAttribute("classes_teaching", classes_teaching);

        // Bring list of classes to model under the name "classes"
        model.addAttribute("classes", classes);

        // Bring list of requests from students to model under the name "requestsFromStudents"
        model.addAttribute("requestsFromStudents", requests_from_students);

        System.out.println("Requests: " + requests_from_students);

        // Username does not exist --> Return to login page
        if (model.getAttribute("tutor") == null) {
            return "tutor/login.html";
        } else {
            Tutor tutor_test = (Tutor) model.getAttribute("tutor");
            // Login successfully! --> Move to homepage
            if (tutor_test.getPassword().equals(password)) {
                return "tutor/home.html";
            } else {
                // Username exists, but wrong password --> Also return to login page
                return "tutor/login.html";
            }
        }

    }

    @RequestMapping(value = "/tutor/profile", method = RequestMethod.GET)
    public String getProfile() {
        return "tutor/profile.html";
    }

    @ModelAttribute("tutor")
    public Tutor getTutor() {
        return new Tutor();

    }

}
