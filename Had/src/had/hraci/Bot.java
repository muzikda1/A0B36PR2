/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Had.hraci;

import Had.gui.Gui;
import Had.snake.Snake.Telo;
import Had.spolecne.Smer;
import java.util.Map;

/**
 *
 * @author David
 * 
 * Implementace hrace s umelou inteligenci
 * 
 */
public class Bot extends AbstraktniHrac{

    Gui rozhrani;
    Smer smer = Smer.NAHORU;
    Map open, closed;

    public Bot(Gui rozhrani) {
        super(10, 10);
        this.rozhrani = rozhrani;
    }
    
    @Override
    public void tah() {
        this.had.posunHada(smer);
    }
    
    private Smer getSmer(Telo telo1, Telo telo2){
        if(telo1.x-telo2.x == 1) return Smer.DOLEVA;
        if(telo1.x-telo2.x == -1) return Smer.DOPRAVA;
        if(telo1.y-telo2.y == 1) return Smer.DOLU;
        return Smer.NAHORU;
    }

    
    
}
