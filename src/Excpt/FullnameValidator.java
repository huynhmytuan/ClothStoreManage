package Excpt;

public class FullnameValidator {
	public static void isValid(String fullname) 
	        throws FullnameException 
	    {  
	        // to check contain character
	        if (fullname.matches(".*\\d.*")) { 
	           throw new FullnameException(1);
	        } 
	        // to check special character
	        if((fullname.contains("@") || fullname.contains("#") 
	                || fullname.contains("!") || fullname.contains("~") 
	                || fullname.contains("$") || fullname.contains("%") 
	                || fullname.contains("^") || fullname.contains("&") 
	                || fullname.contains("*") || fullname.contains("(") 
	                || fullname.contains(")") || fullname.contains("-") 
	                || fullname.contains("+") || fullname.contains("/") 
	                || fullname.contains(":") || fullname.contains(".") 
	                || fullname.contains(", ") || fullname.contains("<") 
	                || fullname.contains(">") || fullname.contains("?") 
	                || fullname.contains("|"))) {
	        	throw new FullnameException(2);
	        }
	    }
}
