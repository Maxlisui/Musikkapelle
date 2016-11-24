package DBClasses;
import java.util.ArrayList;

public class Instrument 
{
	private ArrayList<Instrument> instrumente = new ArrayList<Instrument>();
	
	private int instrumentenID;
	private String name;
	private String kathegorie;
	private float preis;
	
	public Instrument(int id, String na, String kath, float pr)
	{
		instrumentenID = id;
		name = na;
		kathegorie = kath;
		preis = pr;
	}

	public ArrayList<Instrument> getInstrumente() {
		return instrumente;
	}

	public void setInstrumente(ArrayList<Instrument> instrumente) {
		this.instrumente = instrumente;
	}

	public int getInstrumentenID() {
		return instrumentenID;
	}

	public void setInstrumentenID(int instrumentenID) {
		this.instrumentenID = instrumentenID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKathegorie() {
		return kathegorie;
	}

	public void setKathegorie(String kathegorie) {
		this.kathegorie = kathegorie;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
	}
	
	
	
	
}
