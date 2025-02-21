package com.juandavyc.university;

import com.juandavyc.university.entities.StudentEntity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
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
