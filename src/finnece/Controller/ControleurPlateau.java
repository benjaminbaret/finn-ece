/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Controller;

import finnece.Modele.Banquise;
import finnece.Modele.EceMan;
import finnece.Modele.Plateau;

import java.util.Scanner;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author benja
 */
public class ControleurPlateau implements KeyListener {

    private Plateau plateauJeu;

    public ControleurPlateau(Plateau plateauJeu, EceMan personnage) {

        this.plateauJeu = plateauJeu;

    }

    public Plateau modifierMap(EceMan Personnage, Scanner clavier) {

        //System.out.print("Déplacement : ");
        //  toucheDeplacement = clavier.next().charAt(0);
        return plateauJeu;
    }

    @Override
    public void keyPressed(KeyEvent ke) {

        int getXEceMan = plateauJeu.getXSymbol('P');
        int getYEceMan = plateauJeu.getYSymbol('P');
        String Bloc = " ";

        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP: { //déplacement vers le haut
                if ((plateauJeu.getPlateau()[getXEceMan - 1][getYEceMan]) != 'M') {
                    if ((plateauJeu.getPlateau()[getXEceMan - 1][getYEceMan]) == 'D') {
                        if ((plateauJeu.getPlateau()[getXEceMan - 2][getYEceMan]) != 'M') {
                            Bloc = "murdeplacable";
                            plateauJeu.changeSymbol(getXEceMan, getYEceMan, "-X", Bloc);
                            Bloc = " ";
                        }
                    } else {
                        plateauJeu.changeSymbol(getXEceMan, getYEceMan, "-X", Bloc);
                    }
                }
                break;
            }
            case KeyEvent.VK_DOWN: { //déplacement vers le bas
                if ((plateauJeu.getPlateau()[getXEceMan + 1][getYEceMan]) != 'M') {
                    if ((plateauJeu.getPlateau()[getXEceMan + 1][getYEceMan]) == 'D') {
                        if ((plateauJeu.getPlateau()[getXEceMan + 2][getYEceMan]) != 'M') {
                            Bloc = "murdeplacable";
                            plateauJeu.changeSymbol(getXEceMan, getYEceMan, "+X", Bloc);
                            Bloc = " ";
                        }
                    } else {
                        plateauJeu.changeSymbol(getXEceMan, getYEceMan, "+X", Bloc);
                    }
                }
                break;
            }
            case KeyEvent.VK_LEFT: {//déplacement vers la gauche
                if ((plateauJeu.getPlateau()[getXEceMan][getYEceMan - 1]) != 'M') { // ajouter les vérifs pour ne pas être sur pointeur null ... 

                    if ((plateauJeu.getPlateau()[getXEceMan][getYEceMan - 1]) == 'D') {
                        if ((plateauJeu.getPlateau()[getXEceMan][getYEceMan - 2]) != 'M') {
                            Bloc = "murdeplacable";
                            plateauJeu.changeSymbol(getXEceMan, getYEceMan, "-Y", Bloc);
                            Bloc = " ";
                        }
                    } else {
                        plateauJeu.changeSymbol(getXEceMan, getYEceMan, "-Y", Bloc);
                    }
                }
                break;
            }
            case KeyEvent.VK_RIGHT: { //déplacement vers la droite

                if ((plateauJeu.getPlateau()[getXEceMan][getYEceMan + 1]) != 'M') {
                    if ((plateauJeu.getPlateau()[getXEceMan][getYEceMan + 1]) == 'D') {
                        if ((plateauJeu.getPlateau()[getXEceMan][getYEceMan + 2]) != 'M') {
                        Bloc = "murdeplacable";
                        plateauJeu.changeSymbol(getXEceMan, getYEceMan, "+Y", Bloc);
                        Bloc = " ";
                    }
                } else {
                    plateauJeu.changeSymbol(getXEceMan, getYEceMan, "+Y", Bloc);
                }
            }

            break;
        }
    

default: {
                System.out.println("Veuillez réessayer / Déplacement impossible ! \n Appuyer sur z pour aller vers le haut; s vers le bas ; q vers la gauche et d vers lz droite");
            }

        }

    }

    @Override
        public void keyTyped(KeyEvent ke) {
    }

    @Override
        public void keyReleased(KeyEvent ke) {
    }

}
