package domein;

import java.security.SecureRandom;

public final class Speler {

    private int aantalHout;
    private int aantalLeem;
    private int aantalSteen;
    private int aantalGoud;
    private int aantalVoedsel;
    private final String naamSpeler;
    private static String kleur;
            
    
    /*
    DE CODE DIE TOCH LEEG WAS HEB IK WEGGEDAAN
    OMDAT HET TOCH NIET NUTTIG WAS EROP TE STAREN
    EN NOG NIET NODIG IS VOOR ITERATIE 1 -Ruben
    */
    
    public Speler(String naamSpeler, int aantalHout, int aantalLeem, int aantalSteen, int aantalGoud, int aantalVoedsel, String kleur)
    {
        this.naamSpeler = naamSpeler;
        setAantalHout(aantalHout);
        setAantalLeem(aantalLeem);
        setAantalSteen(aantalSteen);
        setAantalGoud(aantalGoud);
        setAantalVoedsel(aantalVoedsel);
        setKleur(kleur);
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
    
    @Override
    public String toString()
    {
        return String.format("%n%s %10s | KLEUR : %6s | Aantal hout: %2d | Aantal leem: %2d | Aantal steen: %2d | Aantal goud:  %2d | Aantal voedsel: %2d%n",
                this.getClass().getSimpleName().toUpperCase(), getNaamSpeler(), getKleur(),
                getAantalHout(), getAantalLeem(), getAantalSteen(), getAantalGoud(), getAantalVoedsel());
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
