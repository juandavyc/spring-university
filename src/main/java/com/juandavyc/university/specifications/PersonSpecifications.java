package com.juandavyc.university.specifications;

import com.juandavyc.university.entities.DocumentTypeEntity;
import com.juandavyc.university.entities.PersonEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

public class PersonSpecifications {

    public static Specification<PersonEntity> hasId(Long id) {
        return (root, criteriaQuery, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<PersonEntity> hasName(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<PersonEntity> nameStartingWith(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), name.concat("%"));
    }

    public static Specification<PersonEntity> hasDocument(String document) {
        return (root, criteriaQuery, criteriaBuilder) ->
                document == null ? null : criteriaBuilder.equal(root.get("document"), document);
    }

    //    public static Specification<PersonEntity> has(Long id) {
//        return (root, criteriaQuery, criteriaBuilder) ->
//                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
//    }
    public static Specification<PersonEntity> hasCreatedAt(
            LocalDate date
    ) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (date == null) {
                return null;
            } else {
                return dateBetween(date, root, criteriaBuilder, "createdAt");
            }
        };
    }

    public static Specification<PersonEntity> hasUpdateAt(
            LocalDate date
    ) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (date == null) {
                return null;
            } else {
                return dateBetween(date, root, criteriaBuilder, "updatedAt");
            }
        };
    }

    public static Specification<PersonEntity> hasDocumentTypeName(String documentTypeName) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (documentTypeName == null) {
                return null;
            } else {
                Join<PersonEntity, DocumentTypeEntity> join = root.join("documentType"/*, JoinType.LEFT*/);
                return criteriaBuilder.equal(join.get("name"), documentTypeName);
            }
        };
    }

    public static Specification<PersonEntity> hasDocumentTypeId(Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (id == null) {
                return null;
            } else {
                Join<PersonEntity, DocumentTypeEntity> join = root.join("documentType"/*, JoinType.LEFT*/);
                return criteriaBuilder.equal(join.get("id"), id);
            }
        };
    }


    private static Predicate dateBetween(
            LocalDate date,
            Root<PersonEntity> root,
            CriteriaBuilder criteriaBuilder,
            String field
    ) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return criteriaBuilder.between(root.get(field), startOfDay, endOfDay);
    }


}
