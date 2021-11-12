/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finnece;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author theoc
 */
public class FinnEce {

    public static void main(String[] args) {

        Plateau N1 = new Plateau();
        N1.chargerMap();
        N1.afficherMap();
        N1.modifierMap();
        N1.afficherMap();

        N1.sauvegarderMap();

    }

}
