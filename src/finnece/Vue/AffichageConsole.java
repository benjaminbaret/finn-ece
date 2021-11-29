/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Vue;

import finnece.Modele.EceMan;
import finnece.Modele.Plateau;

/**
 *
 * @author benja
 */
public class AffichageConsole {
    
    private Plateau plateau;
    
    public AffichageConsole (Plateau plateau){
        this.plateau = plateau;
    }
    
    public void update(Plateau tab){
        this.plateau = tab;
    }
    
    public void afficherMap(EceMan Personnage) {
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n            Name : " + Personnage.getNom());
        System.out.println("Score : " + Personnage.getScore() + "                  Niveau : " + Personnage.getLevel());
        for (int i = 0; i < Plateau.HEIGHT; i++) {
            for (int j = 0; j < Plateau.WIDTH; j++) {
                
                char var = plateau.getPlateau()[i][j];
                
                if ("G".equals(var)) {
                    System.out.print("\033[34m" + var+ " ");
                } else if ("P".equals(var)) {
                    System.out.print("\033[33m" + var + " ");
                } else if ("X".equals(var)) {
                    System.out.print("\033[36m" + var + " ");
                } else if ("O".equals(var)) {
                    System.out.print("\033[31m" + var + " ");
                } else {
                    System.out.print("\033[0m" + var + " ");
                }

            }
            System.out.println(" ");
        }
        System.out.print("\033[0m");
    }
    
}
