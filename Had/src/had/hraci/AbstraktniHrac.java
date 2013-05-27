/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Had.hraci;

import Had.snake.Snake;
import Had.spolecne.Smer;
import java.awt.Color;

/**
 *
 * @author David
 * 
 * Trida predstavuje abstraktniho hrace, od ktereho dedi jak lide tak boti
 * 
 */
public abstract class AbstraktniHrac {
    
    protected Color barva;
    protected int skore;
    protected int delka;
    protected Snake had;
    
    public AbstraktniHrac(int initX, int initY){
        this.skore = 0;
        this.had = new Snake(initX, initY);
    }
    
    public abstract void tah();
    
    public Snake getHad(){
        return had;
    }
    
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
