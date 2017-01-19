package DBClasses;

public class Instrument 
{
	private int Id;
	private String name;
	private String kategorie;
	private double preis;
    private boolean isUpdated;
    private boolean isNew;
    private boolean isDeleted;

	public Instrument(int id, String na, String kat, double pr, boolean isNew)
	{
		Id = id;
		name = na;
		kategorie = kat;
		preis = pr;
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

    public boolean isUpdated() {
        return isUpdated;
    }

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
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
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}
}
