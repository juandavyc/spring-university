package com.juandavyc.university.services;

import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import com.juandavyc.university.dtos.person.response.PersonWithDocumentTypeResponseDTO;
import com.juandavyc.university.entities.PersonEntity;
import com.juandavyc.university.mappers.PersonMapper;
import com.juandavyc.university.repositories.PersonRepository;
import com.juandavyc.university.specifications.PersonSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Page<PersonWithDocumentTypeResponseDTO> findAll(Pageable pageable) {
        final var personsEntities = personRepository.findAll(pageable);
        if(personsEntities.isEmpty()) {
            throw new IllegalArgumentException("No persons found");
        }
        return personsEntities.map(personMapper::toPersonWithDocumentTypeResponseDTO);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Page<PersonWithDocumentTypeResponseDTO> findByFilters(
            Long id,
            String name,
            String document,
            LocalDate createdAt,
            LocalDate updatedAt,
            Long documentTypeId,
            String documentTypeName,
            Pageable pageable
    ) {


        Specification<PersonEntity> specification = Specification
                .where(PersonSpecifications.hasId(id))
                .and(PersonSpecifications.hasName(name)) //.nameStartingWith(name))
                .and(PersonSpecifications.hasDocument(document))
                .and(PersonSpecifications.hasCreatedAt(createdAt))
                .and(PersonSpecifications.hasUpdateAt(updatedAt))
                // joins
                .and(PersonSpecifications.hasDocumentTypeId(documentTypeId))
                .and(PersonSpecifications.hasDocumentTypeName(documentTypeName));

        final var personsEntities = personRepository.findAll(specification, pageable);

        if(personsEntities.getContent().isEmpty()) {
            throw new IllegalArgumentException("No persons found");
        }

        return personsEntities.map(personMapper::toPersonWithDocumentTypeResponseDTO);
    }


//
//    @Override
//    public PersonEntity create(PersonEntity personEntity) {
//        return personRepository.save(personEntity);
//    }
//
//    @Override
//    public List<PersonEntity> createAll(List<PersonEntity> persons) {
//        return personRepository.saveAll(persons);
//    }
//
//    @Override
//    public PersonEntity update(PersonEntity personEntity, Long id) {
//
//       final var person = personRepository.findById(id);
//
//       if(person.isPresent()){
//
//           person.get().setName(personEntity.getName());
//           person.get().setDocument(personEntity.getDocument());
//
//           return personRepository.save(person.get());
//       }
//       else{
//           throw new IllegalArgumentException("No persons found");
//       }
//
//    }
//
//   // @Transactional(readOnly = true)
//    @Override
//    public List<PersonEntity> findAll() {
//        final var persons = personRepository.findAll();
//        if (persons.isEmpty()) {
//            throw new IllegalArgumentException("No persons found");
//        } else {
//            return persons;
//        }
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public PersonEntity findById(Long id) {
//        return personRepository
//                .findById(id)
//                .orElseThrow(()-> new IllegalArgumentException("No person found with id: " + id));
//    }

//    @Override
//    public PersonEntity findByProfessorId(Long id) {
//        return personRepository.findByProfessorId(id);
//    }

}
