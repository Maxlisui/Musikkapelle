package DBClasses;

import java.sql.Date;

/**
 * Created by Maxlisui on 24/11/2016.
 */
public class Musikant
{
    private int id;
    private String vorname;
    private String nachname;
    private Date geburtsdatum;
    private String email;
    private String Spezailfunktion;
    private int hauptwohnsitzId;
    private Hauptwohnsitz hauptwohnsitz;
    private boolean isUpdated;

    public Musikant(int id, String vorname, String nachname, Date geburtsdatum, String email, String spezailfunktion, int hauptwohnsitzId) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.email = email;
        Spezailfunktion = spezailfunktion;
        this.hauptwohnsitzId = hauptwohnsitzId;
        isUpdated = false;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public Hauptwohnsitz getHauptwohnsitz() {
        return hauptwohnsitz;
    }

    public void setHauptwohnsitz(Hauptwohnsitz hauptwohnsitz) {
        this.hauptwohnsitz = hauptwohnsitz;
        isUpdated = true;
    }

    public int getHauptwohnsitzId() {
        return hauptwohnsitzId;
    }

    public void setHauptwohnsitzId(int hauptwohnsitzId) {
        this.hauptwohnsitzId = hauptwohnsitzId;
        isUpdated = true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
        isUpdated = true;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
        isUpdated = true;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        isUpdated = true;
    }

    public void setEmail(String email) {
        this.email = email;
        isUpdated = true;
    }

    public void setSpezailfunktion(String spezailfunktion) {
        Spezailfunktion = spezailfunktion;
        isUpdated = true;
    }

    public int getId() {

        return id;
    }
    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getEmail() {
        return email;
    }

    public String getSpezailfunktion() {
        return Spezailfunktion;
    }
}
