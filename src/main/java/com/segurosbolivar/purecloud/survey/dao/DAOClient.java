package com.segurosbolivar.purecloud.survey.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.segurosbolivar.purecloud.survey.controller.DbConnection;
import com.segurosbolivar.purecloud.survey.dto.DTOClient;
import com.segurosbolivar.purecloud.survey.resources.Constant;

public class DAOClient {

	/**
	 * Obtiene un Client Oauth de la base de datos
	 * @param clientId El identificador del cliente.
	 * @param clientSecret La clave del cliente
	 * @return Objeto DTOCliente con la información del cliente
	 * @throws InstantiationException 
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public DTOClient getClient(String clientId, String clientSecret) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NamingException {
		DTOClient dtoClient = new DTOClient();

		DbConnection dbConnection = new DbConnection();
		Connection connection = dbConnection.getConnection();

		String sql = "SELECT * FROM pure_enc_auth_clients WHERE clientid = '"+clientId+"' AND clientsecret = '"+clientSecret+"'";
		CallableStatement cs = connection.prepareCall(sql);
		ResultSet result = cs.executeQuery();

		while(result.next()) {
			
			boolean isActive = false;
			
			if(result.getInt(11) == 1)
				isActive = true;

			dtoClient.setClientId(result.getString(1));
			dtoClient.setClientSecret(result.getString(2));
			dtoClient.setClientName(result.getString(3));
			dtoClient.setDescription(result.getString(4));
			dtoClient.setAccessTokenValiditySeconds(result.getInt(5));
			dtoClient.setRedirectUri(result.getString(6));
			dtoClient.setScope(result.getString(7));
			dtoClient.setOwner(result.getString(8));
			dtoClient.setCreatedDate(result.getString(9));
			dtoClient.setUpdatedDate(result.getString(10));
			dtoClient.setActive(isActive);
		}

		dbConnection.closeConnection(connection);

		return dtoClient;
	}

	/**
	 * Crea un cliente oAuth para el acceso a los servicios web
	 * @param dtoClient Información del nuevo cliente oauth
	 * @return true o false dependiendo de si la operación tuvo éxito o falló
	 * @throws Exception
	 */
	public boolean createOAuthClient(DTOClient dtoClient) throws Exception {
		DbConnection dbConnection = new DbConnection();
		Connection connection = dbConnection.getConnection();
		boolean created = false;
		
		int isActive = 0;
		
		if(dtoClient.isActive())
			isActive = 1;
		
		String sql = "INSERT INTO pure_enc_auth_clients(clientid, clientsecret, clientname, description, tokenvalidity, redirecturi, scope, owner, createddate, updateddate, isactive) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		//creating PreparedStatement object to execute query
		PreparedStatement preStatement = connection.prepareStatement(sql);
		preStatement.setString(1, dtoClient.getClientId());
		preStatement.setString(2, dtoClient.getClientSecret());
		preStatement.setString(3, dtoClient.getClientName());
		preStatement.setString(4, dtoClient.getDescription());
		preStatement.setInt(5, (int) dtoClient.getAccessTokenValiditySeconds());
		preStatement.setString(6, dtoClient.getRedirectUri());
		preStatement.setString(7, dtoClient.getScope());
		preStatement.setString(8, dtoClient.getOwner());
		preStatement.setString(9, dtoClient.getCreatedDate());
		preStatement.setString(10, dtoClient.getUpdatedDate());
		preStatement.setInt(11, isActive);
		
		int result = preStatement.executeUpdate();
		//ResultSet result = preStatement.executeQuery();

		System.out.println("Result = " + result);
		if(result > 0) {
			System.out.println("No insertado");
		} else {
			System.out.println("Insertado");
			created = true;
		}

		dbConnection.closeConnection(connection);
		System.out.println("Conexión cerrada");
		return created;
	}
}
