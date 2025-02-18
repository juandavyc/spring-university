package com.juandavyc.university.services;


import com.juandavyc.university.dtos.person.response.PersonWithDocumentTypeResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PersonService {


    Page<PersonWithDocumentTypeResponseDTO> findAll(Pageable pageable);

    Page<PersonWithDocumentTypeResponseDTO> findByFilters(
            Long id,
            String name,
            String document,
            LocalDate createdAt,
            LocalDate updatedAt,
            Long documentTypeId,
            String documentTypeName,
            Pageable pageable
    );


    // test
//    PersonEntity create(PersonEntity person);
//    List<PersonEntity> createAll(List<PersonEntity> persons);
//    PersonEntity update(PersonEntity person, Long id);
//
//    List<PersonEntity> findAll();
//
//    PersonEntity findById(Long id);

    // PersonEntity findByProfessorId(Long id) ;

}
