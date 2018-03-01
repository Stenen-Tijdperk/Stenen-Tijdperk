package ui;

import domein.DomeinController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StenenTijdperkApplicatie 
{
    public static void main (String[] args)
    {
        //Object van de scanner aanmaken
        Scanner invoer = new Scanner(System.in);
        
        int aantalSpelers = 0;
        
        //Aantal spelers, indien fout aantal gegeven, correcte error message
        do {
            //Try and catch zodat het programma niet crasht bij foute ingave
            try 
            {
                System.out.print("Geef het aantal spelers tussen 2 en 4: ");
                aantalSpelers = invoer.nextInt();
                invoer.nextLine();
            } 
            catch (InputMismatchException keuzeAantalSpelers) 
            {
                invoer.nextLine();
                System.out.println("Voer een getal in!");
            }
            //Minstens 2 spelers, maximum 4 spelers
        } while (aantalSpelers < 2 || aantalSpelers > 4);

        spelerLijstMaken(aantalSpelers);
                
        //Gebieden worden aangemaakt
        Gebied[] gebiedLijst = aanmakenGebieden();
        
        
        //Hutten worden aangemaakt
        Hut[] huttenLijst = aanmakenHutten();
        
        //De kleuren van de spelers
        String[] kleurenLijst = {"rood", "blauw", "groen", "geel"};
        
        //De nummers van de spelers
        int[] nummersLijst = {1,2,3,4};
        
        //Witte lijn voor overzicht
        System.out.println();
        
        //aanmaken van het aantalSpelers
        for (int i = 0; i < spelerLijst.length; i++) 
        {
            String naamSpeler;
            
            //Kies spelernaam
            do{
                System.out.printf("Geef naam van speler %d: ", i+1);
                naamSpeler = invoer.next();
                //De namen van de spelers controleren
            }while(controleerNaamSpelers(spelerLijst, naamSpeler, i) == false);
            
    
            //Constructor van de speler: naam, nummer, kleur, aanBeurt, aantalStamleden, aantalHout, aantalLeem, aantalSteen, aantalGoud, aantalVoedsel
            Speler speler = new Speler(naamSpeler, nummersLijst[i], kleurenLijst[i], false, 5, 0, 0, 0, 0,12);
            
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

   
        
    public void start() throws InterruptedException
    {
        
    } 
}