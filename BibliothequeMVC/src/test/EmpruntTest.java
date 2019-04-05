/**
 * 
 */
package test;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.EmprunterCtl;
import model.BiblioException;

/**
 * @author admin
 *
 */
public class EmpruntTest {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws HeadlessException
	 * @throws NumberFormatException
	 * @throws BiblioException
	 */
	public static void main(String[] args)
			throws NumberFormatException, HeadlessException, SQLException, BiblioException {

		EmprunterCtl.connection("biblio");
		EmprunterCtl.conditionsEmprunt();

		EmprunterCtl.entreeUtilisateur(
				Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez l'idUtilisateur (id de 10 à 18)")));
		EmprunterCtl.addEmpruntEnCours();
		
		EmprunterCtl.entreeExemplaire(
				Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez l'idExemplaire (id de 9 à 16)")));
		
		EmprunterCtl.enregistrerEmprunt();

	}
}
