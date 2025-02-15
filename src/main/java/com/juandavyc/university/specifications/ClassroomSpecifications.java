package com.juandavyc.university.specifications;

import com.juandavyc.university.entities.ClassroomEntity;
import org.springframework.data.jpa.domain.Specification;

public class ClassroomSpecifications {


    public static Specification<ClassroomEntity> hasRoom(Integer room) {
        return(root, query, criteriaBuilder) ->
                room == null ? null : criteriaBuilder.equal(root.get("room"), room);
    }

    public static Specification<ClassroomEntity> hasId(Long id) {
        return (root,query,criteriaBuilder)->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

}
