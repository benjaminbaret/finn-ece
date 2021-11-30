/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Controller;

import finnece.Modele.EceMan;
import finnece.Modele.Plateau;
import finnece.Vue.AffichageConsole;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author benja
 */
public class moteurJeu {

    public static void main(String[] args) {
        boolean end = false, touche = false;
        char choix = '0';

        Scanner clavier = null;
        clavier = new Scanner(System.in);

        List<List<String>> infoSavePlayer = new ArrayList<List<String>>();
        List<String> tempoInfo = new ArrayList<String>();

        while (!end) {
            infoSavePlayer = fileSavePlayer(); //chargement des infos depuis le fichier .txt de sauvegarde
            //cls();
            System.out.println("               "
                    + "Menu :\n\n    "
                    + "- 1 : Affichage des règles\n    "
                    + "- 2 : Nouvelle partie\n    "
                    + "- 3 : Reprise de la dernière partie\n    "
                    + "- 4 : Affichage des scores\n    "
                    + "- 5 : Quitter\n");

            while (!touche) {
                choix = clavier.next().charAt(0);
                touche = checkToucheValide(choix);
            }
            touche = false;

            switch (choix) {

                case '1': { //regle
                    System.out.println("Mettre la fonction afficher regle ici !");
                    break;
                }
                case '2': {//nouvelle partie
                    String name;
                    System.out.print("Veuillez saisir votre nom de joueur : ");
                    name = clavier.next();
                    System.out.println(" ");
                    jeu(name, 1, 0, clavier, infoSavePlayer);
                    System.out.println("\n\n");

                    break;
                }
                case '3': {//reprise partie sauvegardée

                    int numberPlayer = infoSavePlayer.size();
                    numberPlayer--;
                    String name = infoSavePlayer.get(numberPlayer).get(0);
                    String levelString = infoSavePlayer.get(numberPlayer).get(1);
                    String scoreString = infoSavePlayer.get(numberPlayer).get(2);
                    
                    infoSavePlayer.remove(numberPlayer);
                    jeu(name, Integer.parseInt(levelString), Integer.parseInt(scoreString), clavier, infoSavePlayer);

                    System.out.println("\n\n");

                    break;
                }
                case '4': {//voir les scores

                    System.out.println("        Score :\n");

                    for (int i = 0; i < infoSavePlayer.size(); i++) {
                        for (int j = 0; j < infoSavePlayer.get(i).size(); j++) {
                            if (j == 0) {
                                System.out.print("Nom : ");
                            } else if (j == 1) {
                                System.out.print("   |   Level : ");
                            } else {
                                System.out.print("   |   Score : ");
                            }
                            System.out.print(infoSavePlayer.get(i).get(j) + " ");
                        }
                        System.out.println("");
                    }
                    break;
                }

                case '5': {//quitter
                    end = true;
                    break;
                }
                default:
                    System.out.println("Veuillez réessayer ! \n");
            }
        }

        clavier.close();
    }

    //LOAD FILE SAVE
    public static List<List<String>> fileSavePlayer() {

        List<List<String>> infoSavePlayer = new ArrayList<List<String>>();
        String lignemap = new String();

        try {

            String saveScore = "./Sauvegarde/Score.txt";

            FileInputStream filemap = new FileInputStream(saveScore);
            Scanner scanner = new Scanner(filemap);

            while (scanner.hasNext()) {

                List<String> coloneinfo = new ArrayList<String>();

                lignemap = scanner.nextLine();
                String tbtempo[] = lignemap.split(" ");

                for (int i = 0; i < tbtempo.length; i++) {

                    coloneinfo.add(tbtempo[i]);
                }
                infoSavePlayer.add(coloneinfo);
            }
        } catch (IOException e) {
            System.out.println("Le fichier de chargement des scores n'a pas pu être lu");
            System.out.println(" ");
        }
        return infoSavePlayer;
    }

    //LANCE LE JEU
    public static List<List<String>> jeu(String name, int level, int score, Scanner clavier, List<List<String>> infoSavePlayer) {

        List<String> newPlayer = new ArrayList<String>();

        boolean endPartie = false;
        Plateau map = new Plateau();
        map.loadMap(level);

        EceMan Personnage = new EceMan(name, level, map.getXSymbol('P'), map.getYSymbol('P'), score);

        ControleurPlateau controleur = new ControleurPlateau(map, Personnage);
        AffichageConsole afficher = new AffichageConsole(map);

        while (!endPartie) {
            afficher.afficherMap(Personnage);
            map = controleur.modifierMap(Personnage, clavier);
            afficher.update(map);

            if (map.endGame()) {
                endPartie = true;
                Personnage.setScore(10);
                Personnage.setLevel();
            }
        }

        //ajout new player au fichier de sauvegarde
        newPlayer.add(Personnage.getNom());
        newPlayer.add(String.valueOf(Personnage.getLevel()));
        newPlayer.add(String.valueOf(Personnage.getScore()));
        infoSavePlayer.add(newPlayer);

        afficher.afficherMap(Personnage);
        if (Personnage.getNiveau() != level) {
            System.out.println("Bravo niveau complété");
            map.sauvegardeScore(infoSavePlayer);

        }
        return infoSavePlayer;
    }

    public static boolean checkToucheValide(char choix) {
        if (choix == '1' || choix == '2' || choix == '3' || choix == '4' || choix == '5') {
            return true;
        } else {
            System.out.println("Veuillez réessayer ! Touche non valide");
            return false;
        }
    }

    
    
    
    public static void cls(){
        
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch(Exception e){
            System.out.println(e);
        }
        
    }
    
}
