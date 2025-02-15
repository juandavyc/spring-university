package com.juandavyc.university.services;

import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ClassroomService {

    ClassroomEntity findByIdEntity(Long id);


    Page<ClassroomResponseDTO> findByFilters(Long id, Integer room, Pageable pageable);

    Page<ClassroomResponseDTO> findAll(Pageable pageable);
    List<ClassroomWithCoursesResponseDTO> findAllWithCourses();

    ClassroomResponseDTO findById(Long id);
    ClassroomWithCoursesResponseDTO findByIdWithCourses(Long id);

    ClassroomEntity create(ClassroomEntity classroom);




}
