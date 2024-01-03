package Controller;

import java.sql.*;
import java.util.Date;
import java.util.*;

import Model.*;
import View.*;

public class UserController {
	
    public static void userController(String[] arg) {
    	
        Scanner sc = new Scanner(System.in);
        Connection con = SQLConnectivity.connectivity();

        String vehicleType = UserInterface.getVehicleType(sc);

        if (!vehicleType.isEmpty() && con != null) {
            try {
                DBOperations.selectAllVehiclesIfAvailable(con, vehicleType);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        int vehicleId = UserInterface.getVehicleIdToRent(sc);
        boolean idAvailable = true;
        Date date = new Date();
        while (vehicleId != 0) {
            try {
                List<Integer> vehicleIdsList = DBOperations.availableVehicleIdCheck(con, vehicleId, vehicleType);
                if (!vehicleIdsList.contains(vehicleId)) {
                    idAvailable = false;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if (idAvailable) {
            	
                String renterName = UserInterface.getRenterName(sc);
                String renterMobileNumber = UserInterface.getRenterMobileNumber(sc);
                double securityAmount = UserInterface.getRentAmount(sc, vehicleType);

                if (!renterName.isEmpty() && !renterMobileNumber.isEmpty()) {
                    try {
                        RentingVehicleInfo rentalInfo = new RentingVehicleInfo(renterName, renterMobileNumber, vehicleId, securityAmount, date);
                        String result = DBOperations.updateForRenting(con, rentalInfo);
                        if (!result.isEmpty())
                            System.out.println(result);
                        else
                            System.out.println("Failed to rent the vehicle. Please enter a valid, available Vehicle Id.");
                    } catch (Exception e) {
                        System.out.println("Failed to rent the vehicle. Please enter a valid, available Vehicle Id.");
                    }
                }
            } 
            else
                System.out.print("Enter the Id which is displayed or type 0 to logout : ");
            
            vehicleId = UserInterface.getVehicleIdToRent(sc);
            idAvailable = true;
        }

        System.out.println("-----------------------------------------------------------");
        MainController.main(arg);
        sc.close();
    }
}