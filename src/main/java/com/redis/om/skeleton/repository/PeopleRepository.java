package com.redis.om.skeleton.repository;

import com.redis.om.skeleton.models.Person;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends RedisDocumentRepository<Person, String> {
    Person findByFirstNameAndLastName(String firstName, String lastName);
}
