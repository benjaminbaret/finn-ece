/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece;

/**
 *
 * @author theoc
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FinnEce {

    public static void main(String[] args) {
        boolean end = false, touche = false;
        char choix = '0';
        fonctionBasique fMenu = new fonctionBasique();

        Scanner clavier = null;
        clavier = new Scanner(System.in);

        //Tableau de sauvegarde avec les informations des joueurs (nom, level, score)
        List<List<String>> infoSavePlayer = new ArrayList<List<String>>();

        while (!end) {
            infoSavePlayer = fMenu.fileSavePlayer(); //chargement des infos depuis le fichier .txt de sauvegarde

            System.out.println("               "
                    + "Menu :\n\n    "
                    + "- 1 : Affichage des règles\n    "
                    + "- 2 : Nouvelle partie\n    "
                    + "- 3 : Reprise de la dernière partie\n    "
                    + "- 4 : Affichage des scores\n    "
                    + "- 5 : Quitter\n");

            while (!touche) {
                choix = clavier.next().charAt(0);
                touche = fMenu.checkToucheValide(choix);
            }
            touche = false;

            switch (choix) {

                case '1': { //regle
                    System.out.println("Mettre la fonction afficher regle ici !");
                    System.out.println("\n\n");
                    break;
                }
                case '2': {//nouvelle partie
                    String name;
                    System.out.print("Veuillez saisir votre nom de joueur : ");
                    name = clavier.next();
                    System.out.println(" ");
                    fMenu.jeu(name, 1, 0, clavier, infoSavePlayer);

                    System.out.println("\n\n");
                    break;
                }
                case '3': {//reprise de la derniere partie sauvegardée

                    int numberPlayer = infoSavePlayer.size();
                    numberPlayer--;
                    String name = infoSavePlayer.get(numberPlayer).get(0);
                    String levelString = infoSavePlayer.get(numberPlayer).get(1);
                    String scoreString = infoSavePlayer.get(numberPlayer).get(2);
                    //Integer.parseInt(...) -> passer un string en int
                    fMenu.jeu(name, Integer.parseInt(levelString), Integer.parseInt(scoreString), clavier, infoSavePlayer);

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
}
