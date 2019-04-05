/**
 * 
 */
package model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * @author admin
 *
 */
public class EmpruntArchive {
	private int idEmpruntArchive;
	private Date dateEmprunt;
	private Date dateRetour;
	private Exemplaire exemplaire;
	private Utilisateur emprunteur;

	@Override
	public String toString() {
		return "EmpruntArchive dateRestitutionEff=" + dateRetour + ", dateEmprunt=" + dateEmprunt
				+ ", emprunteur=" + emprunteur + ", exemplaire=" + exemplaire;
	}

}
