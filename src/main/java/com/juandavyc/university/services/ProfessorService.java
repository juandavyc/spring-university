package com.juandavyc.university.services;

import com.juandavyc.university.entities.ProfessorEntity;

public interface ProfessorService {

    ProfessorEntity create(ProfessorEntity professor, Long personId);
    ProfessorEntity findById(Long id);

    ProfessorEntity create(ProfessorEntity professor);
}
