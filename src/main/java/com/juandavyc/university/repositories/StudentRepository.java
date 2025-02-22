package com.juandavyc.university.repositories;

import com.juandavyc.university.entities.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

public interface StudentRepository
        extends JpaRepository<StudentEntity, Long>,
        JpaSpecificationExecutor<StudentEntity> {

        @NonNull
        Page<StudentEntity> findAll(
                Specification<StudentEntity> specification,
                @NonNull Pageable pageable
        );




}
