package com.juandavyc.university.services;

import com.juandavyc.university.entities.StudentEntity;

import java.util.List;

public interface StudentService {

    List<StudentEntity> findAll();
    StudentEntity findById(long id);
    StudentEntity create(StudentEntity student, Long id);
    StudentEntity create(StudentEntity student);
}
