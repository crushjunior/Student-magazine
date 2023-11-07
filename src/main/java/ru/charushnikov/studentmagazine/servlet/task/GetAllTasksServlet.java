package ru.charushnikov.studentmagazine.servlet.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.charushnikov.studentmagazine.dao.TaskDAO;
import ru.charushnikov.studentmagazine.dao.TaskDAOImpl;
import ru.charushnikov.studentmagazine.entity.Task;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetAllTasksServlet", value = "/task/get_all")
public class GetAllTasksServlet extends HttpServlet {
    private final TaskDAO taskDAO = new TaskDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Task> allTasks = taskDAO.getAllTasks();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String tasks = objectMapper.writeValueAsString(allTasks);
            resp.getWriter().write("Find tasks: " + tasks);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Tasks not found");
        }
    }
}
