package control;

import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import dao.AdherentGeneralDao;
import dao.ConnectionFactory;
import dao.EmpruntEnCoursDao;
import dao.ExemplaireDao;
import dao.UtilisateurDao;
import model.Adherent;
import model.BiblioException;
import model.EnumCategorieUtilisateur;
import model.EnumStatusExemplaire;
import model.Exemplaire;
import model.Utilisateur;
import test.DaoTest;
import view.EmpruntView;

public class EmprunterCtl {

	private static Utilisateur utilisateur;
	private static Exemplaire exemplaire;
	private static Connection connection;
	private static EmpruntView view;

	public static boolean changerStatus() throws SQLException {

		return new ExemplaireDao(connection).updateStatus(exemplaire);
	}

	/**
	 * @param Connection
	 *            co
	 */
	public static void conditionsEmprunt() {
		AdherentGeneralDao.setConnection(connection);
		Adherent.setDureeMaxPret(AdherentGeneralDao.findDureeMaxPret());
		Adherent.setNbMaxPret(AdherentGeneralDao.findNbMaxPret());
	}

	/**
	 * @param propertiesName
	 * @return
	 */
	public static boolean connection(String propertiesName) {

		Connection co = null;
		InputStream in = DaoTest.class.getResourceAsStream("..\\" + propertiesName + ".properties");

		Properties p = new Properties();

		try {
			p.load(in);
			co = new ConnectionFactory().getConnectionSansAutoCommit(p.getProperty("driver"), p.getProperty("url"),
					p.getProperty("user"), p.getProperty("pwd"));
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

		connection = co;

		return true;
	}

	/**
	 * @param idExemplaire
	 * @param Connection
	 *            co
	 * @return
	 * @throws SQLException
	 */
	public static boolean entreeExemplaire(int idExemplaire) throws SQLException {

		exemplaire = new ExemplaireDao(connection).findByKey(idExemplaire);

		return true;
	}

	/**
	 * @param idUtilisateur
	 * @param Connection
	 *            co
	 * @return
	 * @throws SQLException
	 */
	public static boolean entreeUtilisateur(int idUtilisateur) throws SQLException {

		utilisateur = new UtilisateurDao(connection).findByKey(idUtilisateur);

		return true;
	}

	/**
	 * @param Utilisateur
	 *            u
	 * @param Connection
	 *            co
	 * @return
	 * @throws SQLException
	 */
	public static boolean addEmpruntEnCours() throws SQLException {

		utilisateur.setEmpruntEnCours(new EmpruntEnCoursDao(connection).findByUtilisateur(utilisateur));

		return true;
	}

	/**
	 * @param Utilisateur
	 *            u
	 * @param Exemplaire
	 *            e
	 * @param Connection
	 *            co
	 * @return
	 * @throws SQLException
	 * @throws BiblioException
	 */
	public static boolean enregistrerEmprunt() throws SQLException, BiblioException {

		boolean b;

		if (exemplaire.isDisponible() && utilisateur.getCategorieUtilisateur().equals(EnumCategorieUtilisateur.ADHERENT)
				&& ((Adherent) utilisateur).isConditionPretAcceptees()) {

			exemplaire.setStatus(EnumStatusExemplaire.PRETE);

			changerStatus();

			b = new EmpruntEnCoursDao(connection).insertEmpruntEnCours(utilisateur, exemplaire);

			connection.commit();

			return b;

		} else if (exemplaire.isDisponible()) {

			exemplaire.setStatus(EnumStatusExemplaire.PRETE);

			changerStatus();

			b = new EmpruntEnCoursDao(connection).insertEmpruntEnCours(utilisateur, exemplaire);

			connection.commit();

			return b;

		} else {

			return false;
		}
	}

	public static Component getView() {

		if (view == null)
			return new EmpruntView();
		else
			return view;
	}
}
