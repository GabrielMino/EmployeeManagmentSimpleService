package employee.management.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {




	public NoDataFoundException(String message) {
		super(message);
		
	}
}

/*

public class NoDataFoundException extends ResponseStatusException {
	
	  public NoDataFoundException(String message){
		  
		  
	    super(HttpStatus.NOT_FOUND, message);
	  }
	
	}
*/

