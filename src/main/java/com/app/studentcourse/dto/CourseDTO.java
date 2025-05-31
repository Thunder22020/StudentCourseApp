package com.app.studentcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO extends CourseShortDTO {
    private List<StudentShortDTO> students;
}
