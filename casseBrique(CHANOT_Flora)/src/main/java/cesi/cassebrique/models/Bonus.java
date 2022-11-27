package cesi.cassebrique.models;

import java.awt.*;

public class Bonus extends Rond{
    public final static int TIMER_DEPART = 1000;
    private int type;
    private boolean actif; /* Affiche ou non le bonus */
    private int timerEffet;

    public Bonus(int type, int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.type = type;
        this.actif = true;
        this.timerEffet = TIMER_DEPART;
        this.diametre = 20;

        switch (this.type){
            case 1:
                this.couleur = Color.GREEN; /* Vitesse++ */
                break;
            case 2:
                this.couleur = Color.MAGENTA; /* Barre plus grande */
        }
    }

    public int getType() {
        return type;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public int getTimerEffet() {
        return timerEffet;
    }

    public void setTimerEffet(int timerEffet) {
        this.timerEffet = timerEffet;
    }
}
