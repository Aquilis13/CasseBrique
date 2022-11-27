package cesi.cassebrique.models;

import java.awt.*;

public class Rectangle extends Carre {
    protected int hauteur;

    public Rectangle(int positionX, int positionY, int largeur, int hauteur) {
        super(positionX, positionY, largeur);
        this.hauteur = hauteur;
    }

    public void dessiner(Graphics2D dessin){
        dessin.setColor(couleur);
        dessin.fillRect(this.positionX, this.positionY, this.largeur, this.hauteur);
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
