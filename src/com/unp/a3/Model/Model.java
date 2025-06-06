package com.unp.a3.Model;

import com.unp.a3.Database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;

public abstract class Model {
    protected DataBaseConnection connection;

    public Model() {
        this.connection = DataBaseConnection.getConnection();
    }

    // Subclasse define o nome da tabela
    protected abstract String getTableName();

    // Subclasse transforma uma linha do ResultSet em um objeto do tipo correto
    protected abstract Model mapRow(ResultSet rs) throws SQLException;

    public ArrayList<Model> getAll() {
        ArrayList<Model> list = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName();

        try (Connection conn = (Connection) connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Model find(int id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";

        
        try (Connection conn = (Connection) connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
