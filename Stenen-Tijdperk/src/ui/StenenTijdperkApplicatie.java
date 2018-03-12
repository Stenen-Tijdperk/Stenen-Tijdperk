package ui;

import domein.DomeinController;
import domein.Speler;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StenenTijdperkApplicatie 
{
    //Ojbect van de domeincontroller voor methodes te kunnen oproepen
    private static DomeinController dom;
    
    private static void aanmakenObjectDomeinController()
    {
        dom = new DomeinController();
    }
    
    public void start() throws InterruptedException
    {
        aanmakenObjectDomeinController();
        
        //Hutten, gebieden en spelers worden gemaakt
        dom.aanmakenHutten();
        dom.aanmakenGebieden();
        aanmakenSpelers();
        
        
        
        
        //Speel de eerste ronde;
        deelRonde1Spelen();
    }

    private static void deelRonde1Spelen()
    {  
        toonMenu1MetKeuze();
    }

    private static boolean controleerStamleden(int aantal, int gebiedNummer)
    {
        int spelerIndex = dom.spelerNummerOphalen()-1, gebiedIndex = gebiedNummer -1; 
        boolean oke = true; 
        
        //Op de hut moet je twee stamleden zetten, niet meer, niet minder
        if (gebiedNummer == 1 && aantal !=2)
        {
            oke = false;
            System.out.println("Op de hut moet je twee stamleden zetten.");
        }
        
        //Je mag niet meer stamleden plaatsen dan je hebt
        if(dom.getSpelerLijst()[spelerIndex].getAantalStamleden() < aantal)
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
        if(dom.getGebiedLijst()[gebiedIndex].getAantalMaxLeden() - dom.getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() < aantal)
        {           
            oke = false;
            System.out.println("Je gaf een te hoog aantal stamleden in voor dit gebied.");
        }
        
        //Je kan niet plaatsen op een gebied waar je eerder al hebt geplaatst
        if (dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex] != 0)
        {
            oke = false;
            System.out.println("Je hebt in een vorige ronde al op dat gebied stamleden geplaatst.");
            System.out.println();
            toonMenu1MetKeuze();
        }
        
        //Je hebt slechts 1 ventje maar wilt op gebied hut gaan
        if (gebiedNummer == 1 && dom.getSpelerLijst()[spelerIndex].getAantalStamleden() == 1)
        {
            oke = false;
            System.out.printf("Je hebt minder als twee stamleden.%n%n");
            toonMenu1MetKeuze();
        }
        return oke;
    }

    private static void overgangVolgendeDeelronde()
    {
            int spelerIndex = dom.spelerNummerOphalen()-1, spelerNummer = dom.spelerNummerOphalen();
            System.out.println("De deelronde voor het plaatsen van de stamleden is afgelopen.");
            //Speluitslag afprinten na deelronde van het paatsen
            dom.toonSpelers();
            System.out.println();
            dom.toonGebieden();
            //Speler die aan beurt was verliest zijn beurt
            dom.getSpelerLijst()[spelerIndex].setAanBeurt(false); 
            //Als de laatste speler aan beurt was dan krijgt de eerste speler de beurt,
            //anders krijgt de volgende speler de beurt
            if (spelerNummer == dom.getSpelerLijst().length)
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
            dom.getSpelerLijst()[spelerNummer-1].setAanBeurt(true);
            //Overgaan naar volgende deelronde
            deelRonde2Spelen();
    }

    private static void aanmakenSpelers()
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
        
        //Spelerlijst wordt aangemaakt
        dom.aanmakenSpelerLijst(aantal);
        
        //Locatielijst wordt aangemaakt
        dom.aanmakenLocatieLijst();
        
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
            }while(dom.controleerNaamSpelers(naamSpeler, spelerLoper) == false);
            
            //Constructor van de speler: naam, nummer, kleur
            Speler speler = new Speler(naamSpeler, nummersLijst[spelerLoper], kleurenLijst[spelerLoper]);
            
            //Speler opslaan in de lijst
            dom.getSpelerLijst()[spelerLoper] = speler;
            
            //Mooie lijn tussen elke speler
            System.out.println();
        }
                
        //Print out dat spel is begonnen
        System.out.printf("Het spel is begonnen met %d spelers.%n", aantal);
        
        //Bepalen van speler die begint
        dom.bepaalRandomSpelerAanBeurt();
    }
    
    private static void deelRonde2Spelen()
    {  
        Scanner invoer = new Scanner(System.in);
        int meestePunten = 0, minstePunten=100;
        String antwoord;
        //Elke 2e deelronde wordt de speler met de meeste en met de minste punten bijgehouden
        //NOG TE DOEN: HOE RANG BIJHOUDEN VAN 2 MIDDELSTE SPELERS
        for (Speler speler : dom.getSpelerLijst())
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
        if(dom.deelRonde2Gedaan())
        {
            if(dom.spelGedaan())
            {
                System.out.printf("Het spel is gedaan want alle hutten zijn gekocht.%n"
                        + "De eindstand is als volgt:%n");
                dom.toonSpelers();
                //De spelers overlopen en de speler met het meeste punten wint
                for (Speler speler : dom.getSpelerLijst())
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
                for (Speler speler : dom.getSpelerLijst())
                {
                    speler.setAantalVoedsel(speler.getAantalVoedsel()+speler.getVoedselProductie());
                }
                //De spelers moeten hun stamleden voeden
                for (Speler speler : dom.getSpelerLijst())
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
                        if (dom.kijkOfSpelerGrondstoffenHeeft(speler.getNummer()))
                        {
                            do{
                            System.out.println("Wilt u betalen met grondstoffen?");
                            antwoord = invoer.nextLine();
                            }while((!antwoord.toLowerCase().equals("ja")) && (!antwoord.toLowerCase().equals("nee")));
                            if(antwoord.toLowerCase().equals("ja"))
                            {
                                dom.betaalTekortVoedselMetGrondstoffen(speler.getNummer());
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
                dom.gereedschapResetten();
                //Men speelt weer de eerste deelronde
                deelRonde1Spelen();
            }
        }
        else
        {
            toonMenu2MetKeuze();
        }
    }
    
    private static void toonMenu1MetKeuze()
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
                dom.toonSpelers();
                System.out.println();
                deelRonde1Spelen();
            break;
            case 2:
                dom.toonGebieden();
                System.out.println();
                deelRonde1Spelen();
            break;
            case 3:
                dom.toonGebieden();
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

                dom.plaatsStamleden(gebiedNummer, aantal);
                
                if(aantal != 0)
                {
                    beurtOverslaan1();
                }
                
                if(dom.controleerAlleStamledenGeplaatst())
                {
                    overgangVolgendeDeelronde();
                }
                
            break;
            case 4:
                beurtOverslaan1();
                deelRonde1Spelen();
            break;
            case 5:
                dom.toonMijnStamleden();
                deelRonde1Spelen();
            break;
            case 6:
                dom.toonMijnGereedschap();
                deelRonde1Spelen();
             break;
        }
    }
    
    private static void toonMenu2MetKeuze()
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
                dom.toonSpelers();
                deelRonde2Spelen();
            break;
            case 2:
                dom.toonGebieden();
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
                dom.toonMijnStamleden();
                deelRonde2Spelen();
            break;
            case 6:
                dom.toonMijnGereedschap();
                deelRonde2Spelen();
             break;
        }
    }
    
    //AAN DEZE METHODE IS ER SUPERVEEL WERK, DIT IS HET BELANGRIJKSTE DAT NOG MOET GEBEUREN
    private static void stamledenInnen()
    {
        Scanner invoer = new Scanner(System.in);
        String antwoord;
        int dobbelResultaat, gebiedNummer=0, spelerIndex = dom.spelerNummerOphalen()-1, gebiedIndex=0, aantalPunten = 0, aantalStamleden, temp, grootsteWaarde=0;
        boolean gereedschap1 = false, gereedschap2 = false, gereedschap3 = false;
        //De stamleden en de gebieden waar ze opstaan worden afgeprint
        dom.toonMijnStamleden();
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
        aantalStamleden = dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
        //Verwijder het aantal stamleden van het gebied
        temp = dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
        dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
        dom.getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(dom.getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);
        //Geef de speler zijn stamleden terug
        dom.getSpelerLijst()[spelerIndex].setAantalStamleden(dom.getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
        //De hut, voedselproductie, gereedschapsmaker (gebiedIndex 0 t.e.m. 2)
        //Voor hutten kopen (gebiedIndex 8 t.e.m. 11)
        //NAKIJKEN: klopt de voorwaarde wel???
        if ((gebiedIndex >= 0 && gebiedIndex <= 2) || (gebiedIndex >= 8 && gebiedIndex <= 11))
        {
            //Voor deze speciale gebieden krijgt de speler zijn rewards
            switch(gebiedIndex)
            {
                case 0:
                    //Geef de speler zijn reward: 1 extra stamlid
                    dom.getSpelerLijst()[spelerIndex].setAantalStamleden(dom.getSpelerLijst()[spelerIndex].getAantalStamleden()+1);
                break;
                case 1:dom.getSpelerLijst()[spelerIndex].setAantalStamleden(dom.getSpelerLijst()[spelerIndex].getAantalStamleden()+temp);
                    //Geef de speler zijn reward: 1 extra voedselproductie
                    dom.getSpelerLijst()[spelerIndex].setVoedselProductie(dom.getSpelerLijst()[spelerIndex].getVoedselProductie()+1);
                break;
                case 2:
                    //Geef de speler zijn reward: 1 extra waarde van werktuig
                    for (int loper=0;loper<dom.getSpelerLijst()[spelerIndex].getGereedschapskistje().length;loper++)
                    {
                        //Als de waarde van het current gereedschap groter is als 0 dan kijk het naar de eerstvolgende 0
                        //of lager getal, anders krijgt het zijn eerste gereedschap +1
                        if (dom.getSpelerLijst()[spelerIndex].getGereedschapskistje()[loper].getWaarde()> grootsteWaarde)
                        {
                            grootsteWaarde = dom.getSpelerLijst()[spelerIndex].getGereedschapskistje()[loper].getWaarde();
                        }
                        else
                        {
                            if (loper==1)
                            {
                                dom.getSpelerLijst()[spelerIndex].getGereedschapskistje()[loper+1].setWaarde(loper+1);
                            }
                            if (loper==2)
                            {
                                dom.getSpelerLijst()[spelerIndex].getGereedschapskistje()[loper-2].setWaarde(loper+1);
                            }
                            break;
                        }
                    }
                break;
                case 8:
                    //Geef speler het aantal punten + hut wordt verwijderd uit de lijst NOG TOEVOEGEN
                    aantalPunten = dom.getHutLijst()[8].berekenKostPrijsHut();
                    dom.getSpelerLijst()[spelerIndex].setPunten(dom.getSpelerLijst()[spelerIndex].getPunten() + aantalPunten);
                break;
                case 9:
                    //Geef speler het aantal punten + hut wordt verwijderd uit de lijst NOG TOEVOEGEN
                    aantalPunten = dom.getHutLijst()[9].berekenKostPrijsHut();
                    dom.getSpelerLijst()[spelerIndex].setPunten(dom.getSpelerLijst()[spelerIndex].getPunten() + aantalPunten);
                break;
                case 10:
                    //Geef speler het aantal punten + hut wordt verwijderd uit de lijst NOG TOEVOEGEN
                    aantalPunten = dom.getHutLijst()[10].berekenKostPrijsHut();
                    dom.getSpelerLijst()[spelerIndex].setPunten(dom.getSpelerLijst()[spelerIndex].getPunten() + aantalPunten);
                break;
                case 11:
                    //Geef speler het aantal punten + hut wordt verwijderd uit de lijst NOG TOEVOEGEN
                    aantalPunten = dom.getHutLijst()[11].berekenKostPrijsHut();
                    dom.getSpelerLijst()[spelerIndex].setPunten(dom.getSpelerLijst()[spelerIndex].getPunten() + aantalPunten);
                break;
            }
            
            dom.beurtOverslaan2();
            
        }
        else
        {
            //Er wordt gedobbeld
            dobbelResultaat = dom.dobbelen(aantalStamleden);
            System.out.printf("U hebt %d gedobbeld.", dobbelResultaat);
            
            if(dom.controleerOfBeschikbaarGereedschapBezit(spelerIndex))
            {
               dom.toonMijnGereedschap();
                do{
                    System.out.print("Wilt u uw gereedschap gebruiken?");
                    antwoord = invoer.nextLine();
                }while((!antwoord.toLowerCase().equals("ja")) && (!antwoord.toLowerCase().equals("nee")));
                dom.gebruikGereedschap(dobbelResultaat, gereedschap1, gereedschap2, gereedschap3);  
            }
            else
            {
                System.out.print("U bezit geen gereedschap dat u kan gebruiken.");
            }
                    
            //Gebieden 4 t.e.m. 9
            
            //Verwijder het aantal stamleden van het gebied
            temp = dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
            dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
            dom.getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(dom.getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen() - temp);

            switch(gebiedIndex)
            {
                case 4: //Voedsel
                    //Speler krijgt aantal dobbel gedeeld door de kostprijs
                    dom.getSpelerLijst()[spelerIndex].setAantalVoedsel(dom.getSpelerLijst()[spelerIndex].getAantalVoedsel()+(dobbelResultaat/2));
                    break;
                case 5: //Hout
                    //Speler krijgt aantal dobbel gedeeld door de kostprijs
                    dom.getSpelerLijst()[spelerIndex].setAantalHout(dom.getSpelerLijst()[spelerIndex].getAantalHout()+(dobbelResultaat/3));
                    
                    break;
                case 6: //Leem
                    //Speler krijgt aantal dobbel gedeeld door de kostprijs
                    dom.getSpelerLijst()[spelerIndex].setAantalLeem(dom.getSpelerLijst()[spelerIndex].getAantalLeem()+(dobbelResultaat/4));
                    
                    break;
                case 7: //Steen
                    //Speler krijgt aantal dobbel gedeeld door de kostprijs
                    dom.getSpelerLijst()[spelerIndex].setAantalSteen(dom.getSpelerLijst()[spelerIndex].getAantalSteen()+(dobbelResultaat/5));
                    
                    break;
                case 8: //Goud
                    //Speler krijgt aantal dobbel gedeeld door de kostprijs
                    dom.getSpelerLijst()[spelerIndex].setAantalGoud(dom.getSpelerLijst()[spelerIndex].getAantalGoud()+(dobbelResultaat/6));
                    
                    break;
            }
        }
        
    }
    
    private static void geenStamledenInnen()
    {
        Scanner invoer = new Scanner(System.in);
        int spelerIndex = dom.spelerNummerOphalen() - 1, gebiedNummer = 0, gebiedIndex = 0, temp;
        //De speler krijgt de keuze om zijn stamleden weg te halen van het gebied
        dom.toonMijnStamleden();
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
        temp = dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex];
        //De stamleden worden van het gebied verwijderd
        dom.getGebiedLijst()[gebiedIndex].setAantalGenomenPlaatsen(dom.getGebiedLijst()[gebiedIndex].getAantalGenomenPlaatsen()-temp);
        //De stamleden worden van de stamledenLocatieLijst verwijderd
        dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex] = dom.getStamledenLocatieLijst()[spelerIndex][gebiedIndex] - temp;
        //De speler krijgt zijn stamleden terug
        dom.getSpelerLijst()[spelerIndex].setAantalStamleden(dom.getSpelerLijst()[spelerIndex].getAantalStamleden()+ temp);
        //De volgende speler krijgt de beurt
        dom.beurtOverslaan2();
        toonMenu2MetKeuze();
    }

    private static void beurtOverslaan1()
    {
        int spelerIndex=dom.spelerNummerOphalen()-1, spelerNummer = dom.spelerNummerOphalen();
        
        //Beurt van speler aan beurt veranderen
        dom.getSpelerLijst()[spelerIndex].setAanBeurt(false); 
        
        //Indien laatste speler aan de beurt is, krijgt eerste speler de beurt
        //Zo niet dan krijgt de volgende speler de beurt
        if (spelerNummer == dom.getSpelerLijst().length)
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
        dom.getSpelerLijst()[spelerNummer-1].setAanBeurt(true);
        
        //Controleren of het spel gedaan is
        if(dom.controleerAlleStamledenGeplaatst())
        {
            overgangVolgendeDeelronde();
        }
        else
        {
            //Volgende speler heeft geen stamleden? Beurt overslaan
            if (dom.getSpelerLijst()[spelerIndex].getAantalStamleden() == 0)
            {
                beurtOverslaan1();
            }
                deelRonde1Spelen();
        }
    }
}