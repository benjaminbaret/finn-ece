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
    private CasePlateau plateauJeu[][] = new CasePlateau[HEIGHT][WIDTH];
    
    
    public char[][] getPlateau(){
        char[][] plateau = new char[HEIGHT][WIDTH];
        for(int i=0; i<HEIGHT; i++){
            for(int j=0; j<WIDTH; j++){
                plateau[i][j] = plateauJeu[i][j].getSymbole();
            }
        }
        return plateau;
        
    }
    
    public int getXSymbol(char s){ // fonctionne seulement pour les éléments uniques du tableau (possibilité d'étendre en vérifiant si le symbole est un symbole unique ou non"
        for(int i=0; i<HEIGHT; i++){
            for(int j=0; j<WIDTH; j++){
                if(s == plateauJeu[i][j].getSymbole()){
                    return i;
                }
            }
        }
        return 404;
    }



    public int getYSymbol(char s){ // fonctionne seulement pour les éléments uniques du tableau (possibilité d'étendre en vérifiant si le symbole est un symbole unique ou non"
        for(int i=0; i<HEIGHT; i++){
            for(int j=0; j<WIDTH; j++){
                if(s == plateauJeu[i][j].getSymbole()){
                    return j;
                }
            }
        }
        
        
        return 404;
    }
    
    public void changeSymbol(int x, int y, String str){
        switch(str){
            case "-Y":{
                plateauJeu[x][y-1] = plateauJeu[x][y];
                plateauJeu[x][y] = new Banquise(x, y, 'O', 2);
                break;
            }
            case "+Y":{
                plateauJeu[x][y+1] = plateauJeu[x][y];
                plateauJeu[x][y] = new Banquise(x, y, 'O', 2);
                break;
            }
            case "-X":{
                plateauJeu[x-1][y] = plateauJeu[x][y];
                plateauJeu[x][y] = new Banquise(x, y, 'O', 2);
                break;
            }
            case "+X":{
                plateauJeu[x+1][y] = plateauJeu[x][y];
                plateauJeu[x][y] = new Banquise(x, y, 'O', 2);
                break;
            }
                
        }
        
    }
    
    
   

    // SAUVEGARDE & CHARGEMENT
    public void sauvegarderMap() {
        try {
            FileWriter fileSave = new FileWriter("./Sauvegarde/Score.txt");
            BufferedWriter writter = new BufferedWriter(fileSave);

            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    writter.write(plateauJeu[i][j].getSymbole() + " ");
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
                    switch(tbtempo[j]){
                        case "X":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'X');
                            break;
                        case "M":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'M');
                            break;
                        case "G":
                            plateauJeu[i][j] = new Banquise(i, j, 'G', 1); // Ajouter ici le cas ou la glasse sera sur deux passages
                            break;
                        case "O":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'O');
                            break;
                        case "A":
                            plateauJeu[i][j] = new EceMan();
                            break;
                        case "P":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'P');
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Le fichier n'a pas pu être lu");
            System.out.println(" ");

        }
        
    }
    

}
