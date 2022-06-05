package com.example.baked.repository;

import com.example.baked.model.Student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentRepository extends MongoRepository<Student, String>{
    @Query(value = "{'student_id': ?0}")
    public Student getStudentByStudentID(String id);

    @Query(value = "{'username': ?0}")
    public Student getStudentByUsername(String username);

    /*@Query(value = "{'tutor_id': ?0}", fields = "{'tutor_id': 1, 'username': 1}")
    public Tutor getTutorByTutorID(String id);*/

    @Query(value="{'student_id': ?0}", delete = true)
    public Student deleteStudentByStudentID(String id);

}
