package cesi.cassebrique.models;

import java.awt.*;

public class Carre extends Sprite{

    protected int largeur;

    public Carre(int positionX, int positionY, int largeur) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.largeur = largeur;
    }

    public void dessiner(Graphics2D dessin){
        dessin.setColor(couleur);
        dessin.fillRect(this.positionX, this.positionY, this.largeur, this.largeur);
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
