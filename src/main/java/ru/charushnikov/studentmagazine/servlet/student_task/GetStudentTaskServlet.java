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

@WebServlet(name = "GetStudentTaskServlet", value = "/student_task/get")
public class GetStudentTaskServlet extends HttpServlet {
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
            StudentTask findStudentTask = studentTaskDAO.getStudentTask(id);
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String studentTask = objectMapper.writeValueAsString(findStudentTask);
            resp.getWriter().write("Find StudentTask: " + studentTask);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("StudentTask not found");
        }
    }

}
