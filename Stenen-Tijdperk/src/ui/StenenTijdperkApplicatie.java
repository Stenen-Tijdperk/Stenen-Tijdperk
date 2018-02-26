package ui;

import domein.Gebied;
import domein.Hut;
import domein.Speler;

import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StenenTijdperkApplicatie 
{
    public static void main (String[] args)
    {
        Scanner invoer = new Scanner(System.in);
        
        //Keuze voor het aantal spelers
        //Indien foute ingave van keuze - controle
        int aantalSpelers = 0;
        
        do {
            try 
            {
                System.out.print("Geef het aantal spelers tussen 2 en 4: ");
                aantalSpelers = invoer.nextInt();
                invoer.nextLine();
            } 
            catch (InputMismatchException begin) 
            {
                invoer.nextLine();
                System.out.println("Voer een getal in!");
            }
        } while (aantalSpelers < 2 || aantalSpelers > 4);


        //In deze array worden de spelers hun objecten/namen opgeslagen
        Speler[] spelerLijst = new Speler[aantalSpelers];
        
        
        
        //Gebieden worden aangemaakt
        Gebied[] gebiedLijst = aanmakenGebieden();
        
        //Hutten worden aangemaakt
        Hut[] huttenLijst = aanmakenHutten();
            
        String[] kleurenLijst = {"rood", "geel", "groen", "blauw"};
        int[] nummersLijst = {1,2,3,4};
        
        //Witte lijn voor overzicht
        System.out.println();
        
        //aanmaken van het aantalSpelers
        for (int i = 0; i < spelerLijst.length; i++) 
        {
            String naamSpeler;
            
            //Kies spelernaam, speler naam kan niet meer als 10 characters hebben
            do{
                //PRINT FORMAT VOOR SPELER NUMMER
                System.out.printf("Geef naam van speler %d: ", i+1);
                naamSpeler = invoer.next();
                //Fout error voor als naam te lang is
                if (naamSpeler.length() >= 10)
                {
                    System.out.printf("Naam is te lang! Probeer opnieuw!%n");
                }
            }while(naamSpeler.length() >= 10);
            
            //CONSTRUCTOR VAN SPELER: NAAM, HOUT, LEEM, STEEN, GOUD, VOEDSEL, KLEUR
            Speler speler = new Speler(naamSpeler, 0,0,0,0,12, kleurenLijst[i], 5, nummersLijst[i], false);
            
            //Speler opslaan in de lijst
            spelerLijst[i] = speler;
            
            //Mooie lijn tussen elke speler
            System.out.println();
        }
                
        //Print out dat spel is begonnen
        System.out.printf("Het spel is begonnen met %d spelers.%n", spelerLijst.length);
        // bepalen van speler die begint
        bepaalRandomSpelerAanBeurt(spelerLijst);
        //Geef menu met keuzes
        toonMenuMetKeuze(spelerLijst, gebiedLijst);
        
        System.out.println();   
    }

    private static void toonSpelers(Speler[] spelerLijst)
    {
        //Spelers eens afprinten
        for (Speler loper : spelerLijst) 
        {
            //toString van klasse Speler oproepen
            System.out.print(loper.toString());
            
            System.out.println();
        }
    }
    
    private static void bepaalRandomSpelerAanBeurt(Speler[] spelerLijst)
    {
        SecureRandom random = new SecureRandom();
        
        //Speler int nummer is aan beurt
        int nummer = random.nextInt(spelerLijst.length);
        System.out.printf("Speler %s mag beginnen.%n", spelerLijst[nummer].getNaamSpeler());
        
        //Beurt wordt true gezet bij random bepaalde speler
        spelerLijst[nummer].setAanBeurt(true);
        
        System.out.println();
    }
    
    // de verschillende gebieden maken
    private static Gebied[] aanmakenGebieden()
    {
        //Lijst voor alle gebieden op te slaan, nummer 7 aan te passen adhv kaarten voor hutten
        Gebied[] gebiedLijst = new Gebied[8];
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
        
        //return van de lijst met de gebied-objecten in
        return gebiedLijst;
    }
    
    private static void toonGebieden(Gebied[] gebiedLijst)
    {
        for (int loper=0;gebiedLijst.length>loper;loper++)
        {
            System.out.print(gebiedLijst[loper].toString());
        }
    }
    
    private static void toonMenuMetKeuze(Speler[] spelerLijst, Gebied[] gebiedLijst)
    {
        Scanner invoer = new Scanner(System.in);
        int nummer=0;
        
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
        }while(nummer < 1 || nummer > 4);
        
        //Witte lijn voor overzicht
        System.out.println();
        
        switch(nummer)
        {
            case 1: toonSpelers(spelerLijst);
                    System.out.println();
                    toonMenuMetKeuze(spelerLijst, gebiedLijst);
            break;
            case 2: toonGebieden(gebiedLijst);
                    System.out.println();
                    toonMenuMetKeuze(spelerLijst, gebiedLijst);
            break;
            case 3:
                toonGebieden(gebiedLijst);
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
                    invoer.nextLine();
                    System.out.println("Voer een getal in!");
                    }
                }while(gebiedNummer < 0 || gebiedNummer > 7);
                
                do{
                    try
                    {
                    System.out.print("Hoeveel stamleden wil je er plaatsen? ");
                    aantal = invoer.nextInt();
                    invoer.nextLine();
                    }
                    catch(InputMismatchException aantalStamleden)
                    {
                    invoer.nextLine();
                    System.out.println("Voer een getal in!");
                    }
                }while(controleerStamleden(aantal, spelerLijst, gebiedLijst, gebiedNummer) == false);
                
               
                // zoveel stamleden op die plek plaatsen
                System.out.printf("%nEr %s %d %s geplaatst op %s%n%n",aantal>1?"werden":"werd", aantal, aantal>1?"stamleden":"stamlid", gebiedLijst[gebiedNummer-1].getNaamGebied());
                
                plaatsStamleden(gebiedNummer, aantal, gebiedLijst, spelerLijst);
                beurtOverslaan(spelerLijst);
                toonMenuMetKeuze(spelerLijst, gebiedLijst);
            break;
            case 4:
                beurtOverslaan(spelerLijst);
                toonMenuMetKeuze(spelerLijst, gebiedLijst);
            break;
        }
    }
    
    private static void plaatsStamleden(int gebiedNummer, int aantalStamleden, Gebied[] gebiedLijst, Speler[] spelerLijst)
    {   
        int spelerNummer = 0; 
        boolean oke = true; 
        for (Speler spelerLijst1 : spelerLijst) 
        {
            if (spelerLijst1.getAanBeurt()) 
            {
                spelerNummer = spelerLijst1.getNummer();
            }  
        }
        try{
        gebiedLijst[gebiedNummer-1].setAantalGenomenPlaatsen(gebiedLijst[gebiedNummer-1].getAantalGenomenPlaatsen()+aantalStamleden);
        spelerLijst[spelerNummer-1].setAantalStamleden(spelerLijst[spelerNummer-1].getAantalStamleden()-aantalStamleden);
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println("Geef een geldig aantal!");
        }
    }

    private static boolean controleerStamleden(int aantal, Speler[] spelerLijst, Gebied[] gebiedLijst, int gebiedNummer)
    {
        //aantal stamleden dat ge hebt
        int spelerNummer = 0; 
        boolean oke = true; 
        for (Speler spelerLijst1 : spelerLijst) 
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
        
        if(aantal > spelerLijst[spelerNummer-1].getAantalStamleden() || aantal < 0)
        {
            oke = false;
            System.out.println("Je hebt niet zoveel stamleden.");
        }
        
        //aantal stamleden reeds op plek
        if(gebiedLijst[gebiedNummer-1].getAantalMaxLeden() - gebiedLijst[gebiedNummer-1].getAantalGenomenPlaatsen() < aantal)
        {           
            oke = false;
            System.out.println("Je gaf een te hoog aantal stamleden in voor dit gebied.");
        }
        return oke;
    }

    private static void beurtOverslaan(Speler[] spelerLijst)
    {
        int nummer=1;
        
        for (Speler spelerLijst1 : spelerLijst) 
        {
            if (spelerLijst1.getAanBeurt()) 
            {
                nummer = spelerLijst1.getNummer();
                spelerLijst1.setAanBeurt(false);
            }  
        }
        
        if (nummer == spelerLijst.length)
        {
            nummer = 1;
        }
        else
        {
            nummer += 1;
        }
        spelerLijst[nummer-1].setAanBeurt(true);
    }

    public static Hut[] aanmakenHutten()
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
        return hutLijst;
    }
        
    public void start() throws InterruptedException
    {
        
    } 
}