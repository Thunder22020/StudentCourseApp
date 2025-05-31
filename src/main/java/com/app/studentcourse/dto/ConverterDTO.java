package com.app.studentcourse.dto;

import com.app.studentcourse.entity.Course;
import com.app.studentcourse.entity.Student;

import java.util.stream.Collectors;

public class ConverterDTO {
    public static CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(course.getName());
        courseDTO.setStudents(course.getStudents().stream().map(ConverterDTO::convertToShortDTO).toList());
        return courseDTO;
    }

    public static StudentDTO convertToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setCourses(student.getCourses().stream().map(ConverterDTO::convertToShortDTO).toList());
        return studentDTO;
    }

    public static CourseShortDTO convertToShortDTO(Course course) {
        CourseShortDTO courseShortDTO = new CourseShortDTO();
        courseShortDTO.setName(course.getName());
        return courseShortDTO;
    }

    public static StudentShortDTO convertToShortDTO(Student student) {
        StudentShortDTO studentShortDTO = new StudentShortDTO();
        studentShortDTO.setName(student.getName());
        return studentShortDTO;
    }
}
