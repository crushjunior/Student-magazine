package ru.charushnikov.studentmagazine.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {
    private int id;
    private String name;
    private int project_id;
}
