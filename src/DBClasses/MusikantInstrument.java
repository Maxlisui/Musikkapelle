package DBClasses;

public class MusikantInstrument {

    private Musikant musikant;
    private Instrument instrument;
    private int musikantid;
    private int instrumentid;
    private boolean isUpdated;
    private boolean isNew;
    private boolean isDeleted;

	public MusikantInstrument(int musikantid, int instrumentid, boolean isNew) {
        this.musikantid = musikantid;
        this.instrumentid = instrumentid;
		this.isNew = isNew;
		this.isUpdated = false;
		this.isDeleted = false;
    }

	public boolean isDeleted()
	{
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted)
	{
		this.isDeleted = isDeleted;
	}
	
    public boolean isNew()
	{
		return isNew;
	}

	public void setNew(boolean isNew)
	{
		this.isNew = isNew;
	}

	public void setUpdated(boolean isUpdated)
	{
		this.isUpdated = isUpdated;
	}

    public boolean isUpdated() {
        return isUpdated;
    }

    public int getMusikantid() {
        return musikantid;
    }

    public void setMusikantid(int musikantid) {
        this.musikantid = musikantid;
    }

    public int getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(int instrumentid) {
        this.instrumentid = instrumentid;
    }

    public Musikant getMusikant() {
        return musikant;
    }

    public void setMusikant(Musikant musikant) {
        this.musikant = musikant;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
