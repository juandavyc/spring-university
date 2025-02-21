package com.juandavyc.university.specifications;


import com.juandavyc.university.entities.DocumentTypeEntity;
import com.juandavyc.university.entities.PersonEntity;
import com.juandavyc.university.entities.StudentEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecifications {



    public static Specification<StudentEntity> hasId(Long id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<StudentEntity> hasScholarship(Boolean scholarship) {
        return (root, query, criteriaBuilder) ->
                scholarship == null ? null : criteriaBuilder.equal(root.get("scholarship"), scholarship);
    }

    public static Specification<StudentEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if(name == null) return null;
            Join<StudentEntity, PersonEntity> join = root.join("person");
            return criteriaBuilder.like(join.get("name"), name.concat("%"));
        };
    }
    public static Specification<StudentEntity> hasDocument(String document) {
        return (root, query, criteriaBuilder) -> {
            if(document == null) return null;
            Join<StudentEntity, PersonEntity> join = root.join("document");
            return criteriaBuilder.equal(join.get("document"), document);
        };
    }

    public static Specification<StudentEntity> hasDocumentTypeId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if(id == null) return null;
            Join<StudentEntity, PersonEntity> person = root.join("person");
            Join<PersonEntity, DocumentTypeEntity> joinDocument = person.join("documentType");

            return criteriaBuilder.equal(joinDocument.get("id"), id);
        };
    }

    public static Specification<StudentEntity> hasDocumentTypeName(String name) {
        return (root, query, criteriaBuilder) -> {
            if(name == null) return null;
            Join<StudentEntity, PersonEntity> person = root.join("person");
            Join<PersonEntity, DocumentTypeEntity> joinDocument = person.join("documentType");

            return criteriaBuilder.equal(joinDocument.get("name"), name);
        };
    }

    /*
 String documentTypeName,*/
}
