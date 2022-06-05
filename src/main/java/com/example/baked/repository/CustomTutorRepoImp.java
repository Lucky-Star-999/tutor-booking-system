package com.example.baked.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.example.baked.model.RequestFromStudent;
import com.example.baked.model.Tutor;
//import com.mongodb.client.result.UpdateResult;

@Component
public class CustomTutorRepoImp implements CustomTutorRepo{

    @Autowired
	MongoTemplate mongoTemplate;

    /*@Override
    public List<Tutor> getTutorOnFeatures(String major, String qualification, String subject) {
        // TODO Auto-generated method stub
        Query query = new Query();
        if (major != "")
            query.addCriteria(Criteria.where("major").is(major));
        if (qualification != "")
            query.addCriteria(Criteria.where("qualification").is(qualification));
        if (subject != "")
            query.addCriteria(Criteria.where("subjects").in(subject));
        List<Tutor> tutor = mongoTemplate.find(query, Tutor.class, "Tutor");
        return tutor;
    }*/

    /*@Override
    public List<Tutor> getTutorOnQualification(String qualification) {
        // TODO Auto-generated method stub
        Query query = new Query();
        query.addCriteria(Criteria.where("qualification").is(qualification));
        List<Tutor> tutor = mongoTemplate.find(query, Tutor.class, "Tutor");
        return tutor;
    }*/

    @Override
    public List<Tutor> getTutorOnPopularity() {
        // TODO Auto-generated method stub
        Query query = new Query();
        Update update = new Update();
        List<Tutor> tutors = mongoTemplate.findAll(Tutor.class, "Tutor");
        for (Tutor tutor : tutors) {
            update = new Update();
            update.set("requestCount", mongoTemplate.count(new Query(Criteria.where("tutor_id").is(tutor.getTutor_id())), RequestFromStudent.class, "RequestFromStudent"));
            mongoTemplate.findAndModify(new Query(Criteria.where("tutor_id").is(tutor.getTutor_id())), update, Tutor.class, "Tutor");
        }
        query.with(Sort.by(Sort.Direction.DESC, "requestCount"));
        List<Tutor> tutorList = mongoTemplate.find(query, Tutor.class, "Tutor");
        return tutorList;
    }

    @Override
    public List<Tutor> getTutorOnMainSearch(String city, String subject, String grade) {
        // TODO Auto-generated method stub
        Query query = new Query();
        if (!city.equals(""))
            query.addCriteria(Criteria.where("address.province_city").is(city));
        if (!subject.equals(""))
            query.addCriteria(Criteria.where("subjects").in(subject));
        if (!grade.equals("")) {
            query.addCriteria(Criteria.where("grades").in(grade));
        }
        List<Tutor> tutor = mongoTemplate.find(query, Tutor.class, "Tutor");
        return tutor;
    }

    @Override
    public List<Tutor> getTutorOnMainSearch2(String city, String subject, String grade, String district) {
        // TODO Auto-generated method stub
        Query query = new Query();
        if (!city.equals(""))
            query.addCriteria(Criteria.where("address.province_city").is(city));
        if (!subject.equals(""))
            query.addCriteria(Criteria.where("subjects").in(subject));
        if (!grade.equals("")) {
            query.addCriteria(Criteria.where("grades").in(grade));
        }
        if (!district.equals(""))
            query.addCriteria(Criteria.where("address.ward_district").is(district));
        List<Tutor> tutor = mongoTemplate.find(query, Tutor.class, "Tutor");
        return tutor;
    }

    @Override
    public List<Tutor> getTutorOnText(String name) {
        // TODO Auto-generated method stub
        Query query = new Query();
        if (!name.equals("")) {
            List<Criteria> crit = new ArrayList<Criteria>();
            //crit.add(Criteria.where("fullname.first_name").regex(name, "i"));
            //crit.add(Criteria.where("fullname.last_name").regex(name, "i"));
            String[] wordList = name.split(" ");
            for (String word:wordList) {
                crit.add(Criteria.where("fullname.first_name").regex(word, "i"));
                crit.add(Criteria.where("fullname.last_name").regex(word, "i"));
            }
            //crit.add(Criteria.where("fullname.last_name").in(name));
            query.addCriteria(new Criteria().orOperator(crit));
        }
        List<Tutor> tutor = mongoTemplate.find(query, Tutor.class, "Tutor");
        return tutor;
    }

    public List<Tutor> getTutorOnSubject(String subject) {
        Query query = new Query();
        if (!subject.equals(""))
            query.addCriteria(Criteria.where("subjects").in(subject));
        List<Tutor> tutor = mongoTemplate.find(query, Tutor.class, "Tutor");
        return tutor;
    }
    
}
