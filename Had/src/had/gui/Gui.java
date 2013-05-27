/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Had.gui;

import Had.snake.Snake;
import Had.snake.Snake.Telo;
import Had.hraci.AbstraktniHrac;
import Had.hraci.Bot;
import Had.hraci.Hrac;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class Gui extends JFrame{
    
    ArrayList<AbstraktniHrac> hraci;
    
    protected int[][] herniPlan;
        //-1 pro potravu
        //0 pro prazdne pole
        //N pro hada - Ntym tahem bude pryc - aby byl bot inteligentejsi
    
    public Gui(){
        
        inicializuj();
        hraci = new ArrayList<AbstraktniHrac>();
        herniPlan = new int[20][20];
        
    }
    
    private void novaHra(){
        hraci.add(new Hrac(this));
        hraci.add(new Bot(this));
        hrneckuVar();
    }
    
    public void hrneckuVar(){   //pridame potravu na nahodne souradnice
        while(true){
            int x = (int)(Math.random()*herniPlan.length);
            int y = (int)(Math.random()*herniPlan[0].length);
            if(herniPlan[x][y]==0){
                herniPlan[x][y]=-1;
                vykresli(x, y);
                return;
            }
        }
    }
    
    private void kolo(){
        for (AbstraktniHrac hrac : hraci) {
            hrac.tah();
            updatujHada(hrac);
        }
    }
    
    private void updatujHada(AbstraktniHrac hrac){
        Snake had = hrac.getHad();
        int delka = had.getDelka();
        Telo telo = had.hlava;
        herniPlan[telo.x][telo.y]=delka;
        vykresli(telo.x, telo.y);
        while(true){
            herniPlan[telo.x][telo.y]=delka;    //nakreslime postupne celeho hada
            delka--;
            if(telo == had.ocas) break;
            else telo = telo.dalsi;
        }
        if(had.stin!=null){ //prekreslime prostor, odkud had odesel
            telo = had.stin;
            herniPlan[telo.x][telo.y]=0;
            vykresli(telo.x, telo.y);
        }
    }
    
    public void pause(){
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void askToQuit() {
        throw new UnsupportedOperationException("Not yet implemented");
    }            
    
    private void inicializuj() {
        throw new UnsupportedOperationException("Not yet implemented"); //inicializace gui
    }

    private void vykresli(int x, int y) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
