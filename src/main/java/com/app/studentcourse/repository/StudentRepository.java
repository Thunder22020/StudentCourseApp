package com.app.studentcourse.repository;

import com.app.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
