package Model;

public class SignUp {
    private String userName;
    private String mobileNumber;
    private String emailId;
    private String password;
    private int bikeCount;
    private int carCount;

    public SignUp(String userName, String mobileNumber, String emailId, String password, int bikeCount, int carCount) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.password = password;
        this.bikeCount = bikeCount;
        this.carCount = carCount;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }
    
    public int getBikeRentedCount() {
    	return bikeCount;
    }
    
    public int getCarRentedCount() {
    	return carCount;
    }
}