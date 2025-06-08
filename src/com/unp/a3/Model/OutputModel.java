package com.unp.a3.Model;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OutputModel extends Model implements Persistable {
    private String table;
    private int id;
    private Timestamp outputDate;
    private int quantity;
    private String recipient;
    private ProductModel productModel;
 
    {
        this.table = "Outputs";
    }

    public OutputModel() {
        
    }

    public OutputModel(int quantity, String recipient, ProductModel productModel) {
        this.quantity = quantity;
        this.recipient = recipient;
        this.productModel = productModel;
    }

    public OutputModel(int id, Timestamp outputDate, int quantity, String recipient, ProductModel productModel) {
        this(quantity, recipient, productModel);
        this.id = id;
        this.outputDate = outputDate;
    }
    
    @Override
    protected String getTableName() {
        return this.table;
    }

    @Override
    protected Model mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int productId = rs.getInt("ID_Product");
        Timestamp outputDate = rs.getTimestamp("Output_Date");
        int quantity = rs.getInt("Quantity");
        String recipient = rs.getString("Recipient");
        
        ProductModel productModel = new ProductModel();
        Model finded = productModel.find(productId);
        
        if (finded != null) {
            return new OutputModel(id, outputDate, quantity, recipient, (ProductModel) finded);
        }
        
        return null;
    }

    @Override
    public boolean save() {
        String query = "INSERT INTO %s (ID_Product, Quantity, Recipient) VALUES (?, ?, ?)".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, this.productModel.getId());
            prepareStatement.setInt(2, this.quantity);
            prepareStatement.setString(3, this.recipient);
            
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
        String query = "UPDATE %s SET ID_Product = ?, Quantity = ?, Recipient = ? WHERE id = ?".formatted(getTableName());
        
        try {
            connection.openConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, this.productModel.getId());
            prepareStatement.setInt(2, this.quantity);
            prepareStatement.setString(3, this.recipient);
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

    public Timestamp getOutputDate() {
        return outputDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getRecipient() {
        return recipient;
    }

    public ProductModel getProductModel() {
        return productModel;
    }
}
