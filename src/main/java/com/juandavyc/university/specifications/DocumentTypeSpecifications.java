package com.juandavyc.university.specifications;


import com.juandavyc.university.entities.DocumentTypeEntity;
import org.springframework.data.jpa.domain.Specification;

public class DocumentTypeSpecifications {

    public static Specification<DocumentTypeEntity> hasId(Long id) {
        return (root,query,criteriaBuilder)->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<DocumentTypeEntity> hasName(String name) {
        return (root,query,criteriaBuilder)->
                name == null ? null : criteriaBuilder.equal(root.get("name"), name);
                //name == null ? null : criteriaBuilder.like(root.get("name"), name+"%");
    }
}
