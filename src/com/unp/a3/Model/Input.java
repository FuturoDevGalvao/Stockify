package com.unp.a3.Model;

import com.unp.a3.Model.interfaces.Persistable;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Input extends Model implements Persistable {
    private String table;
    private int id;
    private Timestamp inputDate;
    private int quantity;
    private String supplier;
    private Product productModel;
 
    {
        this.table = "Inputs";
    }

    public Input() {
        
    }

    public Input(int quantity, String supplier, Product productModel) {
        this.quantity = quantity;
        this.supplier = supplier;
        this.productModel = productModel;
    }

    public Input(int id, Timestamp inputDate, int quantity, String supplier, Product productModel) {
        this(quantity, supplier, productModel);
        this.id = id;
        this.inputDate = inputDate;
    }
    
    @Override
    protected String getTableName() {
        return this.table;
    }

    @Override
    protected Model mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int productId = rs.getInt("ID_Product");
        Timestamp inputDate = rs.getTimestamp("Input_Date");
        int quantity = rs.getInt("Quantity");
        String supplier = rs.getString("Supplier");
        
        Product productModel = new Product();
        Model finded = productModel.find(productId);
        
        if (finded != null) {
            return new Input(id, inputDate, quantity, supplier, (Product) finded);
        }
        
        return null;
    }

    @Override
    public boolean save() {
        String query = "INSERT INTO %s (ID_Product, Quantity, Supplier) VALUES (?, ?, ?)".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, this.productModel.getId());
            prepareStatement.setInt(2, this.quantity);
            prepareStatement.setString(3, this.supplier);
            
            int rowsAffected = prepareStatement.executeUpdate(); 

            return rowsAffected > 0;        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }

        return false;    }

    @Override
    public boolean update() {
        String query = "UPDATE %s SET ID_Product = ?, Quantity = ?, Supplier = ? WHERE id = ?".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, this.productModel.getId());
            prepareStatement.setInt(2, this.quantity);
            prepareStatement.setString(3, this.supplier);
            prepareStatement.setInt(4, this.id);
            
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

    public String getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public Timestamp getInputDate() {
        return inputDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public Product getProductModel() {
        return productModel;
    }
}
