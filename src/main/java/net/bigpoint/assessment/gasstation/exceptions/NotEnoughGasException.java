package main.java.net.bigpoint.assessment.gasstation.exceptions;

/**
 * This exception is thrown whenever gas could not be bought because not enough was available
 * 
 */
public class NotEnoughGasException extends Exception {

	private String msg = "Gas is short now a days";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4577139900795204370L;

	
	public String getMessage() {
        return msg ;
    }
	
	public String getLocalizedMessage() {
        return msg ;
    }
	
	public NotEnoughGasException(String msg){
		this.msg = msg ;
	}
}
