import DBClasses.*;
import DBConnection.DBManager;
import Utils.ListUtils;

import java.sql.Date;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Musikant> musikanten = new ArrayList<>();
        ArrayList<Hauptwohnsitz> hauptwohnsitze = new ArrayList<>();
        ArrayList<Instrument> instrumente = new ArrayList<>();
        ArrayList<MusikantInstrument> musikantInstrumente = new ArrayList<>();

        try {
    		musikanten = DBManager.getInstance().selectAllMusikanten();	
    		hauptwohnsitze = DBManager.getInstance().selectAllHauptwohnsitze();
    		instrumente = DBManager.getInstance().selectAllIntrumente();
    		musikantInstrumente = DBManager.getInstance().selectAllInstrumentMusikant();
    		
    		Hauptwohnsitz h = new Hauptwohnsitz(ListUtils.getInstance().getLastHauptwohnsitzId(hauptwohnsitze) + 1, "hansstrasse", "17b", 9999, "ort", "land");
    		h.setNew(true);
    		Musikant m = new Musikant(ListUtils.getInstance().getLastMusikantId(musikanten) + 1, "TestJava", "Java", Date.valueOf("1890-02-03"), 
    				"test@test.com", "Zipfl", h.getId());
    		m.setNew(true);
    		hauptwohnsitze.add(h);
    		musikanten.add(m);
    		
    		DBManager.getInstance().refreshDB(musikanten, instrumente, hauptwohnsitze, musikantInstrumente);
    		
    		DBManager.getInstance().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}