package Excpt;

public class EmailException extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		int emailConditionViolated = 0; 
		
	    public EmailException(int conditionViolated) 
	    { 
	        super("Invalid Password: "); 
	        emailConditionViolated = conditionViolated; 
	    }
	    
}
