package com.segurosbolivar.purecloud.survey.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;
import com.google.inject.Singleton;
import com.segurosbolivar.purecloud.survey.dao.DAOClient;
import com.segurosbolivar.purecloud.survey.dao.DAOToken;
import com.segurosbolivar.purecloud.survey.dto.DTOClient;
import com.segurosbolivar.purecloud.survey.dto.DTOToken;
import com.segurosbolivar.purecloud.survey.exceptions.InvalidCredentialsException;
import com.segurosbolivar.purecloud.survey.model.Token;
import com.segurosbolivar.purecloud.survey.resources.Constant;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOCreateUserRequest;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetValidateTokenRequest;

/**
 * Clase controladora de las consultas y solicitudes a la base de datos.
 * @author Ricardo Delgado
 *
 */
@Singleton
public class DbControl {

	private static DbControl dbControl;

	/**
	 * Instancia la clase DBControl.
	 * @return La instancia de la clase.
	 */
	public static DbControl getInstance() {
		if(dbControl == null)
			dbControl = new DbControl();

		return dbControl;
	}

	private DbControl() {
	}

	/**
	 * Valida que el usuario y password de un servicio es correcto.
	 * Cifra las credenciales en MD5.
	 * @param username
	 * @param password
	 * @return true o false
	 * @throws InvalidWsUserException
	 * @throws InvalidCredentialsException 
	 */
	@Deprecated
	public boolean verificarUsuarioPassword(String clientId, String clientSecret) throws InvalidCredentialsException {

		// Password de ejemplo en claro: rX#.cw8@3%D 
		String cifratedPassword = "6392087bf091b54cddde0eb6c5966e17"; //Debe llamar un método que permita consultar en la base de datos el passoword cifrado. 

		// Username de ejemplo en claro: U5u@r10@.#S
		String cifratedUser = "d63b612e066867bc08291fb9940feb40"; 

		System.out.println("clientId :" + clientId + " - clientIdCifrado: " + cifrarMD5(clientId));
		System.out.println("clientSecret: " + clientSecret + " - clientSecretCifrado: " + cifrarMD5(clientSecret));

		if( cifrarMD5(clientId).equalsIgnoreCase(cifratedUser) &&
				cifrarMD5(clientSecret).equalsIgnoreCase(cifratedPassword) ) {
			return true;
		}
		else {
			throw new InvalidCredentialsException(Constant.RESPONSE_MESSAGE_INVALID_401, Constant.RESPONSE_CODE_INVALID_401);
		}
	}

	/**
	 * Cifra una cadena en MD5
	 * @param Cadena a cifrar
	 * @return Cadena cifrada
	 */
	public String cifrarMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Obtener un Cliente Oauth de la base de datos
	 * @param clientId Identificador del cliente
	 * @param clientSecret Secreto del cliente
	 * @return Objeto DTOCliente con información del cliente de autenticación. 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public DTOClient getClient(String clientId, String clientSecret) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NamingException {
		DAOClient daoClient = new DAOClient();
		
		//TODO eliminar syso
		System.out.println(cifrarMD5(clientId));
		System.out.println(cifrarMD5(clientSecret));
		
		return daoClient.getClient(cifrarMD5(clientId), cifrarMD5(clientSecret));
	}

	/**
	 * Crea un cliente de autenticación en la base de datos
	 * @param voCreateUserRequest Información del cliente oauth
	 * @return true o false dependiendo de si el cliente existe o no, respectivamente.
	 * @throws Exception
	 */
	public boolean createOAuthClient(VOCreateUserRequest voCreateUserRequest) throws Exception {
		DAOClient daoClient = new DAOClient();
		DTOClient dtoClient = new DTOClient();
		Control control = Control.getInstance();
		boolean active = false;

		if(voCreateUserRequest.getIsActive() == 1)
			active = true;

		dtoClient.setAccessTokenValiditySeconds(voCreateUserRequest.getTokenValidity());
		dtoClient.setActive(active);
		dtoClient.setClientId(control.cifrarMD5(voCreateUserRequest.getClientId()));
		dtoClient.setClientName(voCreateUserRequest.getClientName());
		dtoClient.setClientSecret(control.cifrarMD5(voCreateUserRequest.getClientSecret()));
		dtoClient.setCreatedDate(control.dateSerConvertToLocalDateTimeViaInstant(new Date()).toString());
		dtoClient.setDescription(voCreateUserRequest.getDescription());
		dtoClient.setOwner(voCreateUserRequest.getOwner());
		dtoClient.setRedirectUri(voCreateUserRequest.getRedirectUri());
		dtoClient.setScope(voCreateUserRequest.getScope());
		dtoClient.setUpdatedDate(control.dateSerConvertToLocalDateTimeViaInstant(new Date()).toString());

		return daoClient.createOAuthClient(dtoClient);
	}

	/**
	 * Almacena un token en la base de datos
	 * @param token Objeto a almacenar
	 * @return True o false dependiendo de si la operación tiene o no éxito.
	 * @throws Exception
	 */
	public boolean saveToken(Token token) throws Exception {
		DAOToken daoToken = new DAOToken();
		DTOToken dtoToken = new DTOToken();

		dtoToken.setAccess_token(token.getAccess_token());
		dtoToken.setExpires_in(token.getExpires_in());
		dtoToken.setStarDate(token.getStart_in());
		dtoToken.setToken_type(token.getToken_type());
		dtoToken.setTokenId(token.getTokenId());
		dtoToken.setCreatedDate(token.getCreatedDate());
		dtoToken.setUpdatedDate(token.getUpdatedDate());

		return daoToken.saveToken(dtoToken);
	}

	/**
	 * Obtener un token de la base de datos
	 * @return Objeto Token
	 */
	public DTOToken getToken(VOGetValidateTokenRequest voGetValidateTokenRequest) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NamingException {
		DAOToken daoToken = new DAOToken();
		return daoToken.getToken(voGetValidateTokenRequest.getAccess_token());
	}

	/** Elimina tokens que han expirado de la base de datos.
	 * @throws Exception
	 */
	public void deleteOldTokens(String date) throws Exception {
		DAOToken daoToken = new DAOToken();
		daoToken.deleteOldTokend(date);		
	}

}
