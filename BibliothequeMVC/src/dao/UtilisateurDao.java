package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.IUtilisateurDao;
import model.Adherent;
import model.Employe;
import model.EnumCategorieEmploye;
import model.EnumCategorieUtilisateur;
import model.Utilisateur;

public class UtilisateurDao implements IUtilisateurDao {

	private Connection connection;

	public UtilisateurDao(Connection connection) {

		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see dao.IUtilisateurDao#findByKey(int)
	 */
	@Override
	public Utilisateur findByKey(int id) throws SQLException {

		PreparedStatement pState = connection.prepareStatement(
				"SELECT * FROM utilisateur u LEFT OUTER JOIN employe e ON u.idUtilisateur = e.idUtilisateur LEFT OUTER JOIN adherent a ON u.idUtilisateur = a.idUtilisateur WHERE (a.idUtilisateur IS NOT NULL OR e.idUtilisateur IS NOT NULL) AND u.idUtilisateur = ?");

		pState.setInt(1, id);

		ResultSet result = pState.executeQuery();
		
		result.next();

		if (result.getString("categorieutilisateur").equalsIgnoreCase("adherent")) {
			return new Adherent(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
					result.getString(5), result.getTimestamp(6), result.getString(7), EnumCategorieUtilisateur.ADHERENT,
					null, result.getString(9));
		} else if (result.getString("categorieutilisateur").equalsIgnoreCase("employe")) {
			return new Employe(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
					result.getString(5), result.getTimestamp(6), result.getString(7), EnumCategorieUtilisateur.EMPLOYE,
					result.getString(9), EnumCategorieEmploye.valueOf(result.getString("CATEGORIEEMPLOYE").toUpperCase()), null);
		} else
			return null;
	}

	/* (non-Javadoc)
	 * @see dao.IUtilisateurDao#findAll()
	 */
	@Override
	public List<Utilisateur> findAll() throws SQLException {

		PreparedStatement pState = connection.prepareStatement(
				"SELECT * FROM utilisateur u LEFT OUTER JOIN employe e ON u.idUtilisateur = e.idUtilisateur LEFT OUTER JOIN adherent a ON u.idUtilisateur = a.idUtilisateur WHERE a.idUtilisateur IS NOT NULL OR e.idUtilisateur IS NOT NULL");

		ResultSet result = pState.executeQuery();

		ArrayList<Utilisateur> utilisateurs = new ArrayList<>();

		while (result.next()) {

//			EnumCategorieUtilisateur categorie = null;

			if (result.getString("categorieutilisateur").equalsIgnoreCase("adherent")) {
				utilisateurs.add(new Adherent(result.getInt(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getTimestamp(6), result.getString(7),
						EnumCategorieUtilisateur.ADHERENT, null, result.getString(9)));
			} else if (result.getString("categorieutilisateur").equalsIgnoreCase("employe")) {
				utilisateurs.add(new Employe(result.getInt(1), result.getString(2), result.getString(3),
						result.getString(4), result.getString(5), result.getTimestamp(6), result.getString(7),
						EnumCategorieUtilisateur.EMPLOYE, result.getString(9),
						EnumCategorieEmploye.valueOf(result.getString(10)), null));
			}

		}
		return utilisateurs;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println(new UtilisateurDao(new ConnectionFactory().getConnectionSansAutoCommit("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "biblio", "biblio")).findByKey(17));
	}
}