package ru.charushnikov.studentmagazine.servlet.student_task;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.charushnikov.studentmagazine.dao.StudentTaskDAO;
import ru.charushnikov.studentmagazine.dao.StudentTaskDAOImpl;
import ru.charushnikov.studentmagazine.entity.StudentTask;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetAllStudentTasksServlet", value = "/student_task/get_all")
public class GetAllStudentTasksServlet extends HttpServlet {
    private final StudentTaskDAO studentTaskDAO = new StudentTaskDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<StudentTask> allStudentTasks = studentTaskDAO.getAllStudentTasks();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String studentTasks = objectMapper.writeValueAsString(allStudentTasks);
            resp.getWriter().write("Find StudentTasks: " + studentTasks);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("StudentTasks not found");
        }
    }
}
