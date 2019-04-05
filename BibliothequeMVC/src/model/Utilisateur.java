package model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Utilisateur {

	private int idUtilisateur;
	private String nom;
	private String prenom;
	private String pwd;
	private String pseudonyme;
	private Date dateNaissance;
	private String sexe;
	private EnumCategorieUtilisateur categorieUtilisateur;
	private List<EmpruntEnCoursDB> empruntEnCours;

	public void addEmpruntEncours(EmpruntEnCoursDB emprunt) {
		empruntEnCours.add(emprunt);
	}

	public int getNbEmpruntsEnCours() {
		return empruntEnCours.size();
	}

	public boolean isConditionPretAcceptees() throws BiblioException {
		return true;
	}

	public String infosUtilisateur() {

		return "ID utilisateur : " + getIdUtilisateur()
		+ "\n pseudonyme : " + getPseudonyme()
		+ "\n NOM: " + getNom()
		+ "\n\tPRENOM: " + getPrenom()
		+ "\nDate de naissance: " + getDateNaissance()
		+ "\nSexe: " + getSexe()
		+ "\nCatégorie: " + getCategorieUtilisateur();
	}

}