/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece;

import java.io.*;
import java.util.Scanner;

public class Plateau {

    private String plateauJeux[][] = new String[10][10];
    private String Level;
    private int lvl = 1;

    void chargerMap() {

        String lignemap = new String();

        try {
            if (lvl == 1) {
                Level = "./Sauvegarde/level1.txt";
            } else if (lvl == 2) {
                Level = "./Sauvegarde/level2.txt";
            } else {
                Level = "./Sauvegarde/Save.txt";
            }

            FileInputStream filemap = new FileInputStream(Level);
            Scanner scanner = new Scanner(filemap);

            for (int i = 0; i < plateauJeux.length; i++) {
                lignemap = scanner.nextLine();
                String tbtempo[] = lignemap.split(" ");
                for (int j = 0; j < plateauJeux.length; j++) {
                    plateauJeux[i][j] = tbtempo[j];
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Le fichier n'a pas pu être lu");
            System.out.println(" ");

        }
    }

    void afficherMap() {
        for (int i = 0; i < plateauJeux.length; i++) {
            for (int j = 0; j < plateauJeux.length; j++) {
                System.out.print(plateauJeux[i][j] + " ");
            }
            System.out.println(" ");

        }
    }

    void modifierMap() {

        Scanner clavier = null;
        clavier = new Scanner(System.in);

        int x_pingouin = 0, y_pingouin = 0;
        char toucheDeplacement;

        for (int i = 0; i < plateauJeux.length; i++) {
            for (int j = 0; j < plateauJeux.length; j++) {
                if (plateauJeux[i][j] == "P") {
                    x_pingouin = i + 1;
                    y_pingouin = j + 1;
                }
            }
        }

        //PROBLEME ICI
        System.out.print("Déplacement : ");
        toucheDeplacement = clavier.next().charAt(0);
        System.out.println(toucheDeplacement);

        while ((toucheDeplacement != 122) || (toucheDeplacement != 113) || (toucheDeplacement != 115) || (toucheDeplacement != 100)) {
            System.out.print("Déplacement (z vers le haut; q vers la gauche, s vers le baas, d vers la droite) : ");
            toucheDeplacement = clavier.next().charAt(0);
        }

        if (toucheDeplacement == 'z' && plateauJeux[x_pingouin][y_pingouin - 1] != "M") {

            plateauJeux[x_pingouin][y_pingouin - 1] = "P";
            plateauJeux[x_pingouin][y_pingouin] = "G";

        } else if (toucheDeplacement == 'q' && plateauJeux[x_pingouin - 1][y_pingouin] != "M") {
            plateauJeux[x_pingouin - 1][y_pingouin] = "P";
            plateauJeux[x_pingouin][y_pingouin] = "G";
        } else if (toucheDeplacement == 's' && plateauJeux[x_pingouin][y_pingouin + 1] != "M") {
            plateauJeux[x_pingouin][y_pingouin + 1] = "P";
            plateauJeux[x_pingouin][y_pingouin] = "G";
        } else if (toucheDeplacement == 'd' && plateauJeux[x_pingouin + 1][y_pingouin] != "M") {
            plateauJeux[x_pingouin + 1][y_pingouin] = "P";
            plateauJeux[x_pingouin][y_pingouin] = "G";
        }
        //FIN PROBLEME ICI

    }

    void sauvegarderMap() {
        try {
            FileWriter fileSave = new FileWriter("./Sauvegarde/Save.txt");
            BufferedWriter writter = new BufferedWriter(fileSave);

            for (int i = 0; i < plateauJeux.length; i++) {
                for (int j = 0; j < plateauJeux.length; j++) {
                    writter.write(plateauJeux[i][j] + " ");
                }
                writter.newLine();
            }

            writter.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
