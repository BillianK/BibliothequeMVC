package control;

import java.sql.SQLException;
import java.util.List;

import model.EmpruntEnCoursDB;
import model.Exemplaire;
import model.Utilisateur;

public interface IEmpruntEnCoursDao {

	EmpruntEnCoursDB findByKey(int idExemplaire) throws SQLException;

	boolean insertEmpruntEnCours(Utilisateur utilisateur, Exemplaire exemplaire) throws SQLException;

	boolean deleteEmpruntEnCours(EmpruntEnCoursDB emprunt) throws SQLException;

	List<EmpruntEnCoursDB> findByUtilisateur(Utilisateur u) throws SQLException;

}