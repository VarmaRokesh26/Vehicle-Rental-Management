package mainActivity.package1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	public static boolean validateName(String name) {
		int minLength = 3;
		int maxLength = 50;
		
		if(name.length() < minLength || name.length() > maxLength) {
			return false;
		}
		
		for(char ch : name.toCharArray()) {
			if(!Character.isLetter(ch) && ch != ' ')
				return false;
		}
		return true;
	}
	
	public static boolean validateMobileNumber(String mobileNumber) {
		
		String mobileNumberRegex = "^[6789]\\d{9}$";
		Pattern pattern = Pattern.compile(mobileNumberRegex);
		Matcher matcher = pattern.matcher(mobileNumber);
		
		return matcher.matches();
	}
	
	public static boolean validateEmail(String email) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
	
	public static boolean validatePassword(String password) {
		
		int minLength = 8;
		boolean isPasswordValid = false;
		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasDigit = false;
		boolean hasSpecialChar = false;
		
		String specialChars = "!@#$%^&*()-_=+[]{};:'\",.<>?/";
		
		if(password.length() < minLength)
			return false;
		for(char ch : password.toCharArray()) {
			if(Character.isUpperCase(ch))
				hasUpperCase = true;
			else if(Character.isLowerCase(ch))
				hasLowerCase = true;
			else if(Character.isDigit(ch))
				hasDigit = true;
			else if(specialChars.contains(String.valueOf(ch)))	
				hasSpecialChar = true;
		}
		
		isPasswordValid = hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
		
		return isPasswordValid;
	}
	
	public static boolean isValidLicensePlate(String license_plate) {
		 
        String pattern = "^TN-[0-9]{2}-[A-Z]{2}-[0-9]{4}$";
        Pattern licensePlatePattern = Pattern.compile(pattern);
        Matcher matcher = licensePlatePattern.matcher(license_plate);
        
        return matcher.matches();
    }
}