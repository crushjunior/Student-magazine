package ru.charushnikov.studentmagazine.dao;

import ru.charushnikov.studentmagazine.dbc.DBConnection;
import ru.charushnikov.studentmagazine.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{
    private static final String INSERT_QUERY = "INSERT INTO students (name) VALUES (?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM students  WHERE id=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM students";
    private static final String UPDATE_QUERY = "UPDATE students SET name=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM students WHERE id=?";

    @Override
    public void createStudent(Student student) throws SQLException {
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, student.getName());
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Student getStudent(int id) throws SQLException {
        Student findStudent = new Student();
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                findStudent.setId(resultSet.getInt("id"));
                findStudent.setName(resultSet.getString("name"));
            }
            resultSet.close();
            preparedStatement.close();
            return findStudent;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> allStudents = new ArrayList<>();
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setName(resultSet.getString("name"));
                student.setId(resultSet.getInt("id"));
                allStudents.add(student);
            }
            resultSet.close();
            preparedStatement.close();
            return allStudents;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteStudent(int id) throws SQLException {
        try (Connection connection = DBConnection.toConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
