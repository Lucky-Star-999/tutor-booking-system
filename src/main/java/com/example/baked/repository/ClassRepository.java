package com.example.baked.repository;

import java.util.List;

import com.example.baked.model.Class;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClassRepository extends MongoRepository<Class, String>{
    @Query(value = "{'class_id': ?0}")
    public Class getClassByClassID(String id);

    @Query(value = "{'student_id': ?0}")
    public List<Class> getClassByStudentID(String id);

    @Query(value = "{'tutor_id': ?0}")
    public List<Class> getClassByTutorID(String id);

    @Query(value="{'class_id': ?0}", delete = true)
    public Class deleteClassByClassID(String id);
}
