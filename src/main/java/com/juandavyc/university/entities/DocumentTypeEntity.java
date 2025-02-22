package com.juandavyc.university.entities;

import com.juandavyc.university.embeddables.AuditInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "document_type")

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Setter
@Getter

@ToString

public class DocumentTypeEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "documentType",
            fetch = FetchType.LAZY,
           // orphanRemoval = true,
            cascade = CascadeType.PERSIST
    )
    @ToString.Exclude
    private List<PersonEntity> persons;

//    @Embedded
//    private AuditInfo auditInfo = new AuditInfo();;

//    @Version
//    private Short version = 0;



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DocumentTypeEntity that = (DocumentTypeEntity) o;
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
