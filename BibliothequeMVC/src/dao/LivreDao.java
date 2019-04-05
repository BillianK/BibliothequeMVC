package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.ILivreDao;
import model.Livre;

public class LivreDao implements ILivreDao {

	private Connection connection;

	public LivreDao(Connection connection) {

		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see dao.ILivreDao#findByKey(java.lang.String)
	 */
	@Override
	public Livre findByKey(String isbn) throws SQLException {

		PreparedStatement pState = connection.prepareStatement("SELECT * FROM livre WHERE isbn = ?");

		pState.setString(1, isbn);
		
		ResultSet result = pState.executeQuery();

		return new Livre(result.getString(1), result.getString(2), result.getTimestamp(5), result.getInt(6), null, null, null);
	}
}
