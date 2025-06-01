package com.app.studentcourse.service;

import com.app.studentcourse.dto.ConverterDTO;
import com.app.studentcourse.dto.CourseDTO;
import com.app.studentcourse.dto.CourseShortDTO;
import com.app.studentcourse.entity.Course;
import com.app.studentcourse.entity.Student;
import com.app.studentcourse.repository.CourseRepository;
import com.app.studentcourse.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student create(Student student) {
        if (student.getCourses() != null && !student.getCourses().isEmpty()) {
            List<Course> coursesFromDb = student.getCourses().stream()
                    .map(course -> courseRepository.findById(course.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Курс с id " + course.getId() + " не найден")))
                    .toList();

            for (Course course : coursesFromDb) {
                if (course.getStudents() == null) {
                    course.setStudents(new ArrayList<>());
                }
                course.getStudents().add(student);
            }

            student.setCourses(coursesFromDb);
        }

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

    public void addCoursesToStudent(Long studentId, List<CourseShortDTO> courses) {
        Student student = getStudentById(studentId);
        List<Course> courseList = new ArrayList<>();
        for (CourseShortDTO course : courses) {
            courseList.add(courseRepository.findAll().stream()
                    .filter(i->i.getName().equals(course.getName()))
                    .findAny()
                    .orElse(null));
        }
        student.getCourses().addAll(courseList);
        studentRepository.save(student);
        courseRepository.saveAll(courseList);
    }
}
