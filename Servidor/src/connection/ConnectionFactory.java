/*
 * Classe que realiza a conexão com o banco de dados MySQL
 */
package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data: 05/09/2017
 * @author Tarcnux
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/secret";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: " + ex);
        }
    }
    //Fechar a Conexão
    public static void closeConnection(Connection con){
       if(con != null) {
           try {
               con.close();
           } catch (SQLException ex) {
               System.err.println("Erro ao fechar a conexão: " + ex);
           }
       }
    }
    
    //Fechar a Conexão com Prepared Statement
    public static void closeConnection(Connection con, PreparedStatement stmt){
       if(stmt != null) {
           try {
               stmt.close();
           } catch (SQLException ex) {
               System.err.println("Erro ao fechar PreparedStatement: " + ex);
           }
       }
        closeConnection(con);
    }
    
    //Fechando ResultSet
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
       if(rs != null) {
           try {
               rs.close();
           } catch (SQLException ex) {
               System.err.println("Erro ao fechar ResultSet: " + ex);
           }
       }
        closeConnection(con,stmt);
    }
    
}