/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author theoc
 */
public class fonctionBasique {
    
    //CHECK VALIDE NUMBER FOR CHOICE
     public boolean checkToucheValide(char choix) {
        if (choix == '1' || choix == '2' || choix == '3' || choix == '4' || choix == '5') {
            return true;
        } else {
            System.out.println("Veuillez réessayer ! Touche non valide");
            return false;
        }
    }
     
    //LOAD FILE SAVE
    public List<List<String>> fileSavePlayer() {

        List<List<String>> infoSavePlayer = new ArrayList<List<String>>();
        String lignemap = new String();

        try {

            String saveScore = "./src/Sauvegarde/Score.txt";

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
            System.out.println("Le fichier n'a pas pu être lu");
            System.out.println(" ");
        }
        return infoSavePlayer;
    }

    //LANCE LE JEU
    public void jeu(String name, int level, int score, Scanner clavier, List<List<String>> infoSavePlayer) {
        boolean endPartie = false;
        Plateau map = new Plateau();
        List<String> newPlayer = new ArrayList<String>();

        map.loadMap(level);
        EceMan Personnage = new EceMan(name, level, map.getXPerso(), map.getYPerso(), score);

        while (!endPartie) {
            map.afficherMap(Personnage);
            endPartie = map.modifierMap(Personnage, clavier);
        }

        //ajout new player au fichier de sauvegarde
        newPlayer.add(Personnage.getNom());
        newPlayer.add(String.valueOf(Personnage.getLevel()));
        newPlayer.add(String.valueOf(Personnage.getScore()));
        infoSavePlayer.add(newPlayer);

        map.afficherMap(Personnage);
        if (Personnage.getLevel() != level) {
            System.out.println("Bravo niveau complété");
            map.sauvegardeScore(infoSavePlayer);
        }
    }

}
