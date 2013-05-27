/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Had.hraci;

import Had.gui.Gui;
import Had.snake.Snake.Telo;
import Had.spolecne.Smer;
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

    public Hrac(Gui rozhrani) {
        super(10, 10);
        this.rozhrani = rozhrani;
        rozhrani.addKeyListener(klavesnice);
    }

    public KeyListener klavesnice = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'p') { // pause
                rozhrani.pause();
            } else if (e.getKeyChar() == 'w') { // up
                smer = Smer.NAHORU;
            } else if (e.getKeyChar() == 's') { // down
                smer = Smer.DOLU;
            } else if (e.getKeyChar() == 'a') { // left
                smer = Smer.DOLEVA;
            } else if (e.getKeyChar() == 'd') { // right
                smer = Smer.DOPRAVA;
            } else if (e.getKeyChar() == 'q') { // quit
                rozhrani.askToQuit();
            }
        }
    };

    @Override
    public void tah() {
        this.had.posunHada(smer);
    }
    
}
