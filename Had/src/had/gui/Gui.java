/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package had.gui;

import had.Logika;
import had.Nastaveni;
import had.snake.Snake;
import had.snake.Snake.Telo;
import had.hraci.AbstraktniHrac;
import had.hraci.Bot;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class Gui extends JFrame{
    
    private Logika herniLogika;
    private JPanel herniPolicka;
    private ArrayList<Gui.Policko> policka;
    public ArrayList<Bot> boti;
    
    private class Policko extends JPanel{
        public Policko(){
            super();
            setBackground(Color.WHITE);
        }
    }
    
    
    public int[][] herniPlan;
        //-1 pro potravu
        //0 pro prazdne pole
        //N pro hada - Ntym tahem bude pryc - aby byl bot inteligentejsi
    
    public Gui(Logika logika, int sirka, ArrayList<Bot> boti){
        herniLogika = logika;
        herniPlan = new int[sirka][sirka];
        policka = new ArrayList<Gui.Policko>();
        for (int i = 0; i < sirka*sirka; i++) {
            policka.add(new Gui.Policko());
        }
        inicializuj();
    }
    
    public boolean pridejPotravu(int x, int y){
        if(herniPlan[x][y]==0){
            herniPlan[x][y]=-1;
            vykresli(x, y, Color.GREEN);
            for (Bot bot : boti) {
                bot.setTarget(x,y);
            }
            return true;
        }
        return false;
    }
    
    public void updatujHada(AbstraktniHrac hrac){
        Snake had = hrac.getHad();
        int delka = had.getDelka();
        Telo telo;
        if(had.stin!=null){ //prekreslime prostor, odkud had odesel
            telo = had.stin;
            herniPlan[telo.x][telo.y]=0;
            vykresli(telo.x, telo.y, hrac.getBarva());
        }
        telo = had.hlava;
        System.out.println("hlava na "+telo.x+" "+telo.y);
        if(telo.x < 0 ||
           telo.y < 0 ||
           telo.x >= herniPlan.length ||
           telo.y >= herniPlan[0].length ||
           herniPlan[telo.x][telo.y] != 0){
            if(telo.x > -1 &&
               telo.y > -1 &&
               telo.x < herniPlan.length &&
               telo.y < herniPlan[0].length && 
               herniPlan[telo.x][telo.y] == -1){
                hrac.incrementSkore();
                herniLogika.hrneckuVar();   //pošleme tam novou stravu
                hrac.getHad().zvetsi(Nastaveni.ZVETSENI_HADA);
            }else{
                hracProhral(hrac);
                return;
            }
        }
        herniPlan[telo.x][telo.y]=delka;
        vykresli(telo.x, telo.y, hrac.getBarva()); //nakreslime hlavu
        while(true){
            herniPlan[telo.x][telo.y]=delka;    //"nakreslime" postupne celeho hada
            delka--;
            if(telo == had.ocas) break;
            else telo = telo.dalsi;
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

    private void hracProhral(AbstraktniHrac hrac) {
        herniLogika.tajmr.stop();
        Object[] options = {"Ok"};  //dialogove okno
        JOptionPane.showOptionDialog(this,
        "Konec hry\n Prohrál "+hrac.getName()+"!",
        "Konec hry",
        JOptionPane.OK_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        options,
        options[0]);
        setVisible(false);
        System.exit(0);
    }
    
}
