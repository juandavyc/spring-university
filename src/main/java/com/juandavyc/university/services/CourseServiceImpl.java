package com.juandavyc.university.services;

import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.CourseEntity;
import com.juandavyc.university.entities.StudentEntity;
import com.juandavyc.university.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final ProfessorService professorService;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CourseEntity create(CourseEntity course) {
        return courseRepository.save(course);
    }


    @Transactional(propagation=Propagation.NESTED)
    @Override
    public CourseEntity findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Course by id " + id + " not found"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CourseEntity addStudentToCourse(Long courseId, Long studentId) {
        final var student = studentService.findById(studentId);
        final var course = findById(courseId);

        course.getStudents().add(student);
        student.getCourses().add(course);

        return courseRepository.save(course);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CourseEntity addProfessorToCourse(Long courseId, Long professorId) {

        final var professor = professorService.findById(professorId);
        final var course = findById(courseId);

        course.getProfessors().add(professor);
        professor.getCourses().add(course);

        return courseRepository.save(course);
    }

    @Override
    public CourseEntity findByName(String name) {
        return courseRepository.findByName(name);
    }

//    @Override
//    public CourseEntity findAll() {
//        return null;
//    }
//
//    @Override
//    public CourseEntity findById(long id) {
//        return null;
//    }


}
