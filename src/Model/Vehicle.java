package Model;

public class Vehicle {
    private String vehicleType;
    private String brand;
    private String model;
    private String licensePlate;
    private int available;

    public Vehicle(String vehicleType, String brand, String model, String licensePlate, int available) {
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.available = available;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getAvailable() {
        return available;
    }
}