package control;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionFactory {

	Connection getConnection(String nomPilote, String UrlBD, String authorizationID, String password)
			throws ClassNotFoundException, SQLException;

	Connection getConnectionSansAutoCommit(String nomPilote, String UrlBD, String authorizationID, String password)
			throws ClassNotFoundException, SQLException;

}