package control;

import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import dao.AdherentGeneralDao;
import dao.ConnectionFactory;
import dao.EmpruntEnCoursDao;
import dao.ExemplaireDao;
import dao.UtilisateurDao;
import model.Adherent;
import model.BiblioException;
import model.EmpruntEnCoursDB;
import model.Exemplaire;
import model.Utilisateur;
import test.DaoTest;
import view.ConsulterView;
import view.EmpruntView;

public class ConsulterCtl {

	private static Utilisateur utilisateur;
	private static List<Exemplaire> exemplaires;
	private static List<EmpruntEnCoursDB> empruntEnCoursDBs;
	private static List<String[]> infosEmprunts;
	private static Connection connection;
	private static EmpruntView view;

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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static boolean connection(String propertiesName) throws ClassNotFoundException, SQLException, IOException {

		Connection co = null;
		InputStream in = DaoTest.class.getResourceAsStream("..\\" + propertiesName + ".properties");

		Properties p = new Properties();

		
			p.load(in);
			co = new ConnectionFactory().getConnectionSansAutoCommit(p.getProperty("driver"), p.getProperty("url"),
					p.getProperty("user"), p.getProperty("pwd"));
	

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
	// public static boolean entreeExemplaire(int idExemplaire) throws SQLException
	// {
	//
	// exemplaire = new ExemplaireDao(connection).findByKey(idExemplaire);
	//
	// return true;
	// }

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

		empruntEnCoursDBs = new EmpruntEnCoursDao(connection).findByUtilisateur(utilisateur);

		return true;
	}

	public static void createExemplaires() throws SQLException {

		exemplaires = new ArrayList<>();
		
		for (EmpruntEnCoursDB e : empruntEnCoursDBs) {
			exemplaires.add(new ExemplaireDao(connection).findByKey(e.getIdExemplaire()));
			
		}

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
	public static String showUtilisateur() throws SQLException, BiblioException {

		return utilisateur.infosUtilisateur();
	}
	
	/**
	 * @return
	 */
	public static String[] getUserInfo() {
		String[] userInfo = new String[5];
		userInfo[0] = Integer.toString(utilisateur.getIdUtilisateur());
		userInfo[1] = utilisateur.getNom();
		userInfo[2] = utilisateur.getPrenom();
		userInfo[3] = utilisateur.getPseudonyme();
		userInfo[4] = utilisateur.getCategorieUtilisateur().toString();
		return userInfo;
	}
	
	public static List<String[]> getEmpruntInfo() {
		
		String[] empruntInfo = new String[3];
		 for (EmpruntEnCoursDB empruntEnCoursDB : empruntEnCoursDBs ) {
		
			 empruntInfo[0] = Integer.toString(empruntEnCoursDBs.indexOf(empruntEnCoursDB));
			 empruntInfo[1] = Integer.toString(empruntEnCoursDB.getIdExemplaire());
			 empruntInfo[2] = empruntEnCoursDB.getDateEmprunt().toString();
			 infosEmprunts.add(empruntInfo);
		 }
		
		return infosEmprunts;
	}
	public static String showExemplaire() throws SQLException, BiblioException {

		String exemplaireInfos = "";

		for (Exemplaire e : exemplaires) {
			for (EmpruntEnCoursDB edb : empruntEnCoursDBs) {
				if (e.getIdExemplaire() == edb.getIdExemplaire())
					exemplaireInfos += e.infosExemplaire() + "\n" + edb.infoEmpruntEnCoursDB() + "\n";
			}
		}

		return exemplaireInfos;
	}

	public static Component getView() {

		if (view == null)
			return new ConsulterView();
		else
			return view;
	}
	
	public static void main(String[] args){
		
		try {
			connection("biblio");
		} catch (ClassNotFoundException | SQLException | IOException e1) {
		
			e1.printStackTrace();
		}
		try {
			entreeUtilisateur(17);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		System.out.println(Arrays.toString(getUserInfo()));
	}
}
