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

@WebServlet(name = "GetProjectServlet", value = "/project/get")
public class GetProjectServlet extends HttpServlet {
    private final ProjectDAO projectDAO = new ProjectDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = (req.getParameter("id"));
        }

        try {
            Project findProject = projectDAO.getProject(Integer.parseInt(id));
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String project = objectMapper.writeValueAsString(findProject);
            resp.getWriter().write("Find project: " + project);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Project not found");
        }
    }

}
