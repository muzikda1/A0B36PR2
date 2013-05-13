/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package had.hraci;

import had.snake.Snake;
import java.awt.Color;

/**
 *
 * @author David
 * 
 * Trida predstavuje abstraktniho hrace, od ktereho dedi jak lide tak boti
 * 
 */
public abstract class AbstraktniHrac {
    
    private Color barva;
    private int skore;
    private int delka;
    private Snake had;
    
    public AbstraktniHrac(){
        this.skore = 0;
    }
    
    public abstract int tah();
    
    public int getSkore(){
        return skore;
    }

    public Color getBarva() {
        return barva;
    }
    
    public void setBarva(Color barva){
        this.barva = barva;
    }
    
}
