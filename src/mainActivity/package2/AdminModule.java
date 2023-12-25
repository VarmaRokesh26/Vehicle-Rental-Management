package mainActivity.package2;

import java.sql.Connection;
import java.util.*;
import backendActivity.DBConnection.*;
import mainActivity.package1.*;

public class AdminModule{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SQLConnectivity sqlConnectivity = new SQLConnectivity();
        Connection con = sqlConnectivity.connectivity();

        System.out.println("!! Welcome Back Admin !!");
        while(true) {
        	System.out.print("\nEnter \nL -> Logout \nI -> Add Vehicle \nU -> Update \nD -> Delet \nS -> Display \nA -> Add Admin \n---------------> ");
        	char operation = sc.nextLine().charAt(0);
        	String vehicle_type = "@", brand = "@", model = "@", license_plate = "@", available = "@";
        	
        	if (operation == 'L' || operation == 'l') {
                System.out.println("\nLogging out. Goodbye!");
                System.out.println("----------------------------------------------------------------");
                break;
            }
        	else if (operation == 'I' || operation == 'i') {
        		// Inserting logic
        		System.out.println("\nEnter the Vehicle Details to be Added:");
        		
        		while (true) {
        			// Bike or Car
        			System.out.print("Enter 'Bike' or 'Car': ");
        			vehicle_type = sc.nextLine();
        			if (vehicle_type.equalsIgnoreCase("Car") || vehicle_type.equalsIgnoreCase("Bike")) {
        				break;
        			} else {
        				System.out.println("Enter only 'Car' or 'Bike'");
        			}
        		}
        		
        		while (true) {
        			// Brand name
        			System.out.print("Enter brand name            : ");
        			String[] bikes = {"Yamaha", "Bajaj", "Honda", "TVS", "Royal Enfield", "KTM"};
        			String[] cars = {"Maruti", "Hyundai", "Mahindra", "Tata", "Ford", "Honda", "Toyota", "Renault"};
        			brand = sc.nextLine();
        			if (!brand.trim().isEmpty()) {
        				String[] vehicleArray = (vehicle_type.equalsIgnoreCase("Bike")) ? bikes : cars;
        				if (Arrays.asList(vehicleArray).contains(brand)) {
        					break;
        				} else {
        					System.out.println("Enter a valid brand for the chosen vehicle type.");
        				}
        			}
        		}
        		
        		// Model Logic
        		System.out.print("Enter model                 : ");
        		model = sc.nextLine();
        		
        		// License logic
        		while (true) {
        			System.out.print("License plate Number        : ");
        			license_plate = sc.nextLine();
        			if (Validation.isValidLicensePlate(license_plate)) {
        				break;
        			} else {
        				System.out.println("Enter a valid license plate number");
        			}
        		}
        		
        		// Vehicle availability
        		while (true) {
        			System.out.print("If available type '1' or '0': ");
        			available = sc.nextLine();
        			if (available.equals("1") || available.equals("0")) {
        				break;
        			} else {
        				System.out.print("Enter only '0' and '1'");
        			}
        		}
        		
        		if (con != null) {
        			try {
        				System.out.println(DBOperations.insertNewVehicle(con, vehicle_type, brand, model, license_plate, available));
        			} catch (Exception e) {
        				System.out.println(e.toString());
        			}
        		}
        	} 
        	else if (operation == 'U' || operation == 'u') {
        		System.out.print("\nEnter the License plate number to update the details : "); 
        		String lp = sc.nextLine();
        		System.out.println("Enter Bike type, brand, model, license plate, availabe or not");
        		String vt = sc.nextLine();
        		String b = sc.nextLine();
        		String m = sc.nextLine();
        		String lpo = sc.nextLine();
        		String av = sc.nextLine();
        		if (con != null) {
        			if (!lp.trim().isEmpty()) {
        				try {
        					DBOperations.updateByAdmin(con, lp, vt, b, m, lpo, av);
        				} catch (Exception e) {
        					System.out.println(e.toString());
        				}
        			}
        		}
        	} 
        	else if (operation == 'D' || operation == 'd') {
        		// Delete a Vehicle's Data
        		System.out.print("\nEnter the License Plate to delete the data: ");
        		String license_plate_toDelete = sc.nextLine();
        		if (con != null) {
        			if (!license_plate_toDelete.trim().isEmpty()) {
        				try {
        					DBOperations.deleteVehicle(con, license_plate_toDelete);
        				} catch (Exception e) {
        					System.out.println(e.toString());
        				}
        			}
        		}
        		System.out.println();
        	} 
        	else if (operation == 'S' || operation == 's') {
        		// Display Vehicle Details
        		System.out.println();
        		if (con != null) {
        			try {
        				DBOperations.selectAllVehicles(con, -1);
        			} catch (Exception e) {
        				System.out.println(e.toString());
        			}
        		}
        	}
        	else if(operation=='A' || operation=='a') {
        		// New Admin Insertion
        		String fName = "", lName = "", mobileNo = "", email = "", password = "", confirmPassword = "";
				boolean firstName = false, lastName = false, mobileNumber = false, emailAddress = false, passCode= false;
	            while(true) {
	            	System.out.print("Enter First Name    : ");
	            	fName = sc.nextLine();
	            	if (Validation.validateName(fName)) {
	            		firstName = true;
	            		break;
	            	}
	            	else
	            		System.out.println("<---Name should contains only Characters--->");
	            }
	            while(true) {
	            	System.out.print("Enter Last Name     : ");
	            	lName = sc.nextLine();
	            	if (Validation.validateName(lName)) {
	            		lastName = true;
	            		break;
	            	}
	            	else 
	            		System.out.println("<---Name should contains only Characters--->");
	            }
	            while(true) {
	            	System.out.print("Enter Mobile Number : ");
	            	mobileNo = sc.nextLine();
	            	if (Validation.validateMobileNumber(mobileNo)) {
	            		mobileNumber = true;
	            		break;
	            	}
	            	else
	            		System.out.println("<---Mobile Number should should have 10 numbers--->");
	            }
	            while(true) {
	            	System.out.print("Enter Email Address : ");
	            	email = sc.nextLine();
	            	if (Validation.validateEmail(email)) {
	            		emailAddress = true;
	            		break;
	            	}
	            	else
	            		System.out.println("<---Invalid email--->");
	            }
	            while(true) {
	            	System.out.print("Enter Password      : ");
	            	password = sc.nextLine();
	            	if (Validation.validatePassword(password)) {
	            		while(true) {
	            			System.out.print("Re-enter Password   : ");
		            		confirmPassword = sc.nextLine();
		            		if (password.equals(confirmPassword)) {
		            			passCode = true;
		            			break;
		            		}
		            		else {
		            			System.out.println("<---Password doesn't match--->");
		            		}
	            		}
	            		break;
	            	}
	            	else
	            		System.out.println("<---Password should contains atleast 8 characters, 1 UpperCase, 1 LowerCase, 1 Numbers, 1 SpecialCharacters--->");
	            }
	            if(con!=null) {
	            	try {
	            		DBOperations.insertAdminDetails(con, fName, lName, mobileNo, email, confirmPassword);
	            	}
	            	catch(Exception e) {
	            		System.out.println(e.toString());
	            	}
	            }
	            if(firstName && lastName && mobileNumber && emailAddress && passCode) {
	            	System.out.println("----------------------------------------------------------------");
	            }
        	}
        }
        sc.close();
    }
}
