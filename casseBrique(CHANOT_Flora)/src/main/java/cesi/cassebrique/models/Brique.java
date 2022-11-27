package cesi.cassebrique.models;

import java.awt.*;

public class Brique extends Rectangle{
    private int vie;
    private Color couleur;

    public Brique(int positionX, int positionY, int largeur, int hauteur) {
        super(positionX, positionY, largeur, hauteur);

        do{
            this.vie = (int)(Math.random() * 10);
        }while(this.vie == 0 || this.vie > 3);

        changerCouleur();
    }

    public int toucher(){
        this.vie--;
        changerCouleur();
        return this.vie;
    }

    private void changerCouleur(){
        switch (this.vie){
            case 3:
                this.setCouleur(Color.RED);
                break;
            case 2:
                this.setCouleur(Color.ORANGE);
                break;
            case 1 :
                this.setCouleur(Color.YELLOW);
                break;
        }
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    @Override
    public Color getCouleur() {
        return couleur;
    }
}
