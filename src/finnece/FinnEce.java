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
public class FinnEce {

    public static void main(String[] args) {
        boolean end = false;
        Plateau map = new Plateau();
        map.loadMap();
        EceMan Personnage = new EceMan("ECEMAN", 1, map.getXPerso(), map.getYPerso());

        while (!end) {
            map.afficherMap();
            map.modifierMap(Personnage);
            if (Personnage.getXPerso() == map.getXObjectif() && Personnage.getYPerso() == map.getYObjectif()) {
                System.out.println("enfin bravo couillon");
                end = true;
            }
        }
        map.afficherMap();
        map.sauvegarderMap();

    }

}
