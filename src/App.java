import com.unp.a3.Database.DataBaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    
    public static void main(String[] args) {
        DataBaseConnection dbc = DataBaseConnection.getConnection();
        
        try {
            // Abrir conneção
            dbc.openConnection();
            
//            final String SQL = "SELECT nome, idade FROM users WHERE name LIKE '%Gabriel%'";
//            dbc.createStatement().executeUpdate(SQL);
//            
//            final String SQL2 = "SELECT nome, idade FROM users WHERE name LIKE ?";
//            dbc.prepareStatement(SQL).setString(0, "'%Gabriel%'");
            
            dbc.closeConnection();
        } catch (SQLException ex) {
            System.out.println("Erro ao tentar estabelecer a conexão: %s".formatted(ex.getMessage()));
        }
    }
    
}
