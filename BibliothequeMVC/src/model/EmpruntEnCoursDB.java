/**
 * 
 */
package model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
/**
 * @author admin
 *
 */
public class EmpruntEnCoursDB extends EmpruntEnCours {
	private int idExemplaire;
	private int idUtilisateur;

	/**
	 * @param idExemplaire
	 * @param idUtilisateur
	 */
	public EmpruntEnCoursDB(int idExemplaire, int idUtilisateur, Date dateEmprunt) {
		super(null, null, dateEmprunt);
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}

	public String infoEmpruntEnCoursDB() {
		
		return "Date emprunt: " + getDateEmprunt();
		
	}
}
