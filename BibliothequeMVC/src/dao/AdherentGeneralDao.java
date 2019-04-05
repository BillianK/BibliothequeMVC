package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.IAdherentGeneralDao;

public class AdherentGeneralDao implements IAdherentGeneralDao {

	private static Connection connection;

	public static void setConnection(Connection connection) {

		AdherentGeneralDao.connection = connection;
	}
	
	public static int findNbMaxPret() {

		PreparedStatement pState;
		ResultSet result;
		int pret = 0;
		try {
			pState = connection.prepareStatement("SELECT nbmaxprets FROM adherentgeneral");
			result = pState.executeQuery();
			result.next();
			pret = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pret;
	}

	public static int findDureeMaxPret() {

		PreparedStatement pState;
		ResultSet result;
		int duree = 0;
		try {
			pState = connection.prepareStatement("SELECT dureemaxprets FROM adherentgeneral");
			result = pState.executeQuery();
			result.next();
			duree = result.getInt(1);
		} catch (SQLException e) {
			e.getMessage();
		}
		return duree;
	}
}
