package com.juandavyc.university.controllers;

import com.juandavyc.university.dtos.course.request.CourseRequestDTO;
import com.juandavyc.university.dtos.course.request.CourseUpdateDTO;
import com.juandavyc.university.dtos.course.response.CourseResponseDTO;
import com.juandavyc.university.dtos.student.response.StudentResponseDTO;
import com.juandavyc.university.services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Validated
@RestController
@RequestMapping(path = "api/course")
@RequiredArgsConstructor

public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> findAll(
            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.findAll(pageable));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CourseResponseDTO> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping(path = "search")
    public ResponseEntity<Page<CourseResponseDTO>> findByFilters(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String timePeriod,
            // room
            @RequestParam(required = false) Long roomId,
            @RequestParam(required = false) Integer roomNumber,

            @PageableDefault(size = 2, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                courseService.findByFilters(
                        id,
                        name,
                        timePeriod,
                        roomId,
                        roomNumber,
                        pageable
                )
        );
    }

    @PostMapping
    public ResponseEntity<String> create(
            @Valid @RequestBody CourseRequestDTO courseRequestDTO
    ) {

        return ResponseEntity.created(
                URI.create(
                        ("/").concat(
                                courseService.create(courseRequestDTO).getId().toString()
                        )
                )

        ).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<CourseResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CourseUpdateDTO courseUpdateDTO
    ) {

        return ResponseEntity.ok(
                courseService.update(id, courseUpdateDTO)
        );
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
