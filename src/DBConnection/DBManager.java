package DBConnection;
import DBClasses.Hauptwohnsitz;
import DBClasses.Instrument;
import DBClasses.Musikant;
import DBClasses.MusikantInstrument;

import java.sql.*;
import java.util.ArrayList;

public class DBManager 
{
	private Connection conn;
	private static DBManager instance;

	private DBManager() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");

		if(conn == null) {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Musiverein", "root", "root");
		}
	}

	public static synchronized DBManager getInstance() throws SQLException, ClassNotFoundException{
		if(instance == null){
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
            musikanten.add(new Musikant(rs.getInt("MusikantID"), rs.getString("Vorname"),
                    rs.getString("Nachname"), rs.getDate("Geburtsdatum"),
                    rs.getString("Email"), rs.getString("Spezialfunktion"),
                    rs.getInt("Hauptwohnsitz")));
        }

        stmt.close();
        rs.close();
        this.close();

        return musikanten;
	}

	public Hauptwohnsitz selectHauptwohnsitzPerMusikant(Musikant m) throws SQLException
    {
        Hauptwohnsitz hauptwohnsitz = null;

        String query = "SELECT * FROM hauptwohnsitz where hauptwohnsitzId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, m.getHauptwohnsitzId());
        ResultSet rs = stmt.executeQuery();

        if(rs.next()){
            hauptwohnsitz = new Hauptwohnsitz(rs.getInt("HauptwohnsitzID"),
                    rs.getString("Strasse"), rs.getString("Hausnummer"),
                    rs.getInt("PLZ"), rs.getString("Ort"),
                    rs.getString("Land"));
        }

        this.close();
        stmt.close();
        rs.close();

        return hauptwohnsitz;
    }

    public ArrayList<Instrument> selectIntrumente() throws SQLException
    {
        ArrayList<Instrument> instrumente = new ArrayList<>();

        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM Instrument";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            instrumente.add(new Instrument(rs.getInt("InstrumentID"), rs.getString("Name"),
                    rs.getString("Kategorie"), rs.getFloat("Preis")));
        }

        this.close();
        stmt.close();
        rs.close();

        return instrumente;
    }

    public ArrayList<MusikantInstrument> selectInstrumentMusikant() throws SQLException
    {
        ArrayList<MusikantInstrument> musikantInstrumente = new ArrayList<>();

        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM MusikantInstrument";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next())
        {
            musikantInstrumente.add(new MusikantInstrument(rs.getInt("MusikantId"),
                    rs.getInt("InstrumentID"), rs.getString("Stimme")));
        }

        this.close();
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

        if(rs.next())
        {
            musikant = new Musikant(rs.getInt("MusikantID"), rs.getString("Vorname"),
                    rs.getString("Nachname"), rs.getDate("Geburtsdatum"),
                    rs.getString("Email"), rs.getString("Spezialfunktion"),
                    rs.getInt("Hauptwohnsitz"));
            musikant.setHauptwohnsitz(selectHauptwohnsitzPerMusikant(musikant));
        }

        close();
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

        if(rs.next())
        {
            instrument = new Instrument(rs.getInt("InstrumentID"), rs.getString("Name"), rs.getString("Kategorie"), rs.getFloat("Preis"));

        }

        close();
        stmt.close();
        rs.close();

        return instrument;
    }

    public void updateMusikant(Musikant m) throws SQLException
    {
        if(m.isUpdated()) {
            String query = "UPDATE Musikant set Vorname=?, Nachname=?, Geburtsdatum=?, Email=?, Spezialfunktion=?," +
                    "Hauptwohnsitz=? WHERE MusikantID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, m.getVorname());
            stmt.setString(2, m.getNachname());
            stmt.setDate(3, m.getGeburtsdatum());
            stmt.setString(4, m.getEmail());
            stmt.setString(5, m.getSpezailfunktion());
            stmt.setInt(6, m.getHauptwohnsitzId());
            stmt.setInt(7, m.getId());
            stmt.executeUpdate();

            close();
            stmt.close();
        }
    }

    public void updateInstrument(Instrument i) throws SQLException
    {
        if(i.isUpdated())
        {
            String query = "UPDATE Instrument set Name=?, Kategorie=?, Preis=? WHERE InstrumentID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, i.getName());
            stmt.setString(2, i.getKategorie());
            stmt.setFloat(3, i.getPreis());
            stmt.setInt(4, i.getInstrumentenID());
            stmt.executeUpdate();

            close();
            stmt.close();
        }
    }

    public void updateHauptwohnsitz(Hauptwohnsitz h) throws SQLException
    {
        if(h.isUpdated())
        {
            String query = "UPDATE Hauptwohnsitz set Strasse=?, Hausnummer=?, PLZ=?, Ort=?, Land=?"+
                    "WHERE HauptwohnsitzID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, h.getStrasse());
            stmt.setString(2, h.getHausnummer());
            stmt.setInt(3, h.getPLZ());
            stmt.setString(4, h.getOrt());
            stmt.setString(5, h.getLand());
            stmt.setInt(6, h.getId());
            stmt.executeUpdate();

            close();
            stmt.close();
        }
    }

    public void updateMusikantIntrument(MusikantInstrument mi) throws SQLException
    {
        String query = "UPDATE MusikantInstrument set Stimme=? WHERE MusikantID=? AND InstrumentID=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, mi.getStimme());
        stmt.setInt(2, mi.getMusikantid());
        stmt.setInt(3, mi.getInstrumentid());
        stmt.executeUpdate();

        close();
        stmt.close();
    }

}