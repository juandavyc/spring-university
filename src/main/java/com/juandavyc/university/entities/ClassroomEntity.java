package com.juandavyc.university.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "classroom")

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Setter
@Getter

@ToString
public class ClassroomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer room;

    @OneToMany(
            mappedBy = "classroom",
            fetch = FetchType.LAZY,
            //orphanRemoval = true,
            cascade = CascadeType.PERSIST
    )
    @ToString.Exclude
    // temporal
    // @JsonIgnore
    private List<CourseEntity> courses;

}
