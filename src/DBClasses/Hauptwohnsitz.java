package DBClasses;

/**
 * Created by Maxlisui on 01/12/2016.
 */
public class Hauptwohnsitz
{
    private int id;
    private String strasse;
    private String hausnummer;
    private int PLZ;
    private String ort;
    private String land;
    private boolean isUpdated;
	private boolean isNew;
	private boolean isDeleted;

	public Hauptwohnsitz(int id, String strasse, String hausnummer, int PLZ, String ort, String land, boolean isNew) {
        this.id = id;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.PLZ = PLZ;
        this.ort = ort;
        this.land = land;
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
	
    public boolean isUpdated()
	{
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated)
	{
		this.isUpdated = isUpdated;
	}
	
	public boolean isNew()
	{
		return isNew;
	}

	public void setNew(boolean isNew)
	{
		this.isNew = isNew;
	}

    public int getId() {
        return id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getPLZ() {
        return PLZ;
    }

    public void setPLZ(int PLZ) {
        this.PLZ = PLZ;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

	public void setId(int id)
	{
		this.id = id;
	}
}
