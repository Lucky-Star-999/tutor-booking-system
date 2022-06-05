package com.example.baked.service;

import java.util.*;

import com.example.baked.model.Address;
import com.example.baked.model.Birth;
import com.example.baked.model.Class;
import com.example.baked.model.FullName;
import com.example.baked.model.Period;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Tutor;
import com.example.baked.model.TutorAuthentication;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.CustomTutorRepo;
import com.example.baked.repository.PeriodRepository;
import com.example.baked.repository.RequestFromStudentRepository;
import com.example.baked.repository.TutorAuthenticationRepository;
import com.example.baked.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
@SessionAttributes("tutor")
public class TutorService {
  @Autowired
  private TutorRepository tutorRepository;

  @Autowired
  private TutorAuthenticationRepository tutorAuthenticationRepository;

  @Autowired
  private PeriodRepository periodRepository;

  @Autowired
  private ClassRepository classRepository;

  @Autowired
  private RequestFromStudentRepository requestFromStudentRepository;

  @Autowired
  private ClassService classService = new ClassService();

  @Autowired
  private GeneratorService generatorService = new GeneratorService();

  @Autowired
  private RequestFromStudentService requestFromStudentService = new RequestFromStudentService();

  @Autowired
  private CustomTutorRepo customTutorRepo;

  public TutorService() {
  }

  public TutorRepository getTutorRepository() {
    return this.tutorRepository;
  }

  public void setTutorRepository(TutorRepository tutorRepository) {
    this.tutorRepository = tutorRepository;
  }

  @ModelAttribute("tutor")
  public Tutor getTutor() {
    return new Tutor();
  }

  public List<Tutor> getAllTutors() {
    return tutorRepository.findAll();
  }

  public Tutor findTutorByID(String id) {
    return tutorRepository.getTutorByTutorID(id);
  }

  public String initIndex(Model model) {
    model.addAttribute("tutors", this.getAllTutors());
    model.addAttribute("provinces_cities", getAllProvincesCities());
    model.addAttribute("subjects", getAllSubjects());
    model.addAttribute("grades", getAllGrades());
    model.addAttribute("classes", classRepository.findAll());
    model.addAttribute("tutors_popular", customTutorRepo.getTutorOnPopularity());
    return "index.html";
  }

  public List<TutorAuthentication> getAllTutorsAuthentication() {
    return tutorAuthenticationRepository.findAll();
  }

  public String tutorLogin(String username, String password, Model model) {

    if (username == null || password == null) {
      return "tutor/tutor-login.html";
    }

    TutorAuthentication tutorAuth = tutorAuthenticationRepository.getAuthByUsername(username);

    // Password is undefined
    if (tutorAuth == null) {
      return "tutor/tutor-login.html";
    }

    if (tutorAuth.getPassword() == null) {
      return "tutor/tutor-login.html";
    }

    // Password is correct!
    if (tutorAuth.getPassword().equals(password)) {
      model.addAttribute("tutor", tutorRepository.getTutorByTutorID(tutorAuth.getTutor_id()));
      return "redirect:/tutor-home";
    }

    // Password is incorrect!
    return "tutor/tutor-login.html";
  }

  public String tutorHome(Model model) {

    Tutor tutor = (Tutor) model.getAttribute("tutor");

    if (tutor == null) {
      return "redirect:/";
    }

    model.addAttribute("user", tutor);
    model.addAttribute("classes", classService.getAllTeachingClasses(model));
    model.addAttribute("requestsfromstudents", requestFromStudentService.getRequestsFromStudents(model));

    return "tutor/tutor-home.html";
  }

  public String tutorLogout(Model model) {
    model.addAttribute("tutor", null);
    return "redirect:/";
  }

  public List<String> getAllProvincesCities() {
    List<Tutor> tutors = tutorRepository.findAll();
    List<String> provinces_cities = new ArrayList<>();

    for (Tutor tutor : tutors) {
      provinces_cities.add(tutor.getAddress().getProvince_city());
    }

    // Remove duplicate
    Set<String> set = new LinkedHashSet<>();
    set.addAll(provinces_cities);
    provinces_cities.clear();
    provinces_cities.addAll(set);

    return provinces_cities;
  }

  public List<String> getAllSubjects() {
    List<Tutor> tutors = tutorRepository.findAll();
    List<String> subjects = new ArrayList<>();

    for (Tutor tutor : tutors) {
      for (String subject : tutor.getSubjects()) {
        subjects.add(subject);
      }
    }

    // Remove duplicate
    Set<String> set = new LinkedHashSet<>();
    set.addAll(subjects);
    subjects.clear();
    subjects.addAll(set);

    return subjects;
  }

