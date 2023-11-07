package ru.charushnikov.studentmagazine.servlet.project;

import ru.charushnikov.studentmagazine.dao.ProjectDAO;
import ru.charushnikov.studentmagazine.dao.ProjectDAOImpl;
import ru.charushnikov.studentmagazine.entity.Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreateProjectServlet", value = "/project/add")
public class CreateProjectServlet extends HttpServlet {
    private final ProjectDAO projectDAO = new ProjectDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") == null) {
            throw new IOException("Name is empty");
        }

        Project newProject = new Project();
        newProject.setName(req.getParameter("name"));
        try {
            projectDAO.createProject(newProject);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Successful add new project");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Project not added");
        }
    }
}
