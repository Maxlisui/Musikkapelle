import DBClasses.Musikant;
import DBConnection.DBManager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Musikant> musikanten = null;

        try {
            musikanten = DBManager.getInstance().selectAllMusikanten();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Musikant m : musikanten)
        {
            try{
                m.setHauptwohnsitz(DBManager.getInstance().selectHauptwohnsitzPerMusikant(m));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}