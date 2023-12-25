package backendActivity.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDData {
	
	public static String insertNewVehicle(Connection connection, String vehicle_type, String brand, String model, String license_plate, String available) throws SQLException {
		String res = "";
        String insertQuery = "INSERT INTO vehicledetails (vehicle_type, brand, model, license_plate, available) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
        	
            preparedStatement.setString(1, vehicle_type);
            preparedStatement.setString(2, brand);
            preparedStatement.setString(3, model);
            preparedStatement.setString(4, license_plate);
            preparedStatement.setString(5, available);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected>=1) {
            	System.out.println("Data Inserted!!");
            	res = "["+vehicle_type+", "+brand+", "+model+", "+license_plate+", "+available+"]";
            }
        }
        return res;
    }
	
	public static String insertUserDetails(Connection connection, String firstName, String lastName, String mobileNumber, String email, String passCode) throws SQLException{
		String res = "WelCome to Varma's AutoMobiles Renting";
		String insertQuery = "INSERT INTO userdetails (firstname, lastname, mobileNumber, email, passCode) VALUES (?, ?, ?, ?, ?)";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, mobileNumber);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, passCode);
			
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected >= 1) {
				System.out.println("Signed Up succesfully!!");
			}
		}
		return res;
	}
	public static void deleteVehicle(Connection connection, String licensePlate) throws SQLException {
        String deleteQuery = "DELETE FROM vehicledetails WHERE license_plate = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, licensePlate);

            preparedStatement.executeUpdate();
            System.out.println(" Data Deleted.");
        }
    }
	
	public static void selectAllVehicles(Connection connection) throws SQLException {
	    String selectQuery = "SELECT * FROM vehicledetails";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        while (resultSet.next()) {
	            int id = resultSet.getInt("vehicle_Id");
	            String vehicle_type = resultSet.getString("vehicle_type");
	            String brand = resultSet.getString("brand");
	            String model = resultSet.getString("model");
	            String license_plate = resultSet.getString("license_plate");
	            boolean available = resultSet.getBoolean("available");

	            System.out.println("[  ID: " + id + ", Vehicle_Type: " + vehicle_type + ", Brand: " + brand + ", Model: " + model + ", License Plate: " + license_plate + ", Available: " + available+"   ]");
	        }
	    }
	}	
	
	public static void selectAllVehiclesIfAvailable(Connection connection, String vehicle_typeByUser) throws SQLException {
	    String selectQuery = "SELECT * FROM vehicledetails WHERE vehicle_type = ?";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	         ResultSet resultSet = preparedStatement.executeQuery()) {
	    	
	    	preparedStatement.setString(1, vehicle_typeByUser);
	        while (resultSet.next()) {
	            int id = resultSet.getInt("vehicle_Id");
	            String vehicle_type = resultSet.getString("vehicle_type");
	            String brand = resultSet.getString("brand");
	            String model = resultSet.getString("model");
	            String license_plate = resultSet.getString("license_plate");
	            boolean available = resultSet.getBoolean("available");

	            System.out.println("[  ID: " + id + ", Vehicle_Type: " + vehicle_type + ", Brand: " + brand + ", Model: " + model + ", License Plate: " + license_plate + ", Available: " + available+"   ]");
	        }
	    }
	}
	
	public static void updateForRenting(Connection connection, int Id) throws SQLException {
		String updateQuery = "UPDATE vehicledetails SET available = 0 WHERE vehicle_Id = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			ResultSet resultSet = preparedStatement.executeQuery()) {
				
				preparedStatement.setInt(1, Id);
				preparedStatement.executeUpdate();
				System.out.println("After Data Verification and Payment Get Key");
		}
	}
}