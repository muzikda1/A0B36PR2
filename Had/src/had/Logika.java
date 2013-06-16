/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package had;

import had.gui.Gui;
import had.snake.Snake;
import had.snake.Snake.Telo;
import had.hraci.AbstraktniHrac;
import had.hraci.Bot;
import had.hraci.Hrac;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author David
 * 
 * 
 */
public class Logika {
    private ArrayList<AbstraktniHrac> hraci;
    private ArrayList<Bot> boti;
    private Gui gui;
    private int sirka = 20;
    public Timer tajmr;
    
    public Logika(){
        hraci = new ArrayList<AbstraktniHrac>();
        boti = new ArrayList<Bot>();
        gui = new Gui(this, sirka, boti);
        gui.setVisible(true);
        novaHra();
    }
    
    private void novaHra(){
        hraci.add(new Hrac("Hrac", gui));
        hraci.get(0).setBarva(Nastaveni.BARVA_HRACE);
        Bot b = new Bot("Bot 1", gui);
        boti.add(b);
        hraci.add(b);
        gui.boti = boti;
        hrneckuVar();
        tajmr = new Timer(100, new Logika.TiknutiSveta());
        tajmr.start();
    }
    
    private class TiknutiSveta implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("tik");
            for (AbstraktniHrac hrac : hraci) {
                hrac.tah();
                gui.updatujHada(hrac);
            }
        }
        
    }
    
    public void hrneckuVar(){   //pridame potravu na nahodne souradnice
        while(!gui.pridejPotravu((int)(Math.random()*sirka), (int)(Math.random()*sirka))){
        }
    }
}
