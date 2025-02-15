package com.juandavyc.university.repositories;

import com.juandavyc.university.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {


   // PersonEntity findByProfessorId(Long id);

}
