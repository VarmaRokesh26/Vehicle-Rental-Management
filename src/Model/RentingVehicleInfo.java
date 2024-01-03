package Model;

import java.util.Date;

public class RentingVehicleInfo {
    private int vehicleId;
    private double amount;
    private String name;
    private String mobileNumber;
    private Date date;

    public RentingVehicleInfo(String name, String mobileNumber, int vehicleId, double amount, Date date) {
    	this.name = name;
    	this.mobileNumber = mobileNumber;
        this.vehicleId = vehicleId;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
    	return name;
    }
    
    public String getMobileNumber() {
    	return mobileNumber;
    }
    
    public int getVehicleId() {
        return vehicleId;
    }

    public double getAmount() {
        return amount;
    }
    
    public Date getDate() {
    	return date;
    }
}