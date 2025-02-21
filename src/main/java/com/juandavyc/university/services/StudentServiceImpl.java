package com.juandavyc.university.services;

import com.juandavyc.university.dtos.student.request.StudentRequestDTO;
import com.juandavyc.university.dtos.student.request.StudentUpdateDTO;
import com.juandavyc.university.dtos.student.response.StudentResponseDTO;

import com.juandavyc.university.entities.StudentEntity;
import com.juandavyc.university.mappers.StudentMapper;
import com.juandavyc.university.mappers.StudentUpdateMapper;
import com.juandavyc.university.repositories.StudentRepository;
import com.juandavyc.university.specifications.StudentSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;
    private final StudentUpdateMapper studentUpdateMapper;

    private final PersonService personService;


    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Page<StudentResponseDTO> findAll(Pageable pageable) {

        final var students = studentRepository.findAll(pageable);

        if (students.isEmpty()) {
            throw new IllegalArgumentException("No students found");
        }
        return students.map(studentMapper::toStudentResponseDTO);

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public StudentResponseDTO findById(Long id) {

        final var student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No student with id:" + id + ", found"));

        return studentMapper.toStudentResponseDTO(student);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Page<StudentResponseDTO> findByFilters(
            Long id,
            Boolean scholarship,
            String name,
            String document,
            Long documentTypeId,
            String documentTypeName,
            Pageable pageable
    ) {

        Specification<StudentEntity> specification = Specification
                .where(StudentSpecifications.hasId(id))
                .and(StudentSpecifications.hasScholarship(scholarship))
                .and(StudentSpecifications.hasName(name))
                .and(StudentSpecifications.hasDocument(document))
                .and(StudentSpecifications.hasDocumentTypeId(documentTypeId))
                .and(StudentSpecifications.hasDocumentTypeName(documentTypeName));


        final var students = studentRepository.findAll(specification, pageable);
        return students.map(studentMapper::toStudentResponseDTO);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = RuntimeException.class
    )
    @Override
    public StudentEntity create(StudentRequestDTO studentRequestDTO) {

        final var personEntity = personService.create(studentRequestDTO.getPerson());

        if (personEntity.getId() == null) {
            throw new RuntimeException("Cannot create person");
        }

        final var studentEntity = studentMapper.toStudentEntity(studentRequestDTO);
        studentEntity.setPerson(personEntity);

        return studentRepository.save(studentEntity);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = RuntimeException.class
    )
    @Override
    public StudentResponseDTO update(Long id, StudentUpdateDTO studentUpdateDTO) {

        final var studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No student found with id:" + id + ", found"));

        try {

            if (studentUpdateDTO.getPerson() != null) {
                personService.updateValidation(studentUpdateDTO.getPerson(), studentEntity.getPerson());
            }

            studentUpdateMapper
                    .toUpdateStudentEntityFromDTO(
                            studentUpdateDTO,
                            studentEntity
                    );

            return studentMapper.toStudentResponseDTO(studentEntity);

        } catch (RuntimeException e) {
            throw new RuntimeException("Cannot update student: " + e);
        }


    }

    //@Transactional(propagation = Propagation.REQUIRED);
    @Override
    public void delete(Long id) {

        if (id == null || !studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Person with id: " + id + ", not found");
        }
        studentRepository.deleteById(id);
    }
}


