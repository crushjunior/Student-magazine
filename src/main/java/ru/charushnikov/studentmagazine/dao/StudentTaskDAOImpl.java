package ru.charushnikov.studentmagazine.dao;

import ru.charushnikov.studentmagazine.dbc.DBConnection;
import ru.charushnikov.studentmagazine.entity.StudentTask;
import ru.charushnikov.studentmagazine.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentTaskDAOImpl implements StudentTaskDAO{
    private static final String INSERT_QUERY = "INSERT INTO student_tasks (student_id, task_id) VALUES (?,?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM student_tasks  WHERE id=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM student_tasks";
    private static final String UPDATE_QUERY = "UPDATE student_tasks SET student_id=?, task_id=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM student_tasks WHERE id=?";
    private static final String GET_BY_STUDENT_ID_QUERY = "SELECT * FROM student_tasks WHERE student_id=?";

    @Override
    public void createStudentTask(StudentTask studentTask) throws SQLException {
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, studentTask.getStudent_id());
            preparedStatement.setInt(2, studentTask.getTask_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTask(int id) throws SQLException {
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public StudentTask getStudentTask(int id) throws SQLException {
        StudentTask findStudentTask = new StudentTask();
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                findStudentTask.setId(resultSet.getInt("id"));
                findStudentTask.setTask_id(resultSet.getInt("task_id"));
                findStudentTask.setStudent_id(resultSet.getInt("student_id"));
            }
            resultSet.close();
            preparedStatement.close();
            return findStudentTask;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<StudentTask> getAllStudentTasks() throws SQLException {
        List<StudentTask> allStudentTasks = new ArrayList<>();
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentTask studentTask = new StudentTask();
                studentTask.setTask_id(resultSet.getInt("task_id"));
                studentTask.setId(resultSet.getInt("id"));
                studentTask.setStudent_id(resultSet.getInt("student_id"));
                allStudentTasks.add(studentTask);
            }
            resultSet.close();
            preparedStatement.close();
            return allStudentTasks;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<StudentTask> getStudentTasksByStudentId(int studentId) throws SQLException {
        List<StudentTask> studentTasks = new ArrayList<>();
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_STUDENT_ID_QUERY);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentTask findStudentTask = new StudentTask();
                findStudentTask.setId(resultSet.getInt("id"));
                findStudentTask.setTask_id(resultSet.getInt("task_id"));
                findStudentTask.setStudent_id(resultSet.getInt("student_id"));
                studentTasks.add(findStudentTask);
            }
            resultSet.close();
            preparedStatement.close();
            return studentTasks;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
