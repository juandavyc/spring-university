package com.juandavyc.university.services;

import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.CourseEntity;

public interface CourseService {
//    CourseEntity findAll();
    CourseEntity findById(Long id);

    CourseEntity create(CourseEntity course);

    CourseEntity addStudentToCourse(Long courseId,Long studentId);

    CourseEntity addProfessorToCourse(Long courseId,Long professorId);

}
