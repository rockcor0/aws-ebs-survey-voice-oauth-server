package com.segurosbolivar.purecloud.survey.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import com.segurosbolivar.purecloud.survey.controller.DbConnection;
import com.segurosbolivar.purecloud.survey.dto.DTOToken;
import com.segurosbolivar.purecloud.survey.resources.Constant;

public class DAOToken {

	/**
	 * Almacena un token en la base de datos
	 * @param token Objeto a almacenar
	 * @return True o false dependiendo de si la operación tiene o no éxito.
	 * @throws Exception
	 */
	public boolean saveToken(DTOToken dtoToken) throws Exception {

		boolean saved = false;

		DbConnection dbConnection = new DbConnection();
		Connection connection = dbConnection.getConnection();

		String tokenId = dtoToken.getTokenId();
		String accessToken = dtoToken.getAccess_token();
		String tokenType = dtoToken.getToken_type();
		int expiresIn = (int) dtoToken.getExpires_in();
		String startDate = dtoToken.getStarDate();
		String createdDate = dtoToken.getCreatedDate();
		String updatedDate = dtoToken.getUpdatedDate();

		String sql = "INSERT INTO pure_enc_token_v2(tokenid, access_token, token_type, expires_in, start_in, createddate, updateddate) VALUES(?,?,?,?,?,?,?)";
		
		//creating PreparedStatement object to execute query
		PreparedStatement preStatement = connection.prepareStatement(sql);
		preStatement.setString(1, tokenId);
		preStatement.setString(2, accessToken);
		preStatement.setString(3, tokenType);
		preStatement.setInt(4, expiresIn);
		preStatement.setString(5, startDate);
		preStatement.setString(6, createdDate);
		preStatement.setString(7, updatedDate);

		int result = preStatement.executeUpdate();
		//ResultSet result = preStatement.executeQuery();

		System.out.println("Result = " + result);

		if(result > 0) {
			System.out.println("Insertado");
			saved = true;
		} else {
			System.out.println("No insertado");
		}

		dbConnection.closeConnection(connection);
		System.out.println("Conexión cerrada");

		return saved;
	}

	/**
	 * Obtener un token de la base de datos
	 * @param access_token token
	 * @return Objeto DTOToken con información del token
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NamingException
	 */
	public DTOToken getToken(String access_token) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, NamingException {
		DTOToken dtoToken = new DTOToken();

		DbConnection dbConnection = new DbConnection();
		Connection connection = dbConnection.getConnection();

		String sql = "SELECT * FROM pure_enc_token_v2 WHERE access_token = '"+access_token+"'";
		CallableStatement cs = connection.prepareCall(sql);
		ResultSet result = cs.executeQuery();

		while(result.next()) {
			dtoToken.setTokenId(result.getString(1));
			dtoToken.setAccess_token(result.getString(2));
			dtoToken.setToken_type(result.getString(3));
			dtoToken.setExpires_in(result.getInt(4));
			dtoToken.setStart_in(result.getString(5));
			dtoToken.setCreatedDate(result.getString(6));
			dtoToken.setUpdatedDate(result.getString(7));
		}

		dbConnection.closeConnection(connection);

		return dtoToken;
	}

	/**
	 * Elimina tokens que han expirado de la base de datos.
	 * @param date Fecha límite para eliminación de los tokens.
	 * @throws Exception
	 */
	public void deleteOldTokend(String date) throws Exception {
		String sql = "DELETE from pure_enc_token_v2 WHERE expires_in > '"+ date +"'";
		
		DbConnection dbConnection = new DbConnection();
		Connection connection = dbConnection.getConnection();
		//creating PreparedStatement object to execute query
		PreparedStatement preStatement = connection.prepareStatement(sql);
		preStatement.setString(1, date);

		int result = preStatement.executeUpdate();

		System.out.println("Result = " + result);

		if(result > 0) {
			System.out.println("Insertado");
		} else {
			System.out.println("No insertado");
		}
		dbConnection.closeConnection(connection);
	}

}
