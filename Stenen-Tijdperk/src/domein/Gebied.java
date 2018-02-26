package domein;

public class Gebied 
{
    private String naamGebied;
    private int aantalGenomenPlaatsen;
    private int aantalMaxLeden;
    private String functie;
    
    public Gebied(String naamGebied, int aantalMaxLeden, String functie)
    {
        setNaamGebied(naamGebied);
        setAantalMaxLeden(aantalMaxLeden);
        setFunctie(functie);
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
        return String.format("%s %11s | Aantal genomen plaatsen: %2d | Aantal max stamleden: %2d | Functie %10s%n", this.getClass().getSimpleName().toUpperCase(), getNaamGebied(), getAantalGenomenPlaatsen(), getAantalMaxLeden(), getFunctie());
    }

    private void setNaamGebied(String naamGebied)
    {
        this.naamGebied = naamGebied;
    }

    private void setAantalMaxLeden(int aantalMaxLeden)
    {
        this.aantalMaxLeden = aantalMaxLeden;
    }

    private void setFunctie(String functie)
    {
        this.functie = functie;
    }
}
