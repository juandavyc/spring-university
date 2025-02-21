package com.juandavyc.university.dtos.person.response;

import com.juandavyc.university.dtos.document.response.DocumentTypeResponseDTO;
import com.juandavyc.university.embeddables.AuditInfo;
import com.juandavyc.university.entities.PersonRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PersonResponseDTO {

  private Long id;
  private String name;
  private String document;

  private DocumentTypeResponseDTO documentType;

  private AuditInfo auditInfo;
  private PersonRoleEnum role;
}
