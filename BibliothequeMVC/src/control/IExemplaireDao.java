package control;

import java.sql.SQLException;

import model.Exemplaire;

public interface IExemplaireDao {

	Exemplaire findByKey(int id) throws SQLException;

	boolean updateStatus(Exemplaire exemplaire) throws SQLException;

}