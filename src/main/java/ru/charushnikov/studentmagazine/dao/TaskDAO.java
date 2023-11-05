package ru.charushnikov.studentmagazine.dao;


import ru.charushnikov.studentmagazine.entity.Task;

import java.util.List;

public interface TaskDAO {
    void createTask(Task task);

    Task getTask(int id);

    List<Task> getAllTasks();

    List<Task> getAllTasksByProjectId(int projectId);

    void updateTask(Task task);

    void deleteTask(int id);
}
