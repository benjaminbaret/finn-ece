/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece;

//java.io.* --> SAUVEGARDE
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Plateau {

    private int lenght_plateau = 15;
    private int width_plateau = 19;
    private String plateauJeux[][] = new String[lenght_plateau][width_plateau];
    private String Level;

    /*
                        GETTER
     */
    int getXPerso() {
        int x_pingouin = 0;
        for (int i = 0; i < lenght_plateau; i++) {
            for (int j = 0; j < width_plateau; j++) {
                if ("P".equals(plateauJeux[i][j])) {
                    x_pingouin = i;
                }
            }
        }
        return x_pingouin;
    }

    int getYPerso() {
        int y_pingouin = 0;
        for (int i = 0; i < lenght_plateau; i++) {
            for (int j = 0; j < width_plateau; j++) {
                if ("P".equals(plateauJeux[i][j])) {
                    y_pingouin = j;
                }
            }
        }
        return y_pingouin;
    }

    int getXObjectif() {
        int x_objectif = 0;
        for (int i = 0; i < lenght_plateau; i++) {
            for (int j = 0; j < width_plateau; j++) {
                if ("O".equals(plateauJeux[i][j])) {
                    x_objectif = i;
                }
            }
        }
        return x_objectif;
    }

    int getYObjectif() {
        int y_objectif = 0;
        for (int i = 0; i < lenght_plateau; i++) {
            for (int j = 0; j < width_plateau; j++) {
                if ("O".equals(plateauJeux[i][j])) {
                    y_objectif = j;
                }
            }
        }
        return y_objectif;
    }

    /*
                        FIN GETTER
                        AFFICHAGE
     */
    void afficherMap(EceMan Personnage) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n            Name : " + Personnage.getNom());
        System.out.println("Score : " + Personnage.getScore() + "                  Niveau : " + Personnage.getLevel());
        for (int i = 0; i < lenght_plateau; i++) {
            for (int j = 0; j < width_plateau; j++) {

                if ("G".equals(plateauJeux[i][j])) {
                    System.out.print("\033[34m" + plateauJeux[i][j] + " ");
                } else if ("P".equals(plateauJeux[i][j])) {
                    System.out.print("\033[33m" + plateauJeux[i][j] + " ");
                } else if ("X".equals(plateauJeux[i][j])) {
                    System.out.print("\033[36m" + plateauJeux[i][j] + " ");
                } else if ("O".equals(plateauJeux[i][j])) {
                    System.out.print("\033[31m" + plateauJeux[i][j] + " ");
                } else {
                    System.out.print("\033[0m" + plateauJeux[i][j] + " ");
                }

            }
            System.out.println(" ");
        }
        System.out.print("\033[0m");
    }

    /*
                        FIN AFFICHAGE
                        DEPLACER PERSO SUR LA MAP
     */
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
        validationTouche = Personnage.deplacerPersonnage(toucheDeplacement);

        //MAJ position Eceman
        if (validationTouche == true) {

            if ("M".equals(plateauJeux[Personnage.getXPerso()][Personnage.getYPerso()])) {
                Personnage.setXperso(lastposX);
                Personnage.setYperso(lastposY);
                System.out.println("Déplacement impossible");
                return false;

            } else if ("G".equals(plateauJeux[Personnage.getXPerso()][Personnage.getYPerso()])) {
                plateauJeux[Personnage.getXPerso()][Personnage.getYPerso()] = Personnage.getSymbole();
                plateauJeux[lastposX][lastposY] = "G";
                return false;

            } else if ("O".equals(plateauJeux[Personnage.getXPerso()][Personnage.getYPerso()])) {
                plateauJeux[Personnage.getXPerso()][Personnage.getYPerso()] = Personnage.getSymbole();
                plateauJeux[lastposX][lastposY] = "G";
                Personnage.setLevel();
                return true;
            }
        }
        return false;
    }

    /*
                        FIN DEPLACER PERSO SUR LA MAP
                        SAUVEGARDE & CHARGEMENT
     */
    void sauvegardeScore( List<List<String>> infoPlayer) {
        try {
            FileWriter fileSave = new FileWriter("./Sauvegarde/Score.txt");
            BufferedWriter writter = new BufferedWriter(fileSave);

            for (int i = 0; i < infoPlayer.size(); i++) {
                for (int j = 0; j < infoPlayer.get(i).size(); j++) {
                    writter.write(infoPlayer.get(i).get(j) + " ");
                }
                writter.newLine();
            }

            writter.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    void loadMap(int lvl) {

        String lignemap = new String();

        try {
            if (lvl == 1) {
                Level = "./src/Sauvegarde/level1.txt";
            } else if (lvl == 2) {
                Level = "./src/Sauvegarde/level2.txt";
            }

            FileInputStream filemap = new FileInputStream(Level);
            Scanner scanner = new Scanner(filemap);

            for (int i = 0; i < lenght_plateau; i++) {
                lignemap = scanner.nextLine();
                String tbtempo[] = lignemap.split(" ");
                for (int j = 0; j < width_plateau; j++) {
                    plateauJeux[i][j] = tbtempo[j];
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Le fichier n'a pas pu être lu");
            System.out.println(" ");

        }
    }

    /*
                        FIN SAUVEGARDE & CHARGEMENT
     */
}