  public List<String> getAllGrades() {
    List<Tutor> tutors = tutorRepository.findAll();
    List<String> grades = new ArrayList<>();

    for (Tutor tutor : tutors) {
      for (String grade : tutor.getGrades()) {
        grades.add(grade);
      }
    }

    // Remove duplicate
    Set<String> set = new LinkedHashSet<>();
    set.addAll(grades);
    grades.clear();
    grades.addAll(set);

    return grades;
  }

  public String tutorRequest(Model model) {
    if (model.getAttribute("tutor") == null) {
      return "redirect:/";
    }

    model.addAttribute("user", model.getAttribute("tutor"));
    model.addAttribute("requestsfromstudents", requestFromStudentService.getRequestsFromStudents(model));
    return "tutor/tutor-request.html";
  }

  public String tutorClass(Model model) {
    if (model.getAttribute("tutor") == null) {
      return "redirect:/";
    }

    Tutor tutor = (Tutor) model.getAttribute("tutor");

    model.addAttribute("user", model.getAttribute("tutor"));
    model.addAttribute("classes", classRepository.getClassByTutorID(tutor.getTutor_id()));
    return "tutor/tutor-class.html";
  }

  public String getGlobalDetailTutor(Model model, String id) {
    model.addAttribute("tutor_profile", tutorRepository.getTutorByTutorID(id));
    return "global-detail-tutor.html";
  }

  public String tutorProfile(Model model) {
    if (model.getAttribute("tutor") == null) {
      return "redirect:/";
    }

    Tutor tutor = (Tutor) model.getAttribute("tutor");
    model.addAttribute("tutor_profile", tutor);

    // Get full periods: Periods in teaching classes and free periods
    model.addAttribute("periods", periodRepository.getPeriodByTutorID(tutor.getTutor_id()));

    model.addAttribute("user", tutorAuthenticationRepository.getAuthByTutorID(tutor.getTutor_id()));

    // Get classes
    List<Class> classes = classRepository.getClassByTutorID(tutor.getTutor_id());

    // Get periods in teching classes
    List<String> periods_id = new ArrayList<>();
    List<Period> teaching_periods = new ArrayList<>();
    for (Class c : classes) {
      for (String p : c.getPeriods()) {
        periods_id.add(p);
        teaching_periods.add(periodRepository.getPeriodByPeriodID(p));
      }
    }

    // Get full periods again. I don't know why ^^
    List<Period> full_periods = periodRepository.getPeriodByTutorID(tutor.getTutor_id());

    // Remove periods in teaching classes. So the remains are free periods
    for (int i = full_periods.size() - 1; i >= 0; i--) {
      for (String p_id : periods_id) {
        if (full_periods.get(i).getPeriod_id().equals(p_id)) {
          full_periods.remove(i);
          break;
        }
      }
    }

    // Tranfer the free periods to model
    model.addAttribute("periods", full_periods);

    /*
     * But, we want to handle the conflict periods in the frontend side, so we
     * should also transfer
     * the periods in teaching class to model (frontend) and put them into hidden
     * input
     */
    model.addAttribute("teaching_periods", teaching_periods);

    return "tutor/tutor-profile.html";
  }

  public String addFreeTime(String p_start_time, String p_end_time, String p_day, Model model) {
    int start_time = Integer.parseInt(p_start_time);
    int end_time = Integer.parseInt(p_end_time);
    int day = Integer.parseInt(p_day);

    Tutor tutor = (Tutor) model.getAttribute("tutor");

    Period new_period = new Period(generatorService.generatePeriodId(), tutor.getTutor_id(), start_time, end_time,
        day);

    periodRepository.save(new_period);

    return "redirect:/tutor-profile";
  }

