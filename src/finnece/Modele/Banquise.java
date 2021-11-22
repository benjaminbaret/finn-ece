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
public class Banquise extends CasePlateau {
    
    private int nb_passages; 

    public Banquise(int x, int y, char s, int nb) {
        super(x, y, s);
        nb_passages = nb;
    }
    
    public int getNbPassages(){
        return nb_passages;
    }
    
    public void setNbPassages(int nb){
        nb_passages = nb;
    }

}