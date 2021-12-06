/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Modele;

//java.io.* --> SAUVEGARDE
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Plateau {

    public static final int WIDTH = 19;
    public static final int HEIGHT = 15;
    private int level;
    private CasePlateau plateauJeu[][] = new CasePlateau[HEIGHT][WIDTH];
    private int Xobjectif, Yobjectif, Xtunnel, Ytunnel, XtunnelSortie, YtunnelSortie;
    private boolean dead = false;
    private boolean wasEpaisse = true;
    private String sensEnnemi = "droite";


    /*public Plateau(int level){
        level = level;
    }*/
    public char[][] getPlateau() {
        char[][] plateau = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                plateau[i][j] = plateauJeu[i][j].getSymbole();

   
                    if (plateauJeu[i][j].getSymbole() == 'T') {
                        Xtunnel = i;
                        Ytunnel = j;
                    }
                    if (plateauJeu[i][j].getSymbole() == 'S') {
                        XtunnelSortie = i;
                        YtunnelSortie = j;
                    
                }

            }
        }
        return plateau;

    }

    public int getXSymbol(char s) { // fonctionne seulement pour les éléments uniques du tableau (possibilité d'étendre en vérifiant si le symbole est un symbole unique ou non"
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (s == plateauJeu[i][j].getSymbole()) {
                    return i;
                }
            }
        }
        return 404;
    }

    public int getYSymbol(char s) { // fonctionne seulement pour les éléments uniques du tableau (possibilité d'étendre en vérifiant si le symbole est un symbole unique ou non"
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (s == plateauJeu[i][j].getSymbole()) {
                    return j;
                }
            }
        }

        return 404;
    }
    

    public void changeSymbol(int x, int y, String str, Boolean deplacer) {

        boolean nextEpaisse = false;
        
        

        switch (str) {
            case "-Y": {
                if (deplacer) {
                    plateauJeu[x][y - 2] = plateauJeu[x][y - 1];
                    plateauJeu[x][y - 1] = new Banquise(x, y - 1, 'G', 1);
                }

                if (plateauJeu[x][y - 1].getSymbole() == 'G') {
                    nextEpaisse = false;
                    nextEpaisse = false;
                } else if (plateauJeu[x][y - 1].getSymbole() == 'E') {
                    nextEpaisse = true;
                } else if (plateauJeu[x][y - 1].getSymbole() == 'D') {
                    nextEpaisse = false;
                } else {
                    dead = true;
                }
                plateauJeu[x][y - 1] = plateauJeu[x][y];

                break;
            }
            case "+Y": {
                if (deplacer) {
                    plateauJeu[x][y + 2] = plateauJeu[x][y + 1];
                    plateauJeu[x][y + 1] = new Banquise(x, y + 1, 'G', 1);

                }
                if (plateauJeu[x][y + 1].getSymbole() == 'G') {
                    nextEpaisse = false;
                } else if (plateauJeu[x][y + 1].getSymbole() == 'E') {

                    nextEpaisse = true;
                } else if (plateauJeu[x][y + 1].getSymbole() == 'D') {
                    nextEpaisse = false;
                } else {
                    dead = true;
                }

                plateauJeu[x][y + 1] = plateauJeu[x][y];
                break;
            }
            case "-X": {

                if (deplacer) {
                    plateauJeu[x - 2][y] = plateauJeu[x - 1][y];
                    plateauJeu[x - 1][y] = new Banquise(x - 1, y, 'G', 1);
                }
                if (plateauJeu[x - 1][y].getSymbole() == 'G') {

                    nextEpaisse = false;

                } else if (plateauJeu[x - 1][y].getSymbole() == 'E') {
                    nextEpaisse = true;
                } else if (plateauJeu[x - 1][y].getSymbole() == 'D') {
                    nextEpaisse = false;
                } else {
                    dead = true;
                }
                plateauJeu[x - 1][y] = plateauJeu[x][y];
                break;
            }
            case "+X": {
                if (deplacer) {
                    plateauJeu[x + 2][y] = plateauJeu[x + 1][y];
                    plateauJeu[x + 1][y] = new Banquise(x + 1, y, 'G', 1);

                }
                if (plateauJeu[x + 1][y].getSymbole() == 'G') {

                    nextEpaisse = false;
                } else if (plateauJeu[x + 1][y].getSymbole() == 'E') {

                    nextEpaisse = true;
                } else if (plateauJeu[x + 1][y].getSymbole() == 'D') {

                    nextEpaisse = false;
                } else if (plateauJeu[x + 1][y].getSymbole() == 'E') {
                    nextEpaisse = true;
                } else if (plateauJeu[x + 1][y].getSymbole() == 'D') {
                    nextEpaisse = false;
                } else {
                    dead = true;
                }

                plateauJeu[x + 1][y] = plateauJeu[x][y];
                break;
            }

        }

        if (this.wasEpaisse == false || level == 1 || level == 5) {
            plateauJeu[x][y] = new Banquise(x, y, 'H', 2); // 2 --> score || a voir si ca crée r
        } else if (this.wasEpaisse == true) {
            plateauJeu[x][y] = new Banquise(x, y, 'G', 2);
        }

        this.wasEpaisse = nextEpaisse;


        this.wasEpaisse = nextEpaisse;
        


    }

    public void tunnel(int x, int y) {

        plateauJeu[XtunnelSortie][YtunnelSortie] = plateauJeu[x][y];
        plateauJeu[x][y] = new ObjetPlateau(x, y, 'H');

    }

    public String endGame() {
        boolean endgame = true;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (plateauJeu[i][j].getSymbole() == 'G' || plateauJeu[i][j].getSymbole() == 'E') {
                    endgame = false;
                }
            }
        }
        // A FAIRE : Tunnel --> t = entrée & S = sortie

        if ((Xobjectif == getXSymbol('P')) && (Yobjectif == getYSymbol('P')) && endgame == true) {
            return "GAGNE";
        } else if (dead) {
            return "PERDU";
        }
        return "";
    }
    
    public void edit(){
        int a = getYSymbol('Y');
        if(a>4 && a<13){
            if(sensEnnemi == "droite"){
              plateauJeu[getXSymbol('Y')][a+1] = plateauJeu[getXSymbol('Y')][a];
            plateauJeu[getXSymbol('Y')][a] = new Banquise(getXSymbol('Y'), a, 'G', 2); 
            } else{
                  plateauJeu[getXSymbol('Y')][a-1] = plateauJeu[getXSymbol('Y')][a];
            plateauJeu[getXSymbol('Y')][a] = new Banquise(getXSymbol('Y'), a, 'G', 2);  
            }
            
        } else{
            if(sensEnnemi == "droite"){
                sensEnnemi = "gauche";
                plateauJeu[getXSymbol('Y')][a-1] = plateauJeu[getXSymbol('Y')][a];
            plateauJeu[getXSymbol('Y')][a] = new Banquise(getXSymbol('Y'), a, 'G', 2); 
            }else {
                plateauJeu[getXSymbol('Y')][a+1] = plateauJeu[getXSymbol('Y')][a];
            plateauJeu[getXSymbol('Y')][a] = new Banquise(getXSymbol('Y'), a, 'G', 2); 
                sensEnnemi = "droite";
            }
        }
        
        
      
    }
    // SAUVEGARDE & CHARGEMENT

    public void loadMap(int lvl) {
        String Level = "";

        String lignemap = new String();

        try {
            if (lvl == 1) {
                Level = "./Sauvegarde/level1.txt";
            } else if (lvl == 2) {
                Level = "./Sauvegarde/level2.txt";
            } else if (lvl == 3) {
                Level = "./Sauvegarde/level3.txt";
            } else if (lvl == 4) {
                Level = "./Sauvegarde/level4.txt";

            }
            else if (lvl == 5) {

                Level = "./Sauvegarde/level5.txt";
            }

            level = lvl;

            FileInputStream filemap = new FileInputStream(Level);

            Scanner scanner = new Scanner(filemap);

            for (int i = 0; i < HEIGHT; i++) {
                lignemap = scanner.nextLine();
                String tbtempo[] = lignemap.split(" ");
                for (int j = 0; j < WIDTH; j++) {
                    switch (tbtempo[j]) {
                        case "X":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'X');
                            break;
                        case "M":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'M');
                            break;
                        case "G":
                            plateauJeu[i][j] = new Banquise(i, j, 'G', 1); // Ajouter ici le cas ou la glasse sera sur deux passages
                            break;
                        case "E":
                            plateauJeu[i][j] = new Banquise(i, j, 'E', 1); // Ajouter ici le cas ou la glasse sera sur deux passages
                            break;
                        case "D":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'D');
                            break;
                        case "O":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'O');
                            Xobjectif = i;
                            Yobjectif = j;
                            break;
                        case "S":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'S');
                            XtunnelSortie = i;
                            YtunnelSortie = j;
                            break;
                        case "T":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'T');
                            Xtunnel = i;
                            Ytunnel = j;
                            break;
                        case "P":
                            plateauJeu[i][j] = new EceMan();
                            break;
                        case "H":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'H');
                            break;
                        case "Y":
                            plateauJeu[i][j] = new ObjetPlateau(i, j, 'Y');
                            break;

                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Le fichier de charement de la map n'a pas pu être lu");
            System.out.println(" ");

        }

    }

    public void sauvegardeScore(List<List<String>> infoPlayer) {

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

}
