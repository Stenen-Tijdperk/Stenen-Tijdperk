package domein;

import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class DomeinController
{
    private Speler[] spelerLijst;
    private Gebied[] gebiedLijst;
    
    private Speler[] getSpelerLijst()
    {
        return spelerLijst;
    }
    
    private Gebied[] getGebiedLijst()
    {
        return gebiedLijst;
    }
    
    public void spelerLijstMaken(int aantalSpelers)
    {
        //In deze array worden de spelers hun objecten/namen opgeslagen
        Speler[] spelerLijst = new Speler[aantalSpelers];
        this.spelerLijst = spelerLijst;
    }
    
    public void toonSpelers()
    {
        //Spelers eens afprinten
        for (Speler loper : getSpelerLijst()) 
        {
            //toString van klasse Speler oproepen
            System.out.print(loper.toString());
            
            System.out.println();
        }
    }
    
    public void bepaalRandomSpelerAanBeurt()
    {
        SecureRandom random = new SecureRandom();
        
        //Speler int nummer is aan beurt
        int nummer = random.nextInt(getSpelerLijst().length);
        System.out.printf("Speler %s mag beginnen.%n", getSpelerLijst()[nummer].getNaam());
        
        //Beurt wordt true gezet bij random bepaalde speler
        getSpelerLijst()[nummer].setAanBeurt(true);
        
        System.out.println();
    }
    
    // de verschillende gebieden maken
    public void aanmakenGebieden()
    {   
        //Lijst voor alle gebieden op te slaan, nummer 7 aan te passen adhv kaarten voor hutten
        this.gebiedLijst = new Gebied[8];
        //Constructors vd gebieden
        // Gebied(gebiedNaam, max aantal stamleden, functie, gebiedNummer)
        Gebied hut = new Gebied("hut",2,"extra stamlid", 1);
        gebiedLijst[0] = hut;
        
        Gebied akker = new Gebied("akker",1,"voedselproductie + 1",2);
        gebiedLijst[1] = akker;
        
        Gebied jacht = new Gebied("jacht",40,"voedsel + x",3);
        gebiedLijst[2] = jacht;
        
        Gebied bos = new Gebied("bos",7,"hout + x",4);
        gebiedLijst[3] = bos;
        
        Gebied leemgroeve = new Gebied("leemgroeve",7,"leem + x",5);
        gebiedLijst[4] = leemgroeve;
        
        Gebied steengroeve = new Gebied("steengroeve",7,"steen + x",6);
        gebiedLijst[5] = steengroeve;
        
        Gebied rivier = new Gebied("rivier",7,"goud + x",7);
        gebiedLijst[6] = rivier;
        
        Gebied gereedschapmaker = new Gebied("gereedschapmaker",1,"+ gereedschapfiche",8);
        gebiedLijst[7] = gereedschapmaker;
    }
    
    public void toonGebieden()
    {
        for (int loper=0;getGebiedLijst().length>loper;loper++)
        {
            System.out.print(getGebiedLijst()[loper].toString());
        }
    }
    
    public void toonMenuMetKeuze()
    {
        Scanner invoer = new Scanner(System.in);
        int nummer=0;
        
        if(controleerAlleStamledenGeplaatst() == true)
        {
            do{
                try
                {
                    System.out.printf("Geef een nummer voor de actie die u wilt uitvoeren: %n"
                    + "1: Toon spelers | 2:  Toon gebieden | 3: Plaats stamleden | 4: Beurt overslaan%n");
                    nummer = invoer.nextInt();
                    invoer.nextLine();
                }
                catch (InputMismatchException begin) 
                {
                    invoer.nextLine();
                    System.out.println("Voer een getal in!");
                }
            }while(nummer < 1 || nummer > 5);

            //Witte lijn voor overzicht
            System.out.println();

            switch(nummer)
            {
                case 1: toonSpelers();
                        System.out.println();
                        toonMenuMetKeuze();
                break;
                case 2: toonGebieden();
                        System.out.println();
                        toonMenuMetKeuze();
                break;
                case 3:
                    toonGebieden();
                    System.out.println();
                    int gebiedNummer = 0, aantal = 0;

                    do{
                        try
                        {
                        System.out.print("Waar wil je uw stamleden plaatsen? ");
                        gebiedNummer = invoer.nextInt();
                        invoer.nextLine();
                        }
                        catch(InputMismatchException plaatsStamleden)
                        {
                        System.out.println("Voer een getal in!");
                        invoer.nextLine();
                        }
                    }while(gebiedNummer <= 0 || gebiedNummer > 8);

                    do{
                        try
                        {
                        System.out.print("Hoeveel stamleden wil je er plaatsen? ");
                        aantal = invoer.nextInt();
                        invoer.nextLine();
                        }
                        catch(InputMismatchException aantalStamleden)
                        {
                        System.out.println("Voer een getal in!");
                        invoer.nextLine();
                        }
                    }while(controleerStamleden(aantal, gebiedNummer) == false);

                    // zoveel stamleden op die plek plaatsen
                    if(aantal > 0)
                    {
                        System.out.printf("%nEr %s %d %s geplaatst op het gebied: %s%n%n",aantal>1?"werden":"werd", aantal, aantal>1?"stamleden":"stamlid", gebiedLijst[gebiedNummer-1].getNaamGebied());
                    }
                    else
                    {
                        System.out.printf("Je hebt geen stamleden geplaats, je blijft aan de beurt.");
                    }

                    plaatsStamleden(gebiedNummer, aantal);

                    if(aantal != 0)
                    {
                        beurtOverslaan();
                    }
                    toonMenuMetKeuze();
                break;
                case 4:
                    beurtOverslaan();
                    toonMenuMetKeuze();
                break;
                case 5:
                    toonMijnStamleden();
                break;
            }
        }
        else
        {
            System.out.println("De ronde voor het plaatsen van de stamleden is afgelopen aangezien elke speler al zijn stamleden geplaatst heeft op het spelbord.");
            toonSpelers();
            System.out.println();
            toonGebieden();
        }
    }
    
    public void plaatsStamleden(int gebiedNummer, int aantalStamleden)
    {   
        int spelerNummer = 0; 
        boolean oke = true; 
        for (Speler spelerLijst1 : getSpelerLijst()) 
        {
            if (spelerLijst1.getAanBeurt()) 
            {
                spelerNummer = spelerLijst1.getNummer();
            }  
        }
        try{
        getGebiedLijst()[gebiedNummer-1].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedNummer-1].getAantalGenomenPlaatsen()+aantalStamleden);
        getSpelerLijst()[spelerNummer-1].setAantalStamleden(getSpelerLijst()[spelerNummer-1].getAantalStamleden()-aantalStamleden);
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println("Geef een geldig aantal!");
        }
    }

    public boolean controleerStamleden(int aantal, int gebiedNummer)
    {
        //aantal stamleden dat ge hebt
        int spelerNummer = 0; 
        boolean oke = true; 
        for (Speler spelerLijst1 : getSpelerLijst()) 
        {
            if (spelerLijst1.getAanBeurt()) 
            {
                spelerNummer = spelerLijst1.getNummer();
            }  
        }
        
        if (gebiedNummer == 1 && aantal !=2)
        {
            oke = false;
            System.out.println("Op de hut moet je 2 stamleden zetten.");
        }
        
        if(aantal > getSpelerLijst()[spelerNummer-1].getAantalStamleden() || aantal < 0)
        {
            oke = false;
            System.out.println("Je hebt niet zoveel stamleden.");
        }
        
        //aantal stamleden reeds op plek
        if(getGebiedLijst()[gebiedNummer-1].getAantalMaxLeden() - getGebiedLijst()[gebiedNummer-1].getAantalGenomenPlaatsen() < aantal)
        {           
            oke = false;
            System.out.println("Je gaf een te hoog aantal stamleden in voor dit gebied.");
        }
        return oke;
    }

    public void beurtOverslaan()
    {
        int nummer=1;
        
        for (Speler spelerLijst1 : getSpelerLijst()) 
        {
            if (spelerLijst1.getAanBeurt()) 
            {
                nummer = spelerLijst1.getNummer();
                spelerLijst1.setAanBeurt(false);
            }  
        }
        
        if (nummer == getSpelerLijst().length)
        {
            nummer = 1;
        }
        else
        {
            nummer += 1;
        }
        getSpelerLijst()[nummer-1].setAanBeurt(true);
    }

    public void aanmakenHutten()
    {
        //Hutlijst wordt aangemaakt, alle hutten zijn null
        Hut[] hutLijst = new Hut[28];
        
        int hout = 0, leem = 0, steen = 0, goud = 0, getal = 0;
        
        for (int aantalHutten=0;aantalHutten<=27;aantalHutten++)
        {
            SecureRandom random = new SecureRandom();
            for(int loper=0;loper==3;loper++)
            {
                getal= random.nextInt(4);

                switch(getal)
                {
                    case 1: hout++;
                    break;
                    case 2: leem++;
                    break;
                    case 3: steen++;
                    break;
                    case 4: goud++;
                }
            }
        
            //Hut wordt aangemaakt
            Hut hut = new Hut(hout, leem, steen, goud, aantalHutten);
            //Hut wordt opgeslagen
            hutLijst[aantalHutten] = hut;
        }
    }

    public boolean controleerNaamSpelers(String naamSpeler, int spelerNummer)
    {
        boolean oke = true;
        //Naam mag niet langer als 10 characters zijn
        if (naamSpeler.length() >= 10)
        {
            oke = false;
            System.out.println("De naam is te lang. Maximum 10 characters!");
        }
        
        //Naam mag niet 2 keer dezelfde zijn.
        for (Speler loper : getSpelerLijst()) {
            switch(spelerNummer)
            {
                case 0: 
                    break;
                case 1:
                    if(Objects.equals(naamSpeler.toLowerCase(), getSpelerLijst()[spelerNummer-1].getNaam().toLowerCase()) == true)
                    {
                        oke = false;
                    }
                    break;
                case 2:
                    if(Objects.equals(naamSpeler.toLowerCase(), getSpelerLijst()[spelerNummer-2].getNaam().toLowerCase()) == true 
                            || Objects.equals(naamSpeler.toLowerCase(), getSpelerLijst()[spelerNummer-1].getNaam().toLowerCase()) == true)
                    {
                        oke = false;
                    } 
                    break;
                case 3:
                    if(Objects.equals(naamSpeler.toLowerCase(), spelerLijst[spelerNummer-3].getNaam().toLowerCase()) == true 
                            || Objects.equals(naamSpeler.toLowerCase(), spelerLijst[spelerNummer-2].getNaam().toLowerCase()) == true 
                            || Objects.equals(naamSpeler.toLowerCase(), spelerLijst[spelerNummer-1].getNaam().toLowerCase()) == true)
                    {
                        oke = false;
                    } 
                    break;
            }
                    if (oke == false)
                    {
                        System.out.println("De naam is al genomen.");
                        break;
                    }
        }

        return oke;
    }
        
    public void aanmakenSpeler(int aantalSpelers)
    {
        Scanner invoer = new Scanner(System.in);
        
        //De kleuren van de spelers
        String[] kleurenLijst = {"rood", "blauw", "groen", "geel"};
        
        //De nummers van de spelers
        int[] nummersLijst = {1,2,3,4};
        
        //Witte lijn voor overzicht
        System.out.println();
        
        //aanmaken van het aantalSpelers
        for (int i = 0; i < aantalSpelers; i++) 
        {
            String naamSpeler;
            
            //Kies spelernaam
            do{
                System.out.printf("Geef naam van speler %d: ", i+1);
                naamSpeler = invoer.next();
                //De namen van de spelers controleren
            }while(controleerNaamSpelers(naamSpeler, i) == false);
            
            //Constructor van de speler: naam, nummer, kleur, aanBeurt, aantalStamleden, aantalHout, aantalLeem, aantalSteen, aantalGoud, aantalVoedsel
            Speler speler = new Speler(naamSpeler, nummersLijst[i], kleurenLijst[i], false, 5, 0, 0, 0, 0,12);
            
            //Speler opslaan in de lijst
            spelerLijst[i] = speler;
            
            //Mooie lijn tussen elke speler
            System.out.println();
        }
    }

    private void toonMijnStamleden() 
    {
        
        
    }

    private boolean controleerAlleStamledenGeplaatst() 
    {
        boolean oke = true;
        for(int loper=0; loper<getSpelerLijst().length;loper++)
        {
            if(getSpelerLijst()[loper].getAantalStamleden() == 0)
            {
                oke = false;
            }
            else
            {
                oke = true;
            }
        }
        return oke;
    }
}
