package com.unp.a3.Model;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Report extends Model implements Persistable {
    private String table;
    private int id;
    private String type;
    private Timestamp date;
    private String details;
    
    {
        this.table = "Reports";
    }

    @Override
    protected String getTableName() {
        return this.table;
    }

    public Report(String tipe, String details) {
        this.type = tipe;
        this.details = details;
    }

    public Report(int id, String tipe, Timestamp date, String details) {
        this(tipe, details);
        this.id = id;
        this.date = date;
    }
    
    @Override
    protected Model mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        String type = rs.getString("Type");
        Timestamp date = rs.getTimestamp("Date");
        String details = rs.getString("Details");
        return new Report(id, this.type, date, details);
    }

    @Override
    public boolean save() {
     String query = "INSERT INTO %s (Type, Details) VALUES (?, ?)".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, this.type);
            prepareStatement.setString(2, this.details);
            
            int rowsAffected = prepareStatement.executeUpdate(); 

            return rowsAffected > 0;        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }

        return false;     }

    @Override
    public boolean update() {
       String query = "UPDATE %s SET Type = ?, Details = ? WHERE id = ?".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, this.type);
            prepareStatement.setString(1, this.details);

            
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
            prepareStatement.setInt(1, this.id);
            
            int rowsAffected = prepareStatement.executeUpdate(); 

            return rowsAffected > 0;        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }

        return false;    
    }
    
}
