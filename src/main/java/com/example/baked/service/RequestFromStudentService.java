package com.example.baked.service;

import java.util.*;

import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Student;
import com.example.baked.model.Tutor;
import com.example.baked.repository.RequestFromStudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RequestFromStudentService {
    @Autowired
    private RequestFromStudentRepository requestFromStudentRepository;

    public RequestFromStudentService() {
    }

    public RequestFromStudentRepository getRequestFromStudentRepository() {
        return this.requestFromStudentRepository;
    }

    public void setRequestFromStudentRepository(RequestFromStudentRepository requestFromStudentRepository) {
        this.requestFromStudentRepository = requestFromStudentRepository;
    }

    public List<RequestFromStudent> getRequestsFromStudents(Model model) {
        Tutor tutor = (Tutor) model.getAttribute("tutor");
        return requestFromStudentRepository.getRequestByTutorID(tutor.getTutor_id());
    }

    public List<RequestFromStudent> getRequestsFromStudentsByStudentId(Model model) {
        Student student = (Student) model.getAttribute("student");
        return requestFromStudentRepository.getRequestByStudentID(student.getStudent_id());
    }
    
}
