/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Controller;

import finnece.Modele.EceMan;
import finnece.Modele.Plateau;

import java.util.Scanner;

/**
 *
 * @author benja
 */
public class ControleurPlateau {
    
    private EceMan eceman;
    private Plateau plateauJeux;
    

    public ControleurPlateau(EceMan eceman, Plateau plateauJeux){
        this.eceman = eceman;
        this.plateauJeux = plateauJeux;
    }
    
    
    
    
  
    
    public boolean modifierMap(EceMan Personnage, Scanner clavier) {

        boolean validationTouche = false;

        char toucheDeplacement;

        int lastposX = Personnage.getXPerso();
        int lastposY = Personnage.getYPerso();

        System.out.print("Déplacement : ");
        toucheDeplacement = clavier.next().charAt(0);
        if (toucheDeplacement == 'p') {
            return true;
        }
        validationTouche = deplacerPersonnage(toucheDeplacement);

        //MAJ position Eceman
        if (validationTouche == true) {

            if ("M".equals(plateauJeux.getPlateau()[Personnage.getXPerso()][Personnage.getYPerso()])) {
                Personnage.setXperso(lastposX);
                Personnage.setYperso(lastposY);
                System.out.println("Déplacement impossible");
                return false;

            } else if ("G".equals(plateauJeux.getPlateau()[Personnage.getXPerso()][Personnage.getYPerso()])) {
                plateauJeux.getPlateau()[Personnage.getXPerso()][Personnage.getYPerso()] = Personnage.getSymbole();
                plateauJeux.getPlateau()[lastposX][lastposY] = "G";
                return false;

            } else if ("O".equals(plateauJeux.getPlateau()[Personnage.getXPerso()][Personnage.getYPerso()])) {
                plateauJeux.getPlateau()[Personnage.getXPerso()][Personnage.getYPerso()] = Personnage.getSymbole();
                plateauJeux.getPlateau()[lastposX][lastposY] = "G";
                Personnage.setScore();
                return true;
            }
        }
        return false;
    }
    
    boolean deplacerPersonnage(char direction) {

        switch (direction) {
            case 'z': { //déplacement vers le haut
                eceman.setXperso(eceman.getXPerso()-1);
                return true;
            }
            case 's': { //déplacement vers le bas
                eceman.setXperso(eceman.getXPerso()+1);
                return true;
            }
            case 'q': {//déplacement vers la gauche
                eceman.setYperso(eceman.getYPerso()-1);
                return true;
            }
            case 'd': { //déplacement vers la droite
                eceman.setYperso(eceman.getYPerso()+1);
                return true;
            }
            default: {
                System.out.println("Veuillez réessayer ! \n Appuyer sur z pour aller vers le haut; s vers le bas ; q vers la gauche et d vers lz droite");
                return false;
            }
        }
    }
    
    
    
}
