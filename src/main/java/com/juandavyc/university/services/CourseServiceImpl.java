package com.juandavyc.university.services;


import com.juandavyc.university.dtos.course.request.CourseRequestDTO;
import com.juandavyc.university.dtos.course.request.CourseUpdateDTO;
import com.juandavyc.university.dtos.course.response.CourseResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.CourseEntity;
import com.juandavyc.university.mappers.CourseMapper;
import com.juandavyc.university.mappers.CourseUpdateMapper;
import com.juandavyc.university.repositories.CourseRepository;
import com.juandavyc.university.specifications.CourseSpecifications;
import com.juandavyc.university.validators.CourseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseUpdateMapper courseUpdateMapper;

    // other services
    // private final ClassroomService classroomService;
    private final CourseValidator courseValidator;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Page<CourseResponseDTO> findAll(Pageable pageable) {

        final var courses = courseRepository.findAll(pageable);
        if (courses.isEmpty()) {
            throw new IllegalArgumentException("No students found");
        }
        return courses.map(courseMapper::toCourseResponseDTO);

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public CourseResponseDTO findById(Long id) {

        final var course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No student with id:" + id + ", found"));

        return courseMapper.toCourseResponseDTO(course);
    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)

    public Page<CourseResponseDTO> findByFilters(
            Long id,
            String name,
            String timePeriod,
            Long roomId,
            Integer roomNumber,
            Pageable pageable
    ) {

        Specification<CourseEntity> specification = Specification
                .where(CourseSpecifications.hasId(id))
                .and(CourseSpecifications.hasName(name))
                .and(CourseSpecifications.hasTimePeriod(timePeriod))
                .and(CourseSpecifications.hasRoomId(roomId))
                .and(CourseSpecifications.hasRoomNumber(roomNumber));

        final var courses = courseRepository.findAll(specification, pageable);

        return courses.map(courseMapper::toCourseResponseDTO);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = RuntimeException.class
    )
    @Override
    public CourseEntity create(CourseRequestDTO courseRequestDTO) {

        //
        courseValidator.isAvailableTimeAndClassroom(courseRequestDTO.getTimePeriod(), courseRequestDTO.getClassroomId());
        courseValidator.isAvailableTimeAndName(courseRequestDTO.getTimePeriod(), courseRequestDTO.getName());

        // final var classroomEntity = classroomService.findById(courseRequestDTO.getClassroomId());

        final var toCreate = courseMapper.toCourseEntity(courseRequestDTO);

        final var classroomEntity = ClassroomEntity
                .builder()
                .id(courseRequestDTO.getClassroomId())
                .build();

        toCreate.setClassroom(classroomEntity);

        return courseRepository.save(toCreate);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = RuntimeException.class
    )
    @Override
    public CourseResponseDTO update(Long id, CourseUpdateDTO courseUpdateDTO) {

        final var courseEntity = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No student with id:" + id + ", found"));


        if (courseUpdateDTO.getTimePeriod() != null && courseUpdateDTO.getClassroomId() != null) {

            final var classroomEntity = ClassroomEntity.builder()
                    .id(courseUpdateDTO.getClassroomId())
                    .build();

            courseEntity.setClassroom(classroomEntity);
        }

        courseUpdateMapper.toUpdateCourseEntityFromDTO(courseUpdateDTO, courseEntity);

        return null;
    }


    @Override
    public void delete(Long id) {

        if (id == null || !courseRepository.existsById(id)) {
            throw new IllegalArgumentException("Person with id: " + id + ", not found");
        }
        courseRepository.deleteById(id);
    }


}


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public CourseEntity create(CourseEntity course) {
//        return courseRepository.save(course);
//    }
//
//
//    @Override
//    public CourseEntity findAll() {
//        return null;
//    }
//
//    @Transactional(propagation=Propagation.NESTED)
//    @Override
//    public CourseEntity findById(Long id) {
//        return courseRepository.findById(id)
//                .orElseThrow(()->new IllegalArgumentException("Course by id " + id + " not found"));
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public CourseEntity addStudentToCourse(Long courseId, Long studentId) {
//        final var student = studentService.findById(studentId);
//        final var course = findById(courseId);
//
//        course.getStudents().add(student);
//        student.getCourses().add(course);
//
//        return courseRepository.save(course);
//        return null;
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public CourseEntity addProfessorToCourse(Long courseId, Long professorId) {
/// /
/// /        final var professor = professorService.findById(professorId);
/// /        final var course = findById(courseId);
/// /
/// /        course.getProfessors().add(professor);
/// /        professor.getCourses().add(course);
/// /
/// /        return courseRepository.save(course);
//
//        return null;
//    }
//
//    @Override
//    public CourseEntity findByName(String name) {
//        return courseRepository.findByName(name);
//    }
//
/// /    @Override
/// /    public CourseEntity findAll() {
/// /        return null;
/// /    }
/// /
/// /    @Override
/// /    public CourseEntity findById(long id) {
/// /        return null;
/// /    }


