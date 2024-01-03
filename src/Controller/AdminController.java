package Controller;

import java.sql.*;
import java.util.*;

import Model.*;
import View.*;

public class AdminController {
	public static void adminController(Connection con, String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("!! Welcome Back Admin !!");
        while (true) {
            System.out.print("\nEnter \nL -> Logout \nI -> Add Vehicle \nU -> Update \nD -> Delete \nS -> Display \nA -> Add Admin \n---------------> ");
            char operation = sc.next().charAt(0);
            if (operation == 'L' || operation == 'l') {
                System.out.println("\nLogging out. Goodbye!");
                System.out.println("-----------------------------------------------------------");
                MainController.main(args);
            }
            else {
                operation = performAdminTask(con, sc, operation);
            }
        }
    }
	
	private static char performAdminTask(Connection con, Scanner sc, char operation) {
		if (operation == 'I' || operation == 'i') {
            // Inserting logic
            Vehicle newVehicle = AdminInterface.getNewVehicleDetails(sc);
            if (con != null) {
                try {
                	DBOperations.insertNewVehicle(con, newVehicle.getVehicleType(), newVehicle.getBrand(), newVehicle.getModel(), newVehicle.getLicensePlate(), newVehicle.getAvailable());
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        } 
        else if (operation == 'U' || operation == 'u') {
        	
            // Updating logic
            String lp = AdminInterface.getLicensePlateToUpdate(sc);
            if (con != null && !lp.trim().isEmpty()) {
                try {
                    System.out.println("Enter Bike type, brand, model, license plate, available or not");
                    String vt = sc.nextLine();
                    String b = sc.nextLine();
                    String m = sc.nextLine();
                    String lpo = sc.nextLine();
                    String av = sc.nextLine();
                    DBOperations.updateByAdmin(con, lp, vt, b, m, lpo, av);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        } else if (operation == 'D' || operation == 'd') {
            // Delete a Vehicle's Data
            String licensePlateToDelete = AdminInterface.getLicensePlateToDelete(sc);
            if (con != null && !licensePlateToDelete.trim().isEmpty()) {
                try {
                	DBOperations.deleteVehicle(con, licensePlateToDelete);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            System.out.println();
        } else if (operation == 'S' || operation == 's') {
            // Display Vehicle Details
            System.out.println();
            if (con != null) {
                try {
                	DBOperations.selectAllVehicles(con, -1);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        } else if (operation == 'A' || operation == 'a') {
            // New Admin Insertion
            boolean userName, mobileNumber, emailAddress, passCode;

            System.out.print("Enter your Name     : ");
            while (true) {
                String uName = sc.nextLine();
                userName = Validation.validateName(uName);
                if (userName) {
                	while (true) {
                		System.out.print("Enter Mobile Number : ");
                		String mobileNo = sc.nextLine();
                		mobileNumber = Validation.validateMobileNumber(mobileNo);
                		if (mobileNumber) {                			
                			while (true) {
                				System.out.print("Enter Email Address : ");
                				String email = sc.nextLine();
                				emailAddress = Validation.validateEmail(email);
                				if (emailAddress) {                					
                					while (true) {
                						System.out.print("Enter Password      : ");
                						String password = sc.nextLine();
                						passCode = Validation.validatePassword(password);
                						if (passCode) {
                							while (true) {
                								System.out.print("Re-enter Password   : ");
                								String confirmPassword = sc.nextLine();
                								if (password.equals(confirmPassword)) {
                									if (con != null) {
                						            	try {
                						            		DBOperations.insertAdminDetails(con, uName, mobileNo, email, confirmPassword);
                						            	} catch (Exception e) {
                						            		System.out.println(e.toString());
                						            	}
                						            }
                									break;
                								} else {
                									System.out.println("<---Password doesn't match--->");
                								}
                							}
                							break;
                						} else {
                							System.out.println("<---Password should contain at least 8 characters, 1 UpperCase, 1 LowerCase, 1 Numbers, 1 SpecialCharacters--->");
                						}
                					}
                					break;
                				} else {
                					System.out.println("<---Invalid email--->");
                				}
                			}
                			break;
                		} else {
                			System.out.println("<---Mobile Number should have 10 numbers--->");
                		}
                	}                	
                    break;
                }
            }
        }
		operation = '@';
		return operation;
	}
}
