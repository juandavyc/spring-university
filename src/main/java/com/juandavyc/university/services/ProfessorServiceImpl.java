package com.juandavyc.university.services;

import com.juandavyc.university.dtos.professor.request.ProfessorRequestDTO;
import com.juandavyc.university.dtos.professor.request.ProfessorUpdateDTO;
import com.juandavyc.university.dtos.professor.response.ProfessorCourseResponseDTO;
import com.juandavyc.university.dtos.professor.response.ProfessorCoursesResponseDTO;
import com.juandavyc.university.dtos.professor.response.ProfessorResponseDTO;

import com.juandavyc.university.entities.ProfessorEntity;
import com.juandavyc.university.mappers.ProfessorMapper;
import com.juandavyc.university.mappers.ProfessorUpdateMapper;
import com.juandavyc.university.repositories.ProfessorRepository;
import com.juandavyc.university.specifications.ProfessorSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    private final ProfessorMapper professorMapper;
    private final ProfessorUpdateMapper professorUpdateMapper;

    private final PersonService personService;
    private final CourseService courseService;

    @Transactional(
            propagation = Propagation.NOT_SUPPORTED,
            readOnly = true
    )
    @Override
    public Page<ProfessorResponseDTO> findAll(Pageable pageable) {

        final var professors = professorRepository.findAll(pageable);
        if (professors.isEmpty()) {
            throw new IllegalArgumentException("No professors found");
        }
        return professors.map(professorMapper::toProfessorResponseDTO);
    }

    @Transactional(
            readOnly = true
    )
    public ProfessorResponseDTO findById(Long id) {

        final var professor = professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No professor with id:" + id + ", found"));

        return professorMapper.toProfessorResponseDTO(professor);
    }


    @Transactional(
            propagation = Propagation.NOT_SUPPORTED,
            readOnly = true
    )
    @Override
    public Page<ProfessorResponseDTO> findByFilters(
            Long id,
            String salary,
            String name,
            String document,
            Long documentTypeId,
            String documentTypeName,
            Pageable pageable
    ) {
        Specification<ProfessorEntity> specification = Specification
                .where(ProfessorSpecifications.hasId(id))
                .and(ProfessorSpecifications.hasSalary(salary))
                .and(ProfessorSpecifications.hasName(name))
                .and(ProfessorSpecifications.hasDocument(document))
                .and(ProfessorSpecifications.hasDocumentTypeId(documentTypeId))
                .and(ProfessorSpecifications.hasDocumentTypeName(documentTypeName));

        final var professors = professorRepository.findAll(specification, pageable);
        if (professors.isEmpty()) {
            throw new IllegalArgumentException("No professors found");
        }

        return professors.map(professorMapper::toProfessorResponseDTO);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = RuntimeException.class
    )
    @Override
    public ProfessorEntity create(ProfessorRequestDTO requestDTO) {

        final var createdPerson = personService.create(requestDTO.getPerson());

        if (createdPerson.getId() == null) {
            throw new RuntimeException("Cannot create person");
        }

        final var createPerson = professorMapper.toProfessorEntity(requestDTO);
        createPerson.setPerson(createdPerson);

        return professorRepository.save(createPerson);
    }


    @Transactional
    @Override
    public ProfessorResponseDTO update(Long id, ProfessorUpdateDTO professorUpdateDTO) {

        try {
            final var professorEntity = professorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No professor with id:" + id + ", found"));

            if (professorUpdateDTO.getPerson() != null) {
                personService.updateValidation(professorUpdateDTO.getPerson(), professorEntity.getPerson());
            }
            professorUpdateMapper.toUpdateProfessorEntityFromDTO(professorUpdateDTO, professorEntity);

            return professorMapper.toProfessorResponseDTO(professorEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Cannot update student: " + e);
        }

    }

    @Transactional
    @Override
    public void delete(Long id) {

        if (id == null || !professorRepository.existsById(id)) {
            throw new IllegalArgumentException("Person with id: " + id + ", not found");
        }
        professorRepository.deleteById(id);

    }

    // professor-courses
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public ProfessorCoursesResponseDTO findByIdCourses(Long professorId, String name) {

        final var professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Professor with id:" + professorId + ", not found"));

        return professorMapper.toProfessorCoursesResponseDTO(professor);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ProfessorCourseResponseDTO addProfessorCourse(Long professorId, Long courseId) {

        final var professorEntity = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Professor with id:" + professorId + ", not found"));

        final var courseEntity = courseService.findByIdEntity(courseId);

        try {
            professorEntity.getCourses().add(courseEntity);
            courseEntity.getProfessors().add(professorEntity);

            return ProfessorCourseResponseDTO.builder()
                    .professorId(professorEntity.getId())
                    .courseId(courseEntity.getId())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException("Concurrent error: " + e.getMessage());
        }

    }


    @Override
    public void removeProfessorCourse(Long professorId, Long courseId) {
        final var professorEntity = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("No professor found with id:" + professorId));

        final var courseEntity = courseService.findByIdEntity(courseId);

        professorEntity.getCourses().remove(courseEntity);
        courseEntity.getProfessors().remove(professorEntity);

        professorRepository.save(professorEntity);
    }


}
