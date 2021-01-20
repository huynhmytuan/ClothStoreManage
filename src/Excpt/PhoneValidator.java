package Excpt;

public class PhoneValidator {
	public static void isValid(String phone) 
	        throws PhoneException 
	    {  
	    	// Phone length should be 
	        // between 10 to 11 characters 
	        if (!((phone.length() >= 10) 
	              && (phone.length() <= 11))) { 
	            throw new PhoneException(1); 
	        } 	  
	        // to check space 
	        if (phone.contains(" ")) { 
	            throw new PhoneException(2); 
	        }
	        // to check letter
	        if (!phone.matches("[0-9]+")) { 
	           throw new PhoneException(3);
	        } 
	     // to check special character
	        if((phone.contains("@") || phone.contains("#") 
	                || phone.contains("!") || phone.contains("~") 
	                || phone.contains("$") || phone.contains("%") 
	                || phone.contains("^") || phone.contains("&") 
	                || phone.contains("*") || phone.contains("(") 
	                || phone.contains(")") || phone.contains("-") 
	                || phone.contains("+") || phone.contains("/") 
	                || phone.contains(":") || phone.contains(".") 
	                || phone.contains(", ") || phone.contains("<") 
	                || phone.contains(">") || phone.contains("?") 
	                || phone.contains("|"))) {
	        	throw new PhoneException(3);
	        }
	    }
}
