package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public Connection getConnection() throws ClassNotFoundException {
		
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/pets_db", "SA", "");
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}

//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class ConnectDatabase {
//   public static void main(String[] args) {
//      Connection con = null;
//      
//      try {
//         //Registering the HSQLDB JDBC driver
//         Class.forName("org.hsqldb.jdbc.JDBCDriver");
//         //Creating the connection with HSQLDB
//         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
//         if (con!= null){
//            System.out.println("Connection created successfully");
//            
//         }else{
//            System.out.println("Problem with creating connection");
//         }
//      
//      }  catch (Exception e) {
//         e.printStackTrace(System.out);
//      }
//   }
//}