package com.segurosbolivar.purecloud.survey.dto;
//@Entity
//@Table(name=Constant.DB_TABLE_NAME_TOKEN)
public class DTOToken {

//	@Id
	private String tokenId;
	
//	@Column
	private String start_in;
	
//	@Column
	private String access_token;
	
//	@Column
	private String token_type;
	
//	@Column
	private long expires_in;
	
	private String createdDate;
	
	private String updatedDate;
	
	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getStarDate() {
		return start_in;
	}

	public void setStarDate(String starDate) {
		this.start_in = starDate;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
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
