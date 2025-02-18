package com.juandavyc.university.repositories;

import com.juandavyc.university.entities.ClassroomEntity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface ClassroomRepository
        extends
        JpaRepository<ClassroomEntity, Long>,
        JpaSpecificationExecutor<ClassroomEntity>
{

    @NonNull
    @Override
    Page<ClassroomEntity> findAll(Specification<ClassroomEntity> specifications,@NonNull Pageable pageable);

   // Boolean existsById(@NonNull Long id);
    Boolean existsByRoom(Integer room);



    // replaced by Specification
    // @Query("SELECT c FROM ClassroomEntity c WHERE (:id IS NULL OR c.id=:id) AND (:room IS NULL OR c.room=:room)")
    // Page<ClassroomEntity> findByFilters(@Param("id") Long id, @Param("room") Integer room, Pageable pageable);

}
