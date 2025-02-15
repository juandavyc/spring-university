package com.juandavyc.university.services;

import com.juandavyc.university.entities.PersonEntity;
import com.juandavyc.university.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;


    @Override
    public PersonEntity create(PersonEntity personEntity) {
        return personRepository.save(personEntity);
    }

    @Override
    public List<PersonEntity> createAll(List<PersonEntity> persons) {
        return personRepository.saveAll(persons);
    }

    @Override
    public PersonEntity update(PersonEntity personEntity, Long id) {

       final var person = personRepository.findById(id);

       if(person.isPresent()){

           person.get().setName(personEntity.getName());
           person.get().setDocument(personEntity.getDocument());

           return personRepository.save(person.get());
       }
       else{
           throw new IllegalArgumentException("No persons found");
       }

    }

   // @Transactional(readOnly = true)
    @Override
    public List<PersonEntity> findAll() {
        final var persons = personRepository.findAll();
        if (persons.isEmpty()) {
            throw new IllegalArgumentException("No persons found");
        } else {
            return persons;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PersonEntity findById(Long id) {
        return personRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException("No person found with id: " + id));
    }

//    @Override
//    public PersonEntity findByProfessorId(Long id) {
//        return personRepository.findByProfessorId(id);
//    }

}
