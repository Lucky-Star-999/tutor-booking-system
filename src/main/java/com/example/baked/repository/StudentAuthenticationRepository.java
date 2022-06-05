package com.example.baked.repository;

import com.example.baked.model.StudentAuthentication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentAuthenticationRepository extends MongoRepository<StudentAuthentication, String>{
    @Query(value = "{'username': ?0}")
    public StudentAuthentication getAuthByUsername(String username);

    @Query(value = "{'student_id': ?0}")
    public StudentAuthentication getAuthByStudentID(String id);

    @Query(value="{'student_id': ?0}", delete = true)
    public StudentAuthentication deleteAuthByStudentID(String id);

    @Query(value="{'username': ?0}", delete = true)
    public StudentAuthentication deleteAuthByUsername(String username);
}
