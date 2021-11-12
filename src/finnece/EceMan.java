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
public class EceMan {

    private String name_eceman;
    private String symbole;
    private int level_eceman;
    private int x_eceman;
    private int y_eceman;

    public EceMan() {

        this.name_eceman = "";
        level_eceman = 1;
        x_eceman = 0;
        y_eceman = 0;
        symbole = "";
    }

    public EceMan(String name, int level, int x, int y) {

        this.name_eceman = name;
        level_eceman = level;
        x_eceman = x;
        y_eceman = y;
        symbole = "P";

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

    public void deplacerPersonnage(char direction) {

        switch (direction) {
            case 'z': //déplacement vers le haut
                this.x_eceman--;
                break;

            case 's': //déplacement vers le bas
                this.x_eceman++;
                break;

            case 'q': //déplacement vers la gauche
                this.y_eceman--;
                break;

            case 'd': //déplacement vers la droite
                this.y_eceman++;
                break;

            default:
                System.out.println("Veuillez appuyer sur z pour aller vers le haut; s vers le bas ; q vers la gauche et d vers lz droite");
        }
    }
}
