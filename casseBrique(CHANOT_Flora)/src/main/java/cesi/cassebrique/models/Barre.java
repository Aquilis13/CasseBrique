package cesi.cassebrique.models;

import java.awt.*;

public class Barre extends Rectangle{

    public Barre(int positionX, int positionY, int largeur, int hauteur) {
        super(positionX, positionY, largeur, hauteur);
    }

    public void deplacementDroite(int bordure){
        if(this.positionX < bordure){
            positionX = positionX + 10;
        }
    }

    public void deplacementGauche(){
        if(this.positionX > 0 - this.largeur/2){
            positionX = positionX - 10;
        }
    }
}
