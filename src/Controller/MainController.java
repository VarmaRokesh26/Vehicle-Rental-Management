package Controller;

import java.sql.*;
import java.util.*;

import Model.*;
import View.*;

public class MainController {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = SQLConnectivity.connectivity();

        int loginOrSignUp = UserInterface.getUserChoice(sc);

        while (true) {
            if (loginOrSignUp == 1) {
                // Login logic
                Login login = UserInterface.getLoginDetails(sc);
                System.out.println("-----------------------------------------------------------");
                performLogin(con, login, args);
            } 
            else {
                // Sign-up logic
                SignUp signUp = UserInterface.getSignUpDetails(sc);
                System.out.println("-----------------------------------------------------------");
                performSignUp(con, signUp);

                loginOrSignUp = 1;
            }
        }
    }
    
    private static void performLogin(Connection con, Login login, String[] args) {
        try {
            if (DBOperations.checkAdminDetails(con, login.getEmailId(), login.getPassword())) {
                AdminController.adminController(con, args);
            } 
            else if (DBOperations.checkUserDetails(con, login.getEmailId(), login.getPassword())) {
                UserController.userController(args);
            } 
            else {
                System.out.println("Invalid Combinations");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.toString());
        }
    }
    
    private static void performSignUp(Connection con, SignUp signUp) {
    	
        if (con != null) {
            try {
                DBOperations.insertUserDetails(con, signUp.getUserName(), signUp.getMobileNumber(), signUp.getEmailId(), signUp.getPassword(), signUp.getBikeRentedCount(), signUp.getCarRentedCount());
            } 
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}