package main.java.net.bigpoint.assessment.gasstation.exceptions;

/**
 * This exception is thrown whenever gas could not be bought because the price was too high
 * 
 */
public class GasTooExpensiveException extends Exception {

	private String msg = "Gas is too expensive now a days";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2581151114207596829L;

	
	public String getMessage() {
        return msg ;
    }
	
	public String getLocalizedMessage() {
        return msg ;
    }
	
	public GasTooExpensiveException(String msg){
		this.msg = msg ;
	}
}
