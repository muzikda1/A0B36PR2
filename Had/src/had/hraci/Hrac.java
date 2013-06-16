/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package had.hraci;

import had.gui.Gui;
import had.snake.Snake.Telo;
import had.spolecne.Smer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author David
 * 
 * Implementace ziveho hrace
 * 
 */
public class Hrac extends AbstraktniHrac{

    Gui rozhrani;
    Smer smer = Smer.NAHORU;
    Smer posledniSmer;

    public Hrac(String name, Gui rozhrani) {
        super(name, 10, 10);
        this.rozhrani = rozhrani;
        rozhrani.addKeyListener(klavesnice);
    }

    public KeyListener klavesnice = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'p') { // pause
                System.out.println("pauza zavolana");
                rozhrani.pause();
            } else if (e.getKeyChar() == 'w' && posledniSmer != Smer.DOLU) { // up
                smer = Smer.NAHORU;
            } else if (e.getKeyChar() == 's' && posledniSmer != Smer.NAHORU) { // down
                smer = Smer.DOLU;
            } else if (e.getKeyChar() == 'a' && posledniSmer != Smer.DOPRAVA) { // left
                smer = Smer.DOLEVA;
            } else if (e.getKeyChar() == 'd' && posledniSmer != Smer.DOLEVA) { // right
                smer = Smer.DOPRAVA;
            } else if (e.getKeyChar() == 'q') { // quit
                rozhrani.askToQuit();
            }
        }
    };

    @Override
    public void tah() {
        this.had.posunHada(smer);
        posledniSmer = smer;
    }
    
}
