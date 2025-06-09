package com.unp.a3.Database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DataBaseConnection {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String USER;
    private static String PASS;
    private static String HOST;
    private static String PORT;
    private static String DB_NAME;
    private static String URL;
    
    private static DataBaseConnection instance;
    private Connection connection;
    
    private DataBaseConnection() {
        Dotenv dotenv = Dotenv.load();
        USER = dotenv.get("DB_USER");
        PASS = dotenv.get("DB_PASS");
        HOST = dotenv.get("DB_HOST");
        PORT = dotenv.get("DB_PORT");
        DB_NAME = dotenv.get("DB_NAME");
        URL = dotenv.get("DB_URL");       
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
            //System.out.println(URL);
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            //System.out.println("Conexão com %s aberta".formatted(URL));
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
