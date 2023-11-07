package ru.charushnikov.studentmagazine.servlet.project;

import ru.charushnikov.studentmagazine.dao.ProjectDAO;
import ru.charushnikov.studentmagazine.dao.ProjectDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteProjectServlet", value = "/project/delete")
public class DeleteProjectServlet extends HttpServlet {
    private final ProjectDAO projectDAO = new ProjectDAOImpl();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        try {
            projectDAO.deleteProject(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Project delete successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Project not delete");
        }
    }
}
