package control;

import java.sql.SQLException;
import java.util.List;

import model.Editeur;

public interface IEditeurDao {

	Editeur findByKey(int id) throws SQLException;

	List<Editeur> findAll() throws SQLException;

}