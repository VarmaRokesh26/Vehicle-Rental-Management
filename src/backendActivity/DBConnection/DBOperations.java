package backendActivity.DBConnection;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOperations {
	
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
				System.out.println("\n<--Signed Up succesfully!!-->");
			}
		}
		return res;
	}
	
	public static String selectAllVehicles(Connection connection, int vId) throws SQLException {
		String selectQuery = "SELECT * FROM vehicledetails";
		String res = "";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
				int id = resultSet.getInt("vehicle_Id");
				String vehicle_type = resultSet.getString("vehicle_type");
				String brand = resultSet.getString("brand");
				String model = resultSet.getString("model");
				String license_plate = resultSet.getString("license_plate");
				boolean available = resultSet.getBoolean("available");
				if(id==vId) {
					res = vehicle_type+","+brand+","+model+","+license_plate;	 
					break;
				}
				System.out.println("*******************************");
                System.out.println("ID            : " + id + "\nVehicle_Type  : " + vehicle_type + "\nBrand         : " + brand + "\nModel         : " + model + "\nLicense Plate : " + license_plate + "\nAvailable     : " + available);
			}
		}
        System.out.println("*******************************");
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
	
	
	public static void selectAllVehiclesIfAvailable(Connection connection, String vehicle_typeByUser) throws SQLException {
	    String selectQuery = "SELECT * FROM vehicledetails WHERE available = true AND vehicle_type = ?";
	    
	    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
	        preparedStatement.setString(1, vehicle_typeByUser);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                int id = resultSet.getInt("vehicle_Id");
	                String vehicle_type = resultSet.getString("vehicle_type");
	                String brand = resultSet.getString("brand");
	                String model = resultSet.getString("model");
	                String license_plate = resultSet.getString("license_plate");
	                boolean available = resultSet.getBoolean("available");
	                System.out.println("*******************************");
	                System.out.println("ID            : " + id + "\nVehicle_Type  : " + vehicle_type + "\nBrand         : " + brand + "\nModel         : " + model + "\nLicense Plate : " + license_plate + "\nAvailable     : " + available);
	            }
	        }
	        System.out.println("*******************************");
	    }
	}

	public static String updateForRenting(Connection connection, int vId, String Name, String mobileNo, double amountPaid) throws SQLException {
	    String res = selectAllVehicles(connection, vId);
	    String str[] = res.split(",");
	    String vt = str[0], b = str[1], m = str[2], lp = str[3];
	    String updateVehicledetailsQuery = "UPDATE vehicledetails SET available = 0 WHERE vehicle_Id = ?";
	    
	    String insertRentingDetailsQuery = "INSERT INTO rentingDetails (firstName, mobileNumber, vehicle_type, brand, model, license_plate, amountPaid) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(insertRentingDetailsQuery)) {
	        preparedStatement.setString(1, Name);
	        preparedStatement.setString(2, mobileNo);
	        preparedStatement.setString(3, vt);
	        preparedStatement.setString(4, b);
	        preparedStatement.setString(5, m);
	        preparedStatement.setString(6, lp);
	        preparedStatement.setDouble(7, amountPaid);

	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected >= 1) {
	            try (PreparedStatement updateVehicledetailsStatement = connection.prepareStatement(updateVehicledetailsQuery)) {
	                updateVehicledetailsStatement.setInt(1, vId);

	                int updateRows = updateVehicledetailsStatement.executeUpdate();
	                if (updateRows > 0) {
	                    return "Vehicle Rented Successfully, and available status updated.";
	                } 
	                else {
	                    return "Failed to update available status for vehicle with ID " + vId;
	                }
	            }
	        } 
	        else {
	            return "Failed to insert renting details for vehicle with ID " + vId;
	        }
	    }
	}
	
	public static void updateByAdmin(Connection connection, String lp, String vt, String b, String m, String lpo, String av) throws SQLException {
        String updateQuery = "UPDATE vehicledetails SET vehicle_type=?, brand=?, model=?, license_plate=?, available=? WHERE license_plate=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, vt);
            preparedStatement.setString(2, b);
            preparedStatement.setString(3, m);
            preparedStatement.setString(4, lpo);
            preparedStatement.setString(5, av);
            preparedStatement.setString(6, lp); 
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Update successful.");
            } else {
                System.out.println("No rows were updated.");
            }
        }
    }
	
	public static boolean checkAdminDetails(Connection connection, String email, String password) throws SQLException {
		boolean isAdmin = false;
		String checkQueryBySelect = "SELECT * FROM admindetails";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(checkQueryBySelect); 
				ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while(resultSet.next()) {
				String emailFromDB = resultSet.getString("email");
				String passwordFromDB = resultSet.getString("passCode");
				if(emailFromDB.equals(email) && passwordFromDB.equals(password)) {
					isAdmin = true;
					break;
				}
			}
		}
		return isAdmin;
	}
	
	public static boolean checkUserDetails(Connection connection, String email, String password) throws SQLException {
		boolean isUser = false;
		String checkQueryBySelect = "SELECT * FROM userdetails";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(checkQueryBySelect);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while(resultSet.next()) {
				String emailFromDB = resultSet.getString("email");
				String passwordFromDB = resultSet.getString("passCode");
				if(emailFromDB.equals(email) && passwordFromDB.equals(password)) {
					isUser= true;
					break;
				}
			}
		}
		return isUser;
	}
	
	public static List<Integer> availableVehicleIdCheck(Connection connection, int vId, String veh_type) throws SQLException {
        List<Integer> list = new ArrayList<>();

        String checkVehicleIds = "SELECT vehicle_Id FROM vehicledetails WHERE available = true AND vehicle_type = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkVehicleIds)) {
            preparedStatement.setString(1, veh_type);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int vehicle_Id = resultSet.getInt("vehicle_Id");
                    list.add(vehicle_Id);
                }
            }
        }
        return list;
    }
	
	public static void insertAdminDetails(Connection connection, String firstName, String lastName, String mobileNumber, String email, String passCode) throws SQLException {
		String insertQuery = "INSERT INTO admindetails (firstname, lastname, mobileNumber, email, passCode) VALUES (?, ?, ?, ?, ?)";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, mobileNumber);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, passCode);
			
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected >= 1) {
				System.out.println("Admin Added succesfully!!");
			}
		}
	}
}