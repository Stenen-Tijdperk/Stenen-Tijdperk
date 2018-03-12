package domein;

import java.security.SecureRandom;
import java.util.Objects;


public class DomeinController
{
    public Speler[] spelerLijst;
    public Gebied[] gebiedLijst;
    public Hut[] hutLijst;
    //Hierin worden de [spelers] en [gebieden] opgeslagen, de int geef het aantal stamleden weer
    public int[][] stamledenLocatieLijst;
    
    public Speler[] getSpelerLijst()
    {
        return spelerLijst;
    }
    
    public Gebied[] getGebiedLijst()
    {
        return gebiedLijst;
    }
    
    public Hut[] getHutLijst()
    {
        return hutLijst;
    }
    
    public int[][] getStamledenLocatieLijst()
    {
        return stamledenLocatieLijst;
    }
    
    public boolean controleerNaamSpelers(String naamSpeler, int spelerIndex)
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
    
    public void aanmakenGebieden()
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
    
    public void aanmakenHutten()
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
    
    public void aanmakenLocatieLijst()
    {
         this.stamledenLocatieLijst = new int[getSpelerLijst().length][12];
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
    
    public void toonSpelers()
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
    
    public void toonGebieden()
    {
        String antwoord;
        //Print de toString uit van alle gebieden
        for (Gebied gebied : getGebiedLijst())
        {
            System.out.print(gebied.toString());
            //Witte lijn tussen elk gebied voor overzicht
            System.out.println();
        }
        //Huisjesmarkt bekijken (= gebied 8 t.e.m. 12)
        //De eerste 4 huisjes tonen, als een huisje wordt gekocht verwijdert men het object en het getal uit de array
            System.out.printf("%s%n", getHutLijst()[0].toString());
            System.out.printf("%s%n", getHutLijst()[1].toString());
            System.out.printf("%s%n", getHutLijst()[2].toString());
            System.out.printf("%s%n", getHutLijst()[3].toString());
    }
    
    //Deze methode wordt niet gebruikt maar is handig voor tijdens het programmeren
    //Zo kunnen we zien of de hutten werken
    public void toonHutten()
    {
        for (Hut hut : getHutLijst())
        {
            System.out.print(hut.toString());
            //Witte lijn tussen elke hut voor overzicht
            System.out.println();
        }
    }
    
    public void toonMijnGereedschap()
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
    
    public boolean kijkOfSpelerGrondstoffenHeeft(int spelerIndex)
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
    public void betaalTekortVoedselMetGrondstoffen(int spelerIndex)
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
    
    public boolean spelGedaan()
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
    
    public boolean deelRonde2Gedaan()
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
    
    public void beurtOverslaan2()
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
    
    public void plaatsStamleden(int gebiedNummer, int aantalStamleden)
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

    public void toonMijnStamleden() 
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

    public boolean controleerAlleStamledenGeplaatst() 
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
    
    public boolean controleerOfBeschikbaarGereedschapBezit(int spelerIndex)
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
    
    public int gebruikGereedschap(int dobbelResultaat, boolean gereedschap1, boolean gereedschap2, boolean gereedschap3)
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
    
    public int dobbelen(int aantalStamleden)
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
    
    public int spelerNummerOphalen()
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
    
    public void gereedschapResetten()
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
    public void aanmakenSpelerLijst(int aantalSpelers)
    {
        //Lijst voor alle spelers op te slaan
        this.spelerLijst = new Speler[aantalSpelers];
    }
}