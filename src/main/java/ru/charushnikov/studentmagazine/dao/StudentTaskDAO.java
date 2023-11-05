package ru.charushnikov.studentmagazine.dao;

import ru.charushnikov.studentmagazine.entity.StudentTask;

import java.util.List;

public interface StudentTaskDAO {
    void createStudentTask(StudentTask studentTask);

    void deleteTask(int id);

    StudentTask getStudentTask(int id);

    List<StudentTask> getAllStudentTasks();

    List<StudentTask> getStudentTasksByStudentId(int studentId);

    List<StudentTask> getStudentTasksByTaskId(int taskId);
}
