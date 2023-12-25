package mainActivity.package2;
import java.sql.Connection;
import java.util.*;

import backendActivity.DBConnection.*;
public class UserUsage {
	public static void main(String arg[]) {
		
		Scanner sc = new Scanner(System.in);
		SQLConnectivity sqlConnectivity = new SQLConnectivity();
		Connection con = sqlConnectivity.connectivity();
		System.out.println("Enter the which type of vehicle is required");
		String vehicle_type = sc.next();
		System.out.print("Enter S to see the available vehicles : ");
		char ch = sc.next().charAt(0);
		System.out.println("\n");
		if(ch=='S'||ch=='s') {
			
			if(con!=null) {
				try {
					CRUDData.selectAllVehiclesIfAvailable(con, vehicle_type);
				}
				catch(Exception e) {
					System.out.print(e.toString());
				}
			}
		}
		while(true) {
			System.out.print("\n\nEnter the Vehicle Id to Rent a Vehicle : ");
			int Id = sc.nextInt();
			if(Id!=0) {			
				try {
					CRUDData.updateForRenting(con, Id);
				} 
				catch (Exception e) {
					System.out.println("Enter only the available vehicle Id");
					
				}
			}
			break;
		}
		sc.close();
	}
}
