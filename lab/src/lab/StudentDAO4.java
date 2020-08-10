package lab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lab.DB;

public class StudentDAO4 {
    public static Student createStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setStudentNumber(resultSet.getString("studentNumber"));
        student.setName(resultSet.getString("name"));
        student.setDepartmentId(resultSet.getInt("departmentId"));
        student.setYear(resultSet.getInt("year"));
        student.setDepartmentName(resultSet.getString("departmentName"));
        student.setProfessorName(resultSet.getString("professorName"));
        return student;
    }

    public static List<Student> findAll() throws Exception {
        String sql = "SELECT s.*, p.professorName " + 
                     "FROM student s LEFT JOIN professor p ON s.departmentId = p.departmentId";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<Student> list = new ArrayList<Student>();
            while (resultSet.next())
                list.add(createStudent(resultSet));
            return list;
        }
    }

   
    public static List<Student> findByprofessor(int departmentId) throws Exception {
        String sql = "SELECT s.*, p.professorName " +
                     "FROM student s LEFT JOIN professor p ON s.departmentId = p.departmentId " +
                     "WHERE s.departmentId = ?";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<Student> list = new ArrayList<Student>();
                while (resultSet.next())
                    list.add(createStudent(resultSet));
                return list;
            }
        }
    }
}
