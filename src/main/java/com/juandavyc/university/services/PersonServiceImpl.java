package com.juandavyc.university.services;

import com.juandavyc.university.dtos.person.request.PersonRequestDTO;
import com.juandavyc.university.dtos.person.request.PersonUpdateDTO;
import com.juandavyc.university.dtos.person.response.PersonResponseDTO;
import com.juandavyc.university.entities.PersonEntity;
import com.juandavyc.university.mappers.PersonMapper;
import com.juandavyc.university.mappers.PersonUpdateMapper;
import com.juandavyc.university.repositories.PersonRepository;
import com.juandavyc.university.specifications.PersonSpecifications;
import com.juandavyc.university.validators.PersonValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PersonUpdateMapper personUpdateMapper;
    private final DocumentTypeService documentTypeService;


    private final PersonValidator personValidator;


    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public PersonResponseDTO findById(Long id) {

        final var person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person with id: " + id + ", not found"));

        return personMapper.toPersonResponseDTO(person);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Page<PersonResponseDTO> findAll(Pageable pageable) {

        final var personsEntities = personRepository.findAll(pageable);

        if (personsEntities.isEmpty()) {
            throw new IllegalArgumentException("No persons found");
        }
        return personsEntities.map(personMapper::toPersonResponseDTO);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Page<PersonResponseDTO> findByFilters(
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

        if (personsEntities.getContent().isEmpty()) {
            throw new IllegalArgumentException("No persons found");
        }

        return personsEntities.map(personMapper::toPersonResponseDTO);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = RuntimeException.class
    )
    @Override
    public PersonEntity create(PersonRequestDTO personRequestDTO) {

        personValidator.existsByDocument(personRequestDTO.getDocument());

        try {

            final var personEntity = personMapper.toPersonEntity(personRequestDTO);
            final var documentTypeEntity = documentTypeService.findById(personRequestDTO.getIdDocument());

            personEntity.setDocumentType(documentTypeEntity);

            return personRepository.save(personEntity);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Concurrent: Error creating person: " + e.getMessage());
        }

    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = RuntimeException.class
    )
    @Override
    public PersonResponseDTO update(Long id, PersonUpdateDTO personUpdateDTO) {

        final var personEntity = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person with id: " + id + ", not found"));
        try {

            updateValidation(personUpdateDTO, personEntity);

            personUpdateMapper.toUpdatePersonEntityFromDTO(personUpdateDTO, personEntity);

            return personMapper.toPersonResponseDTO(personEntity);

        } catch (Exception e) {
            throw new RuntimeException("Error while updating person", e);
        }

    }

    public void updateValidation(PersonUpdateDTO personUpdateDTO, PersonEntity personEntity) {

        personValidator.validateDocumentUniqueness(
                personUpdateDTO.getDocument(),
                personEntity.getDocument()
        );

        if (personValidator
                .isDifferentIdDocument(
                        personUpdateDTO.getIdDocument(),
                        personEntity.getDocumentType().getId())
        ) {
            final var documentTypeEntity = documentTypeService.findById(personUpdateDTO.getIdDocument());
            personEntity.setDocumentType(documentTypeEntity);
        }

    }

    @Override
    public void delete(Long id) {

        if (id == null || !personRepository.existsById(id)) {
            throw new IllegalArgumentException("Person with id: " + id + ", not found");
        }
        personRepository.deleteById(id);

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
