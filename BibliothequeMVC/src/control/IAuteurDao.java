package control;

import java.sql.SQLException;
import java.util.List;

import model.Auteur;

public interface IAuteurDao {

	Auteur findByKey(int id) throws SQLException;

	List<Auteur> findAll() throws SQLException;

	List<Auteur> findByLivre(String isbn) throws SQLException;

}