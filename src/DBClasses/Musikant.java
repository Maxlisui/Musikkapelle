package DBClasses;

import java.sql.Date;

/**
 * Created by Maxlisui on 24/11/2016.
 */
public class Musikant
{
	private int id;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private String email;
	private String spezialfunktion;
	private int hauptwohnsitzId;
	private Hauptwohnsitz hauptwohnsitz;
	private boolean isUpdated;
	private boolean isNew;
	private boolean isDeleted;

	public Musikant(int id, String vorname, String nachname, Date geburtsdatum, String email, String spezialfunktion,
			int hauptwohnsitzId, boolean isNew)
	{
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.email = email;
		this.spezialfunktion = spezialfunktion;
		this.hauptwohnsitzId = hauptwohnsitzId;
		this.isNew = isNew;
		this.isUpdated = false;
		this.isDeleted = false;
	}
	
	public boolean isDeleted()
	{
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	public boolean isNew()
	{
		return isNew;
	}

	public void setNew(boolean isNew)
	{
		this.isNew = isNew;
	}

	public void setUpdated(boolean isUpdated)
	{
		this.isUpdated = isUpdated;
	}

	public boolean isUpdated()
	{
		return isUpdated;
	}

	public Hauptwohnsitz getHauptwohnsitz()
	{
		return hauptwohnsitz;
	}

	public void setHauptwohnsitz(Hauptwohnsitz hauptwohnsitz)
	{
		this.hauptwohnsitz = hauptwohnsitz;
		setHauptwohnsitzId(hauptwohnsitz.getId());
	}

	public int getHauptwohnsitzId()
	{
		return hauptwohnsitzId;
	}

	public void setHauptwohnsitzId(int hauptwohnsitzId)
	{
		this.hauptwohnsitzId = hauptwohnsitzId;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}

	public void setNachname(String nachname)
	{
		this.nachname = nachname; 
	}

	public void setGeburtsdatum(Date geburtsdatum)
	{
		this.geburtsdatum = geburtsdatum; 
	}

	public void setEmail(String email)
	{
		this.email = email; 
	}

	public void setSpezialfunktion(String spezialfunktion)
	{
		this.spezialfunktion = spezialfunktion;
	}

	public int getId()
	{

		return id;
	}

	public String getVorname()
	{
		return vorname;
	}

	public String getNachname()
	{
		return nachname;
	}

	public Date getGeburtsdatum()
	{
		return geburtsdatum;
	}

	public String getEmail()
	{
		return email;
	}

	public String getSpezialfunktion()
	{
		return spezialfunktion;
	}
}
