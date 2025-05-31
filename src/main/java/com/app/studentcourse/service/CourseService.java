package com.app.studentcourse.service;

import com.app.studentcourse.dto.ConverterDTO;
import com.app.studentcourse.dto.CourseDTO;
import com.app.studentcourse.dto.StudentDTO;
import com.app.studentcourse.entity.Course;
import com.app.studentcourse.entity.Student;
import com.app.studentcourse.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public List<StudentDTO> readByCourseId(Long courseId) {
        return courseRepository.findById(courseId).get().getStudents().stream()
                .map(ConverterDTO::convertToDTO).toList();
    }

    public void updateStudentsByCourseId(Long courseId, List<Student> students) {
        Course course = courseRepository.findById(courseId).get();
        course.setStudents(students);
        courseRepository.save(course);
    }

    public void deleteStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.getStudents().clear();
        courseRepository.save(course);
    }

    public void addStudentsToCourse(Long courseId, List<Student> students) {
        Course course = courseRepository.findById(courseId).get();
        course.getStudents().addAll(students);
        courseRepository.save(course);
    }
}
