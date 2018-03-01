package ui;

import domein.DomeinController;

public class StenenTijdperkApplicatie 
{
    public void start() throws InterruptedException
    {
        //Ojbect van de domeincontroller voor methodes te kunnen oproepen
        DomeinController dom = new DomeinController();
                
        //Gebieden worden aangemaakt
        dom.aanmakenGebieden();
        
        //Hutten worden aangemaakt
        dom.aanmakenHutten();
        
        //Spelers worden aangemaakt
        dom.aanmakenSpelers();
        
        //Bepalen van speler die begint
        dom.bepaalRandomSpelerAanBeurt();
        
        //Geef menu met keuzes
        dom.toonMenuMetKeuze();
    }
}