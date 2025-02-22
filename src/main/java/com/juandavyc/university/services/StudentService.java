package com.juandavyc.university.services;

import com.juandavyc.university.dtos.student.request.StudentRequestDTO;
import com.juandavyc.university.dtos.student.request.StudentUpdateDTO;

import com.juandavyc.university.dtos.student.response.StudentCourseResponseDTO;
import com.juandavyc.university.dtos.student.response.StudentCoursesResponseDTO;
import com.juandavyc.university.dtos.student.response.StudentResponseDTO;
import com.juandavyc.university.entities.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Page<StudentResponseDTO> findAll(Pageable pageable);

    StudentCoursesResponseDTO findById(Long id);

    StudentCoursesResponseDTO findByIdCourses(
            Long studentId, String name
    );


    Page<StudentResponseDTO> findByFilters(
            Long id,
            Boolean scholarship,
            String name,
            String document,
            Long documentTypeId,
            String documentTypeName,
            Pageable pageable
    );


    StudentEntity create(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO update(Long id, StudentUpdateDTO studentUpdateDTO);

    void delete(Long id);

    StudentCourseResponseDTO addStudentCourse(Long studentId, Long courseId);

    void removeStudentCourse(Long studentId, Long courseId);


//    List<StudentEntity> findAll();
//    StudentEntity findById(long id);
//    StudentEntity create(StudentEntity student, Long id);
//    StudentEntity create(StudentEntity student);
}
