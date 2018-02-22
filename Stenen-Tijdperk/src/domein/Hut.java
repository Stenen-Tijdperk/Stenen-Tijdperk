package domein;

public final class Hut {

    private int aantalHoutPrijs;
    private int aantalLeemPrijs;
    private int aantalSteenPrijs;
    private int aantalGoudPrijs;
    private Speler speler;

    public Hut(int aantalHoutPrijs, int aantalLeemPrijs, int aantalSteenPrijs, int aantalGoudPrijs, Speler speler)
    {
        setAantalHoutPrijs(aantalHoutPrijs);
        setAantalLeemPrijs(aantalLeemPrijs);
        setAantalSteenPrijs(aantalSteenPrijs);
        setAantalGoudPrijs(aantalGoudPrijs);
        setSpeler(speler);
    }
    
    public int berekenKostPrijsHut()
    {
        int kostPrijs = aantalHoutPrijs + aantalLeemPrijs + aantalSteenPrijs + aantalGoudPrijs;
        return kostPrijs;
    }
    
    public int geefKostPrijsHut()
    {
        return berekenKostPrijsHut();
    }
    
    public int geefWaardeHut()
    {
        return berekenKostPrijsHut();
    }
    
    public void setAantalHoutPrijs(int aantalHoutPrijs) 
    {
        //random generator tussen 1-x voor het aantal grondstoffen, staat in boek van programmeren
        this.aantalHoutPrijs = aantalHoutPrijs;
    }

    public void setAantalLeemPrijs(int aantalLeemPrijs) 
    {
        //random generator tussen 1-x voor het aantal grondstoffen, staat in boek van programmeren
        this.aantalLeemPrijs = aantalLeemPrijs;
    }

    public void setAantalSteenPrijs(int aantalSteenPrijs) 
    {
        //random generator tussen 1-x voor het aantal grondstoffen, staat in boek van programmeren
        this.aantalSteenPrijs = aantalSteenPrijs;
    }

    public void setAantalGoudPrijs(int aantalGoudPrijs) 
    {
        //random generator tussen 1-x voor het aantal grondstoffen, staat in boek van programmeren
        this.aantalGoudPrijs = aantalGoudPrijs;
    }

    private void setSpeler(Speler speler)
    {
        //Ik weet niet hoe ge de speler vastzet aan een huizeke -Ruben
        this.Speler = speler;  
    }
}
