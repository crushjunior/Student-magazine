package ru.charushnikov.studentmagazine.dao;

import ru.charushnikov.studentmagazine.dbc.DBConnection;
import ru.charushnikov.studentmagazine.entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO{
    private static final String INSERT_QUERY = "INSERT INTO projects (name) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE projects SET name=? WHERE id=?";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM projects WHERE id=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM projects";
    private static final String DELETE_QUERY = "DELETE FROM projects WHERE id=?";
    @Override
    public void createProject(Project project) throws SQLException {
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, project.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateProject(Project project) throws SQLException {
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Project getProject(int id) throws SQLException {
        Project findProject = new Project();
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                findProject.setId(resultSet.getInt("id"));
                findProject.setName(resultSet.getString("name"));
            }
            resultSet.close();
            preparedStatement.close();
            return findProject;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Project> getAllProjects() throws SQLException {
        List<Project> allProjects = new ArrayList<>();
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setName(resultSet.getString("name"));
                project.setId(resultSet.getInt("id"));
                allProjects.add(project);
            }
            resultSet.close();
            preparedStatement.close();
            return allProjects;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteProject(int id) throws SQLException {
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
}
