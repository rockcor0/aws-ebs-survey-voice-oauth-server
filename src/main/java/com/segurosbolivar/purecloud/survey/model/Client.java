/**
 * 
 */
package com.segurosbolivar.purecloud.survey.model;

/**
 * @author rdelgado
 *
 */
public class Client {
	
	private String clientId;
	private String clientSecret;
	private String clientName;
	private String Description;
	private long accessTokenValiditySeconds;
	private String redirectUri;
	private boolean isActive;
	private String scope;
	private String createdDate;
	private String updatedDate;

	/**
	 * Constructor de la clase
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor de la clase
	 * @param clientId
	 * @param clientSecret
	 * @param clientName
	 * @param description
	 * @param accessTokenValiditySeconds
	 * @param redirectUri
	 * @param isActive
	 * @param scope
	 * @param createdDate
	 * @param updatedDate
	 */
	public Client(String clientId, String clientSecret, String clientName, String description,
			long accessTokenValiditySeconds, String redirectUri, boolean isActive, String scope, String createdDate,
			String updatedDate) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.clientName = clientName;
		Description = description;
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
		this.redirectUri = redirectUri;
		this.isActive = isActive;
		this.scope = scope;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}


	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}


	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}


	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}


	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}


	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}


	/**
	 * @return the accessTokenValiditySeconds
	 */
	public long getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}


	/**
	 * @param accessTokenValiditySeconds the accessTokenValiditySeconds to set
	 */
	public void setAccessTokenValiditySeconds(long accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}


	/**
	 * @return the redirectUri
	 */
	public String getRedirectUri() {
		return redirectUri;
	}


	/**
	 * @param redirectUri the redirectUri to set
	 */
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}


	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}


	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}


	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
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
