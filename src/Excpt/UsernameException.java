package Excpt;

public class UsernameException extends Exception{	  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int usernameConditionViolated = 0; 
  
    public UsernameException(int conditionViolated) 
    { 
        super("Invalid Username: "); 
        usernameConditionViolated = conditionViolated; 
    }
    
    public String printMessage() {
    	switch (usernameConditionViolated) {
    	case 1: 
            return ("Username should not"
                    + " contain any space");
    	case 2: 
            return ("Username should not"
                    + " contain any letter or special character");
    	}
    	return ("");	
    }
}
