package com.juandavyc.university.services;

import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.mappers.ClassroomMapper;
import com.juandavyc.university.repositories.ClassroomRepository;
import com.juandavyc.university.specifications.ClassroomSpecifications;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor

public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;

    @Override
    public ClassroomEntity findByIdEntity(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No classroom found by id: " + id));
    }

    @Override
    public Page<ClassroomResponseDTO> findByFilters(Long id, Integer room, Pageable pageable) {

        Specification<ClassroomEntity> spec = Specification
                .where(ClassroomSpecifications.hasRoom(room))
                .and(ClassroomSpecifications.hasId(id));

        final var classroom = classroomRepository.findAll(spec,pageable);

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
    public List<ClassroomWithCoursesResponseDTO> findAllWithCourses() {

        final var classrooms = classroomRepository.findAll();
        if (classrooms.isEmpty()) {
            throw new IllegalStateException("No classrooms found");
        } else {
            return mapClassrooms(classrooms, classroomMapper::toClassroomWithCoursesResponseDTO);
        }
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

    @Override
    public ClassroomEntity create(ClassroomEntity classroom) {
        return classroomRepository.save(classroom);
    }

    private <T> List<T> mapClassrooms(
            List<ClassroomEntity> classrooms,
            Function<ClassroomEntity, T> mapperTFunction
    ) {
        return classrooms.stream()
                .map(mapperTFunction)
                .toList();
    }
}
