package com.juandavyc.university.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "course")

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Setter
@Getter

@ToString

public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean time;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classroom" /*nullable = false*/)
    private ClassroomEntity classroom;

    @ManyToMany(cascade =
            {
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
            },
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_student")
    )
    @ToString.Exclude
    private List<StudentEntity> students;


    @ManyToMany(cascade =
            {
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
            },
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "professor_course",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_professor")
    )
    @ToString.Exclude
    private List<ProfessorEntity> professors;



}

