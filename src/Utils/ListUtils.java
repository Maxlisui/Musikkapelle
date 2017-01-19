package Utils;

import java.util.*;
import java.util.stream.Collectors;
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
	
	/**
	 * Gets the last id from a Musikant in the given List
	 * @param musikanten List with Musikanten
	 * @return The last Id
	 */
	public int getLastMusikantId(ArrayList<Musikant> musikanten)
	{
		if(!musikanten.isEmpty() && musikanten != null){
			return musikanten.stream().max(Comparator.comparing(Musikant::getId)).get().getId();
		}
		else return 0;
	}
	
	/**
	 * Gets the last id from a Instrument in the given List
	 * @param instrumente List with Instrumente
	 * @return The last Id
	 */
	public int getLastInstrumentId(ArrayList<Instrument> instrumente)
	{
		if(!instrumente.isEmpty() && instrumente != null){
			return instrumente.stream().max(Comparator.comparing(Instrument::getId)).get().getId();
		}
		else return 0;
	}
	
	/**
	 * Gets the last id from a Hauptwohnsitz in the given List
	 * @param instrumente List with Hauptwohnsitze
	 * @return The last Id
	 */
	public int getLastHauptwohnsitzId(ArrayList<Hauptwohnsitz> hauptwohnsitze)
	{
		if(!hauptwohnsitze.isEmpty() && hauptwohnsitze != null){
			return hauptwohnsitze.stream().max(Comparator.comparing(Hauptwohnsitz::getId)).get().getId();
		}
		else return 0;
	}
	
	/**
	 * Checks if the given Id exits in the Hauptwohnsitz List
	 * @param id Given Id
	 * @return Whether the id exists or not
	 */
	public boolean checkIfHauptwohnsitzExists(int id)
	{
		for(Hauptwohnsitz h : GloballyData.getInstance().getHauptwohnsitze())
		{
			if(h.getId() == id) return true;
		}
		return false;
	}
	
	/**
	 * Removes the all entries in the MusikantInstrument List where the specific Musikant is deleted
	 */
	public void updateDeletedMusikantenIntrumente()
	{
		for(Musikant m : GloballyData.getInstance().getMusikanten())
		{
			for(MusikantInstrument mi : GloballyData.getInstance().getMusikantInstrumente())
			{
				if(m.getId() == mi.getMusikantid() && m.isDeleted() && !mi.isDeleted()) mi.setDeleted(true);
			}
		}
		
		for(Instrument i : GloballyData.getInstance().getInstrumente())
		{
			for(MusikantInstrument mi : GloballyData.getInstance().getMusikantInstrumente())
			{
				if(i.getId() == mi.getInstrumentid() && i.isDeleted() && !mi.isDeleted()) mi.setDeleted(true);
			}
		}
	}

	/**
	 * Returns all Hauptwohnsitze which are not used by any Musikant
	 * @return A List with available Hauptwohnsitze
	 */
	public ArrayList<Hauptwohnsitz> getAvailableHauptwohnsitze()
	{
		ArrayList<Hauptwohnsitz> available = new ArrayList<>();
		ArrayList<Integer> allMHsIds = new ArrayList<>();
		ArrayList<Integer> allHsIds = new ArrayList<>();
		
		for(Musikant m : GloballyData.getInstance().getMusikanten())
		{
			allMHsIds.add(m.getHauptwohnsitzId());
		}
		for(Hauptwohnsitz h : GloballyData.getInstance().getHauptwohnsitze())
		{
			allHsIds.add(h.getId());
		}
		allHsIds.removeAll(allMHsIds);
		for(Hauptwohnsitz h : GloballyData.getInstance().getHauptwohnsitze())
		{
			for(Integer i : allHsIds)
			{
				if(h.getId() == i) available.add(h);
			}
		}
		
		return available;
	}
	
	/**
	 * Checks whether a given Instrument is present in the given List
	 * @param newInstrument Given Instrument to check for
	 * @param oldInstrumente Given List to check for
	 * @return Whether the Instrument is present
	 */
	public boolean checkIfInstrumentIsNew(Instrument newInstrument, ArrayList<Instrument> oldInstrumente)
	{
		for(Instrument i : oldInstrumente)
		{
			if(newInstrument.getId() == i.getId()) return false;
		}
		return true;
	}
	
	/**
	 * Checks whether a given Instrument is not present in the given List
	 * @param newInstrument Given Instrument to check for
	 * @param oldInstrumente Given List to check for
	 * @return Whether the Instrument is not present
	 */
	public boolean checkifInstrumentIsDeleted(Instrument oldInstrument, ArrayList<Instrument> newInstrumente)
	{
		for(Instrument i : newInstrumente)
		{
			if(oldInstrument.getId() == i.getId()) return false;
		}
		return true;
	}
	
	/**
	 * Returns all Instrumente which are not used by a specific Musikant
	 * @param id Musikant Id
	 * @return List with available Instrumente
	 */
	public ArrayList<Instrument> getAvailableInstrumente(int id)
	{
		ArrayList<Instrument> available = new ArrayList<>();

		for(MusikantInstrument mi : GloballyData.getInstance().getMusikantInstrumente().stream().filter(x -> x.getMusikantid() == id)
				.collect(Collectors.toList()))
		{
			for(Instrument i : GloballyData.getInstance().getInstrumente().stream().filter(x -> x.getId() == mi.getInstrumentid())
					.collect(Collectors.toList()))
			{
				available.add(i);
			}
		}
		
		return available;
	}
	
	/**
	 * Gets all Instrumente which is not used in the given List
	 * @param instrumente List with Instrumente
	 * @return List with Instrumente which are not used
	 */
	public ArrayList<Instrument> getAvailableInstrumente(ArrayList<Instrument> instrumente)
	{
		ArrayList<Instrument> available = new ArrayList<>();
		ArrayList<Integer> allUIIds = new ArrayList<>();
		ArrayList<Integer> allIIds = new ArrayList<>();
		
		for(Instrument i : GloballyData.getInstance().getInstrumente())
		{
			allIIds.add(i.getId());
		}
		for(Instrument i : instrumente)
		{
			allUIIds.add(i.getId());
		}
		allIIds.removeAll(allUIIds);
		for(Instrument i : GloballyData.getInstance().getInstrumente())
		{
			for(Integer j : allIIds)
			{
				if(i.getId() == j) available.add(i);
			}
		}
		
		return available;
	}
}