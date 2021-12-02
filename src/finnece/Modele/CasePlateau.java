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
public class CasePlateau {

    private int position_x, position_y;
    private char symbole;
    private boolean epaisse;
    private boolean dead;

    CasePlateau(int x, int y, char s) {
        position_x = x;
        position_y = y;
        symbole = s;
        epaisse = false;
        dead = false;
    }

    public int getPositionX() {
        return position_x;
    }

    public int getPositionY() {
        return position_y;
    }

    public void setPositionX(int x) {
        position_x = x;
    }

    public void setPositionY(int y) {
        position_y = y;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setEpaisse(boolean is) {
        epaisse = is;
    }

    public boolean isEpaisse() {
        return epaisse;
    }

    public void setDead(boolean is) {
        dead = is;
    }

    public boolean isDead() {
        return dead;
    }

}
