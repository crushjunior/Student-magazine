package ru.charushnikov.studentmagazine.servlet.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.charushnikov.studentmagazine.dao.ProjectDAO;
import ru.charushnikov.studentmagazine.dao.ProjectDAOImpl;
import ru.charushnikov.studentmagazine.entity.Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetAllProjectsServlet", value = "/project/get_all")
public class GetAllProjectsServlet extends HttpServlet {
    private final ProjectDAO projectDAO = new ProjectDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Project> allProjects = projectDAO.getAllProjects();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String projects = objectMapper.writeValueAsString(allProjects);
            resp.getWriter().write("Find projects: " + allProjects);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Projects not found");
        }
    }

}
