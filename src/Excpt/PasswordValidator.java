package Excpt;

public class PasswordValidator {
	 public static void isValid(String password) 
		        throws PasswordException 
		    { 
		  
		        // for checking if password length 
		        // is between 8 and 15 
		        if (!((password.length() >= 8) 
		              && (password.length() <= 15))) { 
		            throw new PasswordException(1); 
		        } 
		        // to check space 
		        if (password.contains(" ")) { 
		            throw new PasswordException(2); 
		        } 
		        if (true) { 
		            int count = 0; 
		  
		            // check digits from 0 to 9 
		            for (int i = 0; i <= 9; i++) { 
		  
		                // to convert int to string 
		                String str1 = Integer.toString(i); 
		  
		                if (password.contains(str1)) { 
		                    count = 1; 
		                } 
		            } 
		            if (count == 0) { 
		                throw new PasswordException(3); 
		            } 
		        } 
		  
		        // for special characters 
		        if (!(password.contains("@") || password.contains("#") 
		              || password.contains("!") || password.contains("~") 
		              || password.contains("$") || password.contains("%") 
		              || password.contains("^") || password.contains("&") 
		              || password.contains("*") || password.contains("(") 
		              || password.contains(")") || password.contains("-") 
		              || password.contains("+") || password.contains("/") 
		              || password.contains(":") || password.contains(".") 
		              || password.contains(", ") || password.contains("<") 
		              || password.contains(">") || password.contains("?") 
		              || password.contains("|"))) { 
		            throw new PasswordException(4); 
		        } 
		  
		        if (true) { 
		            int count = 0; 
		  
		            // checking capital letters 
		            for (int i = 65; i <= 90; i++) { 
		  
		                // type casting 
		                char c = (char)i; 
		  
		                String str1 = Character.toString(c); 
		                if (password.contains(str1)) { 
		                    count = 1; 
		                } 
		            } 
		            if (count == 0) { 
		                throw new PasswordException(5); 
		            } 
		        } 
		  
		        if (true) { 
		            int count = 0; 
		  
		            // checking small letters 
		            for (int i = 90; i <= 122; i++) { 
		  
		                // type casting 
		                char c = (char)i; 
		                String str1 = Character.toString(c); 
		  
		                if (password.contains(str1)) { 
		                    count = 1; 
		                } 
		            } 
		            if (count == 0) { 
		                throw new PasswordException(6); 
		            } 
		        } 
		  
		        // The password is valid 
		        
		    } 
}
