package ru.charushnikov.studentmagazine.servlet.project;

import ru.charushnikov.studentmagazine.dao.ProjectDAO;
import ru.charushnikov.studentmagazine.dao.ProjectDAOImpl;
import ru.charushnikov.studentmagazine.entity.Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateProjectServlet", value = "/project/update")
public class UpdateProjectServlet extends HttpServlet {
    private final ProjectDAO projectDAO = new ProjectDAOImpl();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        Project updateProject = new Project();
        updateProject.setId(id);
        updateProject.setName(req.getParameter("name"));
        try {
            projectDAO.updateProject(updateProject);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Project update successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Project not update");
        }
    }
}
