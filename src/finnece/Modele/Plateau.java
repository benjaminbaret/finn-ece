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
    private String Level;
    private CasePlateau plateauJeu[][] = new CasePlateau[HEIGHT][WIDTH];
    private int Xobjectif, Yobjectif;

    public char[][] getPlateau() {
        char[][] plateau = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                plateau[i][j] = plateauJeu[i][j].getSymbole();
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

    public void changeSymbol(int x, int y, String str, String Bloc) {

        switch (str) {

            case "-Y": {
                if (Bloc == "murdeplacable") {
                    plateauJeu[x][y - 2] = plateauJeu[x][y - 1];
                    plateauJeu[x][y - 1] = new Banquise(x, y - 1, 'G', 2);

                }
                if (plateauJeu[x][y - 1].getSymbole() == 'G') {
                    plateauJeu[x][y].setEpaisse(false);
                } else if (plateauJeu[x][y - 1].getSymbole() == 'E') {
                    plateauJeu[x][y].setEpaisse(true);
                } else if (plateauJeu[x][y - 1].getSymbole() == 'D') {
                    plateauJeu[x][y].setEpaisse(true);
                } else {
                    plateauJeu[x][y].setDead(true);
                }
                plateauJeu[x][y - 1] = plateauJeu[x][y];

                break;
            }
            case "+Y": {
                if (Bloc == "murdeplacable") {
                    plateauJeu[x][y + 2] = plateauJeu[x][y + 1];
                    plateauJeu[x][y + 1] = new Banquise(x, y + 1, 'G', 2);

                }
                if (plateauJeu[x][y + 1].getSymbole() == 'G') {
                    plateauJeu[x][y].setEpaisse(false);
                } else if (plateauJeu[x][y + 1].getSymbole() == 'E') {
                    plateauJeu[x][y].setEpaisse(true);
                } else if (plateauJeu[x][y + 1].getSymbole() == 'D') {
                    plateauJeu[x][y].setEpaisse(true);
                } else {
                    plateauJeu[x][y].setDead(true);
                }

                plateauJeu[x][y + 1] = plateauJeu[x][y];
                break;
            }
            case "-X": {

                if (Bloc == "murdeplacable") {
                    plateauJeu[x - 2][y] = plateauJeu[x - 1][y];
                    plateauJeu[x - 1][y] = new Banquise(x - 1, y, 'G', 2);
                }
                if (plateauJeu[x - 1][y].getSymbole() == 'G') {
                    plateauJeu[x][y].setEpaisse(false);
                } else if (plateauJeu[x - 1][y].getSymbole() == 'E') {
                    plateauJeu[x][y].setEpaisse(true);
                } else if (plateauJeu[x][y - 1].getSymbole() == 'D') {
                    plateauJeu[x][y].setEpaisse(true);
                } else {
                    plateauJeu[x][y].setDead(true);
                }
                plateauJeu[x - 1][y] = plateauJeu[x][y];
                break;
            }
            case "+X": {
                if (Bloc == "murdeplacable") {
                    plateauJeu[x + 2][y] = plateauJeu[x + 1][y];
                    plateauJeu[x + 1][y] = new Banquise(x + 1, y, 'G', 2);

                }
                if (plateauJeu[x + 1][y].getSymbole() == 'G') {
                    plateauJeu[x][y].setEpaisse(false);
                } else if (plateauJeu[x + 1][y].getSymbole() == 'E') {
                    plateauJeu[x][y].setEpaisse(true);
                } else if (plateauJeu[x][y + 1].getSymbole() == 'D') {
                    plateauJeu[x][y].setEpaisse(true);
                } else {
                    plateauJeu[x][y].setDead(true);
                }
                plateauJeu[x + 1][y] = plateauJeu[x][y];
                break;
            }

        }

        if (plateauJeu[x][y].isEpaisse() == false) {
            plateauJeu[x][y] = new Banquise(x, y, 'H', 2); // 2 --> score || a voir si ca crée r
        } else if (plateauJeu[x][y].isEpaisse() == true) {
            plateauJeu[x][y] = new Banquise(x, y, 'G', 2);
        }

    }

    public String endGame() {
        if ((Xobjectif == getXSymbol('P')) && (Yobjectif == getYSymbol('P'))) {
            return "GAGNE";
        } else if (plateauJeu[this.getXSymbol('P')][this.getYSymbol('P')].isDead()) {
            return "PERDU";
        }
        return "";
    }

    // SAUVEGARDE & CHARGEMENT
    public void loadMap(int lvl) {

        String lignemap = new String();

        try {
            if (lvl == 1) {
                Level = "./Sauvegarde/level1.txt";
            } else if (lvl == 2) {
                Level = "./Sauvegarde/level2.txt";
            }

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
                        case "P":
                            plateauJeu[i][j] = new EceMan();
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
