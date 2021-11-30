/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Vue;

import finnece.Modele.EceMan;
import finnece.Modele.Plateau;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benja
 */
public class AffichageConsole {

    private Plateau plateau;

    public AffichageConsole(Plateau plateau) {
        this.plateau = plateau;
    }

    public void update(Plateau tab) {
        this.plateau = tab;
    }

    public void afficherMap(EceMan Personnage) {

        cls();

        System.out.println("\n            Name : " + Personnage.getNom());
        System.out.println("\nScore : " + Personnage.getScore() + "                  Niveau : " + Personnage.getLevel());
        for (int i = 0; i < Plateau.HEIGHT; i++) {
            for (int j = 0; j < Plateau.WIDTH; j++) {

                char var = plateau.getPlateau()[i][j];

                if (var == 'G') {
                    System.out.print(ANSI_CYAN + var + " " + ANSI_RESET);
                } else if (var == 'P') {
                    System.out.print(ANSI_RED + var + " " + ANSI_RESET);
                } else if (var == 'X') {
                    System.out.print(ANSI_BLACK + var + " " + ANSI_RESET);
                } else if (var == 'O') {
                    System.out.print(ANSI_YELLOW + var + " " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_BLUE + var + " " + ANSI_RESET);
                }

            }
            System.out.println(" ");
        }
        //System.out.print(ANSI_RESET);
    }

    public void cls() {

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

}
