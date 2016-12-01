package DBClasses;

public class MusikantInstrument {

    private Musikant musikant;
    private Instrument instrument;
    private String stimme;
    private int musikantid;
    private int instrumentid;
    private boolean isUpdated;

    public MusikantInstrument(int musikantid, int instrumentid, String stimme) {
        this.stimme = stimme;
        this.musikantid = musikantid;
        this.instrumentid = instrumentid;
        isUpdated = false;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public int getMusikantid() {
        return musikantid;
    }

    public void setMusikantid(int musikantid) {
        this.musikantid = musikantid;
        isUpdated = true;
    }

    public int getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(int instrumentid) {
        this.instrumentid = instrumentid;
        isUpdated = true;
    }

    public Musikant getMusikant() {
        return musikant;
    }

    public void setMusikant(Musikant musikant) {
        this.musikant = musikant;
        isUpdated = true;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
        isUpdated = true;
    }

    public String getStimme() {
        return stimme;
    }

    public void setStimme(String stimme) {
        this.stimme = stimme;
        isUpdated = true;
    }
}
