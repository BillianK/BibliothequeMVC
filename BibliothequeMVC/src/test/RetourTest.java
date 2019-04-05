package test;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.RetourCtl;
import model.BiblioException;

public class RetourTest {

	public static void main(String[] args) {
		
		RetourCtl.connection("biblio");
		
		try {
			RetourCtl.entreeExemplaire(
					Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez l'idExemplaire (id de 9 à 16)")));
			
			RetourCtl.chercherEmpruntEnCours();
			
			RetourCtl.retournerEmprunt();
			
		} catch (NumberFormatException | HeadlessException | SQLException | BiblioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
