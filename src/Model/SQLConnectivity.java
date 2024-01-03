package Model;
import java.sql.*;
public class SQLConnectivity {
	private static Connection connection = null;
	
	static {		
		String URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
		String USER = "root";
		String PASSWORD = "Rokeshv@rm@2603";
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} 
		catch (SQLException e) {
			System.out.println("Error establishing database connection: " + e.getMessage());
		} 
	}
	
	public static Connection connectivity() {
		return connection;
	}
}