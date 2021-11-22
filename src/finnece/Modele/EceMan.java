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
public class EceMan extends ObjetPlateau {

    private String name_eceman;
    private int level_eceman;
    private int score;
    

    EceMan() {
        super(0, 0, 'A');
        this.name_eceman = "";
        this.level_eceman = 1;
        this.score = 0;
        
    }

    public EceMan(String name, int level, int x, int y, int score) {
        super(x, y, 'A');
        this.name_eceman = name;
        this.level_eceman = level;
        this.score = score;
    }

// Getter
    public String getNom() {

        return this.name_eceman;
    }


    public int getNiveau() {

        return this.level_eceman;
    }


    public int getscore() {
        return this.score;
    }


    public void setScore() {
        this.score++;
    }

    
}
