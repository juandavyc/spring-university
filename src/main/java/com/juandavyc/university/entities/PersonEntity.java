package com.juandavyc.university.entities;

import com.juandavyc.university.embeddables.AuditInfo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ManyToOne(fetch = FetchType.EAGER) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_document_type"/*nullable = false*/)
    private DocumentTypeEntity documentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PersonRoleEnum role;

    @Embedded
    private AuditInfo auditInfo;


//    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
//    @ToString.Exclude
//    private ProfessorEntity student;

    @Version
    private Integer version;

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


//    @PreUpdate
//    public void preUpdate() {
//        setVersion(getVersion() == null ? 1 : getVersion());
//    }


}
