package model;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Adherent extends Utilisateur {

	private String telephone;
	private static int nbMaxPret;
	private static int dureeMaxPret;
	 
	public Adherent(int idUtilisateur, String nom, String prenom, String pwd, String pseudonyme, Date dateNaissance,
			String sexe, EnumCategorieUtilisateur categorieUtilisateur, List<EmpruntEnCoursDB> empruntEnCours,
			String telephone) {
		super(idUtilisateur, nom, prenom, pwd, pseudonyme, dateNaissance, sexe, categorieUtilisateur, empruntEnCours);
		this.telephone = telephone;

	}
	
	/* (non-Javadoc)
	 * @see biblio.domain.Utilisateur#isConditionPretAcceptees()
	 */
	public boolean isConditionPretAcceptees() throws BiblioException {
		if (getNbEmpruntsEnCours()>= Adherent.nbMaxPret) {
			throw new BiblioException("L'Adhérent a déjà trois emprunts");
		} else if (getNbRetards() != 0) {
			throw new BiblioException("L'Adhérent a au moins un prêt en retard");
		} else {
			return true;
			}
		}
		
	/**
	 * @return le nombre de retard(s)
	 */
	public int getNbRetards() {
		int retard = 0;
		for (EmpruntEnCoursDB emprunt : getEmpruntEnCours()) {
			if (emprunt.isEnRetard()) {
				retard++;
			}
		}
		return retard;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString()+"Type d'utilisateur : Adhérent\n Téléphone : " + getTelephone() /*+ "\n Emprunts en retard : " + getNbRetards() + "\n"*/;
	}

	public static int getNbMaxPret() {
		return nbMaxPret;
	}

	public static void setNbMaxPret(int nbMaxPret) {
		Adherent.nbMaxPret = nbMaxPret;
	}

	public static int getDureeMaxPret() {
		return dureeMaxPret;
	}

	public static void setDureeMaxPret(int dureeMaxPret) {
		Adherent.dureeMaxPret = dureeMaxPret;
	}
}
