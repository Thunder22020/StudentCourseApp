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
public class StudentDTO extends StudentShortDTO {
    private List<CourseShortDTO> courses;
}

