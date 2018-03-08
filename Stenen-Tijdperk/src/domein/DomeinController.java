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
        this.gebiedLijst = new Gebied[12];
        
        //Constructors van gebieden: Gebied(gebiedNaam, max aantal stamleden, functie, gebiedNummer)
        Gebied hut = new Gebied("hut",2,"extra stamlid", 1);
        gebiedLijst[0] = hut;
        
        Gebied akker = new Gebied("akker",1,"voedselproductie +1",2);
        gebiedLijst[1] = akker;
        
        Gebied gereedschapmaker = new Gebied("gereedschapmaker",1,"gereedschap + 1",3);
        gebiedLijst[2] = gereedschapmaker;
        
        Gebied jacht = new Gebied("jacht",40,"gedobbeld voedsel /2",4);
        gebiedLijst[3] = jacht;
        
        Gebied bos = new Gebied("bos",7,"gedobbeld hout /3",5);
        gebiedLijst[4] = bos;
        
        Gebied leemgroeve = new Gebied("leemgroeve",7,"gedobbeld leem /4",6);
        gebiedLijst[5] = leemgroeve;
        
        Gebied steengroeve = new Gebied("steengroeve",7,"gedobbeld steen /5",7);
        gebiedLijst[6] = steengroeve;
        
        Gebied rivier = new Gebied("rivier",7,"gedobbeld goud /6",8);
        gebiedLijst[7] = rivier;
        
        Gebied hutKopen1 = new Gebied("koop hut 1",1,"koop hut 1",9);
        gebiedLijst[8] = hutKopen1;
        
        Gebied hutKopen2 = new Gebied("koop hut 2",1,"koop hut 2",10);
        gebiedLijst[9] = hutKopen2;
        
        Gebied hutKopen3 = new Gebied("koop hut 3",1,"koop hut 3",11);
        gebiedLijst[10] = hutKopen3;
        
        Gebied hutKopen4 = new Gebied("koop hut 4",1,"koop hut 4",12);
        gebiedLijst[11] = hutKopen4;
    }
    
    private void aanmakenHutten()
    {
        SecureRandom random = new SecureRandom();
        //Hutlijst wordt aangemaakt, alle hutten zijn null
        this.hutLijst = new Hut[28];
        //Alle waardes worden geïnitialiseerd
        int hout = 0, leem = 0, steen = 0, goud = 0, getal = 0;
        //Alle hutten hun kostprijs word random generereert
        for (int aantalHutten=0;aantalHutten<getHutLijst().length;aantalHutten++)
        {
            for(int loper=0;loper<3;loper++)
            { 
                    getal = random.nextInt(4) +1; //Zonder +1 zou 0 kunnen voorkomen
                switch(getal)
                {
                    case 1: hout++;
                    break;
                    case 2: leem++;
                    break;
                    case 3: steen++;
                    break;
                    case 4: goud++;
                    break;
                }
            }
            //Hut wordt aangemaakt
            Hut hut = new Hut(hout, leem, steen, goud, aantalHutten+1);
            //Hut wordt opgeslagen
            hutLijst[aantalHutten] = hut;
            //Kostwaarde resetten voor volgende hut
            hout = 0; leem = 0; steen = 0; goud = 0; getal = 0;
        }
    }
    
    private void aanmakenLocatieLijst()
    {
         stamledenLocatieLijst =new int[getSpelerLijst().length][12];
    }

    public void preLoadSpel()
    {
        aanmakenHutten();
        aanmakenGebieden();
        aanmakenSpelers();
        aanmakenLocatieLijst();
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
    
    private void toonSpelers()
    {
        //Spelers eens afprinten
        for (Speler speler : getSpelerLijst()) 
        {
            //toString van klasse Speler oproepen
            System.out.print(speler.toString());
            //Witte lijn tussen elke speler voor overzicht
            System.out.println();
        }
    }
    
    private void toonGebieden()
    {
        Scanner invoer = new Scanner(System.in);
        String antwoord;
        //Print de toString uit van alle gebieden
        for (Gebied gebied : getGebiedLijst())
        {
            System.out.print(gebied.toString());
            //Witte lijn tussen elk gebied voor overzicht
            System.out.println();
        }
        //Huisjesmarkt bekijken (= gebied 8 t.e.m. 12)
        do{
        System.out.println("Wil je de huisjesmarkt zien?");
        antwoord = invoer.nextLine();
        }while((!antwoord.toLowerCase().equals("ja")) && (!antwoord.toLowerCase().equals("nee")));
        //Witte lijn voor overzicht
        System.out.println();
        //De eerste 4 huisjes tonen, als een huisje wordt gekocht verwijdert men het object en het getal uit de array
        if(antwoord.toLowerCase().equals("ja"))
        {
            System.out.printf("%s%n", getHutLijst()[0].toString());
            System.out.printf("%s%n", getHutLijst()[1].toString());
            System.out.printf("%s%n", getHutLijst()[2].toString());
            System.out.printf("%s%n", getHutLijst()[3].toString());
        }
    }
    
    //Deze methode wordt niet gebruikt maar is handig voor tijdens het programmeren
    //Zo kunnen we zien of de hutten werken
    private void toonHutten()
    {
        for (Hut hut : getHutLijst())
        {
            System.out.print(hut.toString());
            //Witte lijn tussen elke hut voor overzicht
            System.out.println();
        }
    }
    
    private void toonMijnGereedschap()
    {
        int spelerIndex= spelerNummerOphalen()-1,
                lengteGereedschapskistje = getSpelerLijst()[spelerIndex].getGereedschapskistje().length;
        //Men overloopt de 3 objecten
        for(int gereedschap=0;gereedschap<lengteGereedschapskistje;gereedschap++)
        {
            //Als het een null-object is dan wordt het niet uitgeprint, dan 1 lijn commentaar in de else
            if (getSpelerLijst()[spelerIndex].getGereedschapskistje()[gereedschap].getWaarde() > 0)
            {
            System.out.printf("Gereedschap %d | Waarde: %d | Reeds gebruikt deze ronde: %s%n",
                    getSpelerLijst()[spelerIndex].getGereedschapskistje()[gereedschap].getNummer(),
                    getSpelerLijst()[spelerIndex].getGereedschapskistje()[gereedschap].getWaarde(),
                    getSpelerLijst()[spelerIndex].getGereedschapskistje()[gereedschap].getReedsGebruiktDezeRonde()?"ja":"nee"
                    );
            }
            else
            {
                System.out.printf("Je hebt gereedschap nummer %d (nog) niet.%n", gereedschap+1);
            }
        }
        //Witte lijn voor overzicht
        System.out.println();
    }

    public void deelRonde1Spelen()
    {  
            toonMenu1MetKeuze();
    }

    private void overgangVolgendeDeelronde()
    {
            int spelerIndex = spelerNummerOphalen()-1, spelerNummer = spelerNummerOphalen();
            System.out.println("De deelronde voor het plaatsen van de stamleden is afgelopen.");
            //Speluitslag afprinten na deelronde van het paatsen
            toonSpelers();
            System.out.println();
            toonGebieden();
            //Speler die aan beurt was verliest zijn beurt
             getSpelerLijst()[spelerIndex].setAanBeurt(false); 
            //Als de laatste speler aan beurt was dan krijgt de eerste speler de beurt,
            //anders krijgt de volgende speler de beurt
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
        Scanner invoer = new Scanner(System.in);
        int meestePunten = 0, minstePunten=100;
        String antwoord;
        //Elke 2e deelronde wordt de speler met de meeste en met de minste punten bijgehouden
        //NOG TE DOEN: HOE RANG BIJHOUDEN VAN 2 MIDDELSTE SPELERS
        for (Speler speler : getSpelerLijst())
        {
            if(speler.getPunten() > meestePunten)
            {
                meestePunten = speler.getPunten();
            }
            if(speler.getPunten() < minstePunten)
            {
                minstePunten = speler.getPunten();
            }
        }
        /*
        Kijken of iedereen zijn stamleden al heeft teruggenomen:
        Zo ja:
            dan gaat men kijken of het spel gedaan is. Als dat niet
        het geval is dan speelt men weer de eerste ronden.
        Zo niet:
            dan speelt men verder in de 2e deelronde
        */
        if(deelRonde2Gedaan())
        {
            if(spelGedaan())
            {
                System.out.printf("Het spel is gedaan want alle hutten zijn gekocht.%n"
                        + "De eindstand is als volgt:%n");
                toonSpelers();
                //De spelers overlopen en de speler met het meeste punten wint
                for (Speler speler : getSpelerLijst())
                {
                    if(speler.getPunten() == meestePunten)
                    {
                        System.out.printf("Speler %s heeft gewonnen met %d punten.%n",
                                speler.getNaam(), speler.getPunten());
                    }
                }
            }
            else
            {
                //De spelers krijgen hun voedsel per voedselproductie
                System.out.println("Elke speler krijgt voedsel volgens zijn/haar voedselproductie.");
                for (Speler speler : getSpelerLijst())
                {
                    speler.setAantalVoedsel(speler.getAantalVoedsel()+speler.getVoedselProductie());
                }
                //De spelers moeten hun stamleden voeden
                for (Speler speler : getSpelerLijst())
                {
                    try
                    {
                        speler.setAantalVoedsel(speler.getAantalVoedsel()-speler.getAantalStamleden());
                    }
                    catch (IllegalArgumentException teWeinigVoedselVoorStamleden)
                    {
                        System.out.printf("Speler %s heeft te weinig voedsel.%n"
                                + "Betaal met grondstoffen of ontvang strafpunten.%n", speler.getNaam());
                        //KIJKEN OF SPELER NOG GRONDSTOFFEN HEEFT PER ONTBREKEND VOEDSEL, ZONIET DAN STRAFPUNTEN
                        if (kijkOfSpelerGrondstoffenHeeft(speler.getNummer()))
                        {
                            do{
                            System.out.println("Wilt u betalen met grondstoffen?");
                            antwoord = invoer.nextLine();
                            }while((!antwoord.toLowerCase().equals("ja")) && (!antwoord.toLowerCase().equals("nee")));
                            if(antwoord.toLowerCase().equals("ja"))
                            {
                                betaalTekortVoedselMetGrondstoffen(speler.getNummer());
                            }
                            else
                            {
                                System.out.printf("Speler %s had te weinig voedsel, wou zijn grondstoffen niet opofferen en heeft nu %10 strafpunten ontvangen.", speler.getNaam());
                                speler.setPunten(speler.getPunten()-10);
                            }
                        }
                        else
                        {
                            System.out.printf("Speler %s had te weinig voedsel en heeft nu %10 strafpunten ontvangen.", speler.getNaam());
                            speler.setPunten(speler.getPunten()-10);
                        }
                    }
                }
                //Het gereedschap zijn reedsGebruikt wordt weer op false gezet
                gereedschapResetten();
                //Men speelt weer de eerste deelronde
                deelRonde1Spelen();
            }
        }
        else
        {
            toonMenu2MetKeuze();
        }
    }
    
    private boolean kijkOfSpelerGrondstoffenHeeft(int spelerIndex)
    {
        //Als het true is dan is het goed
        boolean heeftGrondstoffen = false;
        //Optelling van alle grondstoffen dat de speler heeft
        int aantalGrondstoffenTerBeschikking =
                getSpelerLijst()[spelerIndex].getAantalHout() + getSpelerLijst()[spelerIndex].getAantalLeem()
                + getSpelerLijst()[spelerIndex].getAantalSteen() + getSpelerLijst()[spelerIndex].getAantalGoud(),
                tekortVoedselGetal = getSpelerLijst()[spelerIndex].getAantalVoedsel() - getSpelerLijst()[spelerIndex].getAantalStamleden();
        //Gecontroleerd of dit hoger is dan het voedsel tekort
        if(aantalGrondstoffenTerBeschikking > tekortVoedselGetal)
        {
            heeftGrondstoffen = true;
        }
        return heeftGrondstoffen;
    }
    
    //METHODE GOED NAKIJKEN
    private void betaalTekortVoedselMetGrondstoffen(int spelerIndex)
    {
        //Gegevens van de speler worden opgehaald
        int aantalHout = getSpelerLijst()[spelerIndex].getAantalHout(),
            aantalLeem = getSpelerLijst()[spelerIndex].getAantalLeem(),
            aantalSteen = getSpelerLijst()[spelerIndex].getAantalSteen(),
            aantalGoud = getSpelerLijst()[spelerIndex].getAantalGoud(),
            aantalVoedsel = getSpelerLijst()[spelerIndex].getAantalVoedsel(),
            tekortVoedselGetal;
        //Het te kort aan voedsel wordt berekend, is een negatief getal
        tekortVoedselGetal = getSpelerLijst()[spelerIndex].getAantalVoedsel() - getSpelerLijst()[spelerIndex].getAantalStamleden();
        //Het voedsel dat er wel is wordt al weggenomen voor de stamleden
        getSpelerLijst()[spelerIndex].setAantalVoedsel(0);
        //Het tekort aan voedsel wordt steeds betaald met de goedkoopste grondstof
        if (tekortVoedselGetal < 0)
        {
            //Het tekort aan voedsel wordt al gecompenseerd aan hout
            tekortVoedselGetal = tekortVoedselGetal - aantalHout;
            //Als er teveel aan hout is betaald dan wordt dat teruggegeven
            if (tekortVoedselGetal > 0)
            {
                getSpelerLijst()[spelerIndex].setAantalHout(tekortVoedselGetal);
                if (tekortVoedselGetal < 0)
                {
                    //Het tekort aan voedsel wordt al gecompenseerd aan hout en leem
                    tekortVoedselGetal = tekortVoedselGetal - aantalLeem;
                    //Als er teveel aan leem betaald is dan wordt dat teruggegeven
                    if (tekortVoedselGetal > 0)
                    {
                        getSpelerLijst()[spelerIndex].setAantalLeem(tekortVoedselGetal);
                        if (tekortVoedselGetal < 0)
                        {
                            //Het tekort aan voedsel wordt al gecompenseerd aan hout, leem en steen
                            tekortVoedselGetal = tekortVoedselGetal - aantalSteen;
                            //Als er teveel aan steen betaald is dan wordt dat teruggegeven
                            if (tekortVoedselGetal > 0)
                            {
                                getSpelerLijst()[spelerIndex].setAantalSteen(tekortVoedselGetal);
                                if (tekortVoedselGetal < 0)
                                {
                                    //Het tekort aan voedsel wordt al gecompenseerd aan hout, leem, steen en goud
                                    tekortVoedselGetal = tekortVoedselGetal - aantalGoud;
                                    //Als er teveel aan goud betaald is dan wordt dat teruggegeven
                                    if (tekortVoedselGetal > 0)
                                    {
                                        getSpelerLijst()[spelerIndex].setAantalGoud(tekortVoedselGetal);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }                      
    }
    
    private boolean spelGedaan()
    {
        //Als het true is dan is het spel volledig gedaan
        boolean gedaan = false;
        //Men kijkt of de hele lijst van hutten leeg is
        //want altijd als men een hut koopt dan verdwijnt dat element
        //uit de hutlijst array
        if (getHutLijst().length < 1)
        {
            gedaan = true;
        }
        
        return gedaan;
    }
    
    private boolean deelRonde2Gedaan()
    {
        //True is gelijk aan het spel is gedaan
        boolean gedaan = true;
        //Men kijkt of er nog waardes in de stamledenLocatieLijst zijn
        //die NIET nul zijn, want dan is de 2e speelronde nog niet gedaan
        for (int[] spelersrij : getStamledenLocatieLijst())
        {
            for (int kolom = 0; kolom < spelersrij.length; kolom++)
            {
                if (spelersrij[kolom] != 0)
                {
                    gedaan = false;
                }
            }
        }
        return gedaan;
    }
    
    public void toonMenu1MetKeuze()
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
                }while(gebiedNummer <= 0 || gebiedNummer > 12);

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
                    toonMenu1MetKeuze();
                }

                plaatsStamleden(gebiedNummer, aantal);
                
                if(aantal != 0)
                {
                    beurtOverslaan1();
                }
                
                if(controleerAlleStamledenGeplaatst())
                {
                    overgangVolgendeDeelronde();
                }
                
            break;
            case 4:
                beurtOverslaan1();
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
    
    private void toonMenu2MetKeuze()
    {
        Scanner invoer = new Scanner(System.in);
        int nummer = 0;
        String antwoord;
        
        do{
            try
            {
                System.out.printf("Geef een nummer voor de actie die u wilt uitvoeren: %n"
                + "1: Toon spelers | 2:  Toon gebieden | 3: Stamleden innen | 4: Geen stamleden innen | 5: Toon mijn stamleden | 6: Toon mijn gereedschap%n");
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
                deelRonde2Spelen();
            break;
            case 2:
                toonGebieden();
                deelRonde2Spelen();
            break;
            case 3:
                stamledenInnen();
            break;
            case 4:
                do
                {
                System.out.print("Bent u zeker dat u geen stamleden wilt innen? Zeg ja of nee: ");
                antwoord = invoer.nextLine();
                }while((!antwoord.toLowerCase().equals("ja")) && (!antwoord.toLowerCase().equals("nee")));
                //Als de speler niet zeker is dan krijgt hij opnieuw de kans
                //een actie uit te voeren
                if(antwoord.equals("nee"))
                {
                    deelRonde2Spelen();
                }
                else
                {
                    geenStamledenInnen();
                }
            break;
            case 5:
                toonMijnStamleden();
                deelRonde2Spelen();
            break;
            case 6:
                toonMijnGereedschap();
                deelRonde2Spelen();
             break;
        }
    }

    private void beurtOverslaan1()
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
                beurtOverslaan1();
            }
                deelRonde1Spelen();
        }
    }
    
    private void beurtOverslaan2()
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
                    //Men kijkt of het gebieden van de speler bezet zijn
                    //Zo niet dan wordt het niet uitgeprint
                    if(getStamledenLocatieLijst()[spelerIndex][loper] > 0)
                    {
                        System.out.printf("Speler %s heeft %d %s op %s (gebied nummer %d).%n",
                        getSpelerLijst()[spelerIndex].getNaam(), getStamledenLocatieLijst()[spelerIndex][loper],
                        getStamledenLocatieLijst()[spelerIndex][loper]==1?"stamlid":"stamleden",
                        getGebiedLijst()[loper].getNaamGebied(),
                        getGebiedLijst()[loper].getNummer());
                    }
                }
            //Het aantal ongebruikte stamleden wordt ook afgeprint, indien er zijn
            if(getSpelerLijst()[spelerIndex].getAantalStamleden() > 0)
            {
                System.out.printf("Speler %s heeft %d ongebruikte stamleden.%n",
                        getSpelerLijst()[spelerIndex].getNaam(),
                        getSpelerLijst()[spelerIndex].getAantalStamleden());
            }
        }
        catch(ArrayIndexOutOfBoundsException tonenVanStamleden)
        {
            System.out.println("Error bij het tonen van de stamleden.");
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
        if (getStamledenLocatieLijst()[spelerIndex][gebiedIndex] != 0)
        {
            oke = false;
            System.out.println("Je hebt in een vorige ronde al op dat gebied stamleden geplaatst.");
            System.out.println();
            toonMenu1MetKeuze();
        }
        
        //Je hebt slechts 1 ventje maar wilt op gebied hut gaan
        if (gebiedNummer == 1 && getSpelerLijst()[spelerIndex].getAantalStamleden() == 1)
        {
            oke = false;
            System.out.printf("Je hebt minder als twee stamleden.%n%n");
            toonMenu1MetKeuze();
        }
        return oke;
    }
    
    //AAN DEZE METHODE IS ER SUPERVEEL WERK, DIT IS HET BELANGRIJKSTE DAT NOG MOET GEBEUREN
    private void stamledenInnen()
    {
        Scanner invoer = new Scanner(System.in);
        String antwoord;
        int dobbelResultaat, gebiedNummer=0, spelerIndex = spelerNummerOphalen()-1, gebiedIndex=0, aantalPunten = 0, aantalStamleden, temp, grootsteWaarde=0;
        boolean gereedschap1 = false, gereedschap2 = false, gereedschap3 = false;
        //De stamleden en de gebieden waar ze opstaan worden afgeprint
        toonMijnStamleden();
        //Gebiednummer opvragen van de speler
        do{
            try
            {
                System.out.println("Van welk gebied wilt u uw stamleden innen?");
                gebiedNummer = invoer.nextInt();
                invoer.nextLine();
            }
            catch (InputMismatchException stamledenInnenVanGebieden) 
            {
                invoer.nextLine();
                System.out.println("Voer een getal in.");
            }
        }while(gebiedNummer < 0 && gebiedNummer > 13);
        //De gebiedindex wordt berekend
        gebiedIndex = gebiedNummer -1;
        //De stamleden van dat gebied worden opgehaald en opgeslagen in aantalStamleden
        aantalStamleden = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
        //De hut, voedselproductie, gereedschapsmaker (gebiedIndex 0 t.e.m. 2)
        //Voor hutten kopen (gebiedIndex 8 t.e.m. 11)
        //NAKIJKEN: klopt de voorwaarde wel???
        if ((gebiedIndex >= 0 && gebiedIndex <= 2) || (gebiedIndex >= 8 && gebiedIndex <= 11))
        {
            //Voor deze speciale gebieden krijgt de speler zijn rewards
            switch(gebiedIndex)
            {
                case 0:
                    //Verwijder het aantal stamleden van het gebied
                    temp = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
                    getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
                    getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);
                    //Geef de speler zijn stamleden terug
                    getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
                    //Geef de speler zijn reward: 1 extra stamlid
                    getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+1);
                break;
                case 1:
                    //Verwijder het aantal stamleden van het gebied
                    temp = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
                    getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
                    getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);
                    //Geef de speler zijn stamleden terug
                    getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
                    //Geef de speler zijn reward: 1 extra voedselproductie
                    getSpelerLijst()[spelerIndex].setVoedselProductie(getSpelerLijst()[spelerIndex].getVoedselProductie()+1);
                break;
                case 2:
                    //DEZE ZEKER NAKIJKEN
                    //Verwijder het aantal stamleden van het gebied
                    temp = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
                    getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
                    getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);
                    //Geef de speler zijn stamleden terug
                    getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
                    //Geef de speler zijn reward: 1 extra waarde van werktuig
                    for (int loper=0;loper<getSpelerLijst()[spelerIndex].getGereedschapskistje().length;loper++)
                    {
                        //Als de waarde van het current gereedschap groter is als 0 dan kijk het naar de eerstvolgende 0
                        //of lager getal, anders krijgt het zijn eerste gereedschap +1
                        if (getSpelerLijst()[spelerIndex].getGereedschapskistje()[loper].getWaarde()> grootsteWaarde)
                        {
                            grootsteWaarde = getSpelerLijst()[spelerIndex].getGereedschapskistje()[loper].getWaarde();
                        }
                        else
                        {
                            if (loper==1)
                            {
                                getSpelerLijst()[spelerIndex].getGereedschapskistje()[loper+1].setWaarde(loper+1);
                            }
                            if (loper==2)
                            {
                                getSpelerLijst()[spelerIndex].getGereedschapskistje()[loper-2].setWaarde(loper+1);
                            }
                            break;
                        }
                    }
                break;
                case 8:
                    //Verwijder het aantal stamleden van het gebied
                    temp = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
                    getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
                    getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);
                    //Geef de speler zijn stamleden terug
                    getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
                    //Geef speler het aantal punten + hut wordt verwijderd uit de lijst NOG TOEVOEGEN
                    aantalPunten = getHutLijst()[8].berekenKostPrijsHut();
                    getSpelerLijst()[spelerIndex].setPunten(getSpelerLijst()[spelerIndex].getPunten() + aantalPunten);
                break;
                case 9:
                    //Verwijder het aantal stamleden van het gebied
                    temp = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
                    getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
                    getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);
                    //Geef de speler zijn stamleden terug
                    getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
                    //Geef speler het aantal punten + hut wordt verwijderd uit de lijst NOG TOEVOEGEN
                    aantalPunten = getHutLijst()[9].berekenKostPrijsHut();
                    getSpelerLijst()[spelerIndex].setPunten(getSpelerLijst()[spelerIndex].getPunten() + aantalPunten);
                break;
                case 10:
                    //Verwijder het aantal stamleden van het gebied
                    temp = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
                    getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
                    getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);
                    //Geef de speler zijn stamleden terug
                    getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
                    //Geef speler het aantal punten + hut wordt verwijderd uit de lijst NOG TOEVOEGEN
                    aantalPunten = getHutLijst()[10].berekenKostPrijsHut();
                    getSpelerLijst()[spelerIndex].setPunten(getSpelerLijst()[spelerIndex].getPunten() + aantalPunten);
                break;
                case 11:
                    //Verwijder het aantal stamleden van het gebied
                    temp = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
                    getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
                    getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);
                    //Geef de speler zijn stamleden terug
                    getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
                    //Geef speler het aantal punten + hut wordt verwijderd uit de lijst NOG TOEVOEGEN
                    aantalPunten = getHutLijst()[11].berekenKostPrijsHut();
                    getSpelerLijst()[spelerIndex].setPunten(getSpelerLijst()[spelerIndex].getPunten() + aantalPunten);
                break;
            }
            
        }
        else
        {
            //Voor deze gebieden moet er gedobbelt worden
            dobbelResultaat = dobbelen(aantalStamleden);
            System.out.printf("U hebt %d gedobbeld.", dobbelResultaat);
        
            if(controleerOfBeschikbaarGereedschapBezit(spelerIndex))
            {
              toonMijnGereedschap();
                do{
                    System.out.print("Wilt u uw gereedschap gebruiken?");
                    antwoord = invoer.nextLine();
                }while((!antwoord.toLowerCase().equals("ja")) && (!antwoord.toLowerCase().equals("nee")));
                gebruikGereedschap(dobbelResultaat, gereedschap1, gereedschap2, gereedschap3);  
            }
            else
            {
                System.out.print("U bezit geen gereedschap dat u kan gebruiken.");
            }
        }
        
    }
    
    private boolean controleerOfBeschikbaarGereedschapBezit(int spelerIndex)
    {
        //True betekent dat de speler bruikbaar gereedschap bezit
        boolean oke = false;
        //Als de waarde groter is als nul en het is nog niet gebruikt dan kan de speler zijn gereedschap gebruiken
        for (Gereedschap gereedschap : getSpelerLijst()[spelerIndex].getGereedschapskistje())
        {
            if (gereedschap.getWaarde() > 0 && gereedschap.getReedsGebruiktDezeRonde() == false)
            {
                oke = true;
            }
        }
        return oke;
    }
    
    private int gebruikGereedschap(int dobbelResultaat, boolean gereedschap1, boolean gereedschap2, boolean gereedschap3)
    {
        int gereedschap1Waarde = 0, gereedschap2Waarde = 0, gereedschap3Waarde = 0, spelerIndex = spelerNummerOphalen() - 1;
        //De waarde van al het gereedschap wordt opgehaald
        gereedschap1Waarde = getSpelerLijst()[spelerIndex].getGereedschapskistje()[0].getWaarde();
        gereedschap2Waarde = getSpelerLijst()[spelerIndex].getGereedschapskistje()[1].getWaarde();
        gereedschap3Waarde = getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].getWaarde();
        //De waarde van het gereedschap wordt bijgeteld als het gebruikt wordt
        //Ook wordt de waarde op false gezet als het gebruikt wordt
        //Voor dat kan gebeuren wordt er eerst gecheckt of het nog niet gebruikt is tho
        if (gereedschap1 == true && getSpelerLijst()[spelerIndex].getGereedschapskistje()[0].getReedsGebruiktDezeRonde() == false)
        {
            dobbelResultaat =  dobbelResultaat + gereedschap1Waarde;
            getSpelerLijst()[spelerIndex].getGereedschapskistje()[0].setReedsGebruiktDezeRonde(true);
            
            if(gereedschap2 == true && getSpelerLijst()[spelerIndex].getGereedschapskistje()[1].getReedsGebruiktDezeRonde() == false)
            {
                dobbelResultaat =  dobbelResultaat + gereedschap2Waarde;
                getSpelerLijst()[spelerIndex].getGereedschapskistje()[1].setReedsGebruiktDezeRonde(true);
                
                if(gereedschap3 == true && getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].getReedsGebruiktDezeRonde() == false)
                {
                    dobbelResultaat = dobbelResultaat + gereedschap3Waarde;
                    getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].setReedsGebruiktDezeRonde(true);
                } 
            }
            else
            {
                if (gereedschap3 == true && getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].getReedsGebruiktDezeRonde() == false)
                {
                    dobbelResultaat = dobbelResultaat + gereedschap3Waarde;
                    getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].setReedsGebruiktDezeRonde(true);
                }
            }
        }
        else
        {
            if(gereedschap2 == true && getSpelerLijst()[spelerIndex].getGereedschapskistje()[1].getReedsGebruiktDezeRonde() == false)
            {
                dobbelResultaat =  dobbelResultaat + gereedschap2Waarde;
                getSpelerLijst()[spelerIndex].getGereedschapskistje()[1].setReedsGebruiktDezeRonde(true);
                
                if(gereedschap3 == true && getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].getReedsGebruiktDezeRonde() == false)
                {
                    dobbelResultaat = dobbelResultaat + gereedschap3Waarde;
                    getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].setReedsGebruiktDezeRonde(true);
                }
            }
            else
            {
                if(gereedschap3 == true && getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].getReedsGebruiktDezeRonde() == false)
                {
                    dobbelResultaat = dobbelResultaat + gereedschap3Waarde;
                    getSpelerLijst()[spelerIndex].getGereedschapskistje()[2].setReedsGebruiktDezeRonde(true);
                }
            }    
        }
        return dobbelResultaat;
    }
    
    private int dobbelen(int aantalStamleden)
    {
        int resultaat = 0;
        SecureRandom random = new SecureRandom();
        //Men krijgt een dobbelbeurt per stamlid
        for(int dobbelbeurt=0;dobbelbeurt<aantalStamleden;dobbelbeurt++)
        {
            resultaat = resultaat + 1 + random.nextInt(6); //+1 omdat men anders kans heeft om 0 te smijten
        }
        return resultaat;
    }
    
    private void geenStamledenInnen()
    {
        Scanner invoer = new Scanner(System.in);
        int spelerIndex = spelerNummerOphalen() - 1, gebiedNummer = 0, gebiedIndex = 0, temp;
        //De speler krijgt de keuze om zijn stamleden weg te halen van het gebied
        toonMijnStamleden();
        do{
            try
            {
                System.out.println("Van welk gebied wil je je stamleden weghalen zonder iets te krijgen?");
                gebiedNummer = invoer.nextInt();
                invoer.nextLine();
            }
            catch (InputMismatchException geenStamledenInnen) 
            {
                invoer.nextLine();
                System.out.println("Voer een getal in.");
            }
        }while(gebiedNummer < 0 && gebiedNummer > 13);
        
        gebiedIndex = gebiedNummer - 1;        
        
        //Het aantal stamleden van speler [spelerIndex] dat op gebied [gebiedIndex] staan worden opgeslagen
        temp = getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
        //De stamleden worden van het gebied verwijderd
        getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen()-temp);
        //De stamleden worden van de stamledenLocatieLijst verwijderd
        getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
        //De speler krijgt zijn stamleden terug
        getSpelerLijst()[spelerIndex].setAantalStamleden(getSpelerLijst()[spelerIndex].getAantalStamleden()+ temp);
        //De volgende speler krijgt de beurt
        beurtOverslaan2();
        toonMenu2MetKeuze();
    }
    
    private int spelerNummerOphalen()
    {
        int spelerNummer=0;
        //Spelernummer ophalen van de speler aan beurt
        for (Speler speler : getSpelerLijst()) 
        {
            if (speler.getAanBeurt()) 
            {
                spelerNummer = speler.getNummer();
            }  
        }  
        return spelerNummer;
    }
    
    private void gereedschapResetten()
    {
        for (Speler speler : getSpelerLijst())
        {
            for (Gereedschap gereedschap : speler.getGereedschapskistje())
            {
                //Het reeds gebruikt deze ronde wordt weer op false gezet zodat ze het de volgende
                //ronde wel kunnen gebruiken
                gereedschap.setReedsGebruiktDezeRonde(false);
            }
        }
        
    }
}