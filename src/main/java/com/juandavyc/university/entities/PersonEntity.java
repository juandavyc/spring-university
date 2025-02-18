package com.juandavyc.university.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "person")

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Setter
@Getter

@ToString

public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false, length = 20)
    private String document;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_document_type"/*nullable = false*/)
    private DocumentTypeEntity documentType;


//    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
//    private ProfessorEntity professor;  // Relación bidireccional



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
