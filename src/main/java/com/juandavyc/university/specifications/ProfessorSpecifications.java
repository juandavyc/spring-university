package com.juandavyc.university.specifications;


import com.juandavyc.university.entities.DocumentTypeEntity;
import com.juandavyc.university.entities.PersonEntity;
import com.juandavyc.university.entities.ProfessorEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProfessorSpecifications {


    public static Specification<ProfessorEntity> hasId(Long id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<ProfessorEntity> hasSalary(String salary) {

        if (salary == null) return null;

        String[] split = salary.split(":");
        if (split.length != 2) throw new IllegalArgumentException("Unsupported operation");

        String operator = split[0];
        Integer value = Integer.parseInt(split[1]);


        return switch (operator) {
            case "less" -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("salary"), value);
            case "greater" -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), value);
            case "equal" -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("salary"), value);
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    public static Specification<ProfessorEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) return null;
            Join<ProfessorEntity, PersonEntity> join = root.join("person");
            return criteriaBuilder.like(join.get("name"), name.concat("%"));
        };
    }

    public static Specification<ProfessorEntity> hasDocument(String document) {
        return (root, query, criteriaBuilder) -> {
            if (document == null) return null;
            Join<ProfessorEntity, PersonEntity> join = root.join("document");
            return criteriaBuilder.equal(join.get("document"), document);
        };
    }

    public static Specification<ProfessorEntity> hasDocumentTypeId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) return null;
            Join<ProfessorEntity, PersonEntity> person = root.join("person");
            Join<PersonEntity, DocumentTypeEntity> joinDocument = person.join("documentType");

            return criteriaBuilder.equal(joinDocument.get("id"), id);
        };
    }

    public static Specification<ProfessorEntity> hasDocumentTypeName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) return null;
            Join<ProfessorEntity, PersonEntity> person = root.join("person");
            Join<PersonEntity, DocumentTypeEntity> joinDocument = person.join("documentType");

            return criteriaBuilder.equal(joinDocument.get("name"), name);
        };
    }


}
