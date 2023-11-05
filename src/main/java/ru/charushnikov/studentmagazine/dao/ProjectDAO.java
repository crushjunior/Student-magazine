package ru.charushnikov.studentmagazine.dao;

import ru.charushnikov.studentmagazine.entity.Project;

import java.util.List;

public interface ProjectDAO {
    void createProject(Project project);

    void updateProject(Project project);

    Project getProject(int id);

    List<Project> getAllProjects();

    void deleteProject(int id);
}
