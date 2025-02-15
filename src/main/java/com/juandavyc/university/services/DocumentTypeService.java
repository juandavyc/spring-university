package com.juandavyc.university.services;

import com.juandavyc.university.entities.DocumentTypeEntity;

import java.util.List;

public interface DocumentTypeService {



    DocumentTypeEntity findById(Long id);

    List<DocumentTypeEntity> findAll();
    List<DocumentTypeEntity> findByName(String name);


}
