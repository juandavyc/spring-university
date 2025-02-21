package com.juandavyc.university.services;

import com.juandavyc.university.dtos.course.request.CourseRequestDTO;
import com.juandavyc.university.dtos.course.request.CourseUpdateDTO;
import com.juandavyc.university.dtos.course.response.CourseResponseDTO;

import com.juandavyc.university.entities.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CourseService {

    Page<CourseResponseDTO> findAll(Pageable pageable);

    CourseResponseDTO findById(Long id);

    Page<CourseResponseDTO> findByFilters(
            Long id,
            String name,
            String timePeriod,
            Long roomId,
            Integer roomNumber,
            Pageable pageable);


    CourseEntity create(CourseRequestDTO courseRequestDTO);

    CourseResponseDTO update(Long id,CourseUpdateDTO courseUpdateDTO);

    void delete(Long id);

}
