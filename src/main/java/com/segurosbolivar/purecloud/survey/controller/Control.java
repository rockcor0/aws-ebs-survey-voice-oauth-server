/**
 * Paquete principal de las clases controladoras.
 */
package com.segurosbolivar.purecloud.survey.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.naming.NamingException;
import com.google.inject.Singleton;
import com.segurosbolivar.purecloud.survey.dto.DTOClient;
import com.segurosbolivar.purecloud.survey.dto.DTOToken;
import com.segurosbolivar.purecloud.survey.exceptions.InvalidCredentialsException;
import com.segurosbolivar.purecloud.survey.model.Token;
import com.segurosbolivar.purecloud.survey.model.TokenFactory;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOCreateUserRequest;
import com.segurosbolivar.purecloud.survey.ws.rest.vo.VOGetValidateTokenRequest;

/**
 * Clase controladora del proyecto que media las funciones entre la base de datos y los web services.
 * @author Ricardo Delgado
 */
@Singleton
public class Control {

	private static DbControl dbControl;

	private static Control control;

	private static TokenFactory tokenFactory;

	/**
	 * Instanciamiento de la clase Control
	 * @return
	 */
	public static Control getInstance() {
		if(control==null)
			control = new Control();
		return control;
	}

	/**
	 * Constructor de la clase Control
	 */
	private Control() {

	}

	/**
	 * Obtener un token de la base de datos
	 * @return Objeto Token
	 */
	public Token getToken() {
		tokenFactory = TokenFactory.getInstance();
		return tokenFactory.createToken();
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
		dbControl = DbControl.getInstance();
		return dbControl.getClient(clientId, clientSecret);
	}

	/**
	 * Crea un cliente de autenticación en la base de datos
	 * @param voCreateUserRequest Información del cliente oauth
	 * @return true o false dependiendo de si el cliente existe o no, respectivamente.
	 * @throws Exception
	 */
	public boolean createOAuthClient(VOCreateUserRequest voCreateUserRequest) throws Exception {
		dbControl = DbControl.getInstance();

		return dbControl.createOAuthClient(voCreateUserRequest);
	}


	/**
	 * Validar credenciales de cliente de autenticación
	 * @param clientId
	 * @param clientSecret
	 * @return
	 * @throws InvalidCredentialsException
	 */
	@Deprecated
	public boolean validateCredentials(String clientId, String clientSecret) throws InvalidCredentialsException {
		dbControl = DbControl.getInstance();
		return dbControl.verificarUsuarioPassword(clientId, clientSecret);
	}

	/**
	 * Cifra una cadena con el algoritmo MD5
	 * @param text Cadena a cifrar
	 * @return Cadena cifrada
	 */
	public String cifrarMD5(String text) {
		dbControl = DbControl.getInstance();
		return dbControl.cifrarMD5(text);
	}

	/**
	 * Almacena un token en la base de datos
	 * @param token Objeto a almacenar
	 * @return True o false dependiendo de si la operación tiene o no éxito.
	 * @throws Exception
	 */
	public boolean saveToken(Token token) throws Exception {
		dbControl = DbControl.getInstance();
		return dbControl.saveToken(token);
	}

	/**
	 * Verifica que un token es válido. 
	 * @param voGetValidateTokenRequest
	 * @return True o false dependiendo de si la operación fue exitosa o falló. 
	 * @throws Exception
	 */
	public boolean validateToken(VOGetValidateTokenRequest voGetValidateTokenRequest) throws Exception {
		tokenFactory = TokenFactory.getInstance();
		dbControl = DbControl.getInstance();
		DTOToken dtoToken = dbControl.getToken(voGetValidateTokenRequest);

		return tokenFactory.decodeToken(dtoToken);
	}

	/**
	 * Elimina tokens que han expirado de la base de datos.
	 * @throws Exception
	 */
	public void deleteOldTokens() throws Exception {

		dbControl = DbControl.getInstance();
		LocalDateTime date = dateSerConvertToLocalDateTimeViaInstant(new Date());
		LocalDateTime oldDate = deleteMinutesToADate(date, 60);

		System.out.println("Fecha act:" + date.toString());
		System.out.println("Fecha con:" + oldDate.toString());

		//Elimina todos los tokens que tengan más de 60 minutos de antigüedad
		dbControl.deleteOldTokens(oldDate.toString());
	}

	/**
	 * Suma una cantidad de minutos a una fecha.
	 * @param intervaloInicioTiempoReal
	 * @param minutes
	 * @return
	 */
	public LocalDateTime deleteMinutesToADate(LocalDateTime intervalo, int minutes) {
		LocalDateTime now = LocalDateTime.parse(intervalo.toString());
		LocalDateTime newDate = now.minusMinutes(minutes);
		return newDate; 
	}

	/**
	 * Convertir una fecha de tipo Date a LocalDateTime
	 * @param dateToConvert
	 * @return
	 */
	public LocalDateTime dateSerConvertToLocalDateTimeViaInstant(Date dateToConvert) {
		//System.out.println("Zona horaria " + dateToConvert.toInstant().atZone(ZoneId.systemDefault()).getOffset());
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}

}
