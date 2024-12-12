package maze;

 

public class MazeReadingException extends Exception {
	
 
	String errorMessage;
	public MazeReadingException(String errorMessage) {
		super(errorMessage);
		 
		this.errorMessage = errorMessage;
		
	}
	
	public MazeReadingException(Throwable cause) {
		super(cause);
	}
	
	
	

}
