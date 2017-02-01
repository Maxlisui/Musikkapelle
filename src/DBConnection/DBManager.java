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
	
	/**
	 * Gets the DBManager Instance.
	 * @return The Instance
	 * @throws SQLException In case there is an error during the DB connection
	 * @throws ClassNotFoundException In case the Driver could not be found
	 */
	public static synchronized DBManager getInstance() throws SQLException, ClassNotFoundException
	{
		if (instance == null)
		{
			instance = new DBManager();
		}
		return instance;
	}

	/**
	 * schlie�t die Verbindung zur Datenbank
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich 
	 */
	public void close() throws SQLException
	{
		conn.close();
	}

	/**
	 * holt Alle Musikannten
	 * @return gibt ArrayList aus Objekten der Klasse Musikant zur�ck
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
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
					rs.getInt("Hauptwohnsitz"), false));
		}

		rs.close();

		return musikanten;
	}
	
	/**
	 * Liefert alle Hauptwohnsitze
	 * @return gibt ArrayList aus Objekten der Klasse Hauptwohnsitz zur�ck
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	public ArrayList<Hauptwohnsitz> selectAllHauptwohnsitze() throws SQLException
	{
		ArrayList<Hauptwohnsitz> hauptwohnsitze = new ArrayList<>();

		String query = "SELECT * FROM Hauptwohnsitz";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next())
		{
			hauptwohnsitze.add(new Hauptwohnsitz(rs.getInt("Id"), rs.getString("Strasse"),
					rs.getString("Hausnummer"), rs.getInt("PLZ"), rs.getString("Ort"), rs.getString("Land"), false));
		}

		stmt.close();
		rs.close();

		return hauptwohnsitze;
	}

	/**
	 * holt den Hauptwohnsitz eines bestimmten Musikanten "m"
	 * @param m; Referenzvariable eines Objektes der Klasse Musikant
	 * @return gibt ein Objekt der Klasse Hauptwohnsitz zur�ck
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
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
					rs.getString("Hausnummer"), rs.getInt("PLZ"), rs.getString("Ort"), rs.getString("Land"), false);
		}

		stmt.close();
		rs.close();

		return hauptwohnsitz;
	}
	
	/**
	 * holt ALLE Instrumente
	 * @return gibt ArrayList aus Objekten der Klasse Instrument zur�ck
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	public ArrayList<Instrument> selectAllIntrumente() throws SQLException
	{
		ArrayList<Instrument> instrumente = new ArrayList<>();

		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM Instrument";
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next())
		{
			instrumente.add(new Instrument(rs.getInt("Id"), rs.getString("Name"), rs.getString("Kategorie"),
					rs.getFloat("Preis"), false));
		}

		stmt.close();
		rs.close();

		return instrumente;
	}

	/**
	 * Liefert alle Musikanten mit den dazu geh�rigen Instrumenten, Musikanten die keine Instrumente spielen,
	 * oder Instrumente die von niemandem gespielt werden, werden nicht angezeigt.
	 * @return gibt ArrayList aus Objekten der Klasse MusikantInstrument zur�ck
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	public ArrayList<MusikantInstrument> selectAllMusikantInstrument() throws SQLException
	{
		ArrayList<MusikantInstrument> musikantInstrumente = new ArrayList<>();

		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM MusikantInstrument";
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next())
		{
			musikantInstrumente.add(
					new MusikantInstrument(rs.getInt("MusikantId"), rs.getInt("InstrumentID"), false));
		}

		stmt.close();
		rs.close();

		return musikantInstrumente;
	}

	/**
	 * Gibt den Musikanten eines bestimmten Instrumentes zur�ck
	 * @param m; Referenzvariable eines Objektes der Klasse MusikantInstrument 
	 * @return gibt ein Objekt der Klasse Musikant zur�ck
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	private Musikant selectMusikantPerMusikantInstrument(MusikantInstrument m) throws SQLException
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
					rs.getInt("Hauptwohnsitz"), false);
			musikant.setHauptwohnsitz(selectHauptwohnsitzPerMusikant(musikant));
		}

		stmt.close();
		rs.close();

		return musikant;
	}

	/**
	 * gibt das Instrument eines bestimmten Musikanten zur�ck
	 * @param m; Referenzvariable eines Objektes der Klasse MusikantInstrument
	 * @return gibt ein Objekt der Klasse Instrument zur�ck
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	private Instrument selectInstrumentPerMusikantInstrument(MusikantInstrument m) throws SQLException
	{
		Instrument instrument = null;

		String query = "SELECT * FROM Instrument where InstrumentID = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, m.getInstrumentid());
		ResultSet rs = stmt.executeQuery();

		if (rs.next())
		{
			instrument = new Instrument(rs.getInt("InstrumentID"), rs.getString("Name"), rs.getString("Kategorie"),
					rs.getFloat("Preis"), false);

		}

		stmt.close();
		rs.close();

		return instrument;
	}
	/**
	 * Parameter eines bestimmten Musikantens �ndern 
	 * @param m; Referenzvariable eines Objektes der Klasse Musikant
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */

	private void updateMusikant(Musikant m) throws SQLException
	{
		String query = "UPDATE Musikant set Vorname=?, Nachname=?, Geburtsdatum=?, Email=?, Spezialfunktion=?,"
				+ "Hauptwohnsitz=? WHERE Id=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, m.getVorname());
		stmt.setString(2, m.getNachname());
		stmt.setDate(3, m.getGeburtsdatum());
		stmt.setString(4, m.getEmail());
		stmt.setString(5, m.getSpezialfunktion());
		stmt.setInt(6, m.getHauptwohnsitzId());
		stmt.setInt(7, m.getId());
		stmt.executeUpdate();

		stmt.close();
	}
	
	/**
	 * Parameter eines bestimmten Instruments �ndern
	 * @param i; Referenzvariable eines Objektes der Klasse Instrument
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich 
	 */

	private void updateInstrument(Instrument i) throws SQLException
	{
		String query = "UPDATE Instrument set Name=?, Kategorie=?, Preis=? WHERE Id=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, i.getName());
		stmt.setString(2, i.getKategorie());
		stmt.setDouble(3, i.getPreis());
		stmt.setInt(4, i.getId());
		stmt.executeUpdate();

		stmt.close();
	}
	
	/**
	 * Parameter eines bestimmten Hauptwohnsitzes �ndern
	 * @param h; Referenzvariable eines Objektes der Klasse Hauptwohnsitz
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */

	private void updateHauptwohnsitz(Hauptwohnsitz h) throws SQLException
	{
		String query = "UPDATE Hauptwohnsitz set Strasse=?, Hausnummer=?, PLZ=?, Ort=?, Land=?"
				+ "WHERE Id=?";
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

	/**
	 * Musikant mit bestimmten Parametern erstellen
	 * @param m; Referenzvariable eines Objektes der Klasse Musikant
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 * 
	 */
	private void insertMusikant(Musikant m) throws SQLException
	{
		String query = "INSERT INTO Musikant (Id, Vorname, Nachname, Geburtsdatum, Email, Spezialfunktion, Hauptwohnsitz)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, m.getId());
		stmt.setString(2, m.getVorname());
		stmt.setString(3, m.getNachname());
		stmt.setDate(4, m.getGeburtsdatum());
		stmt.setString(5, m.getEmail());
		stmt.setString(6, m.getSpezialfunktion());
		stmt.setInt(7, m.getHauptwohnsitzId());
		stmt.executeUpdate();

		stmt.close();
	}
	
	/**
	 * Instrument mit bestimmten Parametern erstellen
	 * @param i; Referenzvariable eines Objektes der Klasse Instrument
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */

	private void insertIntrument(Instrument i) throws SQLException
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

	/**
	 * Hauptwohnsitz erstellen
	 * @param h; Referenzvariable eines Objektes der Klasse Hauptwohnsitz
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich 
	 */
	private void insertHauptwohnsitz(Hauptwohnsitz h) throws SQLException
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

	/**
	 * einem bestimmten Musikanten ein bestimmtes Instrument zuweisen
	 * @param mi; Referenzvariable eines Objektes der Klasse MusikantInstrument
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich * 
	 */
	private void insertMusikantInstrument(MusikantInstrument mi) throws SQLException
	{
		String query = "INSERT INTO MusikantInstrument (MusikantID, InstrumentID VALUES(?, ?)";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, mi.getMusikantid());
		stmt.setInt(2, mi.getInstrumentid());
		stmt.executeUpdate();

		stmt.close();
	}
	
	/**
	 * einen Musikanten mit bestimmten Parametern l�schen
	 * @param m; Referenzvariable eines Objektes der Klasse Musikant
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	private void deleteMusikant(Musikant m) throws SQLException
	{
		String query = "DELETE FROM Musikant WHERE Id=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, m.getId());
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	/**
	 * einen bestimmten Hauptwohnsitz l�schen
	 * @param h; Referenzvariable eines Objektes der Klasse Hauptwohnsitz
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	private void deleteHauptwohnsitz(Hauptwohnsitz h) throws SQLException
	{
		String query = "DELETE FROM Hauptwohnsitz WHERE Id=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, h.getId());
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	/**
	 * ein bestimmtes Instrument l�schen 
	 * @param i; Referenzvariable eines Objektes der Klasse Instrument
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	private void deleteInstrument(Instrument i) throws SQLException
	{
		String query = "DELETE FROM Instrument WHERE Id=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, i.getId());
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	/**
	 * bestimmter Musikant h�rt auf bestimmtes Instrument zu spielen
	 * @param mi; Referenzvariable eines Objektes der Klasse MusikantInstrument
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glic
	 */
	private void deleteMusikantInstrument(MusikantInstrument mi) throws SQLException
	{
		String query = "DELETE FROM MusikantInstrument WHERE MusikantId=? AND InstrumentId=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, mi.getMusikantid());
		stmt.setInt(2, mi.getInstrumentid());
		stmt.executeUpdate();
		
		stmt.close();
	}

	/**
	 * Datenbank aktualisieren
	 * @param musikanten; Referenzvariable eines Objektes der Klasse Musikanten
	 * @param instrumente; Referenzvariable eines Objektes der Klasse Instrumente
	 * @param hauptwohnsitze; Referenzvariable eines Objektes der Klasse Hauptwohnsitze
	 * @param musikantInstrumente; Referenzvariable eines Objektes der Klasse MusikantInstrument
	 * @throws SQLException; Exception wenn kein Verbindungsaufbau m�glich
	 */
	public void refreshDB(ArrayList<Musikant> musikanten, ArrayList<Instrument> instrumente, ArrayList<Hauptwohnsitz> hauptwohnsitze, 
			ArrayList<MusikantInstrument> musikantInstrumente) throws SQLException
	{
		//MusikantInstrument l�schen
		for(MusikantInstrument mi : musikantInstrumente.stream().filter(x -> x.isDeleted()).collect(Collectors.toList()))
		{
			deleteMusikantInstrument(mi);
		}
		
		//Instrument l�schen
		for(Instrument i : instrumente.stream().filter(x -> x.isDeleted()).collect(Collectors.toList()))
		{
			deleteInstrument(i);
		}
		
		//Musikant l�schen
		for(Musikant m : musikanten.stream().filter(x -> x.isDeleted()).collect(Collectors.toList()))
		{
			deleteMusikant(m);
		}
		
		//Hauptwohnsitz l�schen
		for(Hauptwohnsitz h : hauptwohnsitze.stream().filter(x -> x.isDeleted()).collect(Collectors.toList()))
		{
			deleteHauptwohnsitz(h);
		}
		
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
	}

}