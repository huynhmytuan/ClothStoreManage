package Excpt;

public class FullnameException extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		int fullnameConditionViolated = 0; 
		
	    public FullnameException(int conditionViolated) 
	    { 
	        super("Invalid Fullname: "); 
	        fullnameConditionViolated = conditionViolated; 
	    }
	    public String printMessage() {
	    	switch (fullnameConditionViolated) {
	    	case 1: 
	            return ("Name should not"
	                    + " contain number");
	    	case 2: 
	            return ("Name should not"
	                    + " contain special character");
	    	}
	    	return ("");	
	    }
}
