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

@WebServlet(name = "GetTaskByProjectIdServlet", value = "/task/get_by_project_id")
public class GetTaskByProjectIdServlet extends HttpServlet {
    private final TaskDAO taskDAO = new TaskDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int project_id;
        if (req.getParameter("id") == null) {
            throw new IOException("Project_id is empty");
        } else {
            project_id = Integer.parseInt(req.getParameter("id"));
        }
        try {
            List<Task> findTasks = taskDAO.getAllTasksByProjectId(project_id);
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String tasks = objectMapper.writeValueAsString(findTasks);
            resp.getWriter().write("Find tasks of project #" + project_id + ": " + tasks);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Tasks not found");
        }
    }
}
