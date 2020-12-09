package com.segurosbolivar.purecloud.survey.threads;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.segurosbolivar.purecloud.survey.controller.Control;
import com.segurosbolivar.purecloud.survey.dto.DTOClient;
import com.segurosbolivar.purecloud.survey.exceptions.InvalidCredentialsException;
import com.segurosbolivar.purecloud.survey.model.Token;
import com.segurosbolivar.purecloud.survey.resources.Constant;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOCreateUserRequest;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOCreateUserResponse;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetTokenRequest;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetTokenResponse;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetValidateTokenRequest;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetValidateTokenResponse;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * Inicia un nuevo hilo para crear, modificar, obtener o eliminar un token.
 * Maneja las excepciones de los métodos.
 * @author Ricardo Delgado
 *
 */
public class CrudAuth extends Thread {

	/**
	 * Inicia la ejecución del hilo.
	 */
	public void run() {

	}

	/**
	 * Obtiene un token de la base de datos
	 * @param voGetTokenRequest Datos de solicitud del token
	 * @return Respuesta con el token
	 */
	public VOGetTokenResponse getToken(VOGetTokenRequest voGetTokenRequest) {
		//VOHaveTokenResponse voHaveTokenResponse = new VOHaveTokenResponse();
		VOGetTokenResponse voGetTokenResponse = new VOGetTokenResponse();
		Control control = Control.getInstance();
		/*
		 * Intenta obtener un nuevo token y prepara la respuesta o lanza una excepción.
		 */
		try {

			DTOClient dtoClient = control.getClient(voGetTokenRequest.getClientId(), voGetTokenRequest.getClientSecret());

			if(dtoClient.getClientId() != null && dtoClient.getClientSecret() != null && dtoClient.isActive() ) {

				//Generar un token jwt
				Token token = control.getToken();

				//Guardar el token en la base de datos

				boolean savedToken = control.saveToken(token);

				if(!(token.getAccess_token().equals(""))) {
					voGetTokenResponse.setCode(Constant.RESPONSE_CODE_VALID_200);
					voGetTokenResponse.setDescription(Constant.RESPONSE_MESSAGE_VALID_200);
					voGetTokenResponse.setAccess_token(token.getAccess_token());
					voGetTokenResponse.setToken_type(token.getToken_type());
					voGetTokenResponse.setExpires_in(token.getExpires_in());
				}
				else {
					voGetTokenResponse.setCode(Constant.ERROR_CODE_GENERIC_EXCEPTION);
					voGetTokenResponse.setDescription(Constant.ERROR_MESSAGE_BAD_REQUEST);
					voGetTokenResponse.setAccess_token("");
					voGetTokenResponse.setToken_type("");
					voGetTokenResponse.setExpires_in(0);
				}
			} else {
				voGetTokenResponse.setCode(Constant.ERROR_CODE_INVALID_PASSWORD_USER);
				voGetTokenResponse.setDescription(Constant.ERROR_MESSAGE_INVALID_PASSWORD_USER);
			}

		} catch (InvalidCredentialsException e) {
			voGetTokenResponse.setCode(e.getCode());
			voGetTokenResponse.setDescription(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			voGetTokenResponse.setCode(Constant.ERROR_CODE_GENERIC_EXCEPTION);
			voGetTokenResponse.setDescription(e.getMessage());
			e.printStackTrace();
		} 
		return voGetTokenResponse;
	}

	/**
	 * Valida si un token exite
	 * @param voGetValidateTokenRequest Información del token
	 * @return Objeto con la respuesta del token
	 */
	public VOGetValidateTokenResponse validateToken(VOGetValidateTokenRequest voGetValidateTokenRequest) {
		Control control = Control.getInstance();
		VOGetValidateTokenResponse voGetValidateTokenResponse = new VOGetValidateTokenResponse();


		try {
			DTOClient dtoClient = control.getClient(voGetValidateTokenRequest.getClientId(), voGetValidateTokenRequest.getClientSecret());

			if(dtoClient.getClientId() != null && dtoClient.getClientSecret() != null && dtoClient.isActive() ) {
				if(control.validateToken(voGetValidateTokenRequest)) {
					voGetValidateTokenResponse.setCode(Constant.RESPONSE_CODE_VALID_200);
					voGetValidateTokenResponse.setDescription(Constant.RESPONSE_MESSAGE_VALID_200);
					voGetValidateTokenResponse.setValid(true);
				}
			}else {
				voGetValidateTokenResponse.setCode(Constant.ERROR_CODE_INVALID_PASSWORD_USER);
				voGetValidateTokenResponse.setDescription(Constant.ERROR_MESSAGE_INVALID_PASSWORD_USER);
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
			voGetValidateTokenResponse.setCode(Constant.ERROR_CODE_GENERIC_EXCEPTION);
			voGetValidateTokenResponse.setDescription(e.getMessage());
			voGetValidateTokenResponse.setValid(false);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			voGetValidateTokenResponse.setCode(Constant.ERROR_CODE_GENERIC_EXCEPTION);
			voGetValidateTokenResponse.setDescription(e.getMessage());
			voGetValidateTokenResponse.setValid(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			voGetValidateTokenResponse.setCode(Constant.ERROR_CODE_GENERIC_EXCEPTION);
			voGetValidateTokenResponse.setDescription(e.getMessage());
			voGetValidateTokenResponse.setValid(false);
		} catch (SQLException e) {
			e.printStackTrace();
			voGetValidateTokenResponse.setCode(Constant.ERROR_CODE_GENERIC_EXCEPTION);
			voGetValidateTokenResponse.setDescription(e.getMessage());
			voGetValidateTokenResponse.setValid(false);
		} catch (NamingException e) {
			e.printStackTrace();
			voGetValidateTokenResponse.setCode(Constant.ERROR_CODE_GENERIC_EXCEPTION);
			voGetValidateTokenResponse.setDescription(e.getMessage());
			voGetValidateTokenResponse.setValid(false);
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			voGetValidateTokenResponse.setCode(Constant.RESPONSE_CODE_INVALID_401);
			voGetValidateTokenResponse.setDescription(Constant.ERROR_MESSAGE_INVALID_LOGIN_CREDENTIALS);
			voGetValidateTokenResponse.setValid(false);
		} catch (Exception e) {
			e.printStackTrace();
			voGetValidateTokenResponse.setCode(Constant.RESPONSE_CODE_NOT_FOUND_404);
			voGetValidateTokenResponse.setDescription(Constant.ERROR_MESSAGE_NOT_FOUND_TOKEN);
			voGetValidateTokenResponse.setValid(false);
		}
		return voGetValidateTokenResponse;
	}

	/**
	 * Elimina los tokens expirados de la base de datos.
	 * @param voGetTokenRequest 
	 */
	public void deleteOldTokens() {
		Control control = Control.getInstance();
		try {
			control.deleteOldTokens();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Crea un cliente de autenticación
	 * @param voCreateUserRequest Información del cliente.
	 * @return Objeto con la respuesta de la solicitud.
	 */
	public VOCreateUserResponse createOAuthClient(VOCreateUserRequest voCreateUserRequest) {
		Control control = Control.getInstance();
		VOCreateUserResponse voCreateUserResponse = new VOCreateUserResponse();

		try {

			DTOClient dtoClient = control.getClient(voCreateUserRequest.getClientId(), voCreateUserRequest.getClientSecret());

			if(dtoClient.getClientId() != null && dtoClient.getClientSecret() != null && dtoClient.isActive() ) {
				boolean created = control.createOAuthClient(voCreateUserRequest);

				if(created) {
					voCreateUserResponse.setCode(Constant.RESPONSE_CODE_VALID_200);
					voCreateUserResponse.setDescription(Constant.RESPONSE_MESSAGE_VALID_200);
				}
			}else {
				voCreateUserResponse.setCode(Constant.ERROR_CODE_INVALID_PASSWORD_USER);
				voCreateUserResponse.setDescription(Constant.ERROR_MESSAGE_INVALID_PASSWORD_USER);
			}

		} catch (Exception e) {
			voCreateUserResponse.setCode(Constant.RESPONSE_CODE_INVALID_401);	
			voCreateUserResponse.setDescription(e.getMessage());
			e.printStackTrace();
		}

		return voCreateUserResponse;
	}

}
