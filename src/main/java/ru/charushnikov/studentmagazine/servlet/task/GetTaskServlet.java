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

@WebServlet(name = "GetTaskServlet", value = "/task/get")
public class GetTaskServlet extends HttpServlet {
    private final TaskDAO taskDAO = new TaskDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        try {
            Task findTask = taskDAO.getTask(id);
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String task = objectMapper.writeValueAsString(findTask);
            resp.getWriter().write("Find task: " + task);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Task not found");
        }
    }

}
