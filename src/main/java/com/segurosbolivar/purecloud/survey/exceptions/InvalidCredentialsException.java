/**
 * 
 */
package com.segurosbolivar.purecloud.survey.exceptions;

/**
 * @author Ricardo Delgado
 *
 */
public class InvalidCredentialsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8263207003375906148L;
	
	private String message;
	private int code;

	/**
	 * 
	 */
	public InvalidCredentialsException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param code
	 */
	public InvalidCredentialsException(String message, int code) {
		this.message = message;
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

}
