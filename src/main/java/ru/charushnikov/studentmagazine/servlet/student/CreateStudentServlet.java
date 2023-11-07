package ru.charushnikov.studentmagazine.servlet.student;

import ru.charushnikov.studentmagazine.dao.StudentDAO;
import ru.charushnikov.studentmagazine.dao.StudentDAOImpl;
import ru.charushnikov.studentmagazine.entity.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "StudentCreateServlet", value = "/student/add")
public class CreateStudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") == null) {
            throw new IOException("Name is empty");
        }

        Student newStudent = new Student();
        newStudent.setName(req.getParameter("name"));
        try {
            studentDAO.createStudent(newStudent);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Successful add new student");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Student not added");
        }
    }
}
