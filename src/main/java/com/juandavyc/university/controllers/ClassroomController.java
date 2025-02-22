package com.juandavyc.university.controllers;

import com.juandavyc.university.dtos.classroom.request.ClassroomRequestDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.entities.ClassroomEntity;
import com.juandavyc.university.services.ClassroomService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Validated
@RestController
@RequestMapping(path = "api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @GetMapping
    public ResponseEntity<Page<ClassroomResponseDTO>> findAll(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(classroomService.findAll(pageable));
    }

    @GetMapping(path = "courses")
    public ResponseEntity<Page<ClassroomWithCoursesResponseDTO>> findAllWithCourses(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(classroomService.findAllWithCourses(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassroomResponseDTO> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(classroomService.findById(id));
    }

    @GetMapping(path = "/{id}/courses")
    public ResponseEntity<ClassroomWithCoursesResponseDTO> findByIdWithCourses(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findByIdWithCourses(id));
    }

    @GetMapping(path = "search")
    public ResponseEntity<Page<ClassroomWithCoursesResponseDTO>> findByFilters(

            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Integer room,
            // course
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String courseTimePeriod,

            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable

    ) {
        return ResponseEntity.ok(classroomService.findByFilters(
                id,
                room,
                courseId,
                courseName,
                courseTimePeriod,
                pageable));
    }

    @PostMapping
    public ResponseEntity<ClassroomEntity> create(
            @Valid @RequestBody ClassroomRequestDTO classroomRequestDTO
    ) {
        return ResponseEntity.created(
                URI.create(
                        ("/").concat(classroomService.create(classroomRequestDTO).getId().toString())
                )
        ).build();
    }
//
    @PutMapping(path = "{id}")
    public ResponseEntity<ClassroomResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ClassroomRequestDTO classroomDTO
    ){
        return ResponseEntity.ok(classroomService.update(id, classroomDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classroomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
