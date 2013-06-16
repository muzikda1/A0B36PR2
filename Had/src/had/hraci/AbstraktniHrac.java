/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package had.hraci;

import had.snake.Snake;
import had.spolecne.Smer;
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
    protected String name;
    
    public AbstraktniHrac(String name, int initX, int initY){
        this.name = name;
        this.skore = 0;
        this.had = new Snake(initX, initY);
    }
    
    public String getName(){
        return name;
    }
    
    public abstract void tah();
    
    public Snake getHad(){
        return had;
    }
    
    public void incrementSkore(){
        skore++;
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
