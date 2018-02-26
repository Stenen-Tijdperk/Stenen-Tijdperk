package domein;

import java.security.SecureRandom;

public final class Speler {

    private int aantalHout;
    private int aantalLeem;
    private int aantalSteen;
    private int aantalGoud;
    private int aantalVoedsel;
    private int aantalStamleden;
    private int nummer;
    private String naamSpeler;
    private String kleur;
    private boolean aanBeurt = false;
    
    public Speler(String naamSpeler, int aantalHout, int aantalLeem, int aantalSteen, int aantalGoud, int aantalVoedsel, String kleur, int aantalStamleden, int nummer, boolean aanBeurt)
    {
        setNaamSpeler(naamSpeler);
        setAantalHout(aantalHout);
        setAantalLeem(aantalLeem);
        setAantalSteen(aantalSteen);
        setAantalGoud(aantalGoud);
        setAantalVoedsel(aantalVoedsel);
        setKleur(kleur);
        setAantalStamleden(aantalStamleden);
        setNummer(nummer);
        setAanBeurt(aanBeurt);
    }
      
    public void gebruikGereedschapsfische(Gereedschapsfiche gereedschap)
    {
        if (gereedschap.isReedsGebruiktDezeRonde() == true)
        {
            throw new IllegalArgumentException("Het gereedschap is reeds gebruikt. Wacht tot de volgende ronde.");
        }
        else 
        {
            gereedschap.setReedsGebruiktDezeRonde(true);
            //+ methode voor meer dobbelsteenogen per gereedschapsfichewaarde -Ruben
        }
    }
    
    public void setNummer(int nummer) 
    {
        this.nummer = nummer;
    }
    
    public void setAantalHout(int aantalHout) 
    {
        if (aantalHout < 0)
        {
            throw new IllegalArgumentException("De speler kan niet minder als 0 hout hebben.");
        }
        this.aantalHout = aantalHout;
    }

    public void setAantalLeem(int aantalLeem) 
    {
        if (aantalLeem < 0)
        {
            throw new IllegalArgumentException("De speler kan niet minder als 0 leem hebben.");
        }
        this.aantalLeem = aantalLeem;
    }

    public void setAantalSteen(int aantalSteen) 
    {
        if (aantalSteen < 0)
        {
            throw new IllegalArgumentException("De speler kan niet minder als 0 steen hebben.");
        }
        this.aantalSteen = aantalSteen;
    }

    public void setAantalGoud(int aantalGoud) 
    {
        if (aantalGoud < 0)
        {
            throw new IllegalArgumentException("De speler kan niet minder als 0 goud hebben.");
        }
        this.aantalGoud = aantalGoud;
    }
    
     private void setNaamSpeler(String naamSpeler)
    {
        if (naamSpeler.length() > 10)
            throw new IllegalArgumentException("Naam van de speler is te lang!");
        this.naamSpeler = naamSpeler;
    }
    
    public void setKleur(String kleur)
    {
        this.kleur = kleur;
    }
    
    public void setAanBeurt(boolean aanBeurt)
    {
        this.aanBeurt = aanBeurt;
    }

    public void setAantalVoedsel(int aantalVoedsel)
    {
        if (aantalVoedsel < 0)
        {
            throw new IllegalArgumentException("De speler kan niet minder als 0 voedsel hebben.");
        }
        this.aantalVoedsel = aantalVoedsel;
    }
    
    public void setAantalStamleden(int aantalStamleden)
    {
        if (aantalStamleden < 0)
        {
            throw new IllegalArgumentException("De speler kan niet minder als 0 stamleden hebben.");
        }
        this.aantalStamleden = aantalStamleden;
    }
    
    public String getKleur()
    {
        return kleur;
    }

    public int getAantalHout()
    {
        return aantalHout;
    }

    public int getAantalLeem()
    {
        return aantalLeem;
    }

    public int getAantalSteen()
    {
        return aantalSteen;
    }

    public int getAantalGoud()
    {
        return aantalGoud;
    }

    public int getNummer() 
    {
        return nummer;
    }
    
    public int getAantalVoedsel()
    {
        return aantalVoedsel;
    }
    
    public String getNaamSpeler()
    {
        return naamSpeler;
    }
    
    public int getAantalStamleden()
    {
        return aantalStamleden;
    }
    
    public boolean getAanBeurt()
    {
        return aanBeurt;
    }
    
    @Override
    public String toString()
    {
        String leeg = "";
        return String.format("%n%s: %d %10s | KLEUR:%9s | Aantal ongebruikte stamleden: %4d | Aan de beurt: %3s %18s |%n "
                    + "%20s| Aantal hout: %2d | Aantal leem: %2d | Aantal steen: %2d | Aantal goud: %2d | Aantal voedsel: %2d |%n",
                this.getClass().getSimpleName().toUpperCase(), 
                getNummer(), 
                getNaamSpeler(),
                getKleur(), 
                getAantalStamleden(),
                getAanBeurt()?"ja":"nee",
                leeg,
                leeg,
                getAantalHout(),
                getAantalLeem(), 
                getAantalSteen(), 
                getAantalGoud(), 
                getAantalVoedsel()
                );
        
      
       /*
        return String.format("%n%s: %d %10s | KLEUR:%5s | Aantal ongebruikte stamleden: %d | Aantal hout: %2d | Aantal leem: %2d | Aantal steen: %2d | Aantal goud:  %2d | Aantal voedsel: %2d%n | Aan de beurt: %3s",
                this.getClass().getSimpleName().toUpperCase(), getNummer(), getNaamSpeler(), getKleur(), getAantalStamleden(),
                getAantalHout(), getAantalLeem(), getAantalSteen(), getAantalGoud(), getAantalVoedsel(), getAanBeurt()?"ja":"nee");
        */
    }
}