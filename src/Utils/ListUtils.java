package Utils;

import java.util.*;

import DBClasses.*;

public class ListUtils
{
	private static ListUtils instance = null;
	
	private ListUtils()
	{
		
	}
	
	public static synchronized ListUtils getInstance()
	{
		if(instance == null)
		{
			instance = new ListUtils();
		}
		return instance;
	}
	
	public int getLastMusikantId(ArrayList<Musikant> musikanten)
	{
		if(!musikanten.isEmpty() && musikanten != null){
			return musikanten.stream().max(Comparator.comparing(Musikant::getId)).get().getId();
		}
		else return 0;
	}
	
	public int getLastInstrumentId(ArrayList<Instrument> instrumente)
	{
		if(!instrumente.isEmpty() && instrumente != null){
			return instrumente.stream().max(Comparator.comparing(Instrument::getId)).get().getId();
		}
		else return 0;
	}
	
	public int getLastHauptwohnsitzId(ArrayList<Hauptwohnsitz> hauptwohnsitze)
	{
		if(!hauptwohnsitze.isEmpty() && hauptwohnsitze != null){
			return hauptwohnsitze.stream().max(Comparator.comparing(Hauptwohnsitz::getId)).get().getId();
		}
		else return 0;
	}
}
