package ru.charushnikov.studentmagazine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {
    private int id;
    private String description;
    private int project_id;
}
