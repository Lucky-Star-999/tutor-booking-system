package com.example.baked.service;

import java.util.*;

import com.example.baked.model.Address;
import com.example.baked.model.Birth;
import com.example.baked.model.Class;
import com.example.baked.model.FullName;
import com.example.baked.model.Period;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Student;
import com.example.baked.model.StudentAuthentication;
import com.example.baked.model.Tutor;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.PeriodRepository;
import com.example.baked.repository.RequestFromStudentRepository;
import com.example.baked.repository.StudentAuthenticationRepository;
import com.example.baked.repository.StudentRepository;
import com.example.baked.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
@SessionAttributes("student")
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private StudentAuthenticationRepository studentAuthenticationRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private RequestFromStudentRepository requestFromStudentRepository;

    // @Autowired
    // private ClassService classService = new ClassService();

    @Autowired
    private GeneratorService generatorService = new GeneratorService();

    @Autowired
    private RequestFromStudentService requestFromStudentService = new RequestFromStudentService();

    @ModelAttribute("student")
    public Student getStudent() {
        return new Student();
    }

    public StudentRepository getStudentRepository() {
        return this.studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentAuthenticationRepository getStudentAuthenticationRepository() {
        return this.studentAuthenticationRepository;
    }

    public void setStudentAuthenticationRepository(StudentAuthenticationRepository studentAuthenticationRepository) {
        this.studentAuthenticationRepository = studentAuthenticationRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<String> getAllStudentsID() {
        List<Student> students = new ArrayList<Student>(studentRepository.findAll());
        List<String> studentsID = new ArrayList<String>();
        for (Student s : students) {
            studentsID.add(s.getStudent_id());
        }
        return studentsID;
    }

    public List<StudentAuthentication> getAllStudentsAuthentication() {
        System.out.println(studentAuthenticationRepository.findAll());
        return studentAuthenticationRepository.findAll();
    }

    public String studentLogin(String username, String password, Model model) {

        if (username == null || password == null) {
            return "student/student-login.html";
        }

        StudentAuthentication studentAuth = studentAuthenticationRepository.getAuthByUsername(username);

        // Password is undefined
        if (studentAuth == null) {
            return "student/student-login.html";
        }

        if (studentAuth.getPassword() == null) {
            return "student/student-login.html";
        }

        // Password is correct!
        if (studentAuth.getPassword().equals(password)) {
            model.addAttribute("student", studentRepository.getStudentByStudentID(studentAuth.getStudent_id()));
            return "redirect:/student-home";
        }

        // Password is incorrect!
        return "student/student-login.html";
    }

    public String studentHome(Model model) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        model.addAttribute("user", student);
        model.addAttribute("classes", classRepository.getClassByStudentID(student.getStudent_id()));
        model.addAttribute("tutors", tutorRepository.findAll());

        return "student/student-home.html";
    }

    public String getStudentDetailTutor(Model model, String id) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        model.addAttribute("user", student);
        model.addAttribute("tutor_profile", tutorRepository.getTutorByTutorID(id));

        List<Class> classes = classRepository.getClassByTutorID(id);

        List<String> periods_id = new ArrayList<>();
        for (Class c : classes) {
            for (String p : c.getPeriods()) {
                periods_id.add(p);
            }
        }

        List<Period> full_periods = periodRepository.getPeriodByTutorID(id);

        for (int i = full_periods.size() - 1; i >= 0; i--) {
            for (String p_id : periods_id) {
                if (full_periods.get(i).getPeriod_id().equals(p_id)) {
                    full_periods.remove(i);
                    break;
                }
            }
        }

        model.addAttribute("periods", full_periods);

        return "student/student-detail-tutor.html";
    }

    public String studentRequestTutor(
            String province_city,
            String ward_district,
            String home_number,
            String phone,
            String email,
            String grade,
            String subject,
            List<String> periods,
            int salary,
            String requirement,
            String tutor_id,
            Model model) {

        if (model.getAttribute("student") == null) {
            return "redirect:/";
        }

        Student student = (Student) model.getAttribute("student");

        Address address = new Address(province_city, ward_district, home_number);
        String request_id = generatorService.generateRequestFromStudentId();
        String student_id = student.getStudent_id();

        List<String> subjects = new ArrayList<>();
        subjects.add(subject);

        // String class_id = generatorService.generateClassIdForRequest();
        RequestFromStudent new_request = new RequestFromStudent(request_id, tutor_id, student_id, grade, subjects,
                address, salary, requirement, periods);

        requestFromStudentRepository.save(new_request);

        return "redirect:/student-home";
    }

    public String studentRequest(Model model) {
        if (model.getAttribute("student") == null) {
            return "redirect:/";
        }

        model.addAttribute("user", model.getAttribute("student"));
        model.addAttribute("requestsfromstudents", requestFromStudentService.getRequestsFromStudentsByStudentId(model));

        return "student/student-request.html";
    }

    public String studentLogout(Model model) {
        return "redirect:/";
    }

    public String studentClassDetail(String class_id, Model model) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        model.addAttribute("class", classRepository.getClassByClassID(class_id));

        model.addAttribute("user", student);

        Class current_class = classRepository.getClassByClassID(class_id);

        List<Period> periods = new ArrayList<>();

        for (String p : current_class.getPeriods()) {
            periods.add(periodRepository.getPeriodByPeriodID(p));
        }

        model.addAttribute("periods", periods.toString().replaceAll("(^\\[|\\]$)", ""));

        return "student/student-class-detail.html";
    }

    public String studentClass(Model model) {
        if (model.getAttribute("student") == null) {
            return "redirect:/";
        }

        Student student = (Student) model.getAttribute("student");

        model.addAttribute("user", model.getAttribute("student"));
        model.addAttribute("classes", classRepository.getClassByStudentID(student.getStudent_id()));
        return "student/student-class.html";
    }

    public String studentProfile(Model model) {
        if (model.getAttribute("student") == null) {
            return "redirect:/";
        }

        Student student = (Student) model.getAttribute("student");
        model.addAttribute("user", studentAuthenticationRepository.getAuthByStudentID(student.getStudent_id()));
        model.addAttribute("student_profile", student);

        return "student/student-profile.html";
    }

    public String studentRequestDetail(String request_id, Model model) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        model.addAttribute("requestfromstudent", requestFromStudentRepository.getRequestByRequestID(request_id));

        RequestFromStudent current_request = requestFromStudentRepository.getRequestByRequestID(request_id);

        model.addAttribute("user", student);

        List<Period> periods = new ArrayList<>();

        for (String p : current_request.getPeriods()) {
            periods.add(periodRepository.getPeriodByPeriodID(p));
        }

        model.addAttribute("periods", periods.toString().replaceAll("(^\\[|\\]$)", ""));

        return "student/student-request-detail.html";
    }

    public String studentUpdate(Model model) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        StudentAuthentication studentAuthentication = studentAuthenticationRepository
                .getAuthByStudentID(student.getStudent_id());

        model.addAttribute("studentAuthentication", studentAuthentication);
        return "student/student-update.html";
    }

    public String studentRegister() {
        return "student/student-register.html";
    }

    public String studentRegisterSubmit(Model model, String first_name, String last_name, String gender,
            String day, String month, String year, String province_city, String ward_district, String home_number,
            String emails, String phones, String parent_first_name, String parent_last_name,
            String about, String username, String password) {
        String new_student_id = generatorService.generateStudentId();
        FullName new_fullname = new FullName(first_name, last_name);
        FullName new_parent_name = new FullName(first_name, last_name);
        String new_gender = gender;
        Birth new_date_of_birth = new Birth(Integer.parseInt(day), Integer.parseInt(month),
                Integer.parseInt(year));
        Address new_address = new Address(province_city, ward_district, home_number);
        List<String> new_emails = new ArrayList<>();
        new_emails.add(emails);
        List<String> new_phones = new ArrayList<>();
        new_phones.add(phones);
        String new_about = about;
        Student new_student = new Student(new_student_id, new_fullname, new_gender, new_date_of_birth, new_address,
                new_parent_name, new_emails,
                new_phones, new_about);

        System.out.println(new_student);

        StudentAuthentication new_student_authentication = new StudentAuthentication(new_student_id, username,
                password);

        studentRepository.save(new_student);
        studentAuthenticationRepository.save(new_student_authentication);

        return "redirect:/";
    }

    public String studentUpdateSubmit(Model model, String first_name, String last_name, String gender,
            String day, String month, String year, String province_city, String ward_district, String home_number,
            String emails, String phones, String parent_first_name, String parent_last_name,
            String about, String username, String password) {
        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        student.setFullname(new FullName(first_name, last_name));
        student.setParent_name(new FullName(parent_first_name, parent_last_name));
        student.setGender(gender);
        student.setDate_of_birth(new Birth(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year)));
        student.setAddress(new Address(province_city, ward_district, home_number));

        List<String> new_emails = new ArrayList<>();
        new_emails.add(emails);
        student.setEmails(new_emails);

        List<String> new_phones = new ArrayList<>();
        new_phones.add(phones);
        student.setPhones(new_phones);

        student.setAbout(about);

        StudentAuthentication studentAuthentication = studentAuthenticationRepository
                .getAuthByStudentID(student.getStudent_id());

        studentAuthentication.setUsername(username);
        studentAuthentication.setPassword(password);

        studentRepository.save(student);
        studentAuthenticationRepository.save(studentAuthentication);
        model.addAttribute("student", student);

        return "redirect:/student-profile";
    }

    public String studentTutorFilter(Model model, String tutor_id, String subject, String grade, String province_city,
            String ward_district, String qualification) {

        Student student = (Student) model.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        List<Tutor> all_tutors = tutorRepository.findAll();
        List<Tutor> tutors_filtered_by_tutor_id = new ArrayList<>();
        List<Tutor> tutors_filtered_by_subject = new ArrayList<>();
        List<Tutor> tutors_filtered_by_grade = new ArrayList<>();
        List<Tutor> tutors_filtered_by_province_city = new ArrayList<>();
        List<Tutor> tutors_filtered_by_ward_district = new ArrayList<>();
        List<Tutor> tutors_filtered_by_qualification = new ArrayList<>();

        for (Tutor t : all_tutors) {
            if (t.getTutor_id().toLowerCase().equals(tutor_id.toLowerCase())) {
                tutors_filtered_by_tutor_id.add(t);
            }

            if (t.beautifyList(t.getSubjects()).toLowerCase().contains(subject.toLowerCase())) {
                tutors_filtered_by_subject.add(t);
            }

            for (String g : t.getGrades()) {
                if (g.equals(grade)) {
                    tutors_filtered_by_grade.add(t);
                }
            }

            if (t.getAddress().getProvince_city().toLowerCase().contains(province_city.toLowerCase())) {
                tutors_filtered_by_province_city.add(t);
            }

            if (t.getAddress().getWard_district().toLowerCase().contains(ward_district.toLowerCase())) {
                tutors_filtered_by_ward_district.add(t);
            }

            if (t.getQualification().toLowerCase().contains(qualification.toLowerCase())) {
                tutors_filtered_by_qualification.add(t);
            }
        }

        // Get intersection of all filters
        all_tutors.retainAll(tutors_filtered_by_tutor_id);
        all_tutors.retainAll(tutors_filtered_by_subject);
        all_tutors.retainAll(tutors_filtered_by_grade);
        all_tutors.retainAll(tutors_filtered_by_province_city);
        all_tutors.retainAll(tutors_filtered_by_ward_district);
        all_tutors.retainAll(tutors_filtered_by_qualification);

        // Remove duplicate
        Set<Tutor> set = new LinkedHashSet<>();
        set.addAll(all_tutors);
        all_tutors.clear();
        all_tutors.addAll(set);

        // Retrive the results
        List<Tutor> results = all_tutors;

        // Add tutors to model
        model.addAttribute("tutors", results);

        return "student/tutor-filter.html";
    }

    public String studentDeleteRequest(String request_id) {
        requestFromStudentRepository.deleteRequestByRequestID(request_id);
        return "redirect:/student-request";
    }

}
