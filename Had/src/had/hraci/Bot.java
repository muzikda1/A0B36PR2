/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package had.hraci;

import had.gui.Gui;
import had.snake.Snake.Telo;
import had.spolecne.Smer;
import java.util.Map;

/**
 *
 * @author David
 * 
 * Implementace hrace s umelou inteligenci
 * 
 */
public class Bot extends AbstraktniHrac{

    private Gui rozhrani;
    private Smer smer = Smer.NAHORU;
    private int targety, targetx;

    public Bot(String name, Gui rozhrani) {
        super(name, 15, 15);
        this.rozhrani = rozhrani;
    }
    
    @Override
    public void tah() {
        this.had.posunHada(getSmer());
    }
    
    private Smer getSmer(){
        if(this.had.hlava.dalsi==null)return Smer.NAHORU;
        if(this.had.hlava.x-targetx == 0 && this.had.hlava.y-targety > 0){ //nahoru
            if(goUp()){
                return Smer.NAHORU;
            }
            if(goLeft()){
                return Smer.DOLEVA;
            }
            if(goRight()){
                return Smer.DOPRAVA;
            }
            if(goDown()){
                return Smer.DOLU;
            }
        }
        if(this.had.hlava.x-targetx < 0 && this.had.hlava.y-targety > 0){ //doprava nahoru
            if(goUp()){
                return Smer.NAHORU;
            }
            if(goRight()){
                return Smer.DOPRAVA;
            }
            if(goLeft()){
                return Smer.DOLEVA;
            }
            if(goDown()){
                return Smer.DOLU;
            }
        }
        if(this.had.hlava.x-targetx < 0 && this.had.hlava.y-targety == 0){ //doprava
            if(goRight()){
                return Smer.DOPRAVA;
            }
            if(goUp()){
                return Smer.NAHORU;
            }
            if(goDown()){
                return Smer.DOLU;
            }
            if(goLeft()){
                return Smer.DOLEVA;
            }
        }
        if(this.had.hlava.x-targetx < 0 && this.had.hlava.y-targety < 0){ //doprava dolu
            if(goRight()){
                return Smer.DOPRAVA;
            }
            if(goDown()){
                return Smer.DOLU;
            }
            if(goUp()){
                return Smer.NAHORU;
            }
            if(goLeft()){
                return Smer.DOLEVA;
            }
        }
        if(this.had.hlava.x-targetx == 0 && this.had.hlava.y-targety < 0){ //dolu
            if(goDown()){
                return Smer.DOLU;
            }
            if(goRight()){
                return Smer.DOPRAVA;
            }
            if(goLeft()){
                return Smer.DOLEVA;
            }
            if(goUp()){
                return Smer.NAHORU;
            }
        }
        if(this.had.hlava.x-targetx > 0 && this.had.hlava.y-targety < 0){ //doleva dolu
            if(goDown()){
                return Smer.DOLU;
            }
            if(goLeft()){
                return Smer.DOLEVA;
            }
            if(goRight()){
                return Smer.DOPRAVA;
            }
            if(goUp()){
                return Smer.NAHORU;
            }
        }
        if(this.had.hlava.x-targetx > 0 && this.had.hlava.y-targety == 0){ //doleva
            if(goLeft()){
                return Smer.DOLEVA;
            }
            if(goDown()){
                return Smer.DOLU;
            }
            if(goUp()){
                return Smer.NAHORU;
            }
            if(goRight()){
                return Smer.DOPRAVA;
            }
        }
        if(this.had.hlava.x-targetx > 0 && this.had.hlava.y-targety > 0){ //doleva nahoru
            if(goLeft()){
                return Smer.DOLEVA;
            }
            if(goUp()){
                return Smer.NAHORU;
            }
            if(goDown()){
                return Smer.DOLU;
            }
            if(goRight()){
                return Smer.DOPRAVA;
            }
        }
        return Smer.DOLEVA; // goDoprdele() (neboli nemame kam jit)
    }
    
    private boolean goUp(){
        if((this.had.hlava.y-1 > -1) &&
           (this.rozhrani.herniPlan[this.had.hlava.x][this.had.hlava.y-1] == -1) ||
           (this.rozhrani.herniPlan[this.had.hlava.x][this.had.hlava.y-1] == 0)){
            return true;
        }else return false;
    }
    
    private boolean goDown(){
        if((this.had.hlava.y+1 < this.rozhrani.herniPlan[0].length) &&
           (this.rozhrani.herniPlan[this.had.hlava.x][this.had.hlava.y+1] == -1) ||
           (this.rozhrani.herniPlan[this.had.hlava.x][this.had.hlava.y+1] == 0)){
            return true;
        }else return false;
    }
    
    private boolean goLeft(){
        if((this.had.hlava.x-1 > -1) &&
           (this.rozhrani.herniPlan[this.had.hlava.x-1][this.had.hlava.y] == -1) ||
           (this.rozhrani.herniPlan[this.had.hlava.x-1][this.had.hlava.y] == 0)){
            return true;
        }else return false;
    }
    
    private boolean goRight(){
        if((this.had.hlava.x+1 < this.rozhrani.herniPlan.length) &&
           (this.rozhrani.herniPlan[this.had.hlava.x+1][this.had.hlava.y] == -1) ||
           (this.rozhrani.herniPlan[this.had.hlava.x+1][this.had.hlava.y] == 0)){
            return true;
        }else return false;
    }

    public void setTarget(int x, int y) {
        targety = y;
        targetx = x;
    }

    
    
}
