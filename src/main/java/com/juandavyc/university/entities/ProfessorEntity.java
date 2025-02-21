package com.juandavyc.university.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

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

//@DynamicUpdate
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigInteger salary;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    //@MapsId
    @JoinColumn(name = "id_person")
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
