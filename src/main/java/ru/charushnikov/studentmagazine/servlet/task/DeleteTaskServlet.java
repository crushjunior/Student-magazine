package ru.charushnikov.studentmagazine.servlet.task;

import ru.charushnikov.studentmagazine.dao.TaskDAO;
import ru.charushnikov.studentmagazine.dao.TaskDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteTaskServlet", value = "/task/delete")
public class DeleteTaskServlet extends HttpServlet {
    private final TaskDAO taskDAO = new TaskDAOImpl();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        try {
            taskDAO.deleteTask(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Task delete successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Task not delete");
        }
    }
}
