package com.juandavyc.university.services;

import com.juandavyc.university.dtos.classroom.request.ClassroomRequestDTO;
import com.juandavyc.university.dtos.classroom.request.ClassroomWithCoursesRequestDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ClassroomService {


    Page<ClassroomResponseDTO> findAll(Pageable pageable);

    Page<ClassroomWithCoursesResponseDTO> findAllWithCourses(Pageable pageable);

    ClassroomResponseDTO findById(Long id);

    ClassroomWithCoursesResponseDTO findByIdWithCourses(Long id);

    Page<ClassroomWithCoursesResponseDTO> findByFilters(
            Long id,
            Integer room,
            Long courseId,
            String courseName,
            String courseTimePeriod,
            Pageable pageable);

    ClassroomEntity create(ClassroomRequestDTO classroomRequestDTO);
    ClassroomResponseDTO update(Long id, ClassroomRequestDTO classroomDTO);

    void deleteById(Long id);

}