  public String acceptRequestFromStudent(String request_id, Model model) {

    Tutor tutor = (Tutor) model.getAttribute("tutor");

    if (tutor == null) {
      return "redirect:/";
    }

    System.out.println("Request id: " + request_id);

    RequestFromStudent current_request = requestFromStudentRepository.getRequestByRequestID(request_id);
    List<RequestFromStudent> all_requests = requestFromStudentRepository.getRequestByTutorID(tutor.getTutor_id());
    List<String> current_request_periods = current_request.getPeriods();
    List<String> remove_requests = new ArrayList<>();

    for (RequestFromStudent r : all_requests) {
      for (String p : r.getPeriods()) {
        boolean flag = false;
        for (String current_p : current_request_periods) {
          if (p.equals(current_p) && !r.getRequest_id().equals(request_id)) {
            remove_requests.add(r.getRequest_id());
            flag = true;
            break;
          }
        }
        if (flag) {
          break;
        }
      }
    }

    String class_id = generatorService.generateClassId();
    String tutor_id = current_request.getTutor_id();
    String student_id = current_request.getStudent_id();
    String grade = current_request.getGrade();
    List<String> subjects = current_request.getSubjects();
    Address address = current_request.getAddress();
    int salary = current_request.getSalary();
    String requirement = current_request.getRequirement();
    List<String> periods = current_request.getPeriods();

    Class new_class = new Class(class_id, tutor_id, student_id, grade, subjects, address, salary, requirement,
        periods);
    // Save class
    classRepository.save(new_class);

    System.out.println("Current request: ");
    System.out.println(current_request);

    System.out.println("All periods in request: ");
    System.out.println(current_request_periods);

    System.out.println("The requests which conflict the time will be deleted: ");
    System.out.println(remove_requests);

    System.out.println("New class: ");
    System.out.println(new_class);

    // Add student_id to periods
    List<Period> new_periods = new ArrayList<>();
    for (String p_id : current_request_periods) {
      new_periods.add(periodRepository.getPeriodByPeriodID(p_id));
    }

    System.out.println("All periods after updated: ");
    for (Period p : new_periods) {
      p.setStudent_id(student_id);
      // Save all periods to mongodb
      periodRepository.save(p);
    }

    // Delete các request trùng và chính nó
    requestFromStudentRepository.deleteRequestByRequestID(request_id);
    for (String r_delete : remove_requests) {
      requestFromStudentRepository.deleteRequestByRequestID(r_delete);
    }

    return "redirect:/tutor-request";
  }

  public String deleteRequestFromStudent(String request_id, Model model) {

    Tutor tutor = (Tutor) model.getAttribute("tutor");

    if (tutor == null) {
      return "redirect:/";
    }

    requestFromStudentRepository.deleteRequestByRequestID(request_id);

    return "redirect:/tutor-request";
  }

  public String deleteFreeTime(String period_id, Model model) {

    Tutor tutor = (Tutor) model.getAttribute("tutor");

    if (tutor == null) {
      return "redirect:/";
    }

    // periodRepository.deletePeriodByPeriodID(period_id);

    List<RequestFromStudent> all_requests = requestFromStudentRepository.getRequestByTutorID(tutor.getTutor_id());

    for (RequestFromStudent r : all_requests) {
      for (String p_id : r.getPeriods()) {
        if (p_id.equals(period_id)) {
          requestFromStudentRepository.deleteRequestByRequestID(r.getRequest_id());
          break;
        }
      }
    }

    periodRepository.deletePeriodByPeriodID(period_id);

    return "redirect:/tutor-profile";
  }

  public String tutorClassDetail(String class_id, Model model) {
    Tutor tutor = (Tutor) model.getAttribute("tutor");

    if (tutor == null) {
      return "redirect:/";
    }

    model.addAttribute("class", classRepository.getClassByClassID(class_id));

    model.addAttribute("user", tutor);

    Class current_class = classRepository.getClassByClassID(class_id);

    List<Period> periods = new ArrayList<>();

    for (String p : current_class.getPeriods()) {
      periods.add(periodRepository.getPeriodByPeriodID(p));
    }

    model.addAttribute("periods", periods.toString().replaceAll("(^\\[|\\]$)", ""));

    return "tutor/tutor-class-detail.html";
  }

  public String tutorRequestDetail(String request_id, Model model) {
    Tutor tutor = (Tutor) model.getAttribute("tutor");

    if (tutor == null) {
      return "redirect:/";
    }

    model.addAttribute("requestfromstudent", requestFromStudentRepository.getRequestByRequestID(request_id));

    RequestFromStudent current_request = requestFromStudentRepository.getRequestByRequestID(request_id);

    model.addAttribute("user", tutor);

    List<Period> periods = new ArrayList<>();

    for (String p : current_request.getPeriods()) {
      periods.add(periodRepository.getPeriodByPeriodID(p));
    }

    model.addAttribute("periods", periods.toString().replaceAll("(^\\[|\\]$)", ""));

    return "tutor/tutor-request-detail.html";
  }

  public String tutorUpdate(Model model) {
    Tutor tutor = (Tutor) model.getAttribute("tutor");

    if (tutor == null) {
      return "redirect:/";
    }

    TutorAuthentication tutorAuthentication = tutorAuthenticationRepository.getAuthByTutorID(tutor.getTutor_id());

    model.addAttribute("tutorAuthentication", tutorAuthentication);
    return "tutor/tutor-update.html";
  }

  public String tutorRegister() {
    return "tutor/tutor-register.html";
  }

