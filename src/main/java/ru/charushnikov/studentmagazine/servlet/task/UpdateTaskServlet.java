package ru.charushnikov.studentmagazine.servlet.task;

import ru.charushnikov.studentmagazine.dao.TaskDAO;
import ru.charushnikov.studentmagazine.dao.TaskDAOImpl;
import ru.charushnikov.studentmagazine.entity.Task;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateTaskServlet", value = "/task/update")
public class UpdateTaskServlet extends HttpServlet {
    private final TaskDAO taskDAO = new TaskDAOImpl();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id, projectId;
        String name;
        if (req.getParameter("id") == null || req.getParameter("name") == null || req.getParameter("project_id") == null) {
            throw new IOException("Name or projectId or id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
            projectId = Integer.parseInt(req.getParameter("project_id"));
            name = req.getParameter("name");
        }

        Task updateTask = new Task();
        updateTask.setId(id);
        updateTask.setProject_id(projectId);
        updateTask.setName(name);
        try {
            taskDAO.updateTask(updateTask);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Task update successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Task not update");
        }
    }
}
