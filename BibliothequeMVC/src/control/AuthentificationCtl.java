package control;

import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import dao.ConnectionFactory;
import dao.UtilisateurDao;
import model.Utilisateur;
import test.DaoTest;
import view.AuthentificationView;

public class AuthentificationCtl {

	private static Utilisateur utilisateur;
	private static Connection connection;
	private static AuthentificationView view;
	private static boolean token;

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

	public static boolean getToken() {
		return token;
	}
	public static String getAccess() {
		return utilisateur.getPwd();
	}

	public static boolean checkPwd(char[] pwd) {
		getAccess().equals(new String(pwd));
		token = true;
		return token;
	}

	public static Component getView() {

		if (view == null)
			return new AuthentificationView();
		else
			return view;
	}
}
