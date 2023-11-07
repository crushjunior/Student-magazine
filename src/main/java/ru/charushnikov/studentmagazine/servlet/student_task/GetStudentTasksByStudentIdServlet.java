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

@WebServlet(name = "GetStudentTasksByStudentIdServlet", value = "/student_task/by_student")
public class GetStudentTasksByStudentIdServlet extends HttpServlet {
    private final StudentTaskDAO studentTaskDAO = new StudentTaskDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        try {
            List<StudentTask> studentTasksList = studentTaskDAO.getStudentTasksByStudentId(id);
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String studentTasks = objectMapper.writeValueAsString(studentTasksList);
            resp.getWriter().write("Find StudentTasks of student: " + studentTasks);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("StudentTasks not found");
        }
    }

}
