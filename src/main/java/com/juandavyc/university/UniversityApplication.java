package com.juandavyc.university;

import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.CourseEntity;
import com.juandavyc.university.entities.ProfessorEntity;
import com.juandavyc.university.entities.StudentEntity;
import com.juandavyc.university.services.*;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.net.URI;

@SpringBootApplication
public class UniversityApplication implements CommandLineRunner {

//    @Autowired
//    private PersonService personService;
//
//    @Autowired
//    private DocumentTypeService documentTypeService;
//
//    @Autowired
//    private ProfessorService professorService;
//
//    @Autowired
//    private ClassroomService classroomService;
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private StudentService studentService;


    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {


    }
}
