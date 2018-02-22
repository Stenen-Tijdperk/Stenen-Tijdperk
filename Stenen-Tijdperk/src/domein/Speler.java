package domein;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Speler {

    private int aantalHout;
    private int aantalLeem;
    private int aantalSteen;
    private int aantalGoud;
    private final GregorianCalendar geboortdeDatum = null; //DIT IS ECHT FUCKED UP? IK WEET NIET HOE HET WERKT -SOUHAIB 
    //--> ik heb zitten zoeken naar een oplossing, maar vond niet echt iets, heb onderaan wel een mogelijke oplossing gezet - Rob
    private final String kleur;
    private final String naamSpeler;
    private boolean beurt;
    
    public Speler(String kleur, String naamSpeler, GregorianCalendar geboorteDatum)
    {
        this.geboortdeDatum = new GregorianCalendar(); //DIT IS ECHT FUCKED UP? IK WEET NIET HOE HET WERKT -SOUHAIB
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
    
    public boolean isAanBeur()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public void gebruikGereedschapsfische()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }

    public void setAantalHout(int aantalHout) 
    {
        this.aantalHout = aantalHout;
    }

    public void setAantalLeem(int aantalLeem) 
    {
        this.aantalLeem = aantalLeem;
    }

    public void setAantalSteen(int aantalSteen) 
    {
        this.aantalSteen = aantalSteen;
    }

    public void setAantalGoud(int aantalGoud) 
    {
        this.aantalGoud = aantalGoud;
    }
    
    public void geefKleurAanSpeler()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
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
