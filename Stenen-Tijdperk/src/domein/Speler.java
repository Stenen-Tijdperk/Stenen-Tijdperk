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
    
    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }
          
    public void gebruikGereedschapsfische(Gereedschapsfiche gereedschap)
    {
        if (gereedschap.isReedsGebruiktDezeRonde() == true)
            throw new IllegalArgumentException("Het gereedschap is reeds gebruikt. Wacht tot de volgende ronde.");
        else {
            gereedschap.setReedsGebruiktDezeRonde(true);
            //+ methode voor meer dobbelsteenogen per gereedschapsfichewaarde -Ruben
        }
    }

    public void setAantalHout(int aantalHout) 
    {
        if (aantalHout < 0)
            throw new IllegalArgumentException("De speler kan niet minder als 0 hout hebben.");
        this.aantalHout = aantalHout;
    }

    public void setAantalLeem(int aantalLeem) 
    {
        if (aantalLeem < 0)
            throw new IllegalArgumentException("De speler kan niet minder als 0 leem hebben.");
        this.aantalLeem = aantalLeem;
    }

    public void setAantalSteen(int aantalSteen) 
    {
        if (aantalSteen < 0)
            throw new IllegalArgumentException("De speler kan niet minder als 0 steen hebben.");
        this.aantalSteen = aantalSteen;
    }

    public void setAantalGoud(int aantalGoud) 
    {
        if (aantalGoud < 0)
            throw new IllegalArgumentException("De speler kan niet minder als 0 goud hebben.");
        this.aantalGoud = aantalGoud;
    }
    
    public void setKleur(String kleur)
    {
        this.kleur = kleur;
    }

    public void setAantalVoedsel(int aantalVoedsel)
    {
        if (aantalVoedsel < 0)
            throw new IllegalArgumentException("De speler kan niet minder als 0 voedsel hebben.");
        this.aantalVoedsel = aantalVoedsel;
    }
    
    public void setAantalStamleden(int aantalStamleden)
    {
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
    
    public void setAanBeurt(boolean aanBeurt)
    {
        this.aanBeurt = aanBeurt;
    }
    
    public boolean getAanBeurt()
    {
        return aanBeurt;
    }
    
    @Override
    public String toString()
    {
        return String.format("%n%s: %d %10s | KLEUR : %6s | Aantal ongebruikte stamleden: %d | Aantal hout: %2d | Aantal leem: %2d | Aantal steen: %2d | Aantal goud:  %2d | Aantal voedsel: %2d%n | Aan de beurt: %3s",
                this.getClass().getSimpleName().toUpperCase(), getNummer(), getNaamSpeler(), getKleur(), getAantalStamleden(),
                getAantalHout(), getAantalLeem(), getAantalSteen(), getAantalGoud(), getAantalVoedsel(), getAanBeurt()?"ja":"nee");
    }

    private void setNaamSpeler(String naamSpeler)
    {
        if (naamSpeler.length() > 10)
            throw new IllegalArgumentException("Naam van de speler is te lang!");
        this.naamSpeler = naamSpeler;
    }
}

/*
code om de geboortedatum van een string om te zetten naar verschillende int's om dan zo om te zetten naar gregorian calendar.
op deze manier kunnen we de geboortedatum als String inlezen, ik weet niet hoe we dit anders kunnen oplossen.
        int dag=0,maand=0,jaar=0;
        String number;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Geef geboortedatum (dd/mm/yyyy): ");
        // controleren met exception te werpen
        String geboortedatum = input.nextLine();

        int i = 0;
        while(i<3)
        {
            for (String deel: geboortedatum.split("/"))
            {         
                i ++;
                if (i == 1)
                {
                    number = deel;
                    dag = Integer.parseInt(number);
                }
                if (i == 2)
                {
                    number = deel;
                    maand = Integer.parseInt(number);
                }
                if (i == 3)
                {
                    number = deel;
                    jaar = Integer.parseInt(number);           
                }
            }
            i++;
        }
        System.out.println(dag);
        System.out.println(maand);
        System.out.println(jaar);

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, dag);
        cal.set(Calendar.MONTH, maand);
        cal.set(Calendar.YEAR, jaar);

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        System.out.printf("%d/%d/%d%n", day, month, year);
        System.out.println(geboortedatum);
*/ 
