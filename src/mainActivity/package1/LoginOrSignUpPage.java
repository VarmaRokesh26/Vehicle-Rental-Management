package mainActivity.package1;
import java.sql.Connection;
import java.util.*;

import backendActivity.DBConnection.CRUDData;
import backendActivity.DBConnection.SQLConnectivity;
public class LoginOrSignUpPage {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SQLConnectivity sqlConnectivity = new SQLConnectivity();
		Connection con = sqlConnectivity.connectivity();	
		System.out.print("For New User Type 0 or Type 1 for Login : ");
		int loginOrSignUp = Integer.parseInt(sc.nextLine());
		while(true) {
			if(loginOrSignUp == 1) {
				// Login logic
				String email = "", password = "";
				System.out.print("Enter your EmailID : ");
				email = sc.nextLine();
				if(Validation.validateEmail(email)) {
					System.out.print("Enter password : ");
					password = sc.nextLine();
					if(Validation.validatePassword(password))
						System.out.println("Welcome Back");
				}
				break;
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
	            		System.out.println("<---Password should contains atleast 8 characters, 1 UpperCase, 1 LowerCase, 1 Numbers, 1 SpecialCharacters--->");
	            }
	            if(con!=null) {
	            	try {
	            		CRUDData.insertUserDetails(con, fName, lName, mobileNo, email, confirmPassword);
	            	}
	            	catch(Exception e) {
	            		System.out.println(e.toString());
	            	}
	            }
	            if(firstName && lastName && mobileNumber && emailAddress && passCode) {
	            	
	            	System.out.println("Welcome to Mobile Rental App");
	            }
	            loginOrSignUp = 1;
	        }
		}
		
		sc.close();
	}
}
