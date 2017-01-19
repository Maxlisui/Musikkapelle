package Utils;

import java.util.ArrayList;

import DBClasses.Hauptwohnsitz;
import DBClasses.Instrument;
import DBClasses.Musikant;
import DBClasses.MusikantInstrument;

public class GloballyData
{
	private static GloballyData instance;
	private ArrayList<Musikant> musikanten;
	private ArrayList<Hauptwohnsitz> hauptwohnsitze;
	private ArrayList<Instrument> instrumente;
	private ArrayList<MusikantInstrument> musikantInstrumente;
	private Hauptwohnsitz lastSelectedHauptwohnsitz;
	private Instrument lastSelectedInstrument;
	private int musikantIdForSpecificInstrument;
	private ArrayList<Instrument> alreadyUsedInstrumente;
	private int musikantIdForEditing;
	private int wohnsitzIdForEditing;
	private int instrumentIdForEditing;

	private GloballyData() 
	{
		musikanten = new ArrayList<>();
		hauptwohnsitze = new ArrayList<>();
		instrumente = new ArrayList<>();
		musikantInstrumente = new ArrayList<>();
	}
	
	public static synchronized GloballyData getInstance()
	{
		if(instance == null)
		{
			instance = new GloballyData();
		}
		return instance;
	}	

	public int getInstrumentIdForEditing() {
		return instrumentIdForEditing;
	}

	public void setInstrumentIdForEditing(int instrumentIdForEditing) {
		this.instrumentIdForEditing = instrumentIdForEditing;
	}

	public int getWohnsitzIdForEditing() {
		return wohnsitzIdForEditing;
	}

	public void setWohnsitzIdForEditing(int wohnsitzIdForEditing) {
		this.wohnsitzIdForEditing = wohnsitzIdForEditing;
	}
	
	public int getMusikantIdForEditing()
	{
		return musikantIdForEditing;
	}

	public void setMusikantIdForEditing(int musikantIdForEditing)
	{
		this.musikantIdForEditing = musikantIdForEditing;
	}
	
	public ArrayList<Instrument> getAlreadyUsedInstrumente()
	{
		return alreadyUsedInstrumente;
	}

	public void setAlreadyUsedInstrumente(ArrayList<Instrument> alreadyUsedInstrumente)
	{
		this.alreadyUsedInstrumente = alreadyUsedInstrumente;
	}
	
	public Instrument getLastSelectedInstrument()
	{
		return lastSelectedInstrument;
	}

	public void setLastSelectedInstrument(Instrument lastSelectedInstrument)
	{
		this.lastSelectedInstrument = lastSelectedInstrument;
	}
	
	public int getMusikantIdForSpecificInstrument()
	{
		return musikantIdForSpecificInstrument;
	}

	public void setMusikantIdForSpecificInstrument(int MusikanSpecificid)
	{
		this.musikantIdForSpecificInstrument = MusikanSpecificid;
	}
	
	public Hauptwohnsitz getLastSelectedHauptwohnsitz()
	{
		return lastSelectedHauptwohnsitz;
	}

	public void setLastSelectedHauptwohnsitz(Hauptwohnsitz lastSelectedHauptwohnsitz)
	{
		this.lastSelectedHauptwohnsitz = lastSelectedHauptwohnsitz;
	}
	
	public ArrayList<Musikant> getMusikanten()
	{
		return musikanten;
	}

	public void setMusikanten(ArrayList<Musikant> musikanten)
	{
		this.musikanten = musikanten;
	}

	public ArrayList<Hauptwohnsitz> getHauptwohnsitze()
	{
		return hauptwohnsitze;
	}

	public void setHauptwohnsitze(ArrayList<Hauptwohnsitz> hauptwohnsitze)
	{
		this.hauptwohnsitze = hauptwohnsitze;
	}

	public ArrayList<Instrument> getInstrumente()
	{
		return instrumente;
	}

	public void setInstrumente(ArrayList<Instrument> instrumente)
	{
		this.instrumente = instrumente;
	}

	public ArrayList<MusikantInstrument> getMusikantInstrumente()
	{
		return musikantInstrumente;
	}

	public void setMusikantInstrumente(ArrayList<MusikantInstrument> musikantInstrumente)
	{
		this.musikantInstrumente = musikantInstrumente;
	}
}
