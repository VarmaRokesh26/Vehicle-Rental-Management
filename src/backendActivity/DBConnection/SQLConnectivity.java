package backendActivity.DBConnection;
import java.sql.*;
public class SQLConnectivity {
	
	public Connection connectivity() {
		String URL = "jdbc:mysql://localhost:3306/vehiclerentalmanagement";
		String USER = "root";
		String PASSWORD = "Rokeshv@rm@2603";
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} 
		catch (SQLException e) {
			System.out.println(e.toString());
		} 
		return connection;
	}
}