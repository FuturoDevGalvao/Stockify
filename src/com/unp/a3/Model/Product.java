package com.unp.a3.Model;

import com.unp.a3.Model.interfaces.Persistable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product extends Model implements Persistable {
    private String table;
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;
            
    {
        this.table = "Products";
    }

    public Product() {
    }

    public Product(String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Product(int id, String name, String category, double price, int quantity) {
        this(name, category, price, quantity);
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
        String category = rs.getString("category");
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity");
        return new Product(id, name, category, price, quantity);
    }
    
    @Override
    public  boolean save() {
        String query = "INSERT INTO %s (Name, Category, Price, Quantity) VALUES (?, ?, ?, ?)".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, this.name);
            prepareStatement.setString(2, this.category);
            prepareStatement.setDouble(3, this.price);
            prepareStatement.setInt(4, this.quantity);
            
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
        String query = "UPDATE %s SET Name = ?, Category = ?, Price = ?, Quantity = ? WHERE id = ?".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, this.name);
            prepareStatement.setString(2, this.category);
            prepareStatement.setDouble(3, this.price);
            prepareStatement.setInt(4, this.quantity);
            prepareStatement.setInt(5, this.id);
            
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
    
    @Override
    public String toString() {
        return "ProductModel{" + "table=" + table + ", id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", quantity=" + quantity + '}';
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
