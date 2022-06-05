package com.example.baked.repository;

import java.util.List;

import com.example.baked.model.Period;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PeriodRepository extends MongoRepository<Period, String>{
    @Query(value = "{'tutor_id': ?0}")
    public List<Period> getPeriodByTutorID(String id);

    @Query(value = "{'period_id': ?0}")
    public Period getPeriodByPeriodID(String id);

    @Query(value="{'period_id': ?0}", delete = true)
    public Period deletePeriodByPeriodID(String id);
}
