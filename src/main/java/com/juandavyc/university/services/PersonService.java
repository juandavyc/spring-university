package com.juandavyc.university.services;

import com.juandavyc.university.entities.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    PersonEntity create(PersonEntity person);
    List<PersonEntity> createAll(List<PersonEntity> persons);
    PersonEntity update(PersonEntity person, Long id);

    List<PersonEntity> findAll();

    PersonEntity findById(Long id);

   // PersonEntity findByProfessorId(Long id) ;

}
