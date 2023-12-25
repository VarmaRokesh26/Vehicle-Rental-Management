package mainActivity.package2;
import mainActivity.package1.*;
import java.sql.Connection;
import java.util.*;
import backendActivity.DBConnection.*;
import mainActivity.package1.Validation;

public class UserModule{

    public static void main(String arg[]) {

        Scanner sc = new Scanner(System.in);
        SQLConnectivity sqlConnectivity = new SQLConnectivity();
        Connection con = sqlConnectivity.connectivity();

        System.out.print("Enter the type of vehicle required (Car or Bike): ");
        String vehicle_type = sc.nextLine().trim();

        System.out.println();

        if (!vehicle_type.isEmpty()) {
            if (con != null) {
                try {
                	DBOperations.selectAllVehiclesIfAvailable(con, vehicle_type);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }

        System.out.print("\n\nEnter the Vehicle Id to Rent a Vehicle only if available (Enter 0 to LogOut): ");
        int Id = Integer.parseInt(sc.nextLine());
        String Name = "", mobileNo = "";
        boolean firstName = false, mobileNumber = false, idAvailable = true;
        double amount = 0;

        while (Id != 0) {
            try {
                List<Integer> vehicleIdsList = DBOperations.availableVehicleIdCheck(con, Id, vehicle_type);
//                System.out.println(vehicleIdsList);
                if (!vehicleIdsList.contains(Id)) {
                    idAvailable = false;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if (idAvailable) {
                while (true) {
                    System.out.print("Enter Your Name : ");
                    Name = sc.nextLine();
                    if (Validation.validateName(Name)) {
                        firstName = true;
                        break;
                    } else
                        System.out.println("<---Name should contains only Characters--->");
                }

                while (true) {
                    System.out.print("Enter Your Mobile Number : ");
                    mobileNo = sc.nextLine();
                    if (Validation.validateMobileNumber(mobileNo)) {
                        mobileNumber = true;
                        break;
                    } else
                        System.out.println("<---Mobile Number should should have 10 numbers--->");
                }

                while (true) {
                    try {
                        System.out.print("Enter amount paid for security: ");
                        amount = Double.parseDouble(sc.nextLine());
                        if (((vehicle_type.equalsIgnoreCase("Bike")) && amount >= 3000)
                                || ((vehicle_type.equalsIgnoreCase("Car")) && amount >= 10000)) {
                            break;
                        } else {
                            System.out.println("Amount should be more than 3000 for bike and 10000 for car.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid numeric value for the amount.");
                    }
                }

                if (firstName && mobileNumber) {
                    try {
                        String result = DBOperations.updateForRenting(con, Id, Name, mobileNo, amount);
                        if (!result.isEmpty()) {
                            System.out.println(result);
                        } else {
                            System.out.println("Failed to rent the vehicle. Please enter a valid, available Vehicle Id.");
                        }
                    } catch (Exception e) {
                        System.out.println("Failed to rent the vehicle. Please enter a valid, available Vehicle Id.");
                    }
                }
            } else {
                System.out.print("Enter the Id which is displayed or type 0 to logout : ");
            }
            Id = Integer.parseInt(sc.nextLine());
            idAvailable = true;
        }

        System.out.println("----------------------------------------------------------------");
        LoginOrSignUp.main(arg);
        sc.close();
    }
}
