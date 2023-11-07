package ru.charushnikov.studentmagazine.servlet.student;

import ru.charushnikov.studentmagazine.dao.StudentDAO;
import ru.charushnikov.studentmagazine.dao.StudentDAOImpl;
import ru.charushnikov.studentmagazine.entity.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UpdateStudentServlet", value = "/student/update")
public class UpdateStudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        Student updateStudent = new Student();
        updateStudent.setId(id);
        updateStudent.setName(req.getParameter("name"));
        try {
            studentDAO.updateStudent(updateStudent);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Student update successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Student not update");
        }
    }
}
