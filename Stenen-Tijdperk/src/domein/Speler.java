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
    private int punten;
    private String naam;
    private String kleur;
    private boolean aanBeurt = false;
    private Gereedschap[] gereedschapskistje;
    
    public Speler(String naam, int nummer, String kleur)
    {
        setNaam(naam);
        setNummer(nummer);
        setKleur(kleur);
        setAanBeurt(false);
        setAantalStamleden(5);
        setAantalHout(0);
        setAantalLeem(0);
        setAantalSteen(0);
        setAantalGoud(0);
        setAantalVoedsel(12);
        setGereedschapskistje();
        setPunten(0);
    }

    public Gereedschap[] getGereedschapskistje()
    {
        return gereedschapskistje;
    }

    private void setGereedschapskistje()
    {
        gereedschapskistje = new Gereedschap[3];
        
        for(int loper=0; loper<getGereedschapskistje().length;loper++)
        {
            Gereedschap gereedschapje = new Gereedschap(loper+1, 0, false);
            gereedschapskistje[loper] = gereedschapje;
        }
        
    }
    
    private void setPunten(int aantalPunten)
    {
        this.punten = punten;
    }
    
    private int getPunten()
    {
        return punten;
    }
    
    
    
    public final void setNummer(int nummer) 
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
    
     private final void setNaam(String naam)
    {
        if (naam.length() > 10)
            throw new IllegalArgumentException("Naam van de speler is te lang!");
        this.naam = naam;
    }
    
    public final void setKleur(String kleur)
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
    
    public String getNaam()
    {
        return naam;
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
                    + "%20s| Aantal hout: %2d | Aantal leem: %2d | Aantal steen: %2d | Aantal goud: %2d | Aantal voedsel: %2d |%n"
                    + "%20s | Aantal overwinningspunten: %d",
                this.getClass().getSimpleName().toUpperCase(), 
                getNummer(), 
                getNaam(),
                getKleur(), 
                getAantalStamleden(),
                getAanBeurt()?"ja":"nee",
                leeg,
                leeg,
                getAantalHout(),
                getAantalLeem(), 
                getAantalSteen(), 
                getAantalGoud(), 
                getAantalVoedsel(),
                leeg,
                getPunten()
                );
    }
}