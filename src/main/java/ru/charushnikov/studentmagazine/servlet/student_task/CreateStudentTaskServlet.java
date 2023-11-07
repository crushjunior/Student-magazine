package ru.charushnikov.studentmagazine.servlet.student_task;

import ru.charushnikov.studentmagazine.dao.StudentTaskDAO;
import ru.charushnikov.studentmagazine.dao.StudentTaskDAOImpl;
import ru.charushnikov.studentmagazine.entity.StudentTask;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreateStudentTaskServlet", value = "/student_task/add")
public class CreateStudentTaskServlet extends HttpServlet {
    private final StudentTaskDAO studentTaskDAO = new StudentTaskDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId, taskId;
        if (req.getParameter("task_id") == null || req.getParameter("student_id") == null) {
            throw new IOException("Task_id or project_id is empty");
        } else {
            studentId = Integer.parseInt(req.getParameter("student_id"));
            taskId = Integer.parseInt(req.getParameter("task_id"));
        }

        StudentTask newStudentTask = new StudentTask();
        newStudentTask.setStudent_id(studentId);
        newStudentTask.setTask_id(taskId);
        try {
            studentTaskDAO.createStudentTask(newStudentTask);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Successful add new studentTask");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("StudentTask not added");
        }
    }
}
