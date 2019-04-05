package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import control.IConnectionFactory;

public class ConnectionFactory implements IConnectionFactory {

	/* (non-Javadoc)
	 * @see dao.IConnectionFactory#getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Connection getConnection(String nomPilote, String UrlBD, String authorizationID, String password)
			throws ClassNotFoundException, SQLException {

		Class.forName(nomPilote);

		return DriverManager.getConnection(UrlBD, authorizationID, password);
	}

	/* (non-Javadoc)
	 * @see dao.IConnectionFactory#getConnectionSansAutoCommit(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Connection getConnectionSansAutoCommit(String nomPilote, String UrlBD, String authorizationID,
			String password) throws ClassNotFoundException, SQLException {

		Class.forName(nomPilote);

		Connection connection = DriverManager.getConnection(UrlBD, authorizationID, password);
		
		connection.setAutoCommit(false);
		
		return connection;
	}
}
