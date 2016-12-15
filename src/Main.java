import DBClasses.Hauptwohnsitz;
import DBClasses.Musikant;
import DBConnection.DBManager;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {

        Musikant m = new Musikant(0, "Luise", "Sandhacker", Date.valueOf("1998-05-05"), "lise@hans.com", "Kann nix", 0);
        m.setHauptwohnsitz(new Hauptwohnsitz(0, "Messerschmittweg", "16", 6175, "Kematen", "Oesterreich"));

        try {
            DBManager.getInstance().insertHauptwohnsitz(m.getHauptwohnsitz());
            DBManager.getInstance().insertMusikant(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}