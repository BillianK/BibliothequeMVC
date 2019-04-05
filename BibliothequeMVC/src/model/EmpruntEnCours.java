/**
 * 
 */
package model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
/**
 * @author admin
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class EmpruntEnCours {
	private Exemplaire exemplaire;
	private Utilisateur emprunteur;
	private Date dateEmprunt;
	
	/**
	 * @param exemplaire
	 *            the exemplaire to set
	 * @throws BiblioException
	 */
	@Deprecated
	public void setExemplaire(Exemplaire exemplaire) throws BiblioException {
		if (emprunteur.isConditionPretAcceptees()) {
			if (exemplaire.getStatus() == EnumStatusExemplaire.DISPONIBLE) {
				this.exemplaire = exemplaire;
				exemplaire.setStatus(EnumStatusExemplaire.PRETE);
			} else
				throw new BiblioException("Exemplaire non disponible ou supprimé");
		}
	}
	
	/**
	 * @return si un emprunt est en retard
	 */
	public boolean isEnRetard() {

		Date aujourdhui = new Date(System.currentTimeMillis());

		return aujourdhui.after(new Date(dateEmprunt.getTime() + (24 * 60 * 60 * 1000) * Adherent.getDureeMaxPret()));

	}
	
	@Override
	public String toString() {
		return "EmpruntEnCours : \n Exemplaire : " + exemplaire + "\n Date de l'emprunt : " + dateEmprunt
				+ "\n emprunteur : " + getEmprunteur() + "\n";
	}
}
