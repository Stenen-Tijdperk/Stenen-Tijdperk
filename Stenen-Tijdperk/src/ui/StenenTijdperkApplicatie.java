package ui;

import domein.DomeinController;

public class StenenTijdperkApplicatie 
{
    public void start() throws InterruptedException
    {
        //Ojbect van de domeincontroller voor methodes te kunnen oproepen
        DomeinController dom = new DomeinController();
        
        //Hutten, gebieden en spelers worden gemaakt
        dom.preLoadSpel();
        
        //Bepalen van speler die begint
        dom.bepaalRandomSpelerAanBeurt();
        
        //Speel de eerste ronde
        dom.toonMenuMetKeuze();
    }
}