package com.juandavyc.university.repositories;

import com.juandavyc.university.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentTypeEntity, Long> {

    List<DocumentTypeEntity> findByNameStartsWith(String name);
}
