package domein;

public final class Hut {

    private int aantalHoutPrijs;
    private int aantalLeemPrijs;
    private int aantalSteenPrijs;
    private int aantalGoudPrijs;
    private int nummer;
    private Speler speler;

    public Hut(int aantalHoutPrijs, int aantalLeemPrijs, int aantalSteenPrijs, int aantalGoudPrijs, int nummer)
    {
        setAantalHoutPrijs(aantalHoutPrijs);
        setAantalLeemPrijs(aantalLeemPrijs);
        setAantalSteenPrijs(aantalSteenPrijs);
        setAantalGoudPrijs(aantalGoudPrijs);
        setNummer(nummer);
    }
    
    public int berekenKostPrijsHut()
    {
        int kostPrijs = aantalHoutPrijs + aantalLeemPrijs + aantalSteenPrijs + aantalGoudPrijs;
        return kostPrijs;
    }
    
    public void setAantalHoutPrijs(int aantalHoutPrijs) 
    {
        this.aantalHoutPrijs = aantalHoutPrijs;
    }

    public void setAantalLeemPrijs(int aantalLeemPrijs) 
    {
        this.aantalLeemPrijs = aantalLeemPrijs;
    }

    public void setAantalSteenPrijs(int aantalSteenPrijs) 
    {
        this.aantalSteenPrijs = aantalSteenPrijs;
    }

    public void setAantalGoudPrijs(int aantalGoudPrijs) 
    {
        this.aantalGoudPrijs = aantalGoudPrijs;
    }

    private void setNummer(int nummer)
    {
        this.nummer = nummer;
    }
    
    public int getNummer()
    {
        return nummer;
    }
}
