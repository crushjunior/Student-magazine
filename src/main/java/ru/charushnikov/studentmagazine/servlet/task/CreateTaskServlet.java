package ru.charushnikov.studentmagazine.servlet.task;

import ru.charushnikov.studentmagazine.dao.TaskDAO;
import ru.charushnikov.studentmagazine.dao.TaskDAOImpl;
import ru.charushnikov.studentmagazine.entity.Task;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreateTaskServlet", value = "/task/add")
public class CreateTaskServlet extends HttpServlet {
    private final TaskDAO taskDAO = new TaskDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") == null || req.getParameter("project_id") == null) {
            throw new IOException("Name or project_id is empty");
        }

        Task newTask = new Task();
        newTask.setName(req.getParameter("name"));
        newTask.setProject_id(Integer.parseInt(req.getParameter("project_id")));
        try {
            taskDAO.createTask(newTask);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Successful add new task");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Task not added");
        }
    }
}
