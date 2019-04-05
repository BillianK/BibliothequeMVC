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
public class Employe extends Utilisateur {

	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;

	public Employe(int idUtilisateur, String nom, String prenom, String pwd, String pseudonyme, Date dateNaissance,
			String sexe, EnumCategorieUtilisateur categorieUtilisateur, String codeMatricule,
			EnumCategorieEmploye categorieEmploye,List<EmpruntEnCoursDB>empruntEnCours) {
		
		super(idUtilisateur, nom, prenom, pwd, pseudonyme, dateNaissance, sexe, categorieUtilisateur,empruntEnCours);
		this.codeMatricule = codeMatricule;
		this.categorieEmploye = categorieEmploye;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() +"Type d'utilisateur : Employé \n Poste : " + this.getCategorieEmploye() + "\n Matricule : " + this.getCodeMatricule() + "\n";
	}
}
