package domein;

public class Gebied 
{
    private final String naamGebied;
    private final int aantalMaxLeden;
    private final String functie;
    
    public Gebied(String naamGebied, int aantalMaxLeden, String functie)
    {
        this.naamGebied = naamGebied;
        this.aantalMaxLeden = aantalMaxLeden;
        this.functie = functie;
    }

    public String getNaamGebied() 
    {
        return naamGebied;
    }

    public int getAantalMaxLeden() 
    {
        return aantalMaxLeden;
    }

    public String getFunctie() 
    {
        return functie;
    }
}
