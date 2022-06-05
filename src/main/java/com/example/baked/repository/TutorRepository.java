package com.example.baked.repository;

import com.example.baked.model.Tutor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TutorRepository extends MongoRepository<Tutor, String>{
    @Query(value = "{'tutor_id': ?0}")
    public Tutor getTutorByTutorID(String id);

    @Query(value = "{'username': ?0}")
    public Tutor getTutorByUsername(String username);

    /*@Query(value = "{'tutor_id': ?0}", fields = "{'tutor_id': 1, 'username': 1}")
    public Tutor getTutorByTutorID(String id);*/

    @Query(value="{'tutor_id': ?0}", delete = true)
    public Tutor deleteTutorByTutorID(String id);
}
