
package domein;

public class Hut {

    private int aantalHoutPrijs;
    private int aantalLeemPrijs;
    private int aantalSteenPrijs;
    private int aantalGoudPrijs;

    public Hut(int aantalHoutPrijs, int aantalLeemPrijs, int aantalSteenPrijs, int aantalGoudPrijs)
    {
        setAantalHoutPrijs(aantalHoutPrijs);
        setAantalLeemPrijs(aantalLeemPrijs);
        setAantalSteenPrijs(aantalSteenPrijs);
        setAantalGoudPrijs(aantalGoudPrijs);
    }
    
    public void berekenKostPrijsHut()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public int geefKostPrijsHut()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public int geefWaardeHut()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
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
    
    
}
