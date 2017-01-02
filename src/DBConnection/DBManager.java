package DBConnection;

import DBClasses.*;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DBManager
{
	private Connection conn;
	private static DBManager instance;

	private DBManager() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");

		if (conn == null)
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Musikkapelle", "root", "root");
		}
	}

	public static synchronized DBManager getInstance() throws SQLException, ClassNotFoundException
	{
		if (instance == null)
		{
			instance = new DBManager();
		}
		return instance;
	}

	public Connection getConnection()
	{
		return conn;
	}

	public void close() throws SQLException
	{
		conn.close();
	}

	public ArrayList<Musikant> selectAllMusikanten() throws SQLException
	{
		ArrayList<Musikant> musikanten = new ArrayList<>();

		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM Musikant";
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next())
		{
			musikanten.add(new Musikant(rs.getInt("Id"), rs.getString("Vorname"), rs.getString("Nachname"),
					rs.getDate("Geburtsdatum"), rs.getString("Email"), rs.getString("Spezialfunktion"),
					rs.getInt("Hauptwohnsitz")));
		}

		rs.close();

		return musikanten;
	}
	
	public ArrayList<Hauptwohnsitz> selectAllHauptwohnsitze() throws SQLException
	{
		ArrayList<Hauptwohnsitz> hauptwohnsitze = new ArrayList<>();

		String query = "SELECT * FROM Hauptwohnsitz";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next())
		{
			hauptwohnsitze.add(new Hauptwohnsitz(rs.getInt("Id"), rs.getString("Strasse"),
					rs.getString("Hausnummer"), rs.getInt("PLZ"), rs.getString("Ort"), rs.getString("Land")));
		}

		stmt.close();
		rs.close();

		return hauptwohnsitze;
	}

	public Hauptwohnsitz selectHauptwohnsitzPerMusikant(Musikant m) throws SQLException
	{
		Hauptwohnsitz hauptwohnsitz = null;

		String query = "SELECT * FROM hauptwohnsitz where hauptwohnsitzId = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, m.getHauptwohnsitzId());
		ResultSet rs = stmt.executeQuery();

		if (rs.next())
		{
			hauptwohnsitz = new Hauptwohnsitz(rs.getInt("HauptwohnsitzID"), rs.getString("Strasse"),
					rs.getString("Hausnummer"), rs.getInt("PLZ"), rs.getString("Ort"), rs.getString("Land"));
		}

		stmt.close();
		rs.close();

		return hauptwohnsitz;
	}

	public ArrayList<Instrument> selectAllIntrumente() throws SQLException
	{
		ArrayList<Instrument> instrumente = new ArrayList<>();

		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM Instrument";
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next())
		{
			instrumente.add(new Instrument(rs.getInt("Id"), rs.getString("Name"), rs.getString("Kategorie"),
					rs.getFloat("Preis")));
		}

		stmt.close();
		rs.close();

		return instrumente;
	}

	public ArrayList<MusikantInstrument> selectAllInstrumentMusikant() throws SQLException
	{
		ArrayList<MusikantInstrument> musikantInstrumente = new ArrayList<>();

		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM MusikantInstrument";
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next())
		{
			musikantInstrumente.add(
					new MusikantInstrument(rs.getInt("MusikantId"), rs.getInt("InstrumentID"), rs.getString("Stimme")));
		}

		stmt.close();
		rs.close();

		return musikantInstrumente;
	}

	public Musikant selectMusikantPerMusikantInstrument(MusikantInstrument m) throws SQLException
	{
		Musikant musikant = null;

		String query = "SELECT * FROM Musikant where MusikantID = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, m.getMusikantid());
		ResultSet rs = stmt.executeQuery();

		if (rs.next())
		{
			musikant = new Musikant(rs.getInt("MusikantID"), rs.getString("Vorname"), rs.getString("Nachname"),
					rs.getDate("Geburtsdatum"), rs.getString("Email"), rs.getString("Spezialfunktion"),
					rs.getInt("Hauptwohnsitz"));
			musikant.setHauptwohnsitz(selectHauptwohnsitzPerMusikant(musikant));
		}

		stmt.close();
		rs.close();

		return musikant;
	}

	public Instrument selectInstrumentPerMusikantInstrument(MusikantInstrument m) throws SQLException
	{
		Instrument instrument = null;

		String query = "SELECT * FROM Instrument where InstrumentID = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, m.getInstrumentid());
		ResultSet rs = stmt.executeQuery();

		if (rs.next())
		{
			instrument = new Instrument(rs.getInt("InstrumentID"), rs.getString("Name"), rs.getString("Kategorie"),
					rs.getFloat("Preis"));

		}

		stmt.close();
		rs.close();

		return instrument;
	}

	public void updateMusikant(Musikant m) throws SQLException
	{
		if (m.isUpdated())
		{
			String query = "UPDATE Musikant set Vorname=?, Nachname=?, Geburtsdatum=?, Email=?, Spezialfunktion=?,"
					+ "Hauptwohnsitz=? WHERE MusikantID=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, m.getVorname());
			stmt.setString(2, m.getNachname());
			stmt.setDate(3, m.getGeburtsdatum());
			stmt.setString(4, m.getEmail());
			stmt.setString(5, m.getSpezailfunktion());
			stmt.setInt(6, m.getHauptwohnsitzId());
			stmt.setInt(7, m.getId());
			stmt.executeUpdate();

			stmt.close();
		}
	}

	public void updateInstrument(Instrument i) throws SQLException
	{
		String query = "UPDATE Instrument set Name=?, Kategorie=?, Preis=? WHERE InstrumentID=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, i.getName());
		stmt.setString(2, i.getKategorie());
		stmt.setDouble(3, i.getPreis());
		stmt.setInt(4, i.getId());
		stmt.executeUpdate();

		stmt.close();
	}

	public void updateHauptwohnsitz(Hauptwohnsitz h) throws SQLException
	{
		String query = "UPDATE Hauptwohnsitz set Strasse=?, Hausnummer=?, PLZ=?, Ort=?, Land=?"
				+ "WHERE HauptwohnsitzID=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, h.getStrasse());
		stmt.setString(2, h.getHausnummer());
		stmt.setInt(3, h.getPLZ());
		stmt.setString(4, h.getOrt());
		stmt.setString(5, h.getLand());
		stmt.setInt(6, h.getId());
		stmt.executeUpdate();

		stmt.close();
	}

	public void updateMusikantIntrument(MusikantInstrument mi) throws SQLException
	{
		if (mi.isUpdated())
		{
			String query = "UPDATE MusikantInstrument set Stimme=? WHERE MusikantID=? AND InstrumentID=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, mi.getStimme());
			stmt.setInt(2, mi.getMusikantid());
			stmt.setInt(3, mi.getInstrumentid());
			stmt.executeUpdate();

			stmt.close();
		}
	}

	public void insertMusikant(Musikant m) throws SQLException
	{
		String query = "INSERT INTO Musikant (Id, Vorname, Nachname, Geburtsdatum, Email, Spezialfunktion, Hauptwohnsitz)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, m.getId());
		stmt.setString(2, m.getVorname());
		stmt.setString(3, m.getNachname());
		stmt.setDate(4, m.getGeburtsdatum());
		stmt.setString(5, m.getEmail());
		stmt.setString(6, m.getSpezailfunktion());
		stmt.setInt(7, m.getHauptwohnsitzId());
		stmt.executeUpdate();

		stmt.close();
	}

	public void insertIntrument(Instrument i) throws SQLException
	{
		String query = "INSERT INTO Instrument (Id, Name, Kategorie, Preis) VALUES(?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, i.getId());
		stmt.setString(2, i.getName());
		stmt.setString(3, i.getKategorie());
		stmt.setDouble(4, i.getPreis());
		stmt.executeUpdate();

		stmt.close();
	}

	public void insertHauptwohnsitz(Hauptwohnsitz h) throws SQLException
	{
		String query = "INSERT INTO Hauptwohnsitz (Id, Strasse, Hausnummer, PLZ, Ort, Land) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, h.getId());
		stmt.setString(2, h.getStrasse());
		stmt.setString(3, h.getHausnummer());
		stmt.setInt(4, h.getPLZ());
		stmt.setString(5, h.getOrt());
		stmt.setString(6, h.getLand());
		stmt.executeUpdate();

		stmt.close();
	}

	public void insertMusikantInstrument(MusikantInstrument mi) throws SQLException
	{
		String query = "INSERT INTO MusikantInstrument (MusikantID, InstrumentID, Stimme) VALUES(?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, mi.getMusikantid());
		stmt.setInt(2, mi.getInstrumentid());
		stmt.setString(3, mi.getStimme());
		stmt.executeUpdate();

		stmt.close();
	}

	public void refreshDB(ArrayList<Musikant> musikanten, ArrayList<Instrument> instrumente, ArrayList<Hauptwohnsitz> hauptwohnsitze, 
			ArrayList<MusikantInstrument> musikantInstrumente) throws SQLException
	{
		//Neue Hauptwohnsitze
		for(Hauptwohnsitz h : hauptwohnsitze.stream().filter(x -> x.isNew()).collect(Collectors.toList()))
		{
			insertHauptwohnsitz(h);
		}
		
		//Updated Hauptwohnsitz
		for(Hauptwohnsitz h : hauptwohnsitze.stream().filter(x -> x.isUpdated()).collect(Collectors.toList()))
		{
			updateHauptwohnsitz(h);
		}
		
		//Neue Musikanten
		for (Musikant m : musikanten.stream().filter(x -> x.isNew()).collect(Collectors.toList()))
		{
			insertMusikant(m);
		}

		// Updated Musikanten
		for (Musikant m : musikanten.stream().filter(x -> x.isUpdated()).collect(Collectors.toList()))
		{
			updateMusikant(m);
		}
		
		//Neue Instrumente
		for(Instrument i : instrumente.stream().filter(x -> x.isNew()).collect(Collectors.toList()))
		{
			insertIntrument(i);
		}
		
		//Updated Instrumente
		for(Instrument i : instrumente.stream().filter(x -> x.isUpdated()).collect(Collectors.toList()))
		{
			updateInstrument(i);
		}
		
		//Neue MusikantInstrumente
		for(MusikantInstrument mi : musikantInstrumente.stream().filter(x -> x.isNew()).collect(Collectors.toList()))
		{
			insertMusikantInstrument(mi);
		}
		
		//Updated MusikantInstrumente
		for(MusikantInstrument mi : musikantInstrumente.stream().filter(x -> x.isUpdated()).collect(Collectors.toList()))
		{
			insertMusikantInstrument(mi);
		}
	}

}