package com.juandavyc.university.specifications;

import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.entities.CourseEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ClassroomSpecifications {


    public static Specification<ClassroomEntity> hasRoom(Integer room) {
        return (root, query, criteriaBuilder) ->
                room == null ? null : criteriaBuilder.equal(root.get("room"), room);
    }

    public static Specification<ClassroomEntity> hasId(Long id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<ClassroomEntity> hasCourseId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) return null;
            Join<ClassroomEntity, CourseEntity> join = root.join("courses");
            return criteriaBuilder.equal(join.get("id"), id);
        };
    }
    public static Specification<ClassroomEntity> hasCourseName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) return null;
            Join<ClassroomEntity, CourseEntity> join = root.join("courses");
            return criteriaBuilder.equal(join.get("name"), name);
        };
    }
    public static Specification<ClassroomEntity> hasCourseTime(String time) {
        return (root, query, criteriaBuilder) -> {
            if (time == null) return null;
            Join<ClassroomEntity, CourseEntity> join = root.join("courses");
            return criteriaBuilder.equal(join.get("time"), (time.equalsIgnoreCase("AM")));
        };
    }

}
