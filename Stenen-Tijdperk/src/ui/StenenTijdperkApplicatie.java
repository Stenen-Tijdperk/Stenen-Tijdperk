package ui;

import domein.Gebied;
import domein.Hut;
import domein.Speler;
import java.security.SecureRandom;
import java.util.Scanner;


public class StenenTijdperkApplicatie 
{
    public static void main (String[] args)
    {
        Scanner invoer = new Scanner(System.in);
        
        //Keuze voor het aantal spelers
        //Indien foute ingave van keuze - controle
        int aantalSpelers;
        do
        {
            System.out.print("Geef het aantal spelers tussen 2 en 4: ");
            aantalSpelers = invoer.nextInt();
        }while(aantalSpelers < 2 || aantalSpelers > 4);
        
        //In deze array worden de spelers hun objecten/namen opgeslagen
        Speler[] SpelerLijst = new Speler[aantalSpelers];
        
        //Gebieden worden aangemaakt
        Gebied[] gebiedenLijst = aanmakenGebieden();
        
        //Hutten worden aangemaakt
        Hut[] huttenLijst = generateHutten();
            
        String[] kleurenLijst = {"rood", "geel", "groen", "blauw"};
        int[] nummerkesLijst = {1,2,3,4};
        
        //Witte lijn voor overzicht
        System.out.println();
        
        //aanmaken van het aantalSpelers
        for (int i = 0; i < SpelerLijst.length; i++) 
        {
            String naamSpeler;
            
            //Kies spelernaam, speler naam kan niet meer als 10 characters hebben
            do{
                //PRINT FORMAT VOOR SPELER NUMMER
            System.out.printf("Geef naam van speler %d: ", i+1);
            naamSpeler = invoer.next();
            //Fout error voor als naam te lang is
            if (naamSpeler.length() >= 10)
                System.out.printf("Naam is te lang! Probeer opnieuw!%n");
            }while(naamSpeler.length() >= 10);
            
            //CONSTRUCTOR VAN SPELER: NAAM, HOUT, LEEM, STEEN, GOUD, VOEDSEL, KLEUR
            Speler speler = new Speler(naamSpeler, 0,0,0,0,12, kleurenLijst[i], 5, nummerkesLijst[i]);
            
            //Speler opslaan in de lijst
            SpelerLijst[i] = speler;
            
            //Mooie lijn tussen elke speler
            System.out.println();
        }
                
        //Print out dat spel is begonnen
        System.out.printf("Het spel is begonnen met %d spelers.%n", SpelerLijst.length);
        //Geef menu met keuzes
        bepaalRandomSpelerAanBeurt(SpelerLijst);
        
        toonMenuMetKeuze(SpelerLijst, gebiedenLijst);
        
        System.out.println();   
    }

    private static void toonSpelers(Speler[] SpelerLijst)
    {
        //Spelers eens afprinten
        for (Speler loper : SpelerLijst) {
            //toString van klasse Speler oproepen
            System.out.print(loper.toString());
            
            System.out.println();
        }
    }
    
    private static void bepaalRandomSpelerAanBeurt(Speler[] SpelerLijst)
    {
        SecureRandom random = new SecureRandom();
        
        //Speler int nummer is aan beurt
        int nummer = random.nextInt(SpelerLijst.length);
        System.out.printf("Speler %s mag beginnen.%n", SpelerLijst[nummer].getNaamSpeler());
        
        //Beurt wordt true gezet bij random bepaalde speler
        SpelerLijst[nummer].setAanBeurt(true);
        
        System.out.println();
    }
    
    // de verschillende gebieden maken
    private static Gebied[] aanmakenGebieden()
    {
        //Lijst voor alle gebieden op te slaan, nummer 7 aan te passen adhv kaarten voor hutten
        Gebied[] gebiedenLijst = new Gebied[7];
        //Constructors vd gebieden
        Gebied hut = new Gebied("hut",2,"extra stamlid", 1);
        gebiedenLijst[0] = hut;
        
        Gebied akker = new Gebied("akker",1,"voedselproductie + 1",2);
        gebiedenLijst[1] = akker;
        
        Gebied jacht = new Gebied("jacht",40,"voedsel + x",3);
        gebiedenLijst[2] = jacht;
        
        Gebied bos = new Gebied("bos",7,"hout + x",4);
        gebiedenLijst [3] = bos;
        
        Gebied leemgroeve = new Gebied("leemgroeve",7,"leem + x",5);
        gebiedenLijst[4] = leemgroeve;
        
        Gebied steengroeve = new Gebied("steengroeve",7,"steen + x",6);
        gebiedenLijst[5] = steengroeve;
        
        Gebied rivier = new Gebied("rivier",7,"goud + x",7);
        gebiedenLijst[6] = rivier;
        
        //return van de lijst met de gebied-objecten in
        return gebiedenLijst;
    }
    
    private static void toonArrayGebieden(Gebied[] gebiedenLijst)
    {
        for (int loper=0;gebiedenLijst.length>loper;loper++)
        {
            System.out.print(gebiedenLijst[loper].toString());
        }
    }
    
    private static void toonMenuMetKeuze(Speler[] SpelerLijst, Gebied[] gebiedenLijst)
    {
        Scanner invoer = new Scanner(System.in);
        int nummer;
        do{
        System.out.printf("Geef een nummer voor de actie die u wilt uitvoeren: %n"
                + "1: Toon spelers | 2:  Toon gebieden | 3: Plaats stamleden | 4: Beurt overslaan%n");
        nummer = invoer.nextInt();
        }while(nummer < 1 || nummer > 3);
        
        //Witte lijn voor overzicht
        System.out.println();
        
        
        switch(nummer)
        {
            case 1: toonSpelers(SpelerLijst);
                    System.out.println();
                    toonMenuMetKeuze(SpelerLijst, gebiedenLijst);
            break;
            case 2: toonArrayGebieden(gebiedenLijst);
                    System.out.println();
                    toonMenuMetKeuze(SpelerLijst, gebiedenLijst);
            break;
            case 3:
                toonArrayGebieden(gebiedenLijst);
                System.out.println();
                int gebiedNummer, aantal;
                
                do{
                System.out.print("Waar wil je uw stamleden plaatsen?");
                gebiedNummer = invoer.nextInt();
                }while(gebiedNummer < 0 || gebiedNummer > 7);
                
                do{
                System.out.print("Hoeveel stamleden wil je er plaatsen?");
                aantal = invoer.nextInt();
                }while(controleerStamleden(aantal) == false);
                
                plaatsStamleden(gebiedNummer, aantal, gebiedenLijst);
            break;
            case 4:
                //nummer van speler aan beurt verhoogt
                nummerVerhogen(SpelerLijst);
                
                ;
            break;
        }
        
    }

    private static int nummerVerhogen(Speler[] SpelerLijst)
    {
        
        if(SpelerLijst[4].getNummer() == 4)
            //beurt speler 1
    }
    
    private static void plaatsStamleden(int gebiedNummer, int aantalStamleden, Gebied[] gebiedenLijst)
    {
        setAantalStamleden(getAantalStamleden() - aantalStamleden);
        gebiedenLijst[gebiedNummer].setAantalGenomenPlaatsen(aantalStamleden);
    }

    private static boolean controleerStamleden(int aantal)
    {
        //aantal stamleden dat ge hebt
        if (aantal > speler.getAantalStamleden())
        {
            
        }
        //aantal stamleden reeds op plek
        
        //max aantal stamleden op die plek mogelijk
    }

    public void start() throws InterruptedException
    {
        
    }
    
    public static Hut[] generateHutten()
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
}

