package ru.charushnikov.studentmagazine.servlet.student_task;

import ru.charushnikov.studentmagazine.dao.StudentTaskDAO;
import ru.charushnikov.studentmagazine.dao.StudentTaskDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteStudentTaskServlet", value = "/student_task/delete")
public class DeleteStudentTaskServlet extends HttpServlet {
    private final StudentTaskDAO studentTaskDAO = new StudentTaskDAOImpl();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") == null) {
            throw new IOException("id is empty");
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }

        try {
            studentTaskDAO.deleteTask(id);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("StudentTask delete successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("StudentTask not delete");
        }
    }
}
