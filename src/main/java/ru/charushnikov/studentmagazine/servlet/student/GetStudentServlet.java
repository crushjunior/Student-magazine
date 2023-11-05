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

@WebServlet(name = "GetStudentServlet", value = "/student/get")
public class GetStudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = (req.getParameter("id"));
        }

        try {
            Student findStudent = studentDAO.getStudent(Integer.parseInt(id));
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper objectMapper = new ObjectMapper();
            String student = objectMapper.writeValueAsString(findStudent);
            resp.getWriter().write("Find student: " + student);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Student not found");
        }
    }

}
