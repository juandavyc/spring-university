package com.juandavyc.university.entities;


import com.juandavyc.university.embeddables.AuditInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

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

//    @Embedded
//   // @Builder.Default
//    private AuditInfo auditInfo;

//    @Version
//    private Short version = 0;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ClassroomEntity that = (ClassroomEntity) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

//    @PreUpdate
//    public void preUpdate() {
//        System.out.println("preUpdate: " + getVersion());
//        setVersion(getVersion() == null ? 1 : getVersion());
//    }


}
