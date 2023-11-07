package ru.charushnikov.studentmagazine.servlet.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.charushnikov.studentmagazine.dao.StudentDAO;
import ru.charushnikov.studentmagazine.dao.StudentDAOImpl;
import ru.charushnikov.studentmagazine.entity.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetAllStudentsServlet", value = "/student/get_all")
public class GetAllStudentsServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Student> findStudents = studentDAO.getAllStudents();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String students = objectMapper.writeValueAsString(findStudents);
            resp.getWriter().write("Find students: " + findStudents);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Students not found");
        }
    }
}
