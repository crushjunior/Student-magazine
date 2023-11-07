package ru.charushnikov.studentmagazine.dao;

import ru.charushnikov.studentmagazine.entity.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDAO {
    void createProject(Project project) throws SQLException;

    void updateProject(Project project) throws SQLException;

    Project getProject(int id) throws SQLException;

    List<Project> getAllProjects() throws SQLException;

    void deleteProject(int id) throws SQLException;
}
