package DBClasses;

public class Instrument 
{
	private int instrumentenID;
	private String name;
	private String kategorie;
	private float preis;
    private boolean isUpdated;
	
	public Instrument(int id, String na, String kat, float pr)
	{
		instrumentenID = id;
		name = na;
		kategorie = kat;
		preis = pr;
		isUpdated = false;
	}

    public boolean isUpdated() {
        return isUpdated;
    }

	public int getInstrumentenID() {
		return instrumentenID;
	}

	public void setInstrumentenID(int instrumentenID) {
		this.instrumentenID = instrumentenID;
		isUpdated = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
        isUpdated = true;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
        isUpdated = true;
	}
	
	
	
	
}
