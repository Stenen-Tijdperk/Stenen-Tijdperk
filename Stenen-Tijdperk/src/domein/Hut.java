package domein;

public final class Hut {

    private int aantalHoutPrijs;
    private int aantalLeemPrijs;
    private int aantalSteenPrijs;
    private int aantalGoudPrijs;
    private int nummer;
    
    public Hut(int aantalHoutPrijs, int aantalLeemPrijs, int aantalSteenPrijs, int aantalGoudPrijs, int nummer)
    {
        setAantalHoutPrijs(aantalHoutPrijs);
        setAantalLeemPrijs(aantalLeemPrijs);
        setAantalSteenPrijs(aantalSteenPrijs);
        setAantalGoudPrijs(aantalGoudPrijs);
        setNummer(nummer);
    }
    
    private final void setAantalHoutPrijs(int aantalHoutPrijs) 
    {
        this.aantalHoutPrijs = aantalHoutPrijs;
    }

    private final void setAantalLeemPrijs(int aantalLeemPrijs) 
    {
        this.aantalLeemPrijs = aantalLeemPrijs;
    }

    private final void setAantalSteenPrijs(int aantalSteenPrijs) 
    {
        this.aantalSteenPrijs = aantalSteenPrijs;
    }

    private final void setAantalGoudPrijs(int aantalGoudPrijs) 
    {
        this.aantalGoudPrijs = aantalGoudPrijs;
    }

    private final void setNummer(int nummer)
    {
        this.nummer = nummer;
    }
    
    private int getAantalHoutPrijs() 
    {
        return aantalHoutPrijs;
    }

    private int getAantalLeemPrijs() 
    {
        return aantalLeemPrijs;
    }

    private int getAantalSteenPrijs() 
    {
        return aantalSteenPrijs;
    }

    private int getAantalGoudPrijs() 
    {
        return aantalGoudPrijs;
    }
    
    public int getNummer()
    {
        return nummer;
    }
    
    public int berekenKostPrijsHut()
    {
        return (getAantalHoutPrijs() * 3 + getAantalLeemPrijs() * 4 + getAantalSteenPrijs() * 5 + getAantalGoudPrijs() * 6);
    }
    @Override
    public String toString()
    {
        return String.format("%s %2d | Kost: %d hout %d leem %d steen en %d goud | Waarde: %2d punten.%n",
                this.getClass().getSimpleName().toUpperCase(),
                getNummer(), getAantalHoutPrijs(),
                getAantalLeemPrijs(), getAantalSteenPrijs(),
                getAantalGoudPrijs(), berekenKostPrijsHut());
    }
}
