package com.juandavyc.university.specifications;


import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.CourseEntity;

import jakarta.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

public class CourseSpecifications {




    public static Specification<CourseEntity> hasId(Long id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<CourseEntity> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<CourseEntity> hasTimePeriod(String period) {
        return (root, query, criteriaBuilder) ->
                period == null ? null : criteriaBuilder.equal(root.get("time"), (period.equalsIgnoreCase("AM")));
    }

    public static Specification<CourseEntity> hasRoomId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if(id == null) return null;
            Join<CourseEntity, ClassroomEntity> join = root.join("classroom");
            return criteriaBuilder.equal(join.get("id"), id);
        };
    }
    public static Specification<CourseEntity> hasRoomNumber(Integer roomNumber) {
        return (root, query, criteriaBuilder) -> {
            if(roomNumber == null) return null;
            Join<CourseEntity, ClassroomEntity> join = root.join("classroom");
            return criteriaBuilder.equal(join.get("room"), roomNumber);
        };
    }


}
