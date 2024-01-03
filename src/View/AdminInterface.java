package View;

import java.util.*;
import Model.*;

public class AdminInterface {

	public static String vehicleType;
	public static String brand;
	public static String model;
	public static String licensePlate;
	public static int available;
	
    public static Vehicle getNewVehicleDetails(Scanner sc) {
        System.out.println("\nEnter the Vehicle Details to be Added:");
        
        System.out.print("Enter 'Bike' or 'Car'       : ");        		
        while (true) {
            // Bike or Car
       		vehicleType = sc.nextLine();
       		if (vehicleType.equalsIgnoreCase("Car") || vehicleType.equalsIgnoreCase("Bike"))
        		break;
        }

        while (true) {
            // Brand name
            System.out.print("Enter brand name            : ");
            String[] bikes = {"Yamaha", "Bajaj", "Honda", "TVS", "Royal Enfield", "KTM"};
            String[] cars = {"Maruti", "Hyundai", "Mahindra", "Tata", "Ford", "Honda", "Toyota", "Renault"};
            brand = sc.nextLine();
            if (!brand.trim().isEmpty()) {
                String[] vehicleArray = (vehicleType.equalsIgnoreCase("Bike")) ? bikes : cars;
                if (Arrays.asList(vehicleArray).contains(brand))
                    break;
                else
                    System.out.println("Enter a valid brand for the chosen vehicle type.");
            }
        }

        // Model Logic
        System.out.print("Enter model                 : ");
        model = sc.nextLine();

        while (true) {
            // License logic
            System.out.print("License plate Number        : ");
            licensePlate = sc.nextLine();
            if (Validation.isValidLicensePlate(licensePlate)) {
                break;
            } else {
                System.out.println("Enter a valid license plate number");
            }
        }

        while (true) {
        	// Vehicle availability
            System.out.print("If available type '1' or '0': ");
            available = sc.nextInt();
            if (available == 1 || available == 0) {
                break;
            } else {
                System.out.print("Enter only '0' and '1'");
            }
        }

        return new Vehicle(vehicleType, brand, model, licensePlate, available);
    }

    public static String getLicensePlateToUpdate(Scanner sc) {
    	
    	while(true) {
    		System.out.print("\nEnter the License plate number to update the details: ");
    		licensePlate = sc.next();
    		if(Validation.isValidLicensePlate(licensePlate)) {    		
    			break;
    		}
    		else
                System.out.println("Enter a valid license plate number for updation");
    	}
    	return licensePlate;
    }

    public static String getLicensePlateToDelete(Scanner sc) {
    	
        while(true) {
        	System.out.print("\nEnter the License plate number to delete the details: ");
    		licensePlate = sc.next();
    		if(Validation.isValidLicensePlate(licensePlate)) 
    			return licensePlate;
    		else
                System.out.println("Enter a valid license plate number for deletion");
    	}
    }

}
