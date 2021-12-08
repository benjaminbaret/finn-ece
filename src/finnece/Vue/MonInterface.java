package finnece.Vue;

import java.awt.BorderLayout;
import java.awt.*;
import java.applet.Applet;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicArrowButton;


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

    private JLabel fond;
    private JPanel bancquise;
    GridLayout grid = new GridLayout(3, 2);

    public MonInterface() {
        super("Club Penguin Thin Ice ©");//Titre de l'app
        build();//On initialise notre fenêtre
    }

    private void build() {

        //Fond();
        MenuBar();
        Map();


        /*Fenêtre*/
        setSize(828, 467); //On donne une taille à notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(false); //On interdit la redimensionnement de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix

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

        }

        if (s.equals("Règles")) {

        }
        if (s.equals("Nouvelle partie")) {

        }

        if (s.equals("Reprendre la partie")) {

        }

    }

    /*Background*/
    private void Fond() {
        bancquise = new JPanel();
        fond = new JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fond.setIcon(new ImageIcon(getClass().getResource("/finnece/Image/5e0c27b92a3badb6a54d4a306cf0e176.jpg"))); // NOI18N

        GroupLayout Bancquise = new GroupLayout(bancquise);
        bancquise.setLayout(Bancquise);
        Bancquise.setHorizontalGroup(
                Bancquise.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fond, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 832, GroupLayout.PREFERRED_SIZE)
        );
        Bancquise.setVerticalGroup(
                Bancquise.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fond, GroupLayout.PREFERRED_SIZE, 472, GroupLayout.PREFERRED_SIZE)
        );

        GroupLayout arrierePlan = new GroupLayout(getContentPane());
        getContentPane().setLayout(arrierePlan);
        arrierePlan.setHorizontalGroup(
                arrierePlan.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bancquise, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        arrierePlan.setVerticalGroup(
                arrierePlan.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bancquise, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}
