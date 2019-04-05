package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.IThemeDao;
import model.Theme;

public class ThemeDao implements IThemeDao {

	private Connection connection;

	public ThemeDao(Connection connection) {

		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see dao.IThemeDao#findByKey(java.lang.String)
	 */
	@Override
	public Theme findByKey(String theme) throws SQLException {

		PreparedStatement pStateA = connection.prepareStatement("SELECT * FROM theme WHERE codetheme = ?");

		pStateA.setString(1, theme);

		ResultSet resultA = pStateA.executeQuery();

		if (resultA.getString(3).equalsIgnoreCase("null"))

			return new Theme(resultA.getString(1), resultA.getString(2), null);

		else {
			PreparedStatement pStateB = connection.prepareStatement("SELECT * FROM theme WHERE codetheme = ?");

			ResultSet resultB;

			resultB = pStateB.executeQuery();

			pStateB.setString(1, resultB.getString(3));

			return new Theme(resultA.getString(1), resultA.getString(2),
					new Theme(resultB.getString(1), resultB.getString(2), null));
		}
	}

	/* (non-Javadoc)
	 * @see dao.IThemeDao#findAll()
	 */
	@Override
	public List<Theme> findAll() throws SQLException {

		PreparedStatement pStateA = connection.prepareStatement("SELECT * FROM theme");

		ResultSet resultA = pStateA.executeQuery();

		ArrayList<Theme> themes = new ArrayList<>();

		while (resultA.next()) {

			if (resultA.getString(3).equalsIgnoreCase("null"))

				themes.add(new Theme(resultA.getString(1), resultA.getString(2), null));

			else {
				PreparedStatement pStateB = connection.prepareStatement("SELECT * FROM theme WHERE codetheme = ?");

				ResultSet resultB;

				resultB = pStateB.executeQuery();

				pStateB.setString(1, resultB.getString(3));

				themes.add(new Theme(resultA.getString(1), resultA.getString(2),
						new Theme(resultB.getString(1), resultB.getString(2), null)));
			}
		}
		return themes;
	}
}
