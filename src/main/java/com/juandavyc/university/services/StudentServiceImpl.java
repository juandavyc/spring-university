package com.juandavyc.university.services;

import com.juandavyc.university.entities.StudentEntity;
import com.juandavyc.university.repositories.PersonRepository;
import com.juandavyc.university.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PersonService personService;

    @Override
    public List<StudentEntity> findAll() {
        final var students = studentRepository.findAll();
        if(students.isEmpty()){
            throw new IllegalStateException("No students found");
        }
        else{
            return students;
        }
    }

    @Transactional
    @Override
    public StudentEntity findById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Person by id " + id + " not found"));
    }

    @Transactional(propagation=Propagation.NESTED)
    @Override
    public StudentEntity create(StudentEntity student, Long id) {
//        final var person = personService.findById(id);
//        student.setPerson(person);
//        return studentRepository.save(student);

        return null;
    }

    @Override
    public StudentEntity create(StudentEntity student) {
        return studentRepository.save(student);
    }

}
