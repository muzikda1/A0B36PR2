/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package had.snake;

import had.spolecne.Smer;

/**
 *
 * @author David
 * 
 * trida predstavuje reprezentaci hada v pameti, je to vlastne spojovy seznam
 */
public class Snake {
    
    public class Telo{
        public Snake.Telo predchozi, dalsi;
        public int x, y;
        
        public Telo(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public boolean isHead(){
            return predchozi==null ? true : false;
        }
        
        public boolean isTail(){
            return dalsi==null ? true : false;
        }
    }
    
    public Snake.Telo hlava;
    public Snake.Telo ocas;
    public Snake.Telo stin;   //"stin" ocasu, tady se prekresluje ocas hada na volnou plochu
    private int delka, toIncrement;
    
    public Snake(int initX, int initY){
        this.toIncrement = 5;   //tady si nadefinujeme pocatecni delku hada
        this.delka = 1; //zezacatku je had dlouhy 1 (jen hlava), prodluzuje se kazdym krokem az do pocatecni delky
        this.hlava = new Snake.Telo(initX, initY);
        this.ocas = this.hlava;
    }

    public int getDelka() {
        return delka;
    }
    
    public void zvetsi(int increment){
        this.toIncrement += increment;
    }
    
    public void posunHada(Smer smer){
        Snake.Telo novaHlava = new Snake.Telo(0, 0);    //musime inicializovat
        switch (smer) {
            case NAHORU:
                novaHlava = new Snake.Telo(this.hlava.x, this.hlava.y-1); //vytvorime novou hlavu v pozadovanem smeru
                break;
            case DOLU:
                novaHlava = new Snake.Telo(this.hlava.x, this.hlava.y+1);
                break;
            case DOLEVA:
                novaHlava = new Snake.Telo(this.hlava.x-1, this.hlava.y);
                break;
            case DOPRAVA:
                novaHlava = new Snake.Telo(this.hlava.x+1, this.hlava.y);
                break;
        }
        novaHlava.dalsi = this.hlava;   //novou hlavu zapojime do seznamu
        this.hlava = novaHlava;
        this.hlava.dalsi.predchozi = this.hlava;
        if(toIncrement>0){
            this.toIncrement --; //had se prodluzuje
            this.delka ++;
        }else{
            this.stin = this.ocas;
            this.ocas = this.ocas.predchozi; //had se neprodluzuje, urizneme ocas
            this.ocas.dalsi=null;
        }
    }
    
}
