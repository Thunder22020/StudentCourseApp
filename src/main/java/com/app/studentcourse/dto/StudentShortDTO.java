package com.app.studentcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentShortDTO {
    private String name;
    private int age;
    private Long id;
}
