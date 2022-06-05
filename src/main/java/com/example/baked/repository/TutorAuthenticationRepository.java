package com.example.baked.repository;

import com.example.baked.model.TutorAuthentication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TutorAuthenticationRepository extends MongoRepository<TutorAuthentication, String> {
    @Query(value = "{'username': ?0}")
    public TutorAuthentication getAuthByUsername(String username);

    @Query(value = "{'tutor_id': ?0}")
    public TutorAuthentication getAuthByTutorID(String id);

    @Query(value="{'tutor_id': ?0}", delete = true)
    public TutorAuthentication deleteAuthByTutorID(String id);

    @Query(value="{'username': ?0}", delete = true)
    public TutorAuthentication deleteAuthByUsername(String username);
}
