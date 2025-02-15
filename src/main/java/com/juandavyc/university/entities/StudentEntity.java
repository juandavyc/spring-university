package com.juandavyc.university.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "student")

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Setter
@Getter

@ToString

public class StudentEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private Boolean scholarship;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private PersonEntity person;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<CourseEntity> courses;

}