package test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import control.IExemplaireDao;
import control.IUtilisateurDao;
import dao.AdherentGeneralDao;
import dao.ConnectionFactory;
import dao.ExemplaireDao;
import dao.UtilisateurDao;
import model.Adherent;

public class DaoTest {

	public static void main(String[] args) {

		Connection co = null;

		InputStream in = DaoTest.class.getResourceAsStream("..\\biblio.properties");

		Properties p = new Properties();

		try {
			p.load(in);
			co = new ConnectionFactory().getConnectionSansAutoCommit(p.getProperty("driver"), p.getProperty("url"),
					p.getProperty("user"), p.getProperty("pwd"));
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

		AdherentGeneralDao.setConnection(co);
		JOptionPane.showMessageDialog(null, AdherentGeneralDao.findDureeMaxPret(), "Durée Max prêt en jours", 0);
		Adherent.setDureeMaxPret(AdherentGeneralDao.findDureeMaxPret());
		JOptionPane.showMessageDialog(null, AdherentGeneralDao.findNbMaxPret(), "Nombre Max prêts", 0);
		Adherent.setNbMaxPret(AdherentGeneralDao.findNbMaxPret());

		IUtilisateurDao utilisateurCo = new UtilisateurDao(co);
		IExemplaireDao exemplaireCo = new ExemplaireDao(co);

		try {
			JOptionPane.showMessageDialog(null,
					exemplaireCo.findByKey(Integer.parseInt(JOptionPane.showInputDialog(null,
							"Entrez le numéro de l'exemplaire id -> 10 status attendu DISPONIBLE ",
							"ExemplaireDaoTest"))));
			JOptionPane.showMessageDialog(null,
					exemplaireCo.findByKey(Integer.parseInt(JOptionPane.showInputDialog(null,
							"Entrez le numéro de l'exemplaire id -> 14 status attendu PRETE ", "ExemplaireDaoTest"))));
			JOptionPane.showMessageDialog(null,
					utilisateurCo.findByKey(Integer.parseInt(JOptionPane.showInputDialog(null,
							"Entrez le numéro de l'exemplaire id -> 10 retour attendu adhérent",
							"ExemplaireDaoTest"))));
			JOptionPane.showMessageDialog(null,
					utilisateurCo.findByKey(Integer.parseInt(JOptionPane.showInputDialog(null,
							"Entrez le numéro de l'exemplaire id -> 12 retour attendu employé ",
							"ExemplaireDaoTest"))));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
