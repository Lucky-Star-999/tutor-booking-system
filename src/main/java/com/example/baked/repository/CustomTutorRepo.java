package com.example.baked.repository;

import java.util.List;

import com.example.baked.model.Tutor;

public interface CustomTutorRepo {
    //List<Tutor> getTutorOnFeatures(String major, String qualification, String subject);
    //List<Tutor> getTutorOnQualification(String qualification);
    List<Tutor> getTutorOnPopularity();
    List<Tutor> getTutorOnMainSearch(String city, String subject, String grade);
    List<Tutor> getTutorOnMainSearch2(String city, String subject, String grade, String district);
    List<Tutor> getTutorOnText(String name);
    List<Tutor> getTutorOnSubject(String subject);
}
