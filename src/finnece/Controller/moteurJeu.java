/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Controller;

import finnece.Modele.EceMan;
import finnece.Modele.Plateau;
import finnece.Vue.AffichageConsole;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author benja
 */
public class moteurJeu {

    public static void main(String[] args) throws InterruptedException {
        boolean end = false, touche = false;
        char choix = '0';

        Scanner clavier = null;
        clavier = new Scanner(System.in);

        List<List<String>> infoSavePlayer = new ArrayList<List<String>>();

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

            /*SwingUtilities.invokeLater(() -> {
                    //On crée une nouvelle instance de notre JWindow
                    MonInterface window = new MonInterface();
                    window.setVisible(true);//On la rend visible
                });
 
		try { 
			Thread.sleep(5000);
		} catch (InterruptedException e) {}*/
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

                    affichagescore(infoSavePlayer);
                    System.out.print("Pour quel joueur voulez-vous reprendre la partie :  ");

                    int numberPlayer = clavier.nextInt();
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
                    affichagescore(infoSavePlayer);
                    break;
                }

                case '5': {//quitter
                    end = true;
                    //Sytem.exit(0);!
                    break;
                }
                default:
                    System.out.println("Veuillez réessayer ! \n");
            }
        }

        clavier.close();
    }

    public static void affichagescore(List<List<String>> infoSavePlayer) {
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
    public static List<List<String>> jeu(String name, int level, int score, Scanner clavier, List<List<String>> infoSavePlayer) throws InterruptedException {

        List<String> newPlayer = new ArrayList<String>();

        boolean endPartie = false;
        Plateau map = new Plateau();
        map.loadMap(level);

        EceMan Personnage = new EceMan(name, level, map.getXSymbol('P'), map.getYSymbol('P'), score);

        /////////////
        Frame f = new Frame("Demo");
        f.setLayout(new FlowLayout());
        f.setSize(200, 200);
        Label l = new Label();
        l.setText("This is a Game");
        f.add(l);
        f.setVisible(true);

        //Creating and adding the key listener
        ControleurPlateau controleur = new ControleurPlateau(map, Personnage);
        f.addKeyListener(controleur);
        ////////////

        AffichageConsole afficher = new AffichageConsole(map);

        Instant start = Instant.now();
        Instant current = Instant.now();
        long duree = 0;

        while (!endPartie) {
            current = Instant.now();
            duree = current.getEpochSecond() - start.getEpochSecond();

            map = controleur.modifierMap(Personnage, clavier);
            if (Personnage.getLevel() == 5) {
                map.editEnnemi();
            }

            afficher.update(map);
            afficher.afficherMap(Personnage);
            System.out.println("Temps écoulé : " + duree + " secondes");
            Thread.sleep(300);

            if (map.endGame() == "PERDU") {
                endPartie = true;
                Personnage.setScore(0);
                System.out.println("\n\nGame Over \nTry again");

            } else if (map.endGame() == "GAGNE") {
                endPartie = true;
                if (duree > 60) {
                    Personnage.setScore(Personnage.getScore() + 3);
                } else if (30 < duree && duree <= 60) {
                    Personnage.setScore(Personnage.getScore() + 5);
                } else {
                    Personnage.setScore(Personnage.getScore() + 8);
                }
                Personnage.setLevel();
                System.out.println("\n\nGood Game \nLevel completed");

            }
        }

        //ajout new player au fichier de sauvegarde
        newPlayer.add(Personnage.getNom());
        newPlayer.add(String.valueOf(Personnage.getLevel()));
        newPlayer.add(String.valueOf(Personnage.getScore()));
        infoSavePlayer.add(newPlayer);
        map.sauvegardeScore(infoSavePlayer);

        afficher.afficherMap(Personnage);
        f.dispose();

        return infoSavePlayer;
    }

    //Blinder choix
    public static boolean checkToucheValide(char choix) {
        if (choix == '1' || choix == '2' || choix == '3' || choix == '4' || choix == '5') {
            return true;
        } else {
            System.out.println("Veuillez réessayer ! Touche non valide");
            return false;
        }
    }

    //Clear ecran
    public static void cls() {

        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
