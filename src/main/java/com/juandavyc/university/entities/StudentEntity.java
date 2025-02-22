package com.juandavyc.university.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean scholarship;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person")
   // @MapsId
    private PersonEntity person;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
   // @ToString.Exclude
    private List<CourseEntity> courses;

}