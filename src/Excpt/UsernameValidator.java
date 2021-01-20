package Excpt;

public class UsernameValidator {
	public static void isValid(String username) 
	        throws UsernameException 
	    {    
	        // to check space 
	        if (username.contains(" ")) { 
	            throw new UsernameException(1); 
	        }
	     // to check special character
	        if((username.contains("@") || username.contains("#") 
	                || username.contains("!") || username.contains("~") 
	                || username.contains("$") || username.contains("%") 
	                || username.contains("^") || username.contains("&") 
	                || username.contains("*") || username.contains("(") 
	                || username.contains(")") || username.contains("-") 
	                || username.contains("+") || username.contains("/") 
	                || username.contains(":") || username.contains(".") 
	                || username.contains(", ") || username.contains("<") 
	                || username.contains(">") || username.contains("?") 
	                || username.contains("|"))) {
	        	throw new UsernameException(2);
	        }
	    }
}
