/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package had.gui;

import had.Logika;
import had.snake.Snake;
import had.snake.Snake.Telo;
import had.hraci.AbstraktniHrac;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class Gui extends JFrame{
    
    Logika herniLogika;
    JPanel herniPolicka;
    ArrayList<Gui.Policko> policka;
    
    private class Policko extends JPanel{
        public Policko(){
            super();
            setBackground(Color.WHITE);
        }
    }
    
    
    protected int[][] herniPlan;
        //-1 pro potravu
        //0 pro prazdne pole
        //N pro hada - Ntym tahem bude pryc - aby byl bot inteligentejsi
    
    public Gui(Logika logika, int sirka){
        herniLogika = logika;
        herniPlan = new int[sirka][sirka];
        policka = new ArrayList<Gui.Policko>();
        for (int i = 0; i < sirka*sirka; i++) {
            policka.add(new Gui.Policko());
        }
        inicializuj();
    }
    
    public void pridejPotravu(int x, int y){
        if(herniPlan[x][y]==0){
            herniPlan[x][y]=-1;
            vykresli(x, y, Color.GREEN);
        }
    }
    
    public void updatujHada(AbstraktniHrac hrac){
        Snake had = hrac.getHad();
        int delka = had.getDelka();
        Telo telo = had.hlava;
        System.out.println("hlava na "+telo.x+" "+telo.y);
        herniPlan[telo.x][telo.y]=delka;
        vykresli(telo.x, telo.y, hrac.getBarva()); //nakreslime hlavu
        while(true){
            herniPlan[telo.x][telo.y]=delka;    //"nakreslime" postupne celeho hada
            delka--;
            if(telo == had.ocas) break;
            else telo = telo.dalsi;
        }
        if(had.stin!=null){ //prekreslime prostor, odkud had odesel
            telo = had.stin;
            herniPlan[telo.x][telo.y]=0;
            vykresli(telo.x, telo.y, hrac.getBarva());
        }
    }
    
    public void pause(){
        System.out.println("pause");
        if(herniLogika.tajmr.isRunning()){
            herniLogika.tajmr.stop();
        }else{
            herniLogika.tajmr.start();
        }
    }

    public void askToQuit() {
        throw new UnsupportedOperationException("Not yet implemented");
    }            
    
    private void inicializuj() {
        setTitle("Had");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        add(herniPolicka = new JPanel(new GridLayout(herniPlan[0].length, herniPlan.length)), BorderLayout.CENTER);
        for (Gui.Policko policko : policka) {
            herniPolicka.add(policko);
        }
    }

    private void vykresli(int x, int y, Color color) {
        switch (herniPlan[x][y]) {
            case -1:
                policka.get(y*herniPlan.length+x).setBackground(Color.GREEN);
                break;
            case 0:
                policka.get(y*herniPlan.length+x).setBackground(Color.WHITE);
                break;
            default:
                policka.get(y*herniPlan.length+x).setBackground(color);
        }
    }
    
}
