package com.juandavyc.university.services;

import com.juandavyc.university.entities.ProfessorEntity;
import com.juandavyc.university.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {


    private final ProfessorRepository professorRepository;
    private final PersonService personService;

    @Transactional
    @Override
    public ProfessorEntity create(ProfessorEntity professor, Long personId) {

        final var person = personService.findById(personId);

        professor.setPerson(person);

        return professorRepository.save(professor);
    }
    @Transactional(propagation = Propagation.NESTED)
    @Override
    public ProfessorEntity create(ProfessorEntity professor) {
        return professorRepository.save(professor);
    }

    @Override
    public ProfessorEntity findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Person by id " + id + " not found"));
    }

}
