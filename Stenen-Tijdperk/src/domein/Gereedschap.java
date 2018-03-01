package domein;

public class Gereedschap 
{
    private int nummer;
    private int waarde;
    private boolean reedsGebruiktDezeRonde;
       
    public Gereedschap(int nummer, int waarde, boolean reedsGebruiktDezeRonde)
    {
        setNummer(nummer);
        setWaarde(waarde);
        setReedsGebruiktDezeRonde(false);
    }
    
    public final void setNummer(int nummer)
    {
        this.nummer = nummer;
    }

    public void setWaarde(int waarde) 
    {
        this.waarde = waarde;
    }

    public void setReedsGebruiktDezeRonde(boolean reedsGebruiktDezeRonde) 
    {
        this.reedsGebruiktDezeRonde = reedsGebruiktDezeRonde;
    }
    
    public boolean getReedsGebruiktDezeRonde()
    {
        return reedsGebruiktDezeRonde;
    }
    
    public int getWaarde()
    {
        return waarde;
    }
    
    public int getNummer()
    {
        return nummer;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s %d | Waarde: %d | Reeds gebruikt van deze ronde: %s",
                this.getClass().getSimpleName().toUpperCase(), getNummer(),
                getWaarde(), getReedsGebruiktDezeRonde()?"ja":"nee");
    }
}
