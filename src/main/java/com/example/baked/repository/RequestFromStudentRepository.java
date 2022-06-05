package com.example.baked.repository;

import java.util.List;

import com.example.baked.model.RequestFromStudent;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RequestFromStudentRepository extends MongoRepository<RequestFromStudent, String>{
    @Query(value = "{'tutor_id': ?0}")
    public List<RequestFromStudent> getRequestByTutorID(String id);

    @Query(value = "{'student_id': ?0}")
    public List<RequestFromStudent> getRequestByStudentID(String id);

    @Query(value = "{'request_id': ?0}")
    public RequestFromStudent getRequestByRequestID(String id);

    @Query(value="{'request_id': ?0}", delete = true)
    public RequestFromStudent deleteRequestByRequestID(String id);
}


