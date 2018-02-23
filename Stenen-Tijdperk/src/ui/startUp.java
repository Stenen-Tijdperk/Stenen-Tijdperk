package ui;

import domein.Speler;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class startUp 
{
    public static void main (String[] args)
    {
        /*- start spel
          - afhankelijk van hoeveel spelers je kiest maak ze aan
          - een methode die het aantal spelers keer de contructor van speler aanroept
          - (spelers in een arraylist opslaan*/
        
        int aantalSpelers;
        
        Scanner invoer = new Scanner(System.in);
        
        System.out.print("Geef het aantal spelers tussen 2 en 4: ");
        aantalSpelers = invoer.nextInt();
        
        do
        {
            System.out.print("Geef het aantal spelers tussen 2 en 4: ");
            aantalSpelers = invoer.nextInt();
        }while(aantalSpelers < 2 || aantalSpelers > 4);
        
        Speler[] SpelerLijst = new Speler[aantalSpelers];
        
        for (int i = 0; i < SpelerLijst.length ;  i++) 
        {
            String naamSpeler;
            System.out.println("Geef naam van uw speler: ");
            naamSpeler = invoer.next();
            Speler spelerke = new Speler(naamSpeler);
            //elk atribuut van speler een waarde geven
            spelerke.setAantalGoud(0);
            spelerke.setAantalHout(0);
            spelerke.setAantalLeem(0);
            spelerke.setAantalSteen(0);
            spelerke.setAantalVoedsel(12);
            
            SpelerLijst[i] = spelerke;
        }
        
        
    }
}
