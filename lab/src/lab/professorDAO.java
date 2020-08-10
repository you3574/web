package lab;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lab.DB;

public class professorDAO {

    public static List<professor> findAll() throws Exception {
        String sql = "SELECT * FROM professor";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<professor> list = new ArrayList<professor>();
            while (resultSet.next()) {
                professor professor = new professor();
                professor.setId(resultSet.getInt("id"));
                professor.setDepartmentId(resultSet.getInt("departmentId"));
                professor.setProfessorName(resultSet.getString("professorName"));
                list.add(professor);
            }
            return list;
        }
    }
}
