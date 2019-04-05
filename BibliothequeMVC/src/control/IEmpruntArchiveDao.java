package control;

import java.sql.SQLException;

import model.EmpruntArchive;
import model.EmpruntEnCoursDB;

public interface IEmpruntArchiveDao {

	EmpruntArchive findByKey(int id) throws SQLException;

	boolean insertEmpruntArchive(EmpruntEnCoursDB emprunt) throws SQLException;

}