package ui;

import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StenenTijdperkApplicatie 
{
    public void start() throws InterruptedException
    {
        //Ojbect van de domeincontroller voor methodes te kunnen oproepen
        DomeinController dom = new DomeinController();
        
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
                System.out.println("Voer een getal in!");
                invoer.nextLine();
            }
            //Minstens 2 spelers, maximum 4 spelers
        } while (aantalSpelers < 2 || aantalSpelers > 4);

        //In deze array worden de spelers hun objecten/namen opgeslagen
        dom.spelerLijstMaken(aantalSpelers);
                
        //Gebieden worden aangemaakt
        dom.aanmakenGebieden();
        
        //Hutten worden aangemaakt
        dom.aanmakenHutten();
        
        //Spelers worden aangemaakt
        dom.aanmakenSpeler(aantalSpelers);
                
        //Print out dat spel is begonnen
        System.out.printf("Het spel is begonnen met %d spelers.%n", aantalSpelers);
        
        // bepalen van speler die begint
        dom.bepaalRandomSpelerAanBeurt();
        
        //Geef menu met keuzes
        dom.toonMenuMetKeuze();
        
        //Witte lijn voor overzicht
        System.out.println();   
    }
}