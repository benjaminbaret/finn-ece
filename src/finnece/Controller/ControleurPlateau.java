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

        boolean validationTouche = false;

        char toucheDeplacement;

        int lastposX = plateauJeu.getXSymbol('A');
        int lastposY = plateauJeu.getYSymbol('A');
        System.out.println(lastposX);
        System.out.println(lastposY);

        System.out.print("Déplacement : ");
        toucheDeplacement = clavier.next().charAt(0);
        
        validationTouche = deplacerPersonnage(toucheDeplacement);

       
        return plateauJeu;
    }

    boolean deplacerPersonnage(char direction) {

        switch (direction) {
            case 'z': { //déplacement vers le haut
                if ((plateauJeu.getPlateau()[plateauJeu.getXSymbol('A')-1][plateauJeu.getYSymbol('A')]) != 'M') {
                    plateauJeu.changeSymbol(plateauJeu.getXSymbol('A'), plateauJeu.getYSymbol('A'), "-X");
                    return true;
                }
                break;
            }
            case 's': { //déplacement vers le bas
                if ((plateauJeu.getPlateau()[plateauJeu.getXSymbol('A')+1][plateauJeu.getYSymbol('A')]) != 'M') {
                    plateauJeu.changeSymbol(plateauJeu.getXSymbol('A'), plateauJeu.getYSymbol('A'), "+X");
                    return true;
                }
                break;
            }
            case 'q': {//déplacement vers la gauche
                if ((plateauJeu.getPlateau()[plateauJeu.getXSymbol('A')][plateauJeu.getYSymbol('A')-1]) != 'M') { // ajouter les vérifs pour ne pas être sur pointeur null ... 
                    plateauJeu.changeSymbol(plateauJeu.getXSymbol('A'), plateauJeu.getYSymbol('A'), "-Y");
                    return true;
                }
                break;
            }
            case 'd': { //déplacement vers la droite
                
                if ((plateauJeu.getPlateau()[plateauJeu.getXSymbol('A')][plateauJeu.getYSymbol('A')]+1) != 'M') {
                    plateauJeu.changeSymbol(plateauJeu.getXSymbol('A'), plateauJeu.getYSymbol('A'), "+Y");
                    return true;
                }
                break;
            }
            default: {
                System.out.println("Veuillez réessayer / Déplacement impossible ! \n Appuyer sur z pour aller vers le haut; s vers le bas ; q vers la gauche et d vers lz droite");
                return false;
            }

        }
        return false;
    }
    
}
