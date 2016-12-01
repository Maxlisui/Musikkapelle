package DBClasses;

/**
 * Created by Maxlisui on 01/12/2016.
 */
public class Hauptwohnsitz
{
    private int id;
    private String strasse;
    private String hausnummer;
    private int PLZ;
    private String ort;
    private String land;
    private boolean isUpdated;

    public Hauptwohnsitz(int id, String strasse, String hausnummer, int PLZ, String ort, String land) {
        this.id = id;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.PLZ = PLZ;
        this.ort = ort;
        this.land = land;
        isUpdated = false;
    }

    public int getId() {
        return id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
        isUpdated = true;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
        isUpdated = true;
    }

    public int getPLZ() {
        return PLZ;
    }

    public void setPLZ(int PLZ) {
        this.PLZ = PLZ;
        isUpdated = true;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
        isUpdated = true;
    }

    public String getLand() {
        return land;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setLand(String land) {
        this.land = land;
        isUpdated = true;

    }
}
