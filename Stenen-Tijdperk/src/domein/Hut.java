package domein;

import java.util.Random;

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
       
        Random randomGenerator = new Random();
        aantalHoutPrijs = randomGenerator.nextInt(3);
        this.aantalHoutPrijs = aantalHoutPrijs;
    }

    public void setAantalLeemPrijs(int aantalLeemPrijs) 
    {
        
        Random randomGenerator = new Random();
        aantalLeemPrijs = randomGenerator.nextInt(3);
        this.aantalLeemPrijs = aantalLeemPrijs;
    }

    public void setAantalSteenPrijs(int aantalSteenPrijs) 
    {
        
        Random randomGenerator = new Random();
        aantalSteenPrijs = randomGenerator.nextInt(2);
        this.aantalSteenPrijs = aantalSteenPrijs;
    }

    public void setAantalGoudPrijs(int aantalGoudPrijs) 
    {
        
        Random randomGenerator = new Random();
        aantalGoudPrijs = randomGenerator.nextInt(2);
        this.aantalGoudPrijs = aantalGoudPrijs;
    }

    private void setSpeler(Speler speler)
    {
        //Ik weet niet hoe ge de speler vastzet aan een huizeke -Ruben
        this.speler = speler;  
    }
}
