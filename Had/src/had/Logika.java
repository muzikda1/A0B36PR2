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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author David
 */
public class Logika {
    private ArrayList<AbstraktniHrac> hraci;
    private Gui gui;
    private int sirka = 20;
    public Timer tajmr;
    
    public Logika(){
        hraci = new ArrayList<AbstraktniHrac>();
        gui = new Gui(this, sirka);
        gui.setVisible(true);
        novaHra();
    }
    
    private void novaHra(){
        hraci.add(new Hrac(gui));
        hraci.add(new Bot(gui));
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
        int x = (int)(Math.random()*sirka);
        int y = (int)(Math.random()*sirka);
        gui.pridejPotravu(x, y);
    }
}
