package com.example.baked.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;

import com.example.baked.model.Class;
import com.example.baked.model.Period;
import com.example.baked.model.Student;
import com.example.baked.model.Tutor;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.PeriodRepository;
import com.example.baked.repository.StudentRepository;
import com.example.baked.repository.TutorRepository;

@Controller
// Store the object student in the session
@SessionAttributes("student")
public class RoutesStudentController {

    //////////////////////////////////////// Repositories
    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private PeriodRepository periodRepository;

    //////////////////////////////////////// APIs
    @GetMapping(value = "/student/login")
    public String getStudentLogin() {
        return "student/login.html";
    }

    @RequestMapping(value = "/student/home", method = RequestMethod.GET)
    public String getHome(Model model, @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @ModelAttribute Student student) {

        // Bring the student who logging in to model under the name "student"
        model.addAttribute("student", studentRepository.getStudentByUsername(username));

        // Retrieve all tutors from database
        List<Tutor> tutors = new ArrayList<>(tutorRepository.findAll());

        // Retrieve all classes from database
        List<Class> classes = new ArrayList<>(classRepository.findAll());

        // Declare list of classes which the student is learning
        List<Class> classes_learning = null;

        // Student does not exist
        if (model.getAttribute("student") != null) {
            Student student_temp = (Student) model.getAttribute("student");

            // Retrieve all classes which the student is learning from database
            classes_learning = new ArrayList<>(classRepository.getClassByStudentID(student_temp.getStudent_id()));
        }


        // Bring list of tutors to model under the name "tutors"
        // We store in the model to export them to the view
        model.addAttribute("tutors", tutors);

        // Bring list of classes to model under the name "classes"
        model.addAttribute("classes", classes);

        // Create a map for classes to store Class and Period Time
        Map<Class, List<Period>> classes_map = new HashMap<Class, List<Period>>();

        // For each classes, retrieve Periods through for each
        for (Class c: classes){
            List<Period> periods = new ArrayList<>();
            for (String p: c.getPeriods()) {
                periods.add(periodRepository.getPeriodByPeriodID(p));
            }
            classes_map.put(c, periods);
        }

        // Bring combination of list of classes and time to model under the name "classes_map"
        model.addAttribute("classes_map", classes_map);

        /*System.out.println("Mappppp");
        for(Class c : classes_map.keySet()) {
            System.out.println(classes_map.get(c));
        }*/
        //System.out.println(classes_map.keySet());
        //System.out.println(classes_map.values());

        // Bring list of classes which the student is learning to model under the name
        // "classes_learning"
        model.addAttribute("classes_learning", classes_learning);

        // Create a map for Leanring classes to store Class and Period Time
        Map<Class, List<Period>> classes_learning_map = new HashMap<Class, List<Period>>();

        // For each classes, retrieve Periods through for each
        for (Class c: classes_learning){
            List<Period> periods = new ArrayList<>();
            for (String p: c.getPeriods()) {
                periods.add(periodRepository.getPeriodByPeriodID(p));
            }
            classes_learning_map.put(c, periods);
        }

        // Bring combination of list of classes and time to model under the name "classes_learning_map"
        model.addAttribute("classes_learning_map", classes_learning_map);

        // Username does not exist --> Return to login page
        if (model.getAttribute("student") == null) {
            return "student/login.html";
        } else {
            Student student_test = (Student) model.getAttribute("student");
            // Login successfully! --> Move to homepage
            if (student_test.getPassword().equals(password)) {
                return "student/home.html";
            } else {
                // Username exists, but wrong password --> Also return to login page
                return "student/login.html";
            }
        }

    }

    @RequestMapping(value = "/student/tutorProfile/{tutor_id}", method = RequestMethod.GET)
    public String getTutorProfile(@PathVariable(value = "tutor_id") String tutor_id, Model model) {
        // Retrieve a tutor from database
        Tutor tutor = tutorRepository.getTutorByTutorID(tutor_id);

        // Bring the tutor to model for viewing
        model.addAttribute("tutor", tutor);

        return "student/tutor-detail.html";
    }

    @RequestMapping(value = "/student/profile", method = RequestMethod.GET)
    public String getProfile(Model model) {
        return "student/profile.html";
    }

    @ModelAttribute("student")
    public Student getStudent() {
        return new Student();

    }

}
