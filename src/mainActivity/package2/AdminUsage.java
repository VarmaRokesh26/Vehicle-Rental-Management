package mainActivity.package2;
import java.sql.Connection;
import java.util.*;
import backendActivity.DBConnection.*;
import mainActivity.package1.*;
public class AdminUsage {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		SQLConnectivity sqlConnectivity = new SQLConnectivity();
		Connection con = sqlConnectivity.connectivity();
//		while(true) {
//			break;
//		}
		System.out.print("Enter I to Insert, U to Update, D to Delete, S to Display : ");
		char operation = sc.nextLine().charAt(0);
		String vehicle_type = "@", brand = "@", model = "@", license_plate = "@", available = "@";
		
		if(operation=='I') {
			// Inserting logic
			System.out.println("Enter the Vehicle Details to Added ");
			//while(true) {
				while(true) {
					// Bike or Car
					System.out.print("Enter --Bike-- Or --Car-- : ");
					vehicle_type = sc.nextLine();
					if(vehicle_type.equals("Car")||vehicle_type.equals("Bike")||vehicle_type.equals("car")||vehicle_type.equals("bike")) 
						break;
					else {
						System.out.println("Enter only Car or Bike");
					}
				}
				
				while(true) {
					//Brand name
					System.out.print("Enter brand name : ");
					String bikes[] = {"Yamaha", "Bajaj", "Honda", "TVS", "Royal Enfield", "KTM"};
					String cars[] = {"Maruti", "Hyundai", "Mahindra", "Tata", "Ford", "Honda", "Toyota", "Renault"};
					brand = sc.nextLine();
					if(!brand.equals(" ")) {
						if(vehicle_type == "Bike") {
							for(String vehicle : bikes) {
								if(brand.equals(vehicle)) {
									break;
								}
							}
						}
						else if(vehicle_type == "Car") {
							for(String vehicle : cars) {
								if(brand.equals(vehicle)) {
									break;
								}
							}
						}
						break;
					}
				}
				while(true) {
					//Model Logic
					System.out.print("Enter model : ");
					model = sc.nextLine();
					if(!model.equals(" ")) 
						break;
				}
				while(true) {
					//License logic
					System.out.print("Enter vehicle's license plate No.: ");
					license_plate = sc.nextLine();
					if(Validation.isValidLicensePlate(license_plate)) {
						break;					
					}
					else {
						System.out.print("Enter a valid license plate number");
					}
				}
				while (true) {
					// Vehicle available or Not
			        System.out.print("If available type 1 or else 0: ");
			        available = sc.nextLine();
			        if (available.equals("1") || available.equals("0")) {
			            break;
			        } 
			        else {
			            System.out.print("Enter only 0 and 1");
			        }
			    }
				if(con!=null) {
					try {
						System.out.println(CRUDData.insertNewVehicle(con, vehicle_type, brand, model, license_plate, available));
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
//				System.out.println("Add More--> Y/N");
//				char ch = sc.next().charAt(0);
//				if(ch=='N') 
//					break;
//			}
			
		}
		else if(operation=='U'||operation=='u') {
			System.out.println("Updating");
			
		}
		else if(operation=='D'||operation=='d') {
			System.out.print("Enter the License Plate to delete the data: ");
			String license_plate_toDelete= sc.nextLine();
			if(con!=null) {
				if(!license_plate_toDelete.equals(" ")) {
					try {
						CRUDData.deleteVehicle(con, license_plate_toDelete);
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
			}
			System.out.println("Deleting");
		}
		else if(operation=='S'||operation=='s') {
			System.out.println("Display");
			if(con!=null) {
				try {
					CRUDData.selectAllVehicles(con);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		}
		sc.close();
	}
}