package domein;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Speler {

    private int aantalHout;
    private int aantalLeem;
    private int aantalSteen;
    private int aantalGoud;
    private final GregorianCalendar geboortdeDatum = null; //DIT IS ECHT FUCKED UP? IK WEET NIET HOE HET WERKT -SOUHAIB
    private final String kleur;
    private final String naamSpeler;
    private boolean beurt;
    
    public Speler(String kleur, String naamSpeler, GregorianCalendar geboorteDatum)
    {
        
        this.geboortdeDatum = new GregorianCalendar(); //DIT IS ECHT FUCKED UP? IK WEET NIET HOE HET WERKT -SOUHAIB
        this.kleur = kleur;
        this.naamSpeler = naamSpeler;
    }
    
    public int geefAantalHutten()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public int geefAantalGereedschapsfisches()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public int geefAantalStamleden()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public boolean isAanBeur()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
    
    public void gebruikGereedschapsfische()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }

    public void setAantalHout(int aantalHout) {
        this.aantalHout = aantalHout;
    }

    public void setAantalLeem(int aantalLeem) {
        this.aantalLeem = aantalLeem;
    }

    public void setAantalSteen(int aantalSteen) {
        this.aantalSteen = aantalSteen;
    }

    public void setAantalGoud(int aantalGoud) {
        this.aantalGoud = aantalGoud;
    }
    
    public void geefKleurAanSpeler()
    {
        //Ik weet niet wat ik hier moet schrijven -Souhaib
    }
}
