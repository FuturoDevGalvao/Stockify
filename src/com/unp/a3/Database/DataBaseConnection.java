package com.unp.a3.Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DataBaseConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "galvao";
    private static final String PASS = "galvao123";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "stockify";
    private static final String URL = "jdbc:mysql://%s:%s/%s".formatted(HOST, PORT, DB_NAME);
    
    private static DataBaseConnection instance;
    private Connection connection;
    
    private DataBaseConnection() {
        
    }
    
    public static DataBaseConnection getConnection() {
        if (instance == null) {
            return instance = new DataBaseConnection();
        }
        
        return instance;
    }
    
    public void openConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexão com %s aberta".formatted(URL));
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver %s (necessário para a conexão) não localizado.".formatted(URL));
        }
    }
    
    public void closeConnection() {
        try {
            
            if (this.connection != null && !this.connection.isClosed()) this.connection.close();
            System.out.println("Conexão com %s fechada".formatted(URL));

        } catch (SQLException ex) {
            System.out.println("Erro ao tentar encerrar a conexão em %s\nMotivo: %s".formatted(URL, ex.getMessage()));
        }
    }
    
   public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    // Método para criar um PreparedStatement para SQL parametrizado
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }
}
