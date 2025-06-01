package com.app.studentcourse.controller;

import com.app.studentcourse.dto.ConverterDTO;
import com.app.studentcourse.dto.CourseDTO;
import com.app.studentcourse.dto.StudentDTO;
import com.app.studentcourse.dto.StudentShortDTO;
import com.app.studentcourse.entity.Course;
import com.app.studentcourse.entity.Student;
import com.app.studentcourse.repository.CourseRepository;
import com.app.studentcourse.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() {
        List<CourseDTO> courseDTOS = courseService.findAll().stream()
                .map(ConverterDTO::convertToDTO)
                .toList();
        return new ResponseEntity<>(courseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(ConverterDTO.convertToDTO(courseService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> findStudentsByCourseId(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.readByCourseId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.create(course), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/students")
    public HttpStatus addStudentsToCourse(@PathVariable Long id, @RequestBody List<StudentShortDTO> students) {
        courseService.addStudentsToCourse(id, students);
        return HttpStatus.CREATED;
    }

    @PutMapping
    public ResponseEntity<CourseDTO> update(@RequestBody Course course) {
        return new ResponseEntity<>(ConverterDTO.convertToDTO(courseService.update(course)), HttpStatus.OK);
    }

    @PutMapping("/{id}/students")
    public HttpStatus updateStudentsByCourseId(@PathVariable Long id, @RequestBody List<Student> students) {
        courseService.updateStudentsByCourseId(id, students);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        courseService.deleteById(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}/students")
    public HttpStatus deleteStudentsByCourseId(@PathVariable Long id) {
        courseService.deleteStudentsByCourseId(id);
        return HttpStatus.OK;
    }
}
