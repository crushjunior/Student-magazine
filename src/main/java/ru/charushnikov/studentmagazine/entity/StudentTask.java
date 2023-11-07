package ru.charushnikov.studentmagazine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentTask {
    private int id;
    private int student_id;
    private int task_id;
}
