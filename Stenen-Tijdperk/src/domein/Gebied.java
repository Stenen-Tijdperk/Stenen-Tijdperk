package domein;

public class Gebied 
{
    private int nummer;
    private int aantalGenomenPlaatsen;
    private int aantalMaxLeden;
    private String functie;
    private String naamGebied;
       
    public Gebied(String naamGebied, int aantalMaxLeden, String functie, int nummer)
    {
        setNaamGebied(naamGebied);
        setAantalMaxLeden(aantalMaxLeden);
        setFunctie(functie);
        setNummer(nummer);
    }
    
    private final void setNaamGebied(String naamGebied)
    {
        this.naamGebied = naamGebied;
    }

    private final void setAantalMaxLeden(int aantalMaxLeden)
    {
        this.aantalMaxLeden = aantalMaxLeden;
    }

    private final void setFunctie(String functie)
    {
        this.functie = functie;
    }
    
    private final void setNummer(int nummer)
    {
        this.nummer = nummer;
    }
     
    public void setAantalGenomenPlaatsen(int aantalGenomenPlaatsen)
    {
        if (aantalGenomenPlaatsen > aantalMaxLeden)
            throw new IllegalArgumentException("Het max aantal stamleden is overschreden!");
        this.aantalGenomenPlaatsen = aantalGenomenPlaatsen;
    }
    
    public int getNummer()
    {
        return nummer;
    }
    
    public String getNaamGebied() 
    {
        return naamGebied;
    }

    public int getAantalMaxLeden() 
    {
        return aantalMaxLeden;
    }
    
    public int getAantalGenomenPlaatsen()
    {
        return aantalGenomenPlaatsen;
    }
    
    public String getFunctie() 
    {
        return functie;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s %16s | Nummer gebied: %2d | Aantal genomen plaatsen: %2d | Aantal max stamleden: %2d | Functie %20s%n",
                this.getClass().getSimpleName().toUpperCase(), getNaamGebied(), getNummer(),
                getAantalGenomenPlaatsen(), getAantalMaxLeden(), getFunctie());
    }
}

