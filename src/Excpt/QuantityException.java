package Excpt;

public class QuantityException extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		int quantityConditionViolated = 0; 
		
	    public QuantityException(int conditionViolated) 
	    { 
	        super("Invalid Quantity: "); 
	        quantityConditionViolated = conditionViolated; 
	    }
	    public String printMessage() {
	    	switch (quantityConditionViolated) {
	    	case 1: 
	            return ("Quantity can't"
	                    + " contain letter or special charactor!");
	    	case 2: 
	            return ("Quantity can't"
	                    + " contain space!");
	    	
	    	case 3:
	    		return ("Quantity can't"
	                    + " be emmty!");
	    	}
	    	return ("");	
	    }
}
