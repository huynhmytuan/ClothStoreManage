package Excpt;

	public class PhoneException extends Exception { 
		  
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int phoneConditionViolated = 0; 
	  
	    public PhoneException(int conditionViolated) 
	    { 
	        super("Invalid Phone: "); 
	        phoneConditionViolated = conditionViolated; 
	    }
	    
	    public String printMessage() {
	    	switch (phoneConditionViolated) {
	    	case 1: 
	            return ("Phone length should be"
	                    + " between 10 to 11 characters");
	    	case 2: 
	            return ("Phone should not"
	                    + " contain any space");
	    	case 3: 
	            return ("Phone should not"
	                    + " contain any letter or special character");
	    	}
	    	return ("");	
	    }
	}