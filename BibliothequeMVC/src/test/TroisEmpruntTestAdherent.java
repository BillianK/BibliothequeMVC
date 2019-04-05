package test;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.EmprunterCtl;
import model.BiblioException;
import model.Exemplaire;
import model.Utilisateur;

public class TroisEmpruntTestAdherent {

	public static void main(String[] args)
			throws NumberFormatException, HeadlessException, SQLException, BiblioException {

		EmprunterCtl empCtl = new EmprunterCtl();

		Connection cnx = empCtl.connection("biblio");
		empCtl.conditionsEmprunt(cnx);

		Utilisateur utilisateur = empCtl.entreeUtilisateur(
				Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez l'idUtilisateur (id 17)")), cnx);
		
		for (int i = 0; i < 4; i++) {
			utilisateur.setEmpruntEnCours(empCtl.getEmpruntEnCours(utilisateur, cnx));
			Exemplaire exemplaire = empCtl.entreeExemplaire(
					Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez l'idExemplaire (id de 9,12,16,13)")),
					cnx);
			empCtl.enregistrerEmprunt(utilisateur, exemplaire, cnx);
			empCtl.changerStatus(exemplaire, cnx);
		}
		cnx.close();

	}
}
