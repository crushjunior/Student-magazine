package ru.charushnikov.studentmagazine.dao;

import ru.charushnikov.studentmagazine.entity.StudentTask;

import java.sql.SQLException;
import java.util.List;

public interface StudentTaskDAO {
    void createStudentTask(StudentTask studentTask) throws SQLException;

    void deleteTask(int id) throws SQLException;

    StudentTask getStudentTask(int id) throws SQLException;

    List<StudentTask> getAllStudentTasks() throws SQLException;

    List<StudentTask> getStudentTasksByStudentId(int studentId) throws SQLException;
}
