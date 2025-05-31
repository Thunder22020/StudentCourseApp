package com.app.studentcourse.controller;

import com.app.studentcourse.dto.ConverterDTO;
import com.app.studentcourse.dto.CourseDTO;
import com.app.studentcourse.dto.StudentDTO;
import com.app.studentcourse.entity.Course;
import com.app.studentcourse.entity.Student;
import com.app.studentcourse.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentDTOs = studentService.getAllStudents().stream()
                .map(ConverterDTO::convertToDTO)
                .toList();
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(ConverterDTO.convertToDTO(studentService.getStudentById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseDTO>> getStudentCourses(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.readCoursesByStudentId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody Student student) {
        return new ResponseEntity<>(ConverterDTO.convertToDTO(studentService.create(student)), HttpStatus.OK);
    }

    @PostMapping("/{id}/courses")
    public HttpStatus createCourse(@PathVariable Long id, @RequestBody List<Course> courses) {
        studentService.addCoursesToStudent(id, courses);
        return HttpStatus.OK;
    }

    @PutMapping
    public ResponseEntity<StudentDTO> update(@RequestBody Student student) {
        return new ResponseEntity<>(ConverterDTO.convertToDTO(studentService.update(student)), HttpStatus.OK);
    }

    @PutMapping("/{id}/courses")
    public HttpStatus updateCoursesByStudentId(@PathVariable Long id, @RequestBody List<Course> courses) {
        studentService.updateCoursesByStudentId(id, courses);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        studentService.delete(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}/courses")
    public HttpStatus deleteCoursesByStudentId(@PathVariable Long id) {
        studentService.deleteCoursesByStudentId(id);
        return HttpStatus.OK;
    }
}
