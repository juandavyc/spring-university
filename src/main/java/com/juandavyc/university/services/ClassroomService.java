package com.juandavyc.university.services;

import com.juandavyc.university.dtos.classroom.request.ClassroomRequestDTO;
import com.juandavyc.university.dtos.classroom.request.ClassroomWithCoursesRequestDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.dtos.course.request.CourseRequestDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ClassroomService {

    ClassroomEntity findByIdEntity(Long id);

    ClassroomResponseDTO findById(Long id);
    ClassroomWithCoursesResponseDTO findByIdWithCourses(Long id);

    Page<ClassroomResponseDTO> findByFilters(Long id, Integer room, Pageable pageable);
    Page<ClassroomResponseDTO> findAll(Pageable pageable);
    Page<ClassroomWithCoursesResponseDTO> findAllWithCourses(Pageable pageable);


    String create(ClassroomRequestDTO classroomRequestDTO);
    String create(ClassroomWithCoursesRequestDTO classroomWithCoursesRequestDTO);
    String create(Long id, List<CourseRequestDTO> courseRequestDTO);

}
