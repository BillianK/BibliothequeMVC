package control;

import java.sql.SQLException;
import java.util.List;

import model.Utilisateur;

public interface IUtilisateurDao {

	Utilisateur findByKey(int id) throws SQLException;

	List<Utilisateur> findAll() throws SQLException;

}