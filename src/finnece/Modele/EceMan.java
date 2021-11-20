/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece.Modele;

/**
 *
 * @author theoc
 */
public class EceMan {

    private String name_eceman;
    private String symbole;
    private int level_eceman;
    private int score;
    private int x_eceman;
    private int y_eceman;

    public EceMan() {

        this.name_eceman = "";
        this.level_eceman = 1;
        this.x_eceman = 0;
        this.y_eceman = 0;
        this.symbole = "";
        this.score = 0;
    }

    public EceMan(String name, int level, int x, int y, int score) {

        this.name_eceman = name;
        this.level_eceman = level;
        this.x_eceman = x;
        this.y_eceman = y;
        this.symbole = "P";
        this.score = score;

    }

// Getter
    public String getNom() {

        return this.name_eceman;
    }

    public String getSymbole() {

        return this.symbole;
    }

    public int getNiveau() {

        return this.level_eceman;
    }

    public int getXPerso() {

        return this.x_eceman;
    }

    public int getYPerso() {

        return this.y_eceman;
    }

    public int getscore() {
        return this.score;
    }

    public void setXperso(int X) {
        this.x_eceman = X;
    }

    public void setYperso(int Y) {
        this.y_eceman = Y;
    }

    public void setScore() {
        this.score++;
    }

    
}
