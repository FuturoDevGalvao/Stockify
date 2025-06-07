package com.unp.a3.Model;

import com.unp.a3.Database.DataBaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeModel extends Model implements Persistable {
    private String table;
    private int id;
    private String name;
    private String position;
    private String login;
    private String password;
    
    {
        this.table = "Employees";
    }

    public EmployeeModel() {
    }

    public EmployeeModel(String name, String position, String login, String password) {
        this.name = name;
        this.position = position;
        this.login = login;
        this.password = password;
    }
    
    public EmployeeModel(int id, String name, String position, String login, String password) {
        this(name, position, login, password);
        this.id = id;
    }
  
    @Override
    protected String getTableName() {
        return this.table;
    }

    @Override
    protected Model mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String position = rs.getString("position");
        String login = rs.getString("login");
        String password = rs.getString("password");
        return new EmployeeModel(id, name, position, login, password);
    }
    
    @Override
    public  boolean save() {
        String query = "INSERT INTO %s (Name, Position, Login, Password) VALUES (?, ?, ?, ?)".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, this.getName());
            prepareStatement.setString(2, this.getPosition());
            prepareStatement.setString(3, this.getLogin());
            prepareStatement.setString(4, this.getPassword());
            
            int rowsAffected = prepareStatement.executeUpdate(); 

            return rowsAffected > 0;        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }

        return false;
    }
            
    @Override
    public boolean update() {
        String query = "UPDATE %s SET Name = ?, Position = ?, Login = ?, Password = ? WHERE id = ?".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, this.getName());
            prepareStatement.setString(2, this.getPosition());
            prepareStatement.setString(3, this.getLogin());
            prepareStatement.setString(4, this.getPassword());
            prepareStatement.setInt(5, this.getId());
            
            int rowsAffected = prepareStatement.executeUpdate(); 

            return rowsAffected > 0;        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }

        return false;    
    }

    @Override
    public boolean delete() {
        String query = "DELETE FROM %s WHERE id = ?".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, this.getId());
            
            int rowsAffected = prepareStatement.executeUpdate(); 

            return rowsAffected > 0;        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }

        return false;        
    }

    @Override
    public String toString() {
        return "EmployeeModel{" + "table=" + table + ", id=" + id + ", name=" + name + ", position=" + position + ", login=" + login + ", password=" + password + '}';
    }
    
    public String getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
