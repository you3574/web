package lab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lab.DB;

public class userDAO2 {
	public static List<user> findAll(int currentPage,int pageSize) throws Exception {
        String sql ="SELECT u.*, d.departmentName " +
                "FROM user u LEFT JOIN department d ON u.departmentId = d.id " + 
        		"LIMIT ?,?";
        try (Connection connection = DB.getConnection("student1");
        		PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, (currentPage - 1) * pageSize);
                statement.setInt(2, pageSize);
           try(ResultSet resultSet = statement.executeQuery()) {
            ArrayList<user> list = new ArrayList<user>();
            while (resultSet.next()) {
                user user = new user();
                user.setId(resultSet.getInt("id"));
                user.setUserid(resultSet.getString("userid"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setDepartmentId(resultSet.getInt("departmentId"));
                user.setDepartmentName(resultSet.getString("departmentName"));
                list.add(user);
            }
            return list;
           }
          }
    }
	public static int count2(int departmentId) throws Exception {
        String sql = "SELECT COUNT(*) FROM user where departmentId = ?";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql)){
     		 statement.setInt(1, departmentId);
     		 try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt(1);
        
        return 0;
    }}
        }
	
	public static int count() throws Exception {
        String sql ="SELECT COUNT(*) FROM user";
        try (Connection connection = DB.getConnection("student1");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt(1);
        }
        return 0;
    }
   
    public static List<user> findBydepartmentId(int departmentId,int currentPage, int pageSize) throws Exception {
        String sql = "SELECT u.*, d.departmentName " + 
                     "FROM user u LEFT JOIN department d ON u.departmentId = d.id " + 
                     "WHERE u.departmentId = ? " + 
                     "LIMIT ?,? ";
        	try (Connection connection = DB.getConnection("student1");
        		PreparedStatement statement = connection.prepareStatement(sql)){
        		statement.setInt(1, departmentId);
                statement.setInt(2, (currentPage - 1) * pageSize);
                statement.setInt(3, pageSize);
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
                    user.setDepartmentName(resultSet.getString("departmentName"));
                    list.add(user);
                }
                return list;
            }
        
        }
    }
}
