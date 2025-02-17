package com.juandavyc.university.services;

import com.juandavyc.university.dtos.classroom.request.ClassroomRequestDTO;
import com.juandavyc.university.dtos.classroom.request.ClassroomWithCoursesRequestDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.dtos.course.request.CourseRequestDTO;
import com.juandavyc.university.dtos.course.request.CourseRequestFullDTO;
import com.juandavyc.university.dtos.course.request.CourseRequestNameTimeDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.CourseEntity;
import com.juandavyc.university.mappers.ClassroomMapper;
import com.juandavyc.university.mappers.CourseMapper;
import com.juandavyc.university.repositories.ClassroomRepository;
import com.juandavyc.university.specifications.ClassroomSpecifications;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor

public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    // por testear
    private final CourseMapper courseMapper;
    private final CourseService courseService;

    @Override
    public ClassroomEntity findByIdEntity(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No classroom found by id: " + id));
    }

    @Transactional(
            propagation = Propagation.NESTED,
            readOnly = true
    )
    @Override
    public ClassroomWithCoursesResponseDTO findByIdWithCourses(Long id) {

        final var classroom = findByIdEntity(id);
        return classroomMapper.toClassroomWithCoursesResponseDTO(classroom);

    }

    @Transactional(
            propagation = Propagation.NOT_SUPPORTED,
            readOnly = true
    )
    @Override
    public Page<ClassroomResponseDTO> findByFilters(Long id, Integer room, Pageable pageable) {

        Specification<ClassroomEntity> spec = Specification
                .where(ClassroomSpecifications.hasRoom(room))
                .and(ClassroomSpecifications.hasId(id));

        final var classroom = classroomRepository.findAll(spec, pageable);
        return classroom.map(classroomMapper::toClassroomResponseDTO);
    }

    @Transactional(
            propagation = Propagation.NESTED,
            readOnly = true
    )
    @Override
    public ClassroomResponseDTO findById(Long id) {

        return classroomMapper.toClassroomResponseDTO(findByIdEntity(id));

    }

    @Transactional(
            propagation = Propagation.NOT_SUPPORTED,
            readOnly = true
    )
    @Override
    public Page<ClassroomResponseDTO> findAll(Pageable pageable) {

        final var classrooms = classroomRepository.findAll(pageable);
        if (classrooms.hasContent()) {
            return classrooms.map(classroomEntity -> classroomMapper.toClassroomResponseDTO(classroomEntity));

        } else {
            throw new IllegalStateException("No classrooms found");
        }
    }

    @Transactional(
            propagation = Propagation.NOT_SUPPORTED,
            readOnly = true
    )
    @Override
    public Page<ClassroomWithCoursesResponseDTO> findAllWithCourses(Pageable pageable) {

        final var classrooms = classroomRepository.findAll(pageable);
        if (classrooms.isEmpty()) {
            throw new IllegalStateException("No classrooms found");
        } else {
            return classrooms.map(classroomMapper::toClassroomWithCoursesResponseDTO);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String create(ClassroomRequestDTO classroomRequestDTO) {
        if (classroomRepository.existsByRoom(classroomRequestDTO.getRoom())) {
            throw new IllegalStateException("Concurrent: Class room with number: " + classroomRequestDTO.getRoom() + " already exists");
        }
        try {
            final var toCreate = classroomMapper.toClassroomEntity(classroomRequestDTO);
            return classroomRepository.save(toCreate).getId().toString();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Concurrent: Class room with number: " + classroomRequestDTO.getRoom() + " already exists");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String create(ClassroomWithCoursesRequestDTO classroomWithCoursesRequestDTO) {

        if (classroomRepository.existsByRoom(classroomWithCoursesRequestDTO.getRoom())) {
            throw new IllegalStateException("Class room with number: " + classroomWithCoursesRequestDTO.getRoom() + " already exists");
        }
        try {
            final var toCreate = classroomMapper.toClassroomWithCoursesEntity(classroomWithCoursesRequestDTO);
            toCreate.getCourses().forEach(course -> course.setClassroom(toCreate));
            return classroomRepository.save(toCreate).getId().toString();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Concurrent: Class room with number: " + classroomWithCoursesRequestDTO.getRoom() + " already exists");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String create(Long id, List<CourseRequestNameTimeDTO> coursesRequestDTO) {

        final var classroomEntity = findByIdEntity(id);
        // single
        // final var courseEntity = courseMapper.toCourseEntity(courseRequestDTO);
        final var coursesEntities = coursesRequestDTO.stream()
                .map(courseRequestDTO -> courseMapper.toCourseEntity(courseRequestDTO))
                .peek(courseEntity -> courseEntity.setClassroom(classroomEntity))
                .toList();

        classroomEntity.getCourses().addAll(coursesEntities);

        return classroomRepository.save(classroomEntity).getId().toString();

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ClassroomResponseDTO update(Long id, ClassroomRequestDTO classroomDTO) {
        if (classroomRepository.existsById(id)) {
            if (classroomRepository.existsByRoom(classroomDTO.getRoom())) {
                throw new IllegalStateException("Class room with number: " + classroomDTO.getRoom() + " already exists");
            }
            try {

                final var toCreate = classroomMapper.toClassroomEntity(classroomDTO);
                toCreate.setId(id);

                return classroomMapper.toClassroomResponseDTO(
                        classroomRepository.save(toCreate)
                );

            } catch (DataIntegrityViolationException e) {
                throw new IllegalStateException("Class room with number: " + classroomDTO.getRoom() + " already exists");
            }
        } else {
            throw new IllegalStateException("Concurrent: Classroom with number: " + id + " not exists");
        }
    }

    @Override
    public ClassroomWithCoursesResponseDTO updateCourses(Long id, List<CourseRequestFullDTO> courseRequestDTOS) {

        final var classroomEntity = findByIdEntity(id);


        courseRequestDTOS.forEach(courseRequestDTO -> {

            Optional<CourseEntity> courseEntity = findExistingCourse(courseRequestDTO.getId(),classroomEntity );

            if (courseEntity.isPresent()) {
                // TODO: external method
                courseEntity.get().setName(courseRequestDTO.getName());
                courseEntity.get().setTime(courseRequestDTO.getTime());

                courseEntity.get().setClassroom(classroomEntity);
                classroomEntity.getCourses().add(courseEntity.get());
            }
            else{
                // TODO: external method
                final var courseToUpdate = courseService.findById(courseRequestDTO.getId());
                courseToUpdate.setName(courseRequestDTO.getName());
                courseToUpdate.setTime(courseRequestDTO.getTime());

                courseToUpdate.setClassroom(classroomEntity);
                classroomEntity.getCourses().add(courseToUpdate);
            }
        });

        return classroomMapper.toClassroomWithCoursesResponseDTO(
                classroomRepository.save(classroomEntity)
        );
    }

    @Override
    public void deleteById(Long id) {
        if(!classroomRepository.existsById(id)) {
            throw new IllegalStateException("Classroom with id: " + id + " not exists");
        }
        try{
            // CASCADE persist make null all children
            /*
            * find by id, and clear all children and save entity, after delete this.
             */
            classroomRepository.deleteById(id);
        }catch (Exception e) {
            throw new IllegalStateException("Concurrent: Class room with id: " + id + " not exists");
        }
    }


    private Optional<CourseEntity> findExistingCourse(Long id, ClassroomEntity classroomEntity) {
                return classroomEntity.getCourses().stream()
                        .filter(courseEntity1 -> courseEntity1.getId().equals(id))
                        .findFirst();
    }
}
