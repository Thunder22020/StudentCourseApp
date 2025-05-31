package com.app.studentcourse.service;

import com.app.studentcourse.dto.ConverterDTO;
import com.app.studentcourse.dto.CourseDTO;
import com.app.studentcourse.entity.Course;
import com.app.studentcourse.entity.Student;
import com.app.studentcourse.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public List<CourseDTO> readCoursesByStudentId(Long id) {
        return studentRepository.findById(id).get().getCourses().stream().map(ConverterDTO::convertToDTO).toList();
    }

    public void updateCoursesByStudentId(Long id, List<Course> courses) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Студент с ID " + id + " не найден"));

        student.setCourses(courses);
        studentRepository.save(student);
    }

    public void deleteCoursesByStudentId(Long id) {
        Student student = getStudentById(id);
        student.getCourses().clear();
        studentRepository.save(student);
    }

    public void addCoursesToStudent(Long studentId, List<Course> courses) {
        Student student = getStudentById(studentId);
        student.getCourses().addAll(courses);
        studentRepository.save(student);
    }
}
