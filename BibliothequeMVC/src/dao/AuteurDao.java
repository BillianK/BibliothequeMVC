package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.IAuteurDao;
import model.Auteur;

public class AuteurDao implements IAuteurDao {

	private Connection connection;

	public AuteurDao(Connection connection) {

		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see dao.IAuteurDao#findByKey(int)
	 */
	@Override
	public Auteur findByKey(int id) throws SQLException {

		PreparedStatement pState = connection.prepareStatement("SELECT * FROM auteur WHERE idauteur = ?");

		pState.setInt(1, id);

		ResultSet result = pState.executeQuery();

		return new Auteur(result.getInt(1), result.getString(2), result.getString(3));
	}

	/* (non-Javadoc)
	 * @see dao.IAuteurDao#findAll()
	 */
	@Override
	public List<Auteur> findAll() throws SQLException {

		PreparedStatement pState = connection.prepareStatement("SELECT * FROM auteur");

		ResultSet result = pState.executeQuery();

		ArrayList<Auteur> auteurs = new ArrayList<>();

		while (result.next()) {
			auteurs.add(new Auteur(result.getInt(1), result.getString(2), result.getString(3)));
		}
		return auteurs;
	}

	/* (non-Javadoc)
	 * @see dao.IAuteurDao#findByLivre(java.lang.String)
	 */
	@Override
	public List<Auteur> findByLivre(String isbn) throws SQLException {

		PreparedStatement pState = connection.prepareStatement(
				"SELECT * FROM auteur WHERE idauteur IN (SELECT idauteur FROM auteur_livre WHERE isbn = ?)");

		ResultSet result = pState.executeQuery();

		ArrayList<Auteur> auteurs = new ArrayList<>();

		while (result.next()) {
			auteurs.add(new Auteur(result.getInt(1), result.getString(2), result.getString(3)));
		}

		return auteurs;
	}
}
