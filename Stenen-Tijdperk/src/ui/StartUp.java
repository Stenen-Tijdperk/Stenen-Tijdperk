package ui;

import domein.Speler;
import java.security.SecureRandom;
import java.util.Scanner;

public class StartUp 
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
            
            //CONSTRUCTOR VAN SPELER: NAAM, HOUT, LEEM, STEEN, GOUD, VOEDSEL, KLEUR OP LEGE STRING ZETTEN
            Speler speler = new Speler(naamSpeler, 0, 0, 0, 0, 12, "");
            
            
            //Speler opslaan op de i-de plaats in de array
            SpelerLijst[i] = speler;
            
            //Mooie lijn tussen elke speler
            System.out.println();
        }
        
        Speler.bepaalKleurVanSpelers(SpelerLijst);
        
        //Print out dat spel is begonnen
        System.out.printf("Het spel is begonnen met %d spelers.%n", SpelerLijst.length);
        //Geef menu met keuzes
        bepaalRandomSpelerAanBeurt(SpelerLijst);
        toonMenuMetKeuze(SpelerLijst);
        
            
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

    private static void toonMenuMetKeuze(Speler[] SpelerLijst)
    {
        Scanner invoer = new Scanner(System.in);
        int nummer;
        do{
        System.out.printf("Geef een nummer voor de actie die u wilt doen: %n"
                + "1: toon spelers | 2: plaats stamleden | ...%n");
        nummer = invoer.nextInt();
        }while(nummer < 1 || nummer > 2);
        
        //Witte lijn voor overzicht
        System.out.println();
        
        switch(nummer)
        {
            case 1: toonSpelers(SpelerLijst);
            break;
            case 2: plaatsStamleden()/*+ actie dat het van speler aan beurt veranderd*/;
            break;
        }
    }
}
