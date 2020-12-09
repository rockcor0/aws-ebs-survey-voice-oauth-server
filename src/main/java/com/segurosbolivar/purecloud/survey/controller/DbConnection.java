package com.segurosbolivar.purecloud.survey.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Clase controladora de las conexiones a la base de datos. 
 * @author rdelgado
 *
 */
public class DbConnection {

	/**
	 * Constructor de la clase. 
	 */
	public DbConnection() {

	}

	/**
	 * Abrir conexión a la base de datos.
	 * @return Connection Conexión con la base de datos.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NamingException{

//		InitialContext ctx = new InitialContext();
//		DataSource ds = (DataSource)ctx.lookup("*****");
//		Connection conn = ds.getConnection();

		Class.forName("*****").newInstance();
		Properties props = new Properties();
		props.setProperty("*****", "*****");
		props.setProperty("*****", "*****");
		
		//Local
		//String url = "jdbc:postgresql://localhost:5432/survey";
		
		//Aws
		String url = "*****";
		
		Connection conn = DriverManager.getConnection(url,props);

		return conn;

	}

	/**
	 * Cerrar conexión con la base de datos.
	 * @param connection
	 * @throws SQLException
	 */
	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

}
