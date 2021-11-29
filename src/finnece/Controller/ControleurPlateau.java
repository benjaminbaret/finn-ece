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

/**
 *
 * @author benja
 */
public class ControleurPlateau {

    private Plateau plateauJeu;

    public ControleurPlateau(Plateau plateauJeu, EceMan personnage) {

        this.plateauJeu = plateauJeu;

    }

    public Plateau modifierMap(EceMan Personnage, Scanner clavier) {

        char toucheDeplacement;


        System.out.print("Déplacement : ");
        toucheDeplacement = clavier.next().charAt(0);

        deplacerPersonnage(toucheDeplacement);

        return plateauJeu;
    }

    void deplacerPersonnage(char direction) {
        int getXEceMan = plateauJeu.getXSymbol('P');
        int getYEceMan = plateauJeu.getYSymbol('P');

        switch (direction) {
            case 'z': { //déplacement vers le haut
                if ((plateauJeu.getPlateau()[getXEceMan - 1][getYEceMan]) != 'M') {
                    plateauJeu.changeSymbol(getXEceMan, getYEceMan, "-X");
                }
                break;
            }
            case 's': { //déplacement vers le bas
                if ((plateauJeu.getPlateau()[getXEceMan + 1][getYEceMan]) != 'M') {
                    plateauJeu.changeSymbol(getXEceMan, getYEceMan, "+X");
                }
                break;
            }
            case 'q': {//déplacement vers la gauche
                if ((plateauJeu.getPlateau()[getXEceMan][getYEceMan - 1]) != 'M') { // ajouter les vérifs pour ne pas être sur pointeur null ... 
                    plateauJeu.changeSymbol(getXEceMan, getYEceMan, "-Y");
                }
                break;
            }
            case 'd': { //déplacement vers la droite

                if ((plateauJeu.getPlateau()[getXEceMan][getYEceMan + 1]) != 'M') {
                    plateauJeu.changeSymbol(getXEceMan, getYEceMan, "+Y");
                }
                break;
            }
            default: {
                System.out.println("Veuillez réessayer / Déplacement impossible ! \n Appuyer sur z pour aller vers le haut; s vers le bas ; q vers la gauche et d vers lz droite");
            }

        }
    }

}