  public String tutorRegisterSubmit(Model model, String first_name, String last_name, String gender,
      String day, String month, String year, String province_city, String ward_district, String home_number,
      String emails, String phones, String job, String graduated_school, String major, String qualification,
      String graduated_year, String grades, String subjects, String minimum_salary_requirement,
      String about, String username, String password) {
    String new_tutor_id = generatorService.generateTutorId();
    FullName new_fullname = new FullName(first_name, last_name);
    String new_gender = gender;
    Birth new_date_of_birth = new Birth(Integer.parseInt(day), Integer.parseInt(month),
        Integer.parseInt(year));
    Address new_address = new Address(province_city, ward_district, home_number);
    List<String> new_emails = new ArrayList<>();
    new_emails.add(emails);
    List<String> new_phones = new ArrayList<>();
    new_phones.add(phones);
    String new_job = job;
    String new_graduated_school = graduated_school;
    String new_major = major;
    String new_qualification = qualification;
    int new_graduated_year = Integer.parseInt(graduated_year);
    List<String> new_grades = new ArrayList<>();
    new_grades.add(grades);
    List<String> new_subjects = new ArrayList<>();
    new_subjects.add(subjects);
    int new_minimum_salary_requirement = Integer.parseInt(minimum_salary_requirement);
    String new_about = about;
    Tutor new_tutor = new Tutor(new_tutor_id, new_fullname, new_gender, new_date_of_birth, new_address, new_emails,
        new_phones, new_job,
        new_graduated_school, new_major, new_qualification, new_graduated_year, new_grades, new_subjects,
        new_minimum_salary_requirement,
        new_about);

    System.out.println(new_tutor);

    TutorAuthentication new_tutor_authentication = new TutorAuthentication(new_tutor_id, username, password);

    tutorRepository.save(new_tutor);
    tutorAuthenticationRepository.save(new_tutor_authentication);

    return "redirect:/";
  }

  public String tutorUpdateSubmit(Model model, String first_name, String last_name, String gender,
      String day, String month, String year, String province_city, String ward_district, String home_number,
      String emails, String phones, String job, String graduated_school, String major, String qualification,
      String graduated_year, String grades, String subjects, String minimum_salary_requirement,
      String about, String username, String password) {
    Tutor tutor = (Tutor) model.getAttribute("tutor");

    if (tutor == null) {
      return "redirect:/";
    }

    tutor.setFullname(new FullName(first_name, last_name));
    tutor.setGender(gender);
    tutor.setDate_of_birth(new Birth(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year)));
    tutor.setAddress(new Address(province_city, ward_district, home_number));

    List<String> new_emails = new ArrayList<>();
    new_emails.add(emails);
    tutor.setEmails(new_emails);

    List<String> new_phones = new ArrayList<>();
    new_phones.add(phones);
    tutor.setPhones(new_phones);

    tutor.setJob(job);
    tutor.setGraduated_school(graduated_school);
    tutor.setMajor(major);
    tutor.setQualification(qualification);
    tutor.setGraduated_year(Integer.parseInt(graduated_year));

    List<String> new_grades = new ArrayList<>();
    new_grades.add(grades);
    tutor.setGrades(new_grades);

    List<String> new_subjects = new ArrayList<>();
    new_subjects.add(subjects);
    tutor.setSubjects(new_subjects);

    tutor.setMinimum_salary_requirement(Integer.parseInt(minimum_salary_requirement));
    tutor.setAbout(about);

    TutorAuthentication tutorAuthentication = tutorAuthenticationRepository.getAuthByTutorID(tutor.getTutor_id());

    tutorAuthentication.setUsername(username);
    tutorAuthentication.setPassword(password);

    tutorRepository.save(tutor);
    tutorAuthenticationRepository.save(tutorAuthentication);
    model.addAttribute("tutor", tutor);

    return "redirect:/tutor-profile";
  }

  public String getTutorOnMainSearch(String city, String subject, String grade, Model model) {
    model.addAttribute("tutor_search", customTutorRepo.getTutorOnMainSearch(city, subject, grade));
    return "search.html";
  }

  public List<Tutor> getTutorOnMainSearch2(String city, String subject, String grade, String district) {
    return customTutorRepo.getTutorOnMainSearch2(city, subject, grade, district);
  }

  public List<Tutor> getTutorOnPopularity() {
    return customTutorRepo.getTutorOnPopularity();
  }

  public String getTutorOnText(String name, Model model) {
    model.addAttribute("tutor_search", customTutorRepo.getTutorOnText(name));
    return "search.html";
  }

  public String getTutorOnSubject(String subject, Model model) {
    model.addAttribute("tutor_search", customTutorRepo.getTutorOnSubject(subject));
    return "search.html";
  }

}
