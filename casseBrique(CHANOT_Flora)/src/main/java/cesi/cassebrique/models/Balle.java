package cesi.cassebrique.models;

import cesi.cassebrique.Main;
import java.awt.*;
import java.util.ArrayList;

public class Balle extends Rond{
    private int vitesseBalleX;
    private int vitesseBalleY;

    public final static Color COULEUR_PAR_DEFAUT = Color.RED;

    public Balle(int positionX, int positionY, Color couleur, int diametre) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.couleur = couleur;
        this.diametre = diametre;
    }

    public Balle(int positionX, int positionY, int diametre,  int vitesseBalleX, int vitesseBalleY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesseBalleX = vitesseBalleX == 0 ? 1 : vitesseBalleX;
        this.vitesseBalleY = vitesseBalleY == 0 ? 1 : vitesseBalleY;
        this.diametre = diametre;
        this.couleur = COULEUR_PAR_DEFAUT;
    }

    public void mouvement() {
        positionX += vitesseBalleX;
        positionY += vitesseBalleY;
    }

    public void collisionBord(){
        //si la balle est arrivée à droite ou à gauche alors on inverse sa vitesse
        if(positionX >= Main.LARGEUR - this.diametre || positionX <= 0){
            vitesseBalleX *= -1; //vitesseBalleX = vitesseBalleX * -1
        }

        //haut du cadre OU bas du cadre
        if(positionY >= Main.HAUTEUR - this.diametre || positionY <= 0){
            vitesseBalleY *= -1;
        }
    }
    public void collisionBarre(Barre barre){
        int bordGauche = barre.getPositionX() - barre.getLargeur()/2;
        int bordDroit = barre.getPositionX() + barre.getLargeur();

        if(this.positionY + this.getDiametre()/2 == barre.getPositionY()){
            //colision entre le bord gauche ET le milieu de la barre
            if(this.positionX > bordGauche && this.positionX < bordGauche + barre.getLargeur()/2){
                vitesseBalleX *= -1;
                vitesseBalleY *= -1;
            }

            //colision entre le milieu ET le bord droit de la barre
            if(this.positionX > bordGauche + barre.getLargeur()/2 && this.positionX < bordDroit){
                vitesseBalleX *= 1;
                vitesseBalleY *= -1;
            }
        }
    }

    public Brique collisionBrique(ArrayList<Brique> lesBriques){
        Brique briqueRetour = null;
        int bordGauche;
        int bordDroit;

        for (Brique brique : lesBriques) {
            bordGauche = brique.getPositionX();
            bordDroit = brique.getPositionX() + brique.getLargeur();

            if(this.positionX > bordGauche && this.positionX < bordDroit){
                if(this.positionY == brique.getPositionY() || this.positionY == brique.getPositionY() + brique.getHauteur()){
                    vitesseBalleX *= -1;
                    vitesseBalleY *= -1;
                    briqueRetour = brique;
                }
            }
        }
        return briqueRetour;
    }

    public Bonus collisionBonus(ArrayList<Bonus> lesBonus){
        Bonus bonusRetour = null;

        for (Bonus bonus : lesBonus) {
            int gaucheBonus = bonus.getPositionX();
            int droitBonus = bonus.getPositionX() + bonus.getDiametre();
            int hautBonus = bonus.getPositionY() + (bonus.getDiametre()/2);
            int basBonus = bonus.getPositionY() - (bonus.getDiametre()/2);

            int gaucheBalle = this.getPositionX();
            int droiteBalle = this.getPositionX()+this.diametre;
            int hautBalle = this.positionY + this.diametre/2;
            int basBalle = this.positionY - this.diametre/2;

            if(gaucheBalle < gaucheBonus && droiteBalle > gaucheBonus || gaucheBalle < droitBonus && droiteBalle > droitBonus){
                if(hautBalle > hautBonus && basBalle < hautBonus || hautBalle > basBonus && basBalle < basBonus){
                    bonusRetour = bonus;
                }
            }
        }
        return bonusRetour;
    }


    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getVitesseBalleX() {
        return vitesseBalleX;
    }

    public void setVitesseBalleX(int vitesseBalleX) {
        this.vitesseBalleX = vitesseBalleX;
    }

    public int getVitesseBalleY() {
        return vitesseBalleY;
    }

    public void setVitesseBalleY(int vitesseBalleY) {
        this.vitesseBalleY = vitesseBalleY;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
