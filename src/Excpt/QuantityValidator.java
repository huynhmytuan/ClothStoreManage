package Excpt;

public class QuantityValidator {
	public static void isValid(String quantity) 
	        throws QuantityException 
	    {  
	        //Check null
	        if (quantity.equals("")) { 
	            throw new QuantityException(3); 
	        }
	        // to check space 
	        if (quantity.contains(" ")) { 
	            throw new QuantityException(2); 
	        }
	        // to check special character
	        if((quantity.contains("@") || quantity.contains("#") 
	                || quantity.contains("!") || quantity.contains("~") 
	                || quantity.contains("$") || quantity.contains("%") 
	                || quantity.contains("^") || quantity.contains("&") 
	                || quantity.contains("*") || quantity.contains("(") 
	                || quantity.contains(")") || quantity.contains("-") 
	                || quantity.contains("+") || quantity.contains("/") 
	                || quantity.contains(":") || quantity.contains(".") 
	                || quantity.contains(", ") || quantity.contains("<") 
	                || quantity.contains(">") || quantity.contains("?") 
	                || quantity.contains("|"))) {
	        	throw new QuantityException(1);
	        }
	        // to check contain letter
	        if (!quantity.matches("[0-9]+")) { 
	           throw new QuantityException(1);
	        } 
	    }
}
