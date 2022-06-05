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
import com.example.baked.model.TutorAuthentication;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.CustomTutorRepo;
import com.example.baked.repository.PeriodRepository;
import com.example.baked.repository.RequestFromStudentRepository;
import com.example.baked.repository.StudentAuthenticationRepository;
import com.example.baked.repository.StudentRepository;
import com.example.baked.repository.TutorAuthenticationRepository;
import com.example.baked.repository.TutorRepository;
import com.example.baked.requestBody.ClassAddRequestBody;
import com.example.baked.requestBody.PeriodAddRequestBody;
import com.example.baked.requestBody.RequestFromStudentAddRequestBody;
import com.example.baked.requestBody.StudentAddRequestBody;
import com.example.baked.requestBody.StudentAuthenticationAddRequestBody;
import com.example.baked.requestBody.TutorAddRequestBody;
import com.example.baked.requestBody.TutorAuthenticationAddRequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAPIService {
  /////////////////// Import repositories //////////////
  @Autowired
  private TutorRepository tutorRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private TutorAuthenticationRepository tutorAuthenticationRepository;

  @Autowired
  private StudentAuthenticationRepository studentAuthenticationRepository;

  @Autowired
  private ClassRepository classRepository;

  @Autowired
  private PeriodRepository periodRepository;

  @Autowired
  private RequestFromStudentRepository requestFromStudentRepository;

  /////////////////// Import services //////////////
  @Autowired
  private GeneratorService generatorService = new GeneratorService();

  @Autowired
  private CustomTutorRepo customTutorRepo;

  /////////////////// API Service (Read) //////////////
  // Read all
  public List<Tutor> apiGetAllTutors() {
    return tutorRepository.findAll();
  }

  public List<Student> apiGetAllStudents() {
    return studentRepository.findAll();
  }

  public List<TutorAuthentication> apiGetAllTutorsAuthentication() {
    return tutorAuthenticationRepository.findAll();
  }

  public List<StudentAuthentication> apiGetAllStudentsAuthentication() {
    return studentAuthenticationRepository.findAll();
  }

  public List<Class> apiGetAllClasses() {
    return classRepository.findAll();
  }

  public List<Period> apiGetAllPeriods() {
    return periodRepository.findAll();
  }

  public List<RequestFromStudent> apiGetAllRequestsFromStudents() {
    return requestFromStudentRepository.findAll();
  }

  // Read by id itself or username (for authentication)
  public Tutor apiGetTutorByTutorId(String tutor_id) {
    return tutorRepository.getTutorByTutorID(tutor_id);
  }

  public Student apiGetStudentByStudentId(String student_id) {
    return studentRepository.getStudentByStudentID(student_id);
  }

  public TutorAuthentication apiGetTutorAuthenticationByTutorId(String tutor_id) {
    return tutorAuthenticationRepository.getAuthByTutorID(tutor_id);
  }

  public TutorAuthentication apiGetTutorAuthenticationByUsername(String username) {
    return tutorAuthenticationRepository.getAuthByUsername(username);
  }

  public StudentAuthentication apiGetStudentAuthenticationByStudentId(String student_id) {
    return studentAuthenticationRepository.getAuthByStudentID(student_id);
  }

  public StudentAuthentication apiGetStudentAuthenticationByUsername(String username) {
    return studentAuthenticationRepository.getAuthByUsername(username);
  }

  public Class apiGetClassByClassId(String class_id) {
    return classRepository.getClassByClassID(class_id);
  }

  public Period apiGetPeriodByPeriodId(String period_id) {
    return periodRepository.getPeriodByPeriodID(period_id);
  }

  public RequestFromStudent apiGetRequestFromStudentByRequestFromStudentId(String request_id) {
    return requestFromStudentRepository.getRequestByRequestID(request_id);
  }

  public List<Tutor> filterForTutors(String tutor_id, String fullname, String province_city, String ward_district) {
    List<Tutor> all_tutors = apiGetAllTutors();
    List<Tutor> tutors_filtered_by_tutor_id = new ArrayList<>();
    List<Tutor> tutors_filtered_by_fullname = new ArrayList<>();
    List<Tutor> tutors_filtered_by_province_city = new ArrayList<>();
    List<Tutor> tutors_filtered_by_ward_district = new ArrayList<>();

    for (Tutor t : all_tutors) {
      if (t.getTutor_id().toLowerCase().contains(tutor_id.toLowerCase())) {
        tutors_filtered_by_tutor_id.add(t);
      }

      if (t.getFullname().toString().toLowerCase().contains(fullname.toLowerCase())) {
        tutors_filtered_by_fullname.add(t);
      }

      if (t.getAddress().getProvince_city().toLowerCase().contains(province_city.toLowerCase())) {
        tutors_filtered_by_province_city.add(t);
      }

      if (t.getAddress().getWard_district().toLowerCase().contains(ward_district.toLowerCase())) {
        tutors_filtered_by_ward_district.add(t);
      }
    }

    // Get intersection of all filters
    all_tutors.retainAll(tutors_filtered_by_tutor_id);
    all_tutors.retainAll(tutors_filtered_by_fullname);
    all_tutors.retainAll(tutors_filtered_by_province_city);
    all_tutors.retainAll(tutors_filtered_by_ward_district);

    // Remove duplicate
    Set<Tutor> set = new LinkedHashSet<>();
    set.addAll(all_tutors);
    all_tutors.clear();
    all_tutors.addAll(set);

    // Retrive the results
    List<Tutor> results = all_tutors;

    return results;
  }

  public List<Student> filterForStudents(String student_id, String fullname, String province_city,
      String ward_district) {
    List<Student> all_students = apiGetAllStudents();
    List<Student> students_filtered_by_student_id = new ArrayList<>();
    List<Student> students_filtered_by_fullname = new ArrayList<>();
    List<Student> students_filtered_by_province_city = new ArrayList<>();
    List<Student> students_filtered_by_ward_district = new ArrayList<>();

    for (Student s : all_students) {
      if (s.getStudent_id().toLowerCase().contains(student_id.toLowerCase())) {
        students_filtered_by_student_id.add(s);
      }

      if (s.getFullname().toString().toLowerCase().contains(fullname.toLowerCase())) {
        students_filtered_by_fullname.add(s);
      }

      if (s.getAddress().getProvince_city().toLowerCase().contains(province_city.toLowerCase())) {
        students_filtered_by_province_city.add(s);
      }

      if (s.getAddress().getWard_district().toLowerCase().contains(ward_district.toLowerCase())) {
        students_filtered_by_ward_district.add(s);
      }
    }

    // Get intersection of all filters
    all_students.retainAll(students_filtered_by_student_id);
    all_students.retainAll(students_filtered_by_fullname);
    all_students.retainAll(students_filtered_by_province_city);
    all_students.retainAll(students_filtered_by_ward_district);

    // Remove duplicate
    Set<Student> set = new LinkedHashSet<>();
    set.addAll(all_students);
    all_students.clear();
    all_students.addAll(set);

    // Retrive the results
    List<Student> results = all_students;

    return results;
  }

  public List<TutorAuthentication> filterForTutorsAuthentication(String tutor_id, String username) {
    List<TutorAuthentication> all_tutors = apiGetAllTutorsAuthentication();
    List<TutorAuthentication> tutors_filtered_by_tutor_id = new ArrayList<>();
    List<TutorAuthentication> tutors_filtered_by_username = new ArrayList<>();

    for (TutorAuthentication t : all_tutors) {
      if (t.getTutor_id().toLowerCase().contains(tutor_id.toLowerCase())) {
        tutors_filtered_by_tutor_id.add(t);
      }

      if (t.getUsername().toLowerCase().contains(username.toLowerCase())) {
        tutors_filtered_by_username.add(t);
      }

    }

    // Get intersection of all filters
    all_tutors.retainAll(tutors_filtered_by_tutor_id);
    all_tutors.retainAll(tutors_filtered_by_username);

    // Remove duplicate
    Set<TutorAuthentication> set = new LinkedHashSet<>();
    set.addAll(all_tutors);
    all_tutors.clear();
    all_tutors.addAll(set);

    // Retrive the results
    List<TutorAuthentication> results = all_tutors;

    return results;
  }

  public List<StudentAuthentication> filterForStudentsAuthentication(String student_id, String username) {
    List<StudentAuthentication> all_students = apiGetAllStudentsAuthentication();
    List<StudentAuthentication> students_filtered_by_student_id = new ArrayList<>();
    List<StudentAuthentication> students_filtered_by_username = new ArrayList<>();

    for (StudentAuthentication s : all_students) {
      if (s.getStudent_id().toLowerCase().contains(student_id.toLowerCase())) {
        students_filtered_by_student_id.add(s);
      }

      if (s.getUsername().toString().toLowerCase().contains(username.toLowerCase())) {
        students_filtered_by_username.add(s);
      }
    }

    // Get intersection of all filters
    all_students.retainAll(students_filtered_by_student_id);
    all_students.retainAll(students_filtered_by_username);

    // Remove duplicate
    Set<StudentAuthentication> set = new LinkedHashSet<>();
    set.addAll(all_students);
    all_students.clear();
    all_students.addAll(set);

    // Retrive the results
    List<StudentAuthentication> results = all_students;

    return results;
  }

  public List<Class> filterForClasses(String class_id, String tutor_id, String student_id) {
    List<Class> all_classes = apiGetAllClasses();
    List<Class> classes_filtered_by_class_id = new ArrayList<>();
    List<Class> classes_filtered_by_tutor_id = new ArrayList<>();
    List<Class> classes_filtered_by_student_id = new ArrayList<>();

    for (Class t : all_classes) {
      if (t.getClass_id().toLowerCase().contains(class_id.toLowerCase())) {
        classes_filtered_by_class_id.add(t);
      }

      if (t.getTutor_id().toString().toLowerCase().contains(tutor_id.toLowerCase())) {
        classes_filtered_by_tutor_id.add(t);
      }

      if (t.getStudent_id().toLowerCase().contains(student_id.toLowerCase())) {
        classes_filtered_by_student_id.add(t);
      }
    }

    // Get intersection of all filters
    all_classes.retainAll(classes_filtered_by_class_id);
    all_classes.retainAll(classes_filtered_by_tutor_id);
    all_classes.retainAll(classes_filtered_by_student_id);

    // Remove duplicate
    Set<Class> set = new LinkedHashSet<>();
    set.addAll(all_classes);
    all_classes.clear();
    all_classes.addAll(set);

    // Retrive the results
    List<Class> results = all_classes;

    return results;
  }

  public List<RequestFromStudent> filterForRequestsFromStudents(String request_id, String tutor_id,
      String student_id) {
    List<RequestFromStudent> all_requests = apiGetAllRequestsFromStudents();
    List<RequestFromStudent> requests_filtered_by_class_id = new ArrayList<>();
    List<RequestFromStudent> requests_filtered_by_tutor_id = new ArrayList<>();
    List<RequestFromStudent> requests_filtered_by_student_id = new ArrayList<>();

    for (RequestFromStudent t : all_requests) {
      if (t.getRequest_id().toLowerCase().contains(request_id.toLowerCase())) {
        requests_filtered_by_class_id.add(t);
      }

      if (t.getTutor_id().toString().toLowerCase().contains(tutor_id.toLowerCase())) {
        requests_filtered_by_tutor_id.add(t);
      }

      if (t.getStudent_id().toLowerCase().contains(student_id.toLowerCase())) {
        requests_filtered_by_student_id.add(t);
      }
    }

    // Get intersection of all filters
    all_requests.retainAll(requests_filtered_by_class_id);
    all_requests.retainAll(requests_filtered_by_tutor_id);
    all_requests.retainAll(requests_filtered_by_student_id);

    // Remove duplicate
    Set<RequestFromStudent> set = new LinkedHashSet<>();
    set.addAll(all_requests);
    all_requests.clear();
    all_requests.addAll(set);

    // Retrive the results
    List<RequestFromStudent> results = all_requests;

    return results;
  }

  public List<Period> filterForPeriods(String period_id, String start_time, String end_time, String day) {
    List<Period> all_periods = apiGetAllPeriods();
    List<Period> periods_filtered_by_period_id = new ArrayList<>();
    List<Period> periods_filtered_by_start_time = new ArrayList<>();
    List<Period> periods_filtered_by_end_time = new ArrayList<>();
    List<Period> periods_filtered_by_day = new ArrayList<>();

    int start_time_int = Integer.parseInt(start_time);
    int end_time_int = Integer.parseInt(end_time);
    int day_int = Integer.parseInt(day);

    for (Period t : all_periods) {
      if (t.getPeriod_id().toLowerCase().contains(period_id.toLowerCase())) {
        periods_filtered_by_period_id.add(t);
      }

      if (t.getStart_time() == start_time_int) {
        periods_filtered_by_start_time.add(t);
      }

      if (t.getEnd_time() == end_time_int) {
        periods_filtered_by_end_time.add(t);
      }

      if (t.getDay() == day_int) {
        periods_filtered_by_day.add(t);
      }
    }

    // Get intersection of all filters
    all_periods.retainAll(periods_filtered_by_period_id);
    all_periods.retainAll(periods_filtered_by_start_time);
    all_periods.retainAll(periods_filtered_by_end_time);
    all_periods.retainAll(periods_filtered_by_day);

    // Remove duplicate
    Set<Period> set = new LinkedHashSet<>();
    set.addAll(all_periods);
    all_periods.clear();
    all_periods.addAll(set);

    // Retrive the results
    List<Period> results = all_periods;

    return results;
  }

  public List<Tutor> getTutorOnMainSearch(String city, String subject, String grade) {
    return customTutorRepo.getTutorOnMainSearch(city, subject, grade);
  }

  public List<Tutor> getTutorOnMainSearch2(String city, String subject, String grade, String district) {
    return customTutorRepo.getTutorOnMainSearch2(city, subject, grade, district);
  }

  public List<Tutor> getTutorOnPopularity() {
    return customTutorRepo.getTutorOnPopularity();
  }

  // Delete by id itself or username (for Authentication)
  public String apiDeleteTutorByTutorId(String tutor_id) {
    if (tutorRepository.deleteTutorByTutorID(tutor_id) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  public String apiDeleteStudentByStudentId(String student_id) {
    if (studentRepository.deleteStudentByStudentID(student_id) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  public String apiDeleteTutorAuthenticationByTutorId(String tutor_id) {
    if (tutorAuthenticationRepository.deleteAuthByTutorID(tutor_id) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  public String apiDeleteTutorAuthenticationByUsername(String username) {
    if (tutorAuthenticationRepository.deleteAuthByUsername(username) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  public String apiDeleteStudentAuthenticationByStudentId(String student_id) {
    if (studentAuthenticationRepository.deleteAuthByStudentID(student_id) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  public String apiDeleteStudentAuthenticationByUsername(String username) {
    if (studentAuthenticationRepository.deleteAuthByUsername(username) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  public String apiDeleteClassByClassId(String class_id) {
    if (classRepository.deleteClassByClassID(class_id) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  public String apiDeletePeriodByPeriodId(String period_id) {
    if (periodRepository.deletePeriodByPeriodID(period_id) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  public String apiDeleteRequestFromStudentByRequestFromStudentId(String request_id) {
    if (requestFromStudentRepository.deleteRequestByRequestID(request_id) == null) {
      return "Error!";
    } else {
      return "OK";
    }
  }

  /////////////////// API Service (Create) //////////////
  public Tutor apiAddTutor(TutorAddRequestBody tutor) {
    String tutor_id = generatorService.generateTutorId();
    FullName fullname = new FullName(tutor.first_name, tutor.last_name);
    String gender = tutor.gender;
    Birth date_of_birth = new Birth(Integer.parseInt(tutor.day), Integer.parseInt(tutor.month),
        Integer.parseInt(tutor.year));
    Address address = new Address(tutor.province_city, tutor.ward_district, tutor.home_number);
    List<String> emails = new ArrayList<>();
    emails.add(tutor.email);
    List<String> phones = new ArrayList<>();
    phones.add(tutor.phone);
    String job = tutor.job;
    String graduated_school = tutor.graduated_school;
    String major = tutor.major;
    String qualification = tutor.qualification;
    int graduated_year = Integer.parseInt(tutor.graduated_year);
    List<String> grades = new ArrayList<>();
    grades.add(tutor.grades);
    List<String> subjects = new ArrayList<>();
    subjects.add(tutor.subjects);
    int minimum_salary_requirement = Integer.parseInt(tutor.minimum_salary_requirement);
    String about = tutor.about;
    Tutor new_tutor = new Tutor(tutor_id, fullname, gender, date_of_birth, address, emails, phones, job,
        graduated_school, major, qualification, graduated_year, grades, subjects, minimum_salary_requirement,
        about);
    System.out.println(new_tutor);

    tutorRepository.save(new_tutor);

    return new_tutor;
  }

  public Student apiAddStudent(StudentAddRequestBody student) {
    String student_id = generatorService.generateStudentId();
    FullName fullname = new FullName(student.first_name, student.last_name);
    FullName parent_fullname = new FullName(student.parent_first_name, student.parent_last_name);
    String gender = student.gender;
    Birth date_of_birth = new Birth(Integer.parseInt(student.day), Integer.parseInt(student.month),
        Integer.parseInt(student.year));
    Address address = new Address(student.province_city, student.ward_district, student.home_number);
    List<String> emails = new ArrayList<>();
    emails.add(student.email);
    List<String> phones = new ArrayList<>();
    phones.add(student.phone);
    String about = student.about;

    Student new_student = new Student(student_id, fullname, gender, date_of_birth, address, parent_fullname, emails,
        phones, about);
    System.out.println(new_student);

    studentRepository.save(new_student);

    return new_student;
  }

  public TutorAuthentication apiAddTutorAuthentication(
      TutorAuthenticationAddRequestBody tutorAuthenticationAddRequestBody) {
    String tutor_id = tutorAuthenticationAddRequestBody.tutor_id;
    String username = tutorAuthenticationAddRequestBody.username;
    String password = tutorAuthenticationAddRequestBody.password;

    TutorAuthentication new_tutorAuthenTication = new TutorAuthentication(tutor_id, username, password);

    System.out.println(new_tutorAuthenTication);

    tutorAuthenticationRepository.save(new_tutorAuthenTication);

    return new_tutorAuthenTication;
  }

  public StudentAuthentication apiAddStudentAuthentication(
      StudentAuthenticationAddRequestBody studentAuthenticationAddRequestBody) {
    String student_id = studentAuthenticationAddRequestBody.student_id;
    String username = studentAuthenticationAddRequestBody.username;
    String password = studentAuthenticationAddRequestBody.password;

    StudentAuthentication new_studentAuthenTication = new StudentAuthentication(student_id, username, password);

    System.out.println(new_studentAuthenTication);

    studentAuthenticationRepository.save(new_studentAuthenTication);

    return new_studentAuthenTication;
  }

  public Class apiAddClass(ClassAddRequestBody classAddRequestBody) {

    String class_id = generatorService.generateClassId();
    String tutor_id = classAddRequestBody.tutor_id;
    String student_id = classAddRequestBody.student_id;
    String grade = classAddRequestBody.grade;
    List<String> subjects = new ArrayList<>();
    subjects.add(classAddRequestBody.subjects);
    Address address = new Address(classAddRequestBody.province_city, classAddRequestBody.ward_district,
        classAddRequestBody.home_number);
    int salary = Integer.parseInt(classAddRequestBody.salary);
    String requirement = classAddRequestBody.requirement;
    List<String> periods = new ArrayList<>();

    Class new_class = new Class(class_id, tutor_id, student_id,
        grade, subjects, address,
        salary, requirement, periods);

    System.out.println(new_class);

    classRepository.save(new_class);

    return new_class;
  }

  public RequestFromStudent apiAddRequestFromStudent(
      RequestFromStudentAddRequestBody requestFromStudentAddRequestBody) {
    String request_id = generatorService.generateRequestFromStudentId();
    String tutor_id = requestFromStudentAddRequestBody.tutor_id;
    String student_id = requestFromStudentAddRequestBody.student_id;
    String grade = requestFromStudentAddRequestBody.grade;
    List<String> subjects = new ArrayList<>();
    subjects.add(requestFromStudentAddRequestBody.subjects);
    Address address = new Address(requestFromStudentAddRequestBody.province_city,
        requestFromStudentAddRequestBody.ward_district,
        requestFromStudentAddRequestBody.home_number);
    int salary = Integer.parseInt(requestFromStudentAddRequestBody.salary);
    String requirement = requestFromStudentAddRequestBody.requirement;
    List<String> periods = new ArrayList<>();

    RequestFromStudent new_request = new RequestFromStudent(request_id, tutor_id, student_id,
        grade, subjects, address,
        salary, requirement, periods);

    System.out.println(new_request);

    requestFromStudentRepository.save(new_request);

    return new_request;
  }

  public Period apiAddPeriod(PeriodAddRequestBody periodAddRequestBody) {
    String period_id = generatorService.generatePeriodId();
    String tutor_id = periodAddRequestBody.tutor_id;
    // String student_id = periodAddRequestBody.student_id;
    int start_time = Integer.parseInt(periodAddRequestBody.start_time);
    int end_time = Integer.parseInt(periodAddRequestBody.end_time);
    int day = Integer.parseInt(periodAddRequestBody.day);

    Period new_period = new Period(period_id, tutor_id, start_time, end_time, day);

    System.out.println(new_period);

    periodRepository.save(new_period);

    return new_period;
  }
}
