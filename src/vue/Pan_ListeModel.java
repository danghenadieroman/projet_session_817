package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.ListeUtilisateurs;
import model.Utilisateur;
import utils.ManipulationFichier;

public class Pan_ListeModel extends JPanel {

    JList list;
    DefaultListModel model;

    JButton btnCommancer;
    JButton btnAjouter;
    JButton btnSupprimer;

    //constructeur
    public Pan_ListeModel(final Fen_Liste fenListe, final String fichier, final ListeUtilisateurs listeUtilisateurs) {

        setLayout(new BorderLayout());
        model = new DefaultListModel();
        list = new JList(model);
        JScrollPane pane = new JScrollPane(list);

        btnCommancer = new JButton("Commancer");
        btnAjouter = new JButton("Ajouter");
        btnSupprimer = new JButton("Supprimer");

        //
        Fen_Ajouter fenAjouter = new Fen_Ajouter(fenListe, fichier, listeUtilisateurs);
        fenAjouter.setVisible(false);

        //parcourir la liste utilisateurs pour afficher 
        for (Utilisateur utilisateur : listeUtilisateurs) {
            model.addElement(utilisateur.getNom() + " " + utilisateur.getNiveau());
        }

        //bouton ajouter
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Fen_Ajouter fenAjouter = new Fen_Ajouter(fenListe, fichier, listeUtilisateurs);
                
            }
        });

        //bouton commancer
        btnCommancer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fenListe.setVisible(false);
                int index = list.getSelectedIndex();
                Utilisateur utilisateurSelectione = listeUtilisateurs.get(index);
                Fen_Principale fenPrincipal = new Fen_Principale(fichier, listeUtilisateurs, utilisateurSelectione);
                fenListe.setVisible(false);
            }
        });

        //bouton supprimer
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int index = list.getSelectedIndex();
                if (index >= 0) { //Remove only if a particular item is selected
                    model.removeElementAt(index);
                    System.out.println(index);
                    listeUtilisateurs.remove(index);
                    ManipulationFichier.ecrireListeDansLeFichier(fichier, listeUtilisateurs);
                }
            }
        });

        //configuration paneau
        add(pane, BorderLayout.NORTH);
        add(btnCommancer, BorderLayout.CENTER);
        add(btnAjouter, BorderLayout.WEST);
        add(btnSupprimer, BorderLayout.EAST);
    }

}
