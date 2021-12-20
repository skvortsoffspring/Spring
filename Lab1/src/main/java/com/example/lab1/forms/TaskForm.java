package com.example.lab1.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskForm {
    private Integer id;
    private String name;
    private String task;
}
