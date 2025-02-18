package com.juandavyc.university.dtos.person.response;

import com.juandavyc.university.dtos.document.response.DocumentTypeResponseDTO;
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
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
