package com.juandavyc.university.repositories;

import com.juandavyc.university.dtos.document.response.DocumentTypeResponseDTO;
import com.juandavyc.university.entities.DocumentTypeEntity;
import com.juandavyc.university.specifications.DocumentTypeSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;


import java.util.List;

public interface DocumentRepository
        extends JpaRepository<DocumentTypeEntity, Long>,
        JpaSpecificationExecutor<DocumentTypeEntity> {

    @NonNull
    @Override
    Page<DocumentTypeEntity> findAll(@NonNull Pageable pageable);

    @NonNull
    @Override
    Page<DocumentTypeEntity> findAll(Specification<DocumentTypeEntity> specifications, @NonNull Pageable pageable);

    Boolean existsByName(String name);

   // List<DocumentTypeEntity> findByNameStartsWith(String name);


}
