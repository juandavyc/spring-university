package com.juandavyc.university.repositories;

import com.juandavyc.university.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {


    //ProfessorEntity findByEmail(String email);
}
