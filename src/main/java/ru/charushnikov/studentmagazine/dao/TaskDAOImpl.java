package ru.charushnikov.studentmagazine.dao;

import ru.charushnikov.studentmagazine.dbc.DBConnection;
import ru.charushnikov.studentmagazine.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {
    private static final String INSERT_QUERY = "INSERT INTO tasks (name, project_id) VALUES (?,?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM tasks  WHERE id=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM tasks";
    private static final String UPDATE_QUERY = "UPDATE tasks SET name=? project_id=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM tasks WHERE id=?";
    private static final String GET_BY_PROJECT_ID_QUERY = "SELECT * FROM tasks t JOIN projects p on t.project_id = p.id WHERE p.id=?";

    @Override
    public void createTask(Task task) throws SQLException {
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getProject_id());
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Task getTask(int id) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Task> getAllTasksByProjectId(int projectId) throws SQLException {
        List<Task> findTasks = new ArrayList<>();
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_PROJECT_ID_QUERY);
            preparedStatement.setInt(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setName(resultSet.getString("name"));
                task.setId(resultSet.getInt("id"));
                task.setProject_id(resultSet.getInt("project_id"));
                findTasks.add(task);
            }
            resultSet.close();
            preparedStatement.close();
            return findTasks;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTask(int id) {

    }
}
