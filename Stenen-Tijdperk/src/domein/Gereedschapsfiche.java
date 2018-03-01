package domein;

public class Gereedschapsfiche 
{
    private int waarde;
    private boolean reedsGebruiktDezeRonde;
       
    public Gereedschapsfiche(int waarde, boolean reedsGebruiktDezeRonde)
    {
        setWaarde(waarde);
        setReedsGebruiktDezeRonde(false);
    }
    
    //dit is Basically een getter voor een boolean - Ruben
    public boolean isReedsGebruiktDezeRonde()
    {
        return reedsGebruiktDezeRonde;
    }

    public void setWaarde(int waarde) 
    {
        this.waarde = waarde;
    }

    public void setReedsGebruiktDezeRonde(boolean reedsGebruiktDezeRonde) 
    {
        this.reedsGebruiktDezeRonde = reedsGebruiktDezeRonde;
    }
}
