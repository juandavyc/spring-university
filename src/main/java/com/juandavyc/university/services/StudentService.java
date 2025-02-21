package com.juandavyc.university.services;

import com.juandavyc.university.dtos.student.request.StudentRequestDTO;
import com.juandavyc.university.dtos.student.request.StudentUpdateDTO;
import com.juandavyc.university.dtos.student.response.StudentResponseDTO;
import com.juandavyc.university.entities.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Page<StudentResponseDTO> findAll(Pageable pageable);

    StudentResponseDTO findById(Long id);

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
//    List<StudentEntity> findAll();
//    StudentEntity findById(long id);
//    StudentEntity create(StudentEntity student, Long id);
//    StudentEntity create(StudentEntity student);
}
