package com.juandavyc.university.controllers;

import com.juandavyc.university.dtos.classroom.request.ClassroomRequestDTO;
import com.juandavyc.university.dtos.classroom.request.ClassroomWithCoursesRequestDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomResponseDTO;
import com.juandavyc.university.dtos.classroom.response.ClassroomWithCoursesResponseDTO;
import com.juandavyc.university.dtos.course.request.CourseRequestFullDTO;
import com.juandavyc.university.dtos.course.request.CourseRequestNameTimeDTO;
import com.juandavyc.university.services.ClassroomService;

import com.juandavyc.university.services.CourseService;
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
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "api/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<ClassroomResponseDTO>> findAll(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(classroomService.findAll(pageable));
    }


    @GetMapping(path = "search")
    public ResponseEntity<Page<ClassroomResponseDTO>> findByFilters(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Integer room
    ) {
        return ResponseEntity.ok(classroomService.findByFilters(id, room, pageable));
    }

    @GetMapping(path = "courses")
    public ResponseEntity<Page<ClassroomWithCoursesResponseDTO>> findAllWithCourses(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(classroomService.findAllWithCourses(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassroomResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findById(id));
    }

    @GetMapping(path = "/{id}/courses")
    public ResponseEntity<ClassroomWithCoursesResponseDTO> findByIdWithCourses(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findByIdWithCourses(id));
    }

    @PostMapping
    public ResponseEntity<String> create(
            @Valid @RequestBody ClassroomRequestDTO classroomRequestDTO
    ) {
        return ResponseEntity.created(
                URI.create(
                        ("/").concat(classroomService.create(classroomRequestDTO))
                )
        ).build();
    }

    @PostMapping(path = "courses")
    public ResponseEntity<String> createClassroomAndCourses(
           @Valid @RequestBody ClassroomWithCoursesRequestDTO classroomWithCoursesRequestDTO
    ) {

        return ResponseEntity.created(
                URI.create(
                        ("/").concat(classroomService.create(classroomWithCoursesRequestDTO))
                )
        ).build();

    }

    @PostMapping(path = "{id}/courses")
    public ResponseEntity<String> createWithCourses(
            @PathVariable Long id,
            @Valid @RequestBody List<CourseRequestNameTimeDTO> courseRequestDTO
    ) {

        return ResponseEntity.created(
                URI.create(
                        ("/").concat(classroomService.create(id, courseRequestDTO))
                )
        ).build();

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ClassroomResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ClassroomRequestDTO classroomDTO
    ){
        return ResponseEntity.ok(classroomService.update(id, classroomDTO));
    }

    @PutMapping(path = "{id}/courses")
    public ResponseEntity<ClassroomWithCoursesResponseDTO> updateCourses(
            @PathVariable Long id,
            @Valid @RequestBody List<@Valid CourseRequestFullDTO> courseRequestDTOS
    ){
        return ResponseEntity.ok(classroomService.updateCourses(id, courseRequestDTOS));
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classroomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
