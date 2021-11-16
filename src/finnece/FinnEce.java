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
        Plateau map = new Plateau();
        map.loadMap();
        EceMan Personnage = new EceMan("ECEMAN", 1, map.getXPerso(), map.getYPerso(), 0);

        while ((Personnage.getXPerso() != map.getXObjectif()) && (Personnage.getYPerso() != map.getYObjectif())) {
            map.afficherMap(Personnage);
            map.modifierMap(Personnage);
            
        }
        map.afficherMap(Personnage);
        map.sauvegarderMap();

    }

}
