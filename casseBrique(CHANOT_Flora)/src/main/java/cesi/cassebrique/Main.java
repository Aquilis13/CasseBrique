package cesi.cassebrique;

import cesi.cassebrique.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main extends Canvas {
    public final static int LARGEUR = 500;
    public final static int HAUTEUR = 600;
    private int positionX = (this.LARGEUR/2) - 25;
    private int positionY = this.HAUTEUR - 100;
    private int limiteLigneBrique = 3;
    private Barre barre = new Barre(positionX - 60, positionY, 120, 7);
    private ArrayList<Bonus> lesBonus = new ArrayList<>();
    private ArrayList<Brique> lesBriques = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        new Main();
    }

    public Main() throws InterruptedException {
        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        setBounds(0, 0, LARGEUR, HAUTEUR);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();
        fenetre.addKeyListener(eventKey());

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        setFocusable(false);

        demarrer();
    }

    public void demarrer() throws InterruptedException {
        Balle balle = new Balle(positionX, positionY - 30, 25, 1, 1);
        barre.setCouleur(Color.black);

        while(true && this.gameOver(balle) == false){
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            dessin.setColor(Color.WHITE);
            dessin.fillRect(0,0, LARGEUR, HAUTEUR);

            balle.mouvement();
            balle.collisionBord();
            balle.collisionBarre(barre);
            Brique brique = balle.collisionBrique(lesBriques);

            if(brique != null){
                for (Brique b : lesBriques) {
                    if(b == brique){
                        b.setVie(b.toucher());
                    }
                }
            }

            Bonus bonus = balle.collisionBonus(lesBonus);
            if(bonus != null){
                for (Bonus b : lesBonus) {
                    if(b == bonus){
                        b.setActif(false);
                    }
                }
            }

            balle.dessiner(dessin);
            barre.dessiner(dessin);

            generateurBrique(dessin);
            generateurBonus(dessin, balle);
            //-----------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    private void generateurBrique(Graphics2D dessin){
        int briquePosX = 10;
        int briquePosY = 10;
        int briqueLargeur = 50;
        int briqueHauteur = 10;

        while (limiteLigneBrique != 0){
            lesBriques.add(new Brique(briquePosX, briquePosY, briqueLargeur, briqueHauteur));

            briquePosX = briquePosX + briqueLargeur + 10;
            if(briquePosX + briqueLargeur >= LARGEUR){
                briquePosX = 10;
                briquePosY = briquePosY + briqueHauteur + 10;
                limiteLigneBrique--;
            }
        }

        ArrayList<Brique> briquesASupprimer = new ArrayList<>();
        for (Brique b: lesBriques) {
            if(b.getVie() < 1){
                briquesASupprimer.add(b);
            }else{
                b.dessiner(dessin);
            }
        }

        for(Brique b : briquesASupprimer){
            lesBriques.remove(b);
        }
    }

    private void generateurBonus(Graphics2D dessin, Balle balle){
        int ajBonus= (int)(Math.random() * 5000);
        int bonusPosX;
        int bonusPosY;

        if(ajBonus == 13 || ajBonus == 7){
            do{
                bonusPosX = (int)(Math.random() * LARGEUR);
            }while(bonusPosX > LARGEUR);

            do{
                bonusPosY = (int) (Math.random() * HAUTEUR);
            }while(bonusPosY > HAUTEUR || bonusPosY > barre.getPositionY());

            switch (ajBonus){
                case 13:
                    lesBonus.add(new Bonus(1, bonusPosX, bonusPosY));
                    break;
                case 7:
                    lesBonus.add(new Bonus(2, bonusPosX, bonusPosY));
                    break;
            }
        }

        ArrayList<Bonus> bonusASupprimer = new ArrayList<>();
        for (Bonus b: lesBonus) {
            if(b.isActif() == true){
                b.dessiner(dessin);
            }else{
                if(b.getTimerEffet() > 0){
                    if(b.getTimerEffet() == b.TIMER_DEPART) {
                        //Active le bonus
                        switch (b.getType()){
                            case 1:
                                    balle.setVitesseBalleX(balle.getVitesseBalleX()*2);
                                    balle.setVitesseBalleY(balle.getVitesseBalleY()*2);
                                break;
                            case 2:
                                barre.setLargeur(barre.getLargeur()+20);
                        }
                    }
                    b.setTimerEffet(b.getTimerEffet()-1);
                }else{
                    //Désactive le bonus
                    switch (b.getType()){
                        case 1:
                            balle.setVitesseBalleX(balle.getVitesseBalleX()/2);
                            balle.setVitesseBalleY(balle.getVitesseBalleY()/2);
                            break;
                        case 2:
                            barre.setLargeur(barre.getLargeur()-20);
                    }
                    bonusASupprimer.add(b);
                }
            }
        }

        //Supprime les bonus qui ont étaient ramassés et dont le timer c'est écoulés
        for (Bonus b: bonusASupprimer) {
            lesBonus.remove(b);
        }
    }

    private boolean gameOver(Balle balle){
       if(balle.getPositionY() > barre.getPositionY() + 20){
            System.out.println("\nGAME OVER \nRelancer le programme pour avoir une nouvelle partie.");
            return true;
       }else{
            return false;
       }
    }

    private KeyListener eventKey(){
        KeyListener action = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_RIGHT) {
                    barre.deplacementDroite(LARGEUR - barre.getLargeur()/2);
                }

                if (key == KeyEvent.VK_LEFT) {
                    barre.deplacementGauche();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        return action;
    }
}