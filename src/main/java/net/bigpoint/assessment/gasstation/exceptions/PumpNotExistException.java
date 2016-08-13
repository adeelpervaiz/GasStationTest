package main.java.net.bigpoint.assessment.gasstation.exceptions;

/**
 * This exception is thrown whenever Gas pump is not available for Certain Type
 * 
 */
public class PumpNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6122242515896806718L;
	
	private String msg = "Gas is too expensive now a days";

	
	public String getMessage() {
        return msg ;
    }
	
	public String getLocalizedMessage() {
        return msg ;
    }
	
	public PumpNotExistException(String msg){
		this.msg = msg ;
	}

}
