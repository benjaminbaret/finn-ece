package finnece.Vue;

import static finnece.Controller.moteurJeu.affichagescore;
import static finnece.Controller.moteurJeu.fileSavePlayer;
import static finnece.Controller.moteurJeu.regle;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bretechersandric
 */
public class MonInterface extends JFrame implements ActionListener {

    java.util.List<java.util.List<String>> infoSavePlayer = new ArrayList<java.util.List<String>>();

    private JLabel fond;
    private JPanel banquise;
    GridLayout grid = new GridLayout(3, 2);

    public MonInterface() {

        super("Club Penguin Thin Ice ©");//Titre de l'app

        build();//On initialise notre fenêtre

    }

    private void build() {
        infoSavePlayer = fileSavePlayer();

        MenuBar();
        Fond();
        //Map();

        /*Fenêtre*/
        super.setSize(968, 571); //On donne une taille à notre fenêtre
        super.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        super.setResizable(false); //On interdit la redimensionnement de la fenêtre
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
    }

    public static void Map() {
        JPanel lvl1 = new JPanel();
        GroupLayout map = new GroupLayout(lvl1);
        lvl1.setLayout(map);

    }

    public void EceMan() {

    }

    /*Barre de menu*/
    private void MenuBar() {
        //menu en image
        /*GridLayout Test = new GridLayout(2, 1);
        JPanel test = new JPanel();
        JButton regle = new JButton();
        regle.setIcon(new ImageIcon(getClass().getResource("/finnece/images/regles.jpg")));
        test.add(regle);
        JButton newpartie = new JButton();
        newpartie.setIcon(new ImageIcon(getClass().getResource("/finnece/images/newpartie.jpg")));
        test.add(newpartie);
        JButton reprendrepartie = new JButton();
        reprendrepartie.setIcon(new ImageIcon(getClass().getResource("/finnece/images/partie.jpg")));
        test.add(reprendrepartie);
        JButton scores = new JButton();
        scores.setIcon(new ImageIcon(getClass().getResource("/finnece/images/scores.jpg")));
        test.add(scores);
        JButton quitter = new JButton();
        quitter.setIcon(new ImageIcon(getClass().getResource("/finnece/images/quitter.jpg")));
        test.add(quitter);
        test.setLayout(Test);
        add(test);*/

        //On définit le menu
        JMenuBar menubar = new JMenuBar();

        // Créer le menu
        JMenuItem m1 = new JMenuItem("Règles");
        JMenuItem m2 = new JMenuItem("Nouvelle partie");
        JMenuItem m3 = new JMenuItem("Reprendre la partie");
        JMenuItem m4 = new JMenuItem("Scores");
        JMenuItem m5 = new JMenuItem("Quitter");

        // Ajouter le menu au barre de menu
        menubar.add(m1);
        menubar.add(m2);
        menubar.add(m3);
        menubar.add(m4);
        menubar.add(m5);

        // Ajouter la barre de menu au frame
        setJMenuBar(menubar);

        //Actions de la barre de menu
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        m5.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();

        if (s.equals("Quitter")) {
            System.exit(0);
        }

        if (s.equals("Scores")) {
            affichagescore(infoSavePlayer);
        }

        if (s.equals("Règles")) {
            regle();
        }
        if (s.equals("Nouvelle partie")) {

        }

        if (s.equals("Reprendre la partie")) {

        }

    }

    /*Background*/
    private void Fond() {
        banquise = new JPanel();
        fond = new JLabel();

        fond.setIcon(new ImageIcon(getClass().getResource("/finnece/images/Menu.jpg"))); // NOI18N

        GroupLayout Banquise = new GroupLayout(banquise);
        banquise.setLayout(Banquise);
        Banquise.setHorizontalGroup(Banquise.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(fond, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 960, GroupLayout.PREFERRED_SIZE)
        );
        Banquise.setVerticalGroup(Banquise.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(fond, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
        );

        GroupLayout arrierePlan = new GroupLayout(getContentPane());
        getContentPane().setLayout(arrierePlan);
        arrierePlan.setHorizontalGroup(arrierePlan.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(banquise, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        arrierePlan.setVerticalGroup(arrierePlan.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(banquise, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

}
