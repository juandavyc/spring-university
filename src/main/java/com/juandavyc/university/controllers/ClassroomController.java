package com.juandavyc.university.controllers;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @GetMapping
    public ResponseEntity<Page<ClassroomResponseDTO>> findAll(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(classroomService.findAll(pageable));
    }

    @GetMapping(path = "search")
    public ResponseEntity<Page<ClassroomResponseDTO>> findByFilters(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) Long id ,
            @RequestParam(required = false) Integer room
            ) {
        return ResponseEntity.ok(classroomService.findByFilters(id, room, pageable));
    }

    @GetMapping(path = "courses")
    public ResponseEntity<List<ClassroomWithCoursesResponseDTO>> findAllWithCourses() {
        return ResponseEntity.ok(classroomService.findAllWithCourses());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassroomResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findById(id));
    }

    @GetMapping(path = "/{id}/courses")
    public ResponseEntity<ClassroomWithCoursesResponseDTO> findByIdWithCourses(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findByIdWithCourses(id));
    }


}
