package control;

import java.sql.SQLException;
import java.util.List;

import model.Theme;

public interface IThemeDao {

	Theme findByKey(String theme) throws SQLException;

	List<Theme> findAll() throws SQLException;

}