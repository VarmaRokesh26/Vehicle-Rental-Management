package mainActivity.package1;
import mainActivity.package2.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import backendActivity.DBConnection.*;
public class LoginOrSignUp{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SQLConnectivity sqlConnectivity = new SQLConnectivity();
		Connection con = sqlConnectivity.connectivity();	
		System.out.println("-----------------------------------------------------------");
		System.out.print("For New User Type 0 or Type 1 for Login : ");
		int loginOrSignUp = Integer.parseInt(sc.nextLine());
		while(true) {
			if (loginOrSignUp == 1) {
			    // Login logic
			    String email = "", password = "";
			    while(true) {
			    	System.out.print("Enter your EmailID : ");
			    	email = sc.nextLine();
			    	if (Validation.validateEmail(email)) {
			    		System.out.print("Enter password     : ");
			    		password = sc.nextLine();
			    		if (Validation.validatePassword(password)) {
			    			System.out.println("-----------------------------------------------------------");
			    			try {
			    				if (DBOperations.checkAdminDetails(con, email, password)) {
			    					AdminModule.main(args);
			    				} 
			    				else if(DBOperations.checkUserDetails(con, email, password)) {
			    					UserModule.main(args);
			    				}
			    				else {
			    					System.out.println("Invalid Combinations");
			    				}
			    			} catch (SQLException e) {
			    				System.out.println("Error during login: " + e.toString());
			    			}
			    		}
			    		else {
			    			System.out.println("Password doesn't match");
			    		}
			    	}
			    	else {
			    		System.out.println("Enter a proper Email Address");
			    	}
			    }
			}

			else {
	            // Sign-up logic
				String fName = "", lName = "", mobileNo = "", email = "", password = "", confirmPassword = "";
				boolean firstName = false, lastName = false, mobileNumber = false, emailAddress = false, passCode= false;
	            while(true) {
	            	System.out.print("Enter Your First Name : ");
	            	fName = sc.nextLine();
	            	if (Validation.validateName(fName)) {
	            		firstName = true;
	            		break;
	            	}
	            	else
	            		System.out.println("<---Name should contains only Characters--->");
	            }
	            while(true) {
	            	System.out.print("Enter Your Last Name : ");
	            	lName = sc.nextLine();
	            	if (Validation.validateName(lName)) {
	            		lastName = true;
	            		break;
	            	}
	            	else 
	            		System.out.println("<---Name should contains only Characters--->");
	            }
	            while(true) {
	            	System.out.print("Enter Your Mobile Number : ");
	            	mobileNo = sc.nextLine();
	            	if (Validation.validateMobileNumber(mobileNo)) {
	            		mobileNumber = true;
	            		break;
	            	}
	            	else
	            		System.out.println("<---Mobile Number should should have 10 numbers--->");
	            }
	            while(true) {
	            	System.out.print("Enter Your Email Address : ");
	            	email = sc.nextLine();
	            	if (Validation.validateEmail(email)) {
	            		emailAddress = true;
	            		break;
	            	}
	            	else
	            		System.out.println("<---Invalid email--->");
	            }
	            while(true) {
	            	System.out.print("Password : ");
	            	password = sc.nextLine();
	            	if (Validation.validatePassword(password)) {
	            		while(true) {
	            			System.out.print("Re-enter Password : ");
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
	            		System.out.println("<---Password should contains atleast \n8 characters, \n1 UpperCase, \n1 LowerCase, \n1 Numbers, \n1 SpecialCharacters--->");
	            }
	            if(con!=null) {
	            	try {
	            		DBOperations.insertUserDetails(con, fName, lName, mobileNo, email, confirmPassword);
	            	}
	            	catch(Exception e) {
	            		System.out.println(e.toString());
	            	}
	            }
	            if(firstName && lastName && mobileNumber && emailAddress && passCode) {
	            	
	            	System.out.println("Welcome to Mobile Rental App");
	            	System.out.println("-----------------------------------------------------------");
	            }
	            loginOrSignUp = 1;
	        }
		}
	}
}