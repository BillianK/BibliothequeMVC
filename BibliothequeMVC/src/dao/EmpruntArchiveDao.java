package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.IEmpruntArchiveDao;
import model.EmpruntArchive;
import model.EmpruntEnCoursDB;

public class EmpruntArchiveDao implements IEmpruntArchiveDao {

	private Connection connection;

	public EmpruntArchiveDao(Connection connection) {

		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see dao.IEmpruntArchive#findByKey(int)
	 */
	@Override
	public EmpruntArchive findByKey(int id) throws SQLException {

		PreparedStatement pState = connection
				.prepareStatement("SELECT * FROM empruntarchive WHERE idempruntarchive = ?");

		pState.setInt(1, id);

		ResultSet result = pState.executeQuery();

		result.next();

		return new EmpruntArchive(result.getInt(1), result.getTimestamp(2), result.getTimestamp(3), null, null);
	}

	/* (non-Javadoc)
	 * @see dao.IEmpruntArchive#insertEmpruntArchive(model.EmpruntEnCoursDB)
	 */
	@Override
	public boolean insertEmpruntArchive(EmpruntEnCoursDB emprunt) throws SQLException {

		PreparedStatement pState = connection.prepareStatement(
				"INSERT INTO EmpruntArchive( idempruntArchive, dateemprunt, daterestitutioneff, idExemplaire, idUtilisateur) VALUES(seq_archive.nextval,?,?,?,?)");

		pState.setDate(1, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
		pState.setDate(2, new java.sql.Date(java.lang.System.currentTimeMillis()));
		pState.setInt(3, emprunt.getIdExemplaire());
		pState.setInt(4, emprunt.getIdUtilisateur());

		int insertOk = pState.executeUpdate();
		return insertOk == 1 ? true : false;

	}
}
