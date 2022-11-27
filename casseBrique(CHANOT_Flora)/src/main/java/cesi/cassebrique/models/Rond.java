package cesi.cassebrique.models;

import java.awt.*;

public class Rond extends Sprite {
    protected int diametre;

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(positionX,positionY, diametre,diametre);
    }

    public int getDiametre() {
        return diametre;
    }

    public void setDiametre(int diametre) {
        this.diametre = diametre;
    }
}
