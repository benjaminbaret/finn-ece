/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Modele;

/**
 *
 * @author benja
 */
public class ObjetPlateau extends CasePlateau {

    public ObjetPlateau(int x, int y, char s) {
        super(x, y, s);
    }

    public void applyProperty(String myString) { //mystring == po

    }

    //Permet l'héridité

    public void setEpaisse() {
    }

    public void setDead(boolean is) {
    }

    public boolean isDead() {
        return false;
    }
    public boolean isEpaisse() {
        return false;
    }
    
    public int getLevel(){
        return 1;
    }

}
