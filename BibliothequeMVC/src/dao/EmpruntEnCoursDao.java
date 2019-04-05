/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.IEmpruntEnCoursDao;
import model.EmpruntEnCoursDB;
import model.Exemplaire;
import model.Utilisateur;

/**
 * @author admin
 *
 */
public class EmpruntEnCoursDao implements IEmpruntEnCoursDao {
	private Connection connection;

	public EmpruntEnCoursDao(Connection connection) {
		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see dao.IEmpruntEnCours#findByKey(int)
	 */
	@Override
	public EmpruntEnCoursDB findByKey(int idExemplaire) throws SQLException {

		PreparedStatement pState = connection.prepareStatement("SELECT * FROM empruntEnCours WHERE idExemplaire = ?");

		pState.setInt(1, idExemplaire);

		ResultSet result = pState.executeQuery();

		result.next();

		return new EmpruntEnCoursDB(result.getInt(1), result.getInt(2), result.getTimestamp(3));
	}

	/* (non-Javadoc)
	 * @see dao.IEmpruntEnCours#insertEmpruntEnCours(model.Utilisateur, model.Exemplaire)
	 */
	@Override
	public boolean insertEmpruntEnCours(Utilisateur utilisateur, Exemplaire exemplaire) throws SQLException {

		PreparedStatement pState = connection
				.prepareStatement("INSERT INTO EmpruntEnCours(idExemplaire, idUtilisateur, DateEmprunt) VALUES(?,?,?)");

		pState.setInt(1, exemplaire.getIdExemplaire());
		pState.setInt(2, utilisateur.getIdUtilisateur());
		pState.setDate(3, new java.sql.Date(System.currentTimeMillis()));

		int insertOk = pState.executeUpdate();
		return insertOk == 1 ? true : false;

	}

	/* (non-Javadoc)
	 * @see dao.IEmpruntEnCours#deleteEmpruntEnCours(model.EmpruntEnCoursDB)
	 */
	@Override
	public boolean deleteEmpruntEnCours(EmpruntEnCoursDB emprunt) throws SQLException {

		PreparedStatement pState = connection
				.prepareStatement("DELETE FROM EmpruntEnCours WHERE idexemplaire = ? AND idutilisateur = ?");

		pState.setInt(1, emprunt.getIdExemplaire());
		pState.setInt(2, emprunt.getIdUtilisateur());

		int insertOk = pState.executeUpdate();
		
		return insertOk == 1 ? true : false;

	}

	/* (non-Javadoc)
	 * @see dao.IEmpruntEnCours#findByUtilisateur(model.Utilisateur)
	 */
	@Override
	public List<EmpruntEnCoursDB> findByUtilisateur(Utilisateur u) throws SQLException {

		PreparedStatement pState = connection.prepareStatement("SELECT * FROM EmpruntEnCours where idutilisateur = ?");
		pState.setInt(1, u.getIdUtilisateur());
		ResultSet result = pState.executeQuery();

		ArrayList<EmpruntEnCoursDB> empruntEnCours = new ArrayList<>();

		while (result.next()) {
			empruntEnCours.add(new EmpruntEnCoursDB(result.getInt(1), result.getInt(2), result.getTimestamp(3)));
		}
		return empruntEnCours;
	}
}
