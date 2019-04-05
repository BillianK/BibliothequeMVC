package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.IEditeurDao;
import model.Editeur;

public class EditeurDao implements IEditeurDao {

	private Connection connection;

	public EditeurDao(Connection connection) {

		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see dao.IEditeurDao#findByKey(int)
	 */
	@Override
	public Editeur findByKey(int id) throws SQLException {

		PreparedStatement pState = connection.prepareStatement("SELECT * FROM editeur WHERE idediteur = ?");

		pState.setInt(1, id);
		
		ResultSet result = pState.executeQuery();

		return new Editeur(result.getInt(1), result.getString(2), result.getString(3));
	}

	/* (non-Javadoc)
	 * @see dao.IEditeurDao#findAll()
	 */
	@Override
	public List<Editeur> findAll() throws SQLException {

		PreparedStatement pState = connection.prepareStatement("SELECT * FROM editeur");

		ResultSet result = pState.executeQuery();

		ArrayList<Editeur> Editeurs = new ArrayList<>();

		while (result.next()) {
			Editeurs.add(new Editeur(result.getInt(1), result.getString(2), result.getString(3)));
		}
		return Editeurs;
	}
}
