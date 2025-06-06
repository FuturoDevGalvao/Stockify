package com.unp.a3.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel extends Model {
    private String table;
    private int id;
    private String name;
    private String category;
    private double privce;
    private int quantity;
            
    {
        this.table = "Produtos";
    }

    public ProductModel() {
    }

    public ProductModel(int id, String name, String category, double privce, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.privce = privce;
        this.quantity = quantity;
    }

    @Override
    protected String getTableName() {
        return this.table;
    }

    @Override
    protected Model mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String position = rs.getString("category");
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity");
        return new ProductModel(id, name, category, privce, quantity);
    }
    
}
