package com.juandavyc.university.services;

import com.juandavyc.university.dtos.professor.request.ProfessorRequestDTO;
import com.juandavyc.university.dtos.professor.request.ProfessorUpdateDTO;
import com.juandavyc.university.dtos.professor.response.ProfessorResponseDTO;
import com.juandavyc.university.entities.ProfessorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfessorService {


    Page<ProfessorResponseDTO> findAll(Pageable pageable);

    Page<ProfessorResponseDTO> findByFilters(
            Long id,
            String salary,
            String name,
            String document,
            Long documentTypeId,
            String documentTypeName,
            Pageable pageable
    );

    ProfessorResponseDTO findById(Long id);

    ProfessorEntity create(ProfessorRequestDTO requestDTO);

    ProfessorResponseDTO update(Long id,ProfessorUpdateDTO professorUpdateDTO);

    void delete(Long id);


//    ProfessorEntity create(ProfessorEntity professor, Long personId);
//    ProfessorEntity findById(Long id);
//
//    ProfessorEntity create(ProfessorEntity professor);
}
