/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Modele;

//java.io.* --> SAUVEGARDE
import java.io.*;
import java.util.Scanner;


public class Plateau {
    
    public static final int WIDTH = 19;
    public static final int HEIGHT = 15;
    private String Level;


    private String plateauJeux[][] = new String[HEIGHT][WIDTH];
    
   
    
    
    public String[][] getPlateau(){
        return plateauJeux;
    }

    public int getXPerso() {
        int x_pingouin = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if ("P".equals(plateauJeux[i][j])) {
                    x_pingouin = i;
                }
            }
        }
        return x_pingouin;
    }

    public int getYPerso() {
        int y_pingouin = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if ("P".equals(plateauJeux[i][j])) {
                    y_pingouin = j;
                }
            }
        }
        return y_pingouin;
    }

    public int getXObjectif() {
        int x_objectif = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if ("O".equals(plateauJeux[i][j])) {
                    x_objectif = i;
                }
            }
        }
        return x_objectif;
    }

    public int getYObjectif() {
        int y_objectif = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if ("O".equals(plateauJeux[i][j])) {
                    y_objectif = j;
                }
            }
        }
        return y_objectif;
    }


    

    // SAUVEGARDE & CHARGEMENT
    public void sauvegarderMap() {
        try {
            FileWriter fileSave = new FileWriter("./Sauvegarde/Score.txt");
            BufferedWriter writter = new BufferedWriter(fileSave);

            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    writter.write(plateauJeux[i][j] + " ");
                }
                writter.newLine();
            }

            writter.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    
    public void loadMap(int lvl){

        String lignemap = new String();

        try {
            if (lvl == 1) {
                Level = "C:\\Users\\benja\\Documents\\ING3\\Informatique\\Projet S1\\finn-ece\\Sauvegarde\\level1.txt";
            } else if (lvl == 2) {
                Level = "C:\\Users\\benja\\Documents\\ING3\\Informatique\\Projet S1\\finn-ece\\Sauvegarde\\level2.txt";
            } else {
                Level = "C:\\Users\\benja\\Documents\\ING3\\Informatique\\Projet S1\\finn-ece\\Sauvegarde\\Score.txt";
            }
           
            FileInputStream filemap = new FileInputStream(Level);
           
            Scanner scanner = new Scanner(filemap);
           
           

            for (int i = 0; i < HEIGHT; i++) {
                lignemap = scanner.nextLine();
                String tbtempo[] = lignemap.split(" ");
                for (int j = 0; j < WIDTH; j++) {
                    plateauJeux[i][j] = tbtempo[j];
           
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Le fichier n'a pas pu être lu");
            System.out.println(" ");

        }
        
    }
    

}
