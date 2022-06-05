package com.example.baked.service;

import java.util.*;

import com.example.baked.model.Class;
import com.example.baked.model.Period;
import com.example.baked.model.Tutor;
import com.example.baked.repository.ClassRepository;
import com.example.baked.repository.PeriodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private PeriodRepository periodRepository;

    public ClassService() {
    }

    public ClassRepository getClassRepository() {
        return this.classRepository;
    }

    public void setClassRepository(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public List<Class> getAllTeachingClasses(Model model) {
        Tutor tutor = (Tutor) model.getAttribute("tutor");
        return classRepository.getClassByTutorID(tutor.getTutor_id());
    }

    public String getGlobalClassDetail(Model model, String class_id) {
        model.addAttribute("class", classRepository.getClassByClassID(class_id));

        Class current_class = classRepository.getClassByClassID(class_id);

        List<Period> periods = new ArrayList<>();

        for (String p: current_class.getPeriods()) {
            periods.add(periodRepository.getPeriodByPeriodID(p));
        }

        model.addAttribute("periods", periods.toString().replaceAll("(^\\[|\\]$)", ""));

        return "global-class-detail.html";
    }

    
}
