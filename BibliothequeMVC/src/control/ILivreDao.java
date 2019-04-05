package control;

import java.sql.SQLException;

import model.Livre;

public interface ILivreDao {

	Livre findByKey(String isbn) throws SQLException;

}