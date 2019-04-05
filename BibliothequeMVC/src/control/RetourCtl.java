package control;

import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import dao.ConnectionFactory;
import dao.EmpruntArchiveDao;
import dao.EmpruntEnCoursDao;
import dao.ExemplaireDao;
import model.BiblioException;
import model.EmpruntEnCoursDB;
import model.EnumStatusExemplaire;
import model.Exemplaire;
import test.DaoTest;
import view.RetourView;

public class RetourCtl {

	private static Exemplaire exemplaire;
	private static EmpruntEnCoursDB empruntEnCours;
	private static Connection connection;
	private static RetourView view;

	public static boolean changerStatus() throws SQLException {

		return new ExemplaireDao(connection).updateStatus(exemplaire);
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

	public static boolean chercherEmpruntEnCours() throws SQLException {

		empruntEnCours = new EmpruntEnCoursDao(connection).findByKey(exemplaire.getIdExemplaire());

		return true;
	}

	public static boolean supprimerEmpruntEnCours() throws SQLException {

		return new EmpruntEnCoursDao(connection).deleteEmpruntEnCours(empruntEnCours);
	}

	public static boolean archiverEmprunt() throws SQLException {
		
		return new EmpruntArchiveDao(connection).insertEmpruntArchive(empruntEnCours);
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
	public static boolean retournerEmprunt() throws SQLException, BiblioException {

		archiverEmprunt();
		
		supprimerEmpruntEnCours();

		exemplaire.setStatus(EnumStatusExemplaire.DISPONIBLE);

		changerStatus();

		connection.commit();

		return true;
	}

	public static Component getView() {

		if (view == null)
			return new RetourView();
		else
			return view;
	}
	
}
