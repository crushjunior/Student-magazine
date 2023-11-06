package ru.charushnikov.studentmagazine.dao;


import ru.charushnikov.studentmagazine.entity.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {
    void createTask(Task task) throws SQLException;

    Task getTask(int id);

    List<Task> getAllTasks();

    List<Task> getAllTasksByProjectId(int projectId) throws SQLException;

    void updateTask(Task task);

    void deleteTask(int id);
}
