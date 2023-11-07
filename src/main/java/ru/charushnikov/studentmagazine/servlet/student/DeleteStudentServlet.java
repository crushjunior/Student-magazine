package ru.charushnikov.studentmagazine.servlet.student;

import ru.charushnikov.studentmagazine.dao.StudentDAO;
import ru.charushnikov.studentmagazine.dao.StudentDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteStudentServlet", value = "/student/delete")
public class DeleteStudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        try {
            studentDAO.deleteStudent(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Student delete successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Student not delete");
        }
    }
}
