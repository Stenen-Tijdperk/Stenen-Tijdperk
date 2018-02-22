package domein;

import domein.Gereedschapsfiche;

public class Speler {

    private int aantalHout;
    private int aantalLeem;
    private int aantalSteen;
    private int aantalGoud;
    private final String kleur;
    private final String naamSpeler;
    private boolean aanBeurt;
    
    //IK BEN VRIJ ZEKER DAT GEBOORTEDATUM MISSCHIEN WEG MAG OMDAT WE EEN VEREENVOUDIG KRIJGEN
    //DE BEGINNENDE SPELER WORDT RANDOM GEKOZEN -Ruben
    
    public Speler(String kleur, String naamSpeler)
    {
        this.kleur = kleur;
        this.naamSpeler = naamSpeler;
    }
    
    public int geefAantalHutten()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public int geefAantalGereedschapsfisches()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public int geefAantalStamleden()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    //Dit is basically een getter voor een boolean
    public boolean isAanBeurt()
    {
        return aanBeurt;
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
    
    public void geefKleurAanSpeler()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
        //Random generator tussen 1-4 en elke nummerke vaststellen aan een kleur?
        //Maar ik weet niet of dit nodig is.
    }
}

/*
code om de geboortedatum van een string om te zetten naar verschillende int's om dan zo om te zetten naar gregorian calendar.
op deze manier kunnen we de geboortedatum als String inlezen, ik weet niet hoe we dit anders kunnen oplossen.
        int x=0,y=0,z=0;
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
                    x = Integer.parseInt(number);
                }
                if (i == 2)
                {
                    number = deel;
                    y = Integer.parseInt(number);
                }
                if (i == 3)
                {
                    number = deel;
                    z = Integer.parseInt(number);           
                }
            }
            i++;
        }
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, x);
        cal.set(Calendar.MONTH, y);
        cal.set(Calendar.YEAR, z);

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        System.out.printf("%d/%d/%d%n", day, month, year);
        System.out.println(geboortedatum);
*/ 
