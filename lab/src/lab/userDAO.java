package lab;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lab.DB;

public class userDAO {

    public static List<user> findAll() throws Exception {
        String sql = "SELECT * " + "from user";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<user> list = new ArrayList<user>();
            while (resultSet.next()) {
                user user = new user();
                user.setId(resultSet.getInt("id"));
                user.setUserid(resultSet.getString("userid"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setDepartmentId(resultSet.getInt("department"));
                list.add(user);
            }
            return list;
        }
    }
    public static List<user> findByName(String name) throws Exception {
        String sql = "SELECT * " + 
                     "FROM user "+ 
                     "WHERE name LIKE ?";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<user> list = new ArrayList<user>();
                while (resultSet.next()) {
                	user user = new user();
                    user.setId(resultSet.getInt("id"));
                    user.setUserid(resultSet.getString("userid"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setDepartmentId(resultSet.getInt("departmentId"));
                    list.add(user);
                }
                return list;
            }
        }
    }
}
