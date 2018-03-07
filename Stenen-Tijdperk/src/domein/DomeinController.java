package domein;

import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class DomeinController
{
    private Speler[] spelerLijst;
    private Gebied[] gebiedLijst;
    private Hut[] hutLijst;
    //Hierin worden de [spelers] en [gebieden] opgeslagen, de int geef het aantal stamleden weer
    private int[][] stamledenLocatieLijst;
    
    private Speler[] getSpelerLijst()
    {
        return spelerLijst;
    }
    
    private Gebied[] getGebiedLijst()
    {
        return gebiedLijst;
    }
    
    private Hut[] getHutLijst()
    {
        return hutLijst;
    }
    
    private int[][] getStamledenLocatieLijst()
    {
        return stamledenLocatieLijst;
    }

    private void aanmakenSpelers()
    {
        //Voor het aantal spelers
        int aantal = 0;
        
        //Object van de scanner aanmaken
        Scanner invoer = new Scanner(System.in);
        
        //Aantal spelers, indien fout aantal gegeven, correcte error message
        do {
            //Try and catch zodat het programma niet crasht bij foute ingave
            try 
            {
                System.out.print("Geef het aantal spelers tussen 2 en 4: ");
                aantal = invoer.nextInt();
                invoer.nextLine();
            } 
            catch (InputMismatchException keuzeAantalSpelers) 
            {
                System.out.println("Voer een getal in.");
                invoer.nextLine();
            }
          //Minstens 2 spelers, maximum 4 spelers
        } while (aantal < 2 || aantal > 4);
        
        //In deze array worden de spelers hun objecten/namen opgeslagen
        this.spelerLijst = new Speler[aantal];
        
        //De kleuren van de spelers
        String[] kleurenLijst = {"rood", "blauw", "groen", "geel"};
        
        //De nummers van de spelers
        int[] nummersLijst = {1,2,3,4};
        
        //Witte lijn voor overzicht
        System.out.println();
        
        //Aanmaken van het de spelers
        for (int spelerLoper = 0; spelerLoper < aantal; spelerLoper++) 
        {
            String naamSpeler;
            
            //Kies spelernaam
            do{
                System.out.printf("Geef naam van speler %d: ", spelerLoper+1);
                naamSpeler = invoer.next();
                //De namen van de spelers controleren
            }while(controleerNaamSpelers(naamSpeler, spelerLoper) == false);
            
            //Constructor van de speler: naam, nummer, kleur
            Speler speler = new Speler(naamSpeler, nummersLijst[spelerLoper], kleurenLijst[spelerLoper]);
            
            //Speler opslaan in de lijst
            spelerLijst[spelerLoper] = speler;
            
            //Mooie lijn tussen elke speler
            System.out.println();
        }
                
        //Print out dat spel is begonnen
        System.out.printf("Het spel is begonnen met %d spelers.%n", aantal);
    }
    
    private boolean controleerNaamSpelers(String naamSpeler, int spelerIndex)
    {
        boolean oke = true;
        //Naam mag niet langer als 10 characters zijn
        if (naamSpeler.length() >= 10)
        {
            oke = false;
            System.out.println("De naam is te lang. Maximum 10 characters.");
        }
        
        //Naam mag niet 2 keer dezelfde zijn.
        switch(spelerIndex)
        {
            case 0: 
                break;
            case 1:
                if(Objects.equals(naamSpeler.toLowerCase(), getSpelerLijst()[spelerIndex-1].getNaam().toLowerCase()))
                {
                    oke = false;
                }
                break;
            case 2:
                if(Objects.equals(naamSpeler.toLowerCase(), getSpelerLijst()[spelerIndex-2].getNaam().toLowerCase()) 
                    || Objects.equals(naamSpeler.toLowerCase(), getSpelerLijst()[spelerIndex-1].getNaam().toLowerCase()))
                {
                    oke = false;
                } 
                break;
            case 3:
                if(Objects.equals(naamSpeler.toLowerCase(), spelerLijst[spelerIndex-3].getNaam().toLowerCase()) 
                    || Objects.equals(naamSpeler.toLowerCase(), spelerLijst[spelerIndex-2].getNaam().toLowerCase()) 
                    || Objects.equals(naamSpeler.toLowerCase(), spelerLijst[spelerIndex-1].getNaam().toLowerCase()))
                {
                    oke = false;
                } 
                break;
            }
            if (oke == false)
            {
                System.out.println("De naam is al genomen.");
            }
        return oke;
    }
    
    private void aanmakenGebieden()
    {   
        //Lijst voor alle gebieden op te slaan
        this.gebiedLijst = new Gebied[9];
        
        //Constructors van gebieden: Gebied(gebiedNaam, max aantal stamleden, functie, gebiedNummer)
        Gebied hut = new Gebied("hut",2,"stamlid + 1", 1);
        gebiedLijst[0] = hut;
        
        Gebied akker = new Gebied("akker",1,"voedselproductie + 1",2);
        gebiedLijst[1] = akker;
        
        Gebied gereedschapmaker = new Gebied("gereedschapmaker",1,"+ gereedschap + 1",3);
        gebiedLijst[2] = gereedschapmaker;
        
        Gebied jacht = new Gebied("jacht",40,"voedsel + x",4);
        gebiedLijst[3] = jacht;
        
        Gebied bos = new Gebied("bos",7,"hout + x",5);
        gebiedLijst[4] = bos;
        
        Gebied leemgroeve = new Gebied("leemgroeve",7,"leem + x",6);
        gebiedLijst[5] = leemgroeve;
        
        Gebied steengroeve = new Gebied("steengroeve",7,"steen + x",7);
        gebiedLijst[6] = steengroeve;
        
        Gebied rivier = new Gebied("rivier",7,"goud + x",8);
        gebiedLijst[7] = rivier;
        
        Gebied hutKopen = new Gebied("huisjes markt",1,"koop een huisje",9);
        gebiedLijst[8] = hutKopen;
    }
    
    private void aanmakenHutten()
    {
        //Hutlijst wordt aangemaakt, alle hutten zijn null
        this.hutLijst = new Hut[28];
        
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
    
    private void aanmakenLocatieLijst()
    {
         stamledenLocatieLijst =new int[getSpelerLijst().length][9];
    }

    public void preLoadSpel()
    {
        aanmakenHutten();
        aanmakenGebieden();
        aanmakenSpelers();
        aanmakenLocatieLijst();
    }
    
    private void toonSpelers()
    {
        //Spelers eens afprinten
        for (Speler speler : getSpelerLijst()) 
        {
            //toString van klasse Speler oproepen
            System.out.print(speler.toString());
            
            System.out.println();
        }
    }
    
    private void toonGebieden()
    {
        for (Gebied gebied : getGebiedLijst()) {
            System.out.print(gebied.toString());
            System.out.println();
        }
    }
    
    private void toonHutten()
    {
        for (Hut hut : getHutLijst()) {
            System.out.print(hut.toString());
            System.out.println();
        }
    }
    
    private void toonMijnGereedschap()
    {
        int spelerIndex= spelerNummerOphalen()-1,
                lengteVanGereedschapskistje = getSpelerLijst()[spelerIndex].getGereedschapskistje().length;
        
        for(int gereedschap=0;gereedschap<lengteVanGereedschapskistje;gereedschap++)
        {
            System.out.printf("Gereedschap %d | Waarde: %d | Reeds gebruikt deze ronde: %s%n",
                    spelerLijst[spelerIndex].getGereedschapskistje()[gereedschap].getNummer(),
                    spelerLijst[spelerIndex].getGereedschapskistje()[gereedschap].getWaarde(),
                    spelerLijst[spelerIndex].getGereedschapskistje()[gereedschap].getReedsGebruiktDezeRonde()?"ja":"nee"
                    );
        }
        //Witte lijn voor overzicht
        System.out.println();
    }
    
    public void bepaalRandomSpelerAanBeurt()
    {
        SecureRandom random = new SecureRandom();
        
        //Speler int nummer is aan beurt
        int nummer = random.nextInt(getSpelerLijst().length);
        System.out.printf("Speler %s mag beginnen.%n", nummer+1);
        
        //Beurt wordt true gezet bij random bepaalde speler
        getSpelerLijst()[nummer].setAanBeurt(true);
        
        //Mooie lijn voor overzicht
        System.out.println();
    }

    public void deelRonde1Spelen()
    {  
            toonMenuMetKeuze();
    }

    private void overgangVolgendeDeelronde()
    {
            int spelerIndex = spelerNummerOphalen()-1, spelerNummer = spelerNummerOphalen();
            
            System.out.println("De deelronde voor het plaatsen van de stamleden is afgelopen.");
            //Speluitslag afprinten na deelronde van het paatsen
            toonSpelers();
            System.out.println();
            toonGebieden();
            
            //Volgende speler krijgt de beurt
             getSpelerLijst()[spelerIndex].setAanBeurt(false); 
        
            if (spelerNummer == getSpelerLijst().length)
            {
                spelerNummer = 1;
                spelerIndex = 0;
            }
            else
            {
                spelerNummer++;
                spelerIndex++;
            }
        
            //Beurt van volgende speler op true zetten
            getSpelerLijst()[spelerNummer-1].setAanBeurt(true);
            
            //Overgaan naar volgende deelronde
            deelRonde2Spelen();
    }
    
    private void deelRonde2Spelen()
    {  
        //Hierin komt grootste deel van Iteratie 2
    }
    
    public void toonMenuMetKeuze()
    {
        Scanner invoer = new Scanner(System.in);
        int nummer = 0, gebiedNummer = 0, aantal = 0;
        
        do{
            try
            {
                System.out.printf("Geef een nummer voor de actie die u wilt uitvoeren: %n"
                + "1: Toon spelers | 2:  Toon gebieden | 3: Plaats stamleden | 4: Beurt overslaan | 5: Toon mijn stamleden | 6: Toon mijn gereedschap%n");
                nummer = invoer.nextInt();
                invoer.nextLine();
            }
            catch (InputMismatchException begin) 
            {
                invoer.nextLine();
                System.out.println("Voer een getal in.");
            }
        }while(nummer < 1 || nummer > 6);

        //Witte lijn voor overzicht
        System.out.println();

        switch(nummer)
        {
            case 1:
                toonSpelers();
                System.out.println();
                deelRonde1Spelen();
            break;
            case 2:
                toonGebieden();
                System.out.println();
                deelRonde1Spelen();
            break;
            case 3:
                toonGebieden();
                System.out.println();

                do{
                    try
                    {
                    System.out.print("Waar wil je uw stamleden plaatsen? ");
                    gebiedNummer = invoer.nextInt();
                    invoer.nextLine();
                    }
                    catch(InputMismatchException plaatsStamleden)
                    {
                    System.out.println("Voer een getal in.");
                    invoer.nextLine();
                    }
                }while(gebiedNummer <= 0 || gebiedNummer > 9);

                do{
                    try
                    {
                    System.out.print("Hoeveel stamleden wil je er plaatsen? ");
                    aantal = invoer.nextInt();
                    invoer.nextLine();
                    }
                    catch(InputMismatchException aantalStamleden)
                    {
                    System.out.println("Voer een getal in.");
                    invoer.nextLine();
                    }
                }while(controleerStamleden(aantal, gebiedNummer) == false);
                
                if(aantal <= 0)
                {
                    System.out.printf("Je hebt geen stamleden geplaats, je blijft aan de beurt.%n");
                    toonMenuMetKeuze();
                }

                plaatsStamleden(gebiedNummer, aantal);
                
                if(aantal != 0)
                {
                    beurtOverslaan();
                }
                
                if(controleerAlleStamledenGeplaatst())
                {
                    overgangVolgendeDeelronde();
                }
                
            break;
            case 4:
                beurtOverslaan();
                deelRonde1Spelen();
            break;
            case 5:
                toonMijnStamleden();
                deelRonde1Spelen();
            break;
            case 6:
                toonMijnGereedschap();
                deelRonde1Spelen();
             break;
        }
    }

    private void beurtOverslaan()
    {
        int spelerIndex=spelerNummerOphalen()-1, spelerNummer = spelerNummerOphalen();
        
        //Beurt van speler aan beurt veranderen
        getSpelerLijst()[spelerIndex].setAanBeurt(false); 
        
        //Indien laatste speler aan de beurt is, krijgt eerste speler de beurt
        //Zo niet dan krijgt de volgende speler de beurt
        if (spelerNummer == getSpelerLijst().length)
        {
            spelerNummer = 1;
            spelerIndex = 0;
        }
        else
        {
            spelerNummer++;
            spelerIndex++;
        }
        
        //Beurt van volgende speler op true zetten
        getSpelerLijst()[spelerNummer-1].setAanBeurt(true);
        
        //Controleren of het spel gedaan is
        if(controleerAlleStamledenGeplaatst())
        {
            overgangVolgendeDeelronde();
        }
        else
        {
            //Volgende speler heeft geen stamleden? Beurt overslaan
            if (getSpelerLijst()[spelerIndex].getAantalStamleden() == 0)
            {
                beurtOverslaan();
            }
                deelRonde1Spelen();
        }
    }
    
    private void plaatsStamleden(int gebiedNummer, int aantalStamleden)
    {   
        if(aantalStamleden > 0)
        {
            System.out.printf("%nEr %s %d %s geplaatst op het gebied: %s.%n%n",
                    aantalStamleden>1?"werden":"werd",
                    aantalStamleden,
                    aantalStamleden>1?"stamleden":"stamlid",
                    gebiedLijst[gebiedNummer-1].getNaamGebied());
        }
        
        int spelerIndex = spelerNummerOphalen()-1; 
        boolean oke = true;
        
        try{
            //Het aantal genomen plaatsen wordt aangepast
        getGebiedLijst()[gebiedNummer-1].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedNummer-1].getAantalGenomenPlaatsen()+aantalStamleden);
            //Het aantal stamleden van de speler wordt verminderd
        getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()-aantalStamleden);
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println("Geef een geldig aantal.");
        }
        
        //Het aantal geplaatste stamleden wordt opgeslagen op 
        stamledenLocatieLijst[spelerIndex][gebiedNummer-1] = aantalStamleden;
        
    }

    private void toonMijnStamleden() 
    {
        int spelerIndex = spelerNummerOphalen()-1; 
        String spelerNaam = "";
        
        try
        {
            for (int loper = 0; loper < getStamledenLocatieLijst()[spelerIndex].length; loper++) 
                {
                System.out.printf("Speler %s heeft %d %s op %s.%n",
                        getSpelerLijst()[spelerIndex].getNaam(), getStamledenLocatieLijst()[spelerIndex][loper],
                        getStamledenLocatieLijst()[spelerIndex][loper]==1?"stamlid":"stamleden",
                        getGebiedLijst()[loper].getNaamGebied());
                }
        }
        catch(ArrayIndexOutOfBoundsException tonen)
        {
            System.out.println("Geef een geldig aantal.");
        }
        
        //Witte lijn voor overzicht
         System.out.println("");
        
    }

    private boolean controleerAlleStamledenGeplaatst() 
    { 
        //True betekent dat het spel stopt omdat er geen stamleden meer zijn
        boolean stop = false;
        int aantalSpelers = getSpelerLijst().length;
        
            switch(aantalSpelers)
            {
                case 2:
                if (getSpelerLijst()[aantalSpelers-1].getAantalStamleden() == 0
                       && getSpelerLijst()[aantalSpelers-2].getAantalStamleden() == 0)
                {
                    stop = true;
                }
                    break;
                case 3:
                if (getSpelerLijst()[aantalSpelers-1].getAantalStamleden() == 0
                       && getSpelerLijst()[aantalSpelers-2].getAantalStamleden() == 0
                       && getSpelerLijst()[aantalSpelers-3].getAantalStamleden() == 0)
                {
                    stop = true;
                }
                    break;
                case 4:
                if (getSpelerLijst()[aantalSpelers-1].getAantalStamleden() == 0
                       && getSpelerLijst()[aantalSpelers-2].getAantalStamleden() == 0
                       && getSpelerLijst()[aantalSpelers-3].getAantalStamleden() == 0
                       && getSpelerLijst()[aantalSpelers-4].getAantalStamleden() == 0)
                    break;
            }
        
        return stop;
    }

    private boolean controleerStamleden(int aantal, int gebiedNummer)
    {
        int spelerIndex = spelerNummerOphalen()-1, gebiedIndex = gebiedNummer -1; 
        boolean oke = true; 
        
        //Op de hut moet je twee stamleden zetten, niet meer, niet minder
        if (gebiedNummer == 1 && aantal !=2)
        {
            oke = false;
            System.out.println("Op de hut moet je twee stamleden zetten.");
        }
        
        //Je mag niet meer stamleden plaatsen dan je hebt
        if(getSpelerLijst()[spelerIndex].getAantalStamleden() < aantal)
        {
            oke = false;
            System.out.println("Je hebt niet zoveel stamleden.");
        }
        
        //Je kan niet onder nul gaan
        if(aantal < 0)
        {
            oke = false;
            System.out.println("Je kan geen negatief aantal stamleden plaatsen");
        }
        
        //Je kan het max aantal van het gebied niet overschrijden
        if(getGebiedLijst()[gebiedIndex].getAantalMaxLeden() - getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() < aantal)
        {           
            oke = false;
            System.out.println("Je gaf een te hoog aantal stamleden in voor dit gebied.");
        }
        
        //Je kan niet plaatsen op een gebied waar je eerder al hebt geplaatst
        if (stamledenLocatieLijst[spelerIndex][gebiedIndex] != 0)
        {
            oke = false;
            System.out.println("Je hebt in een vorige ronde al op dat gebied stamleden geplaatst.");
            System.out.println();
            toonMenuMetKeuze();
        }
        
        //Je hebt slechts 1 ventje maar wilt op gebied hut gaan
        if (gebiedNummer == 1 && getSpelerLijst()[spelerIndex].getAantalStamleden() == 1)
        {
            oke = false;
            System.out.printf("Je hebt minder als twee stamleden.%n%n");
            toonMenuMetKeuze();
        }
        return oke;
    }
    
    private int spelerNummerOphalen()
    {
        int spelerNummer=0;
        
        //Spelernummer ophalen
        for (Speler speler : getSpelerLijst()) 
        {
            if (speler.getAanBeurt()) 
            {
                spelerNummer = speler.getNummer();
            }  
        }  
        
        return spelerNummer;
    }
}