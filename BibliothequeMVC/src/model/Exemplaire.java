package model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exemplaire {

	private int idExemplaire;
	private Date dateAchat;
	private EnumStatusExemplaire status;
	private Livre livre;

	/**
	 * @return la disponibilit� de l exemplaire
	 * @throws BiblioException
	 */
	public boolean isDisponible() throws BiblioException {
		if (this.getStatus() == EnumStatusExemplaire.DISPONIBLE) {
			return true;
		} else
			throw new BiblioException("Exemplaire d�j� emprunt�");
	}

	public String infosExemplaire() {

		return "IDExemplaire: " + getIdExemplaire();
	}

	@Override
	public String toString() {
		return "Exemplaire : \n id=" + idExemplaire + "\n Achet� le : " + dateAchat + "\n Statut : " + status + "\n";
	}
}
