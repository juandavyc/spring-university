package com.juandavyc.university.repositories;

import com.juandavyc.university.entities.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface CourseRepository extends
        JpaRepository<CourseEntity, Long> ,
        JpaSpecificationExecutor<CourseEntity>

{

    CourseEntity findByName(String name);
    @NonNull
    Page<CourseEntity> findAll(Specification<CourseEntity> specification, @NonNull Pageable pageable);


    Boolean existsByTimeAndClassroomId(Boolean time, Long classroomId);

    Boolean existsByTimeAndName(Boolean time,String name);

}
