package ui;

import domein.Gebied;
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
            
        String[] kleurenLijst = {"rood", "geel", "groen", "blauw"};
        
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
            Speler speler = new Speler(naamSpeler, 0,0,0,0,12, kleurenLijst[i]);
            
            //Speler opslaan in de lijst
            SpelerLijst[i] = speler;
            
            //Mooie lijn tussen elke speler
            System.out.println();
        }
        
        
        //Print out dat spel is begonnen
        System.out.printf("Het spel is begonnen met %d spelers.%n", SpelerLijst.length);
        //Geef menu met keuzes
        bepaalRandomSpelerAanBeurt(SpelerLijst);
        
        Gebied[] gebiedenLijst = aanmakenGebieden();
        
        toonMenuMetKeuze(SpelerLijst, gebiedenLijst);
        
        System.out.println();
        
            
    }

    private static void toonSpelers(Speler[] SpelerLijst)
    {
        //Spelers eens afprinten
        for (Speler loper : SpelerLijst) {
            //toString van klasse Speler oproepen
            System.out.print(loper.toString());
            //mooi lijntje
            for (int j = 0; j < 129; j++)
            {
                System.out.print("_");
            }
            System.out.println();
        }
    }
    
    private static void bepaalRandomSpelerAanBeurt(Speler[] SpelerLijst)
    {
        SecureRandom random = new SecureRandom();
        int nummer = random.nextInt(SpelerLijst.length);
        System.out.printf("Speler %s mag beginnen.%n", SpelerLijst[nummer].getNaamSpeler());
        System.out.println();
    }
    
    // de verschillende gebieden maken
    private static Gebied[] aanmakenGebieden()
    {
        //Lijst voor alle gebieden op te slaan, nummer 7 aan te passen adhv kaarten voor hutten
        Gebied[] gebiedenLijst = new Gebied[7];
        //Constructors vd gebieden
        Gebied hut = new Gebied("hut",2,"extra stamlid");
        gebiedenLijst[0] = hut;
        
        Gebied akker = new Gebied("akker",1,"voedselproductie + 1");
        gebiedenLijst[1] = akker;
        
        Gebied jacht = new Gebied("jacht",40,"voedsel + x");
        gebiedenLijst[2] = jacht;
        
        Gebied bos = new Gebied("bos",7,"hout + x");
        gebiedenLijst [3] = bos;
        
        Gebied leemgroeve = new Gebied("leemgroeve",7,"leem + x");
        gebiedenLijst[4] = leemgroeve;
        
        Gebied steengroeve = new Gebied("steengroeve",7,"steen + x");
        gebiedenLijst[5] = steengroeve;
        
        Gebied rivier = new Gebied("rivier",7,"goud + x");
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
                + "1: Toon spelers | 2:  Toon gebieden | 3: Plaats stamleden ...%n");
        nummer = invoer.nextInt();
        }while(nummer < 1 || nummer > 3);
        
        //Witte lijn voor overzicht
        System.out.println();
        
        
        switch(nummer)
        {
            case 1: toonSpelers(SpelerLijst);
            break;
            case 2: toonArrayGebieden(gebiedenLijst);
            break;
            case 3:
                toonArrayGebieden(gebiedenLijst);
                System.out.println();
                System.out.print("Waar wil je uw stamleden plaatsen?");
                
                
                System.out.print("Hoeveel stamleden wil je er plaatsen?");
                int aantal = invoer.nextInt();
                plaatsStamleden(plaats, aantal);
            break;
        }
        
    }

    private static void plaatsStamleden(Gebied gebied, int aantalStamleden)
    {
        
    
    }

    public void start() throws InterruptedException
    {
        
    }
}

