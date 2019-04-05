package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.IExemplaireDao;
import model.EnumStatusExemplaire;
import model.Exemplaire;

public class ExemplaireDao implements IExemplaireDao {

	private Connection connection;

	public ExemplaireDao(Connection connection) {

		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see dao.IExemplaire#findByKey(int)
	 */
	@Override
	public Exemplaire findByKey(int id) throws SQLException {

		PreparedStatement pState = connection.prepareStatement("SELECT * FROM exemplaire WHERE idExemplaire = ?");

		pState.setInt(1, id);

		ResultSet result = pState.executeQuery();

		result.next();

		return new Exemplaire(result.getInt(1), result.getTimestamp(2),
				EnumStatusExemplaire.valueOf(result.getString(3)), null);
	}

	/* (non-Javadoc)
	 * @see dao.IExemplaire#updateStatus(model.Exemplaire)
	 */
	@Override
	public boolean updateStatus(Exemplaire exemplaire) throws SQLException {

		PreparedStatement pState = connection
				.prepareStatement("UPDATE exemplaire SET status = ? WHERE idexemplaire = ?");

		pState.setString(1, exemplaire.getStatus().name());
		pState.setInt(2, exemplaire.getIdExemplaire());

		return pState.executeUpdate() == 1 ? true : false;

	}
}
