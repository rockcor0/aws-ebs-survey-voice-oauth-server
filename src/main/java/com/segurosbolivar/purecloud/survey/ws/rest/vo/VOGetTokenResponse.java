package com.segurosbolivar.purecloud.survey.ws.rest.vo;

public class VOGetTokenResponse {
	
	private int code;
	private String description;
	private String access_token;
	private String token_type;
	private long expires_in;
	
	public VOGetTokenResponse() {
		
	}
	
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}
	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	/**
	 * @return the expires_in
	 */
	public long getExpires_in() {
		return expires_in;
	}
	/**
	 * @param l the expires_in to set
	 */
	public void setExpires_in(long l) {
		this.expires_in = l;
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
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
