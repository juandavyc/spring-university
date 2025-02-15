package com.juandavyc.university.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "professor")

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Setter
@Getter

@ToString

public class ProfessorEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private BigInteger salary;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId  // Reutiliza el id de Person
    @JoinColumn(name = "id")  // La columna de unión es el mismo id
    private PersonEntity person;


    @ManyToMany(mappedBy = "professors", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<CourseEntity> courses;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorEntity that = (ProfessorEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
