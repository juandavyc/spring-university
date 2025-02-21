package com.juandavyc.university.validators;

import com.juandavyc.university.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseValidator {
    private final CourseRepository courseRepository;

    public void isAvailableTimeAndClassroom(Boolean time, Long classroomId) {
        if (courseRepository.existsByTimeAndClassroomId(time, classroomId)) {
            throw new IllegalArgumentException("Classroom is already in use by other course");
        }
    }
    public void isAvailableTimeAndName(Boolean time, String name) {
        if (courseRepository.existsByTimeAndName(time, name)) {
            throw new IllegalArgumentException("Classroom with name " + name + " and "+time+". Already exists");
        }
    }



}
