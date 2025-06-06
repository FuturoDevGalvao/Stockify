package com.unp.a3.Model;

import com.unp.a3.Database.DataBaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel extends Model {
    private String table;
    private int id;
    private String name;
    private String position;
    private String login;
    private String password;
    
    {
        this.table = "Funcionarios";
    }

    public EmployeeModel() {
    }

    public EmployeeModel(String name, String position, String login, String password) {
        this.name = name;
        this.position = position;
        this.login = login;
        this.password = password;
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
        return new EmployeeModel(name, position, login, password);
    }

    
}
