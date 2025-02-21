package com.juandavyc.university.repositories;

import com.juandavyc.university.entities.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface PersonRepository extends
        JpaRepository<PersonEntity, Long>,
        JpaSpecificationExecutor<PersonEntity> {


    @NonNull
    Page<PersonEntity> findAll(Specification<PersonEntity> specification, @NonNull Pageable pageable);

    Boolean existsByDocument(String document);


   // PersonEntity findByProfessorId(Long id);

}
