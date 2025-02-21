package com.juandavyc.university.services;


import com.juandavyc.university.dtos.person.request.PersonRequestDTO;
import com.juandavyc.university.dtos.person.request.PersonUpdateDTO;

import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import com.juandavyc.university.entities.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;

public interface PersonService {

    PersonResponseDTO findById(Long id);

    Page<PersonResponseDTO> findAll(Pageable pageable);

    Page<PersonResponseDTO> findByFilters(
            Long id,
            String name,
            String document,
            LocalDate createdAt,
            LocalDate updatedAt,
            Long documentTypeId,
            String documentTypeName,
            Pageable pageable
    );

    PersonEntity create(PersonRequestDTO personRequestDTO);

    PersonResponseDTO update(Long id, PersonUpdateDTO personUpdateDTO);

    void delete(Long id);

    void updateValidation(PersonUpdateDTO personUpdateDTO, PersonEntity personEntity);
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
