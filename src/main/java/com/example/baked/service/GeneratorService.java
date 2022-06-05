package com.example.baked.service;

import java.util.*;

import com.example.baked.model.Class;
import com.example.baked.model.Period;
import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Student;
import com.example.baked.model.Tutor;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.PeriodRepository;
import com.example.baked.repository.RequestFromStudentRepository;
import com.example.baked.repository.StudentRepository;
import com.example.baked.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratorService {

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RequestFromStudentRepository requestFromStudentRepository;

    public List<String> getStudentIdList() {
        List<Student> students = studentRepository.findAll();
        List<String> studentIdList = new ArrayList<>();

        for (Student s : students) {
            studentIdList.add(s.getId());
        }

        return studentIdList;
    }

    public List<String> getTutorIdList() {
        List<Tutor> tutors = tutorRepository.findAll();
        List<String> tutorIdList = new ArrayList<>();

        for (Tutor s : tutors) {
            tutorIdList.add(s.getId());
        }

        return tutorIdList;
    }

    public List<String> getClassIdList() {
        List<Class> classes = classRepository.findAll();
        List<String> classIdList = new ArrayList<>();

        for (Class s : classes) {
            classIdList.add(s.getId());
        }

        return classIdList;
    }

    public List<String> getPeriodIdList() {
        List<Period> periods = periodRepository.findAll();
        List<String> periodIdList = new ArrayList<>();

        for (Period s : periods) {
            periodIdList.add(s.getId());
        }

        return periodIdList;
    }

    public List<String> getRequestFromStudentIdList() {
        List<RequestFromStudent> requestFromStudents = requestFromStudentRepository.findAll();
        List<String> requestFromStudentList = new ArrayList<>();

        for (RequestFromStudent s : requestFromStudents) {
            requestFromStudentList.add(s.getId());
        }

        return requestFromStudentList;
    }

    /*public List<String> getRequestFromTutorIdList() {
        List<RequestFromTutor> requestFromTutors = tutorRequestRepo.findAll();
        List<String> requestFromTutorList = new ArrayList<>();

        for (RequestFromTutor s : requestFromTutors) {
            requestFromTutorList.add(s.getId());
        }

        return requestFromTutorList;
    }*/

    public String idGenerator() {
        Random random = new Random();

        int min1 = 48;
        int max1 = 57;
        int min2 = 97;
        int max2 = 122;

        int length = 5;

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            boolean is_number = random.nextBoolean();
            if (is_number) {
                char c = (char) (random.nextInt(max1 - min1 + 1) + min1);
                str.append(c);
            } else {
                char c = (char) (random.nextInt(max2 - min2 + 1) + min2);
                str.append(c);
            }
        }

        return str.toString();
    }

    /////////////////// Generate id ////////////////////////

    public String generateStudentId() {
        String id = "S";
        List<String> studentIdList = getStudentIdList();
        while (true) {
            id += idGenerator();
            for (String exist_id : studentIdList) {
                if (exist_id.equals(id)) {
                    continue;
                }
            }
            break;
        }
        return id;
    }

    public String generateTutorId() {
        String id = "T";
        List<String> tutorIdList = getTutorIdList();
        while (true) {
            id += idGenerator();
            for (String exist_id : tutorIdList) {
                if (exist_id.equals(id)) {
                    continue;
                }
            }
            break;
        }
        return id;
    }

    public String generateClassId() {
        String id = "C";
        List<String> classIdList = getClassIdList();
        while (true) {
            id += idGenerator();
            for (String exist_id : classIdList) {
                if (exist_id.equals(id)) {
                    continue;
                }
            }
            break;
        }
        return id;
    }

    public String generateClassIdForRequest() {
        String id = "C+";
        List<String> classIdList = getClassIdList();
        while (true) {
            id += idGenerator();
            for (String exist_id : classIdList) {
                if (exist_id.equals(id)) {
                    continue;
                }
            }
            break;
        }
        return id;
    }

    public String generatePeriodId() {
        String id = "P";
        List<String> periodIdList = getPeriodIdList();
        while (true) {
            id += idGenerator();
            for (String exist_id : periodIdList) {
                if (exist_id.equals(id)) {
                    continue;
                }
            }
            break;
        }
        return id;
    }

    public String generateRequestFromStudentId() {
        String id = "RS";
        List<String> requestFromStudentIdList = getRequestFromStudentIdList();
        while (true) {
            id += idGenerator();
            for (String exist_id : requestFromStudentIdList) {
                if (exist_id.equals(id)) {
                    continue;
                }
            }
            break;
        }
        return id;
    }

    /*public String generateRequestFromTutorId() {
        String id = "RT";
        List<String> requestFromTutorIdList = getRequestFromTutorIdList();
        while (true) {
            id += idGenerator();
            for (String exist_id : requestFromTutorIdList) {
                if (exist_id.equals(id)) {
                    continue;
                }
            }
            break;
        }
        return id;
    }*/
}
