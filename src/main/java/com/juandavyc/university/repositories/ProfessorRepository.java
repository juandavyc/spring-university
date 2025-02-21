package com.juandavyc.university.repositories;

import com.juandavyc.university.entities.ProfessorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface ProfessorRepository extends
        JpaRepository<ProfessorEntity, Long>,
        JpaSpecificationExecutor<ProfessorEntity>

{


    @NonNull
    Page<ProfessorEntity> findAll(
            Specification<ProfessorEntity> specification,
            @NonNull Pageable pageable
    );
    //ProfessorEntity findByEmail(String email);
}
