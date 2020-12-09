package com.segurosbolivar.purecloud.survey.model;

public class Token {
	
	private String tokenId;
	private String access_token;
	private String token_type;
	private long expires_in;
	private String start_in;
	private String createdDate;
	private String updatedDate;
	
	/**
	 * Constructor de la clase
	 */
	public Token() {
		super();
	}

	/**
	 * Constructor de la clase
	 * @param tokenId
	 * @param access_token
	 * @param token_type
	 * @param expires_in
	 * @param start_in
	 * @param createdDate
	 * @param updatedDate
	 */
	public Token(String tokenId, String access_token, String token_type, int expires_in, String start_in,
			String createdDate, String updatedDate) {
		this.tokenId = tokenId;
		this.access_token = access_token;
		this.token_type = token_type;
		this.expires_in = expires_in;
		this.start_in = start_in;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId the tokenId to set
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
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
	 * @return the start_in
	 */
	public String getStart_in() {
		return start_in;
	}

	/**
	 * @param start_in the start_in to set
	 */
	public void setStart_in(String start_in) {
		this.start_in = start_in;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public String getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
