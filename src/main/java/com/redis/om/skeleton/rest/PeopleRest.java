package com.redis.om.skeleton.rest;

import com.redis.om.skeleton.models.Person;
import com.redis.om.skeleton.models.Person$;
import com.redis.om.skeleton.repository.PeopleRepository;
import com.redis.om.spring.search.stream.EntityStream;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/people")
public class PeopleRest {

    PeopleRepository repository;
    EntityStream entityStream;

    @GetMapping
    public Iterable<Person> all(){
        return repository.findAll();
//        return entityStream.of(Person.class).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable String id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Objeto não existente"));
    }

    @GetMapping("/name")
    public Iterable<Person> findByFirstNameAndLastName(@RequestParam String first, @RequestParam String last){
//        return repository.findByFirstNameAndLastName(first, last);
        return entityStream
                .of(Person.class)
                .filter(Person$.FIRST_NAME.eq(first))
                .filter(Person$.LAST_NAME.eq(last))
                .collect(Collectors.toList());
    }



}
