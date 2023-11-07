package ru.charushnikov.studentmagazine.dao;


import ru.charushnikov.studentmagazine.entity.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {
    void createTask(Task task) throws SQLException;

    Task getTask(int id) throws SQLException;

    List<Task> getAllTasks() throws SQLException;

    List<Task> getAllTasksByProjectId(int projectId) throws SQLException;

    void updateTask(Task task) throws SQLException;

    void deleteTask(int id) throws SQLException;
}
