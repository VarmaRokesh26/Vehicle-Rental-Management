package View;

import java.util.*;
import Model.*;

public class UserInterface {
	
	public static int getUserChoice(Scanner sc) {
		System.out.println("-----------------------------------------------------------");
		while (true) {
		    System.out.print("For New User Type 0 or Type 1 for Login : ");
		    int lOrS;
		    try {
		        lOrS = Integer.parseInt(sc.nextLine());
		        switch (lOrS) {
		            case 1:
		            case 0:
		                return lOrS;
		            default:
		                System.out.println("Enter only 0 and 1-->>!!!");
		        }
		    } catch (NumberFormatException e) {
		        System.out.println("Invalid. Enter only 1 or 0");
		    }
		}
	}

	public static Login getLoginDetails(Scanner sc) {
		String email = "", password = "";
     
		while (true) {
			System.out.println("-----------------------------------------------------------");
			System.out.print("Enter your EmailID : ");
			
			email = sc.nextLine();
			if (Validation.validateEmail(email)) {
				while(true) {					
					System.out.print("Enter password     : ");
					password = sc.nextLine();
					if (Validation.validatePassword(password))
						return new Login(email, password);
					else
						System.out.println("\nEnter a Valid Password");
				}
			} 
			else
				System.out.println("\nEnter a proper Email Address");
		}
	}

	public static SignUp getSignUpDetails(Scanner sc) {
    	
        String userName = "", mobileNo = "", email = "", password = "", confirmPassword = "";
        int bikeCount = 0, carCount = 0;
        
        while (true) {
        	//userName
        	System.out.println("-----------------------------------------------------------");
            System.out.print("Enter Your Name : ");
            
            userName = sc.nextLine();
            if (Validation.validateName(userName))
                break;
            else
                System.out.println("<---Name should contains only Characters--->");
        }


        while (true) {
        	//Mobile Number
            System.out.print("Enter Your Mobile Number : ");
            
            mobileNo = sc.nextLine();
            if (Validation.validateMobileNumber(mobileNo))
                break;
            else
                System.out.println("<---Mobile Number should should have 10 numbers--->");
        }

        while (true) {
        	// Email Address
            System.out.print("Enter Your Email Address : ");
            
            email = sc.nextLine();
            if (Validation.validateEmail(email))
                break;
            else
                System.out.println("<---Invalid email--->");
        }

        while (true) {
            System.out.print("Password : ");
            password = sc.nextLine();
            
            if (Validation.validatePassword(password)) {
                while (true) {
                    System.out.print("Re-enter Password : ");
                    confirmPassword = sc.nextLine();
                    if (password.equals(confirmPassword))
                        break;
                    else
                        System.out.println("<---Password doesn't match--->");
                }
                break;
            } 
            else
                System.out.println("<---Password should contains atleast \n8 characters, \n1 UpperCase, "
                		+ "\n1 LowerCase, \n1 Numbers, \n1 SpecialCharacters--->");
        }
        return new SignUp(userName, mobileNo, email, confirmPassword, bikeCount, carCount);
    }
	
	public static String getVehicleType(Scanner sc) {
        System.out.print("Enter the type of vehicle required \n(Car or Bike): ");
        return sc.nextLine().trim();
    }

    public static int getVehicleIdToRent(Scanner sc) {
        System.out.print("\nEnter the Vehicle Id to Rent a Vehicle\nonly if available (Enter 0 to LogOut): ");
        return Integer.parseInt(sc.nextLine());
    }

    public static String getRenterName(Scanner sc) {
    	System.out.println("-----------------------------------------------------------");
        System.out.print("Enter Your Name              : ");
        return sc.nextLine();
    }

    public static String getRenterMobileNumber(Scanner sc) {
        System.out.print("Enter Your Mobile Number     : ");
        return sc.nextLine();
    }

    public static double getRentAmount(Scanner sc, String vehicleType) {
        double amount = 0;

        while (true) {
            try {
                System.out.print("Enter amount paid for Renting: ");
                amount = Double.parseDouble(sc.nextLine());
                if (((vehicleType.equalsIgnoreCase("Bike")) && amount >= 3000)
                        || ((vehicleType.equalsIgnoreCase("Car")) && amount >= 10000)) {
                    break;
                } else {
                    System.out.println("Amount should be more than 3000\nfor bike and 10000 for car.");
                }
                System.out.println("-----------------------------------------------------------");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric value for the amount.");
            }
        }

        return amount;
    }
}
