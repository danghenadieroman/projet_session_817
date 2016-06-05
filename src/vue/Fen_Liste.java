package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.ListeUtilisateurs;
import model.Utilisateur;
import utils.ManipulationFichier;

/**
 * ver 3.0
 * @author Dan-Ghenadie Roman
 */
public class Fen_Liste extends JFrame {

    //attributs
    private String fichier;
    private ListeUtilisateurs liste;
    private JList list;
    private DefaultListModel model;

    private JLabel lblTitre;

    private JPanel jpPanel;
    private JPanel jpNord;
    private JPanel jpCentre;
    private JPanel jpSud;
    private JPanel jpListe;
    private JPanel jpListeBoutons;

    private JButton btnCommancer;
    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JButton btnRefresh;

    //constructeur
    //pourquoi il me demande ici de le metre final pour les faire visible!!!???
    public Fen_Liste(String fichierRecu, ListeUtilisateurs listeRecu) {

        this.fichier = fichierRecu;
        this.liste = listeRecu;
        //pourquoi ici marche directeument, sans le passer par attributs local????
        //lire le fichier (persistence) dans la liste (memoire)
        ManipulationFichier.lireFichierDansLaListe(fichier, liste);
        //s'il y a une erreur format fichier, alors
        //on ecrie la liste dans le fichier pour ce debaracer de cette ligne invalide
        //parce que la ligne invalide n'est pas copie dans la liste utilisateurs
        ManipulationFichier.ecrireListeDansLeFichier(fichier, liste);

        //les paneaux
        jpPanel = new JPanel();
        jpPanel.setLayout(new BorderLayout());
        jpPanel.setBackground(Color.white);

        jpNord = new JPanel();
        jpPanel.add(jpNord, BorderLayout.NORTH);

        lblTitre = new JLabel("Liste des utilisateurs");
        jpNord.add(lblTitre);

        //panel Liste Utilisateurs
        jpListe = new JPanel(new BorderLayout());
        jpListe.setLayout(new BorderLayout());
        model = new DefaultListModel();
        list = new JList(model);

        JScrollPane pane = new JScrollPane(list);

        btnAjouter = new JButton("Ajouter");
        btnCommancer = new JButton("Commancer");
        btnSupprimer = new JButton("Supprimer");
        btnRefresh = new JButton("Refresh");

        jpListeBoutons = new JPanel(new GridBagLayout());
        jpListe.add(jpListeBoutons);
        jpListe.add(pane, BorderLayout.NORTH);
        jpListeBoutons.add(btnCommancer);
        jpListeBoutons.add(btnAjouter);
        jpListeBoutons.add(btnSupprimer);
        jpListeBoutons.add(btnRefresh);

        //creer la liste model depuis la liste utilisateurs
        ajouterListeAuModel(liste);

        //bouton ajouter
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Fen_Ajouter fenAjouter = new Fen_Ajouter(fichier, liste);
            }
        });

        //bouton commancer
        btnCommancer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() >= 0) {
                    int index = list.getSelectedIndex();
                    Utilisateur utilisateur = liste.get(index);
                    Fen_Principale fenPrincipal = new Fen_Principale(fichier, liste, utilisateur);

                } else {
                    JOptionPane.showMessageDialog(null, " Selectionner l'utilisateur SVP", "Erreur!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //bouton supprimer
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int index = list.getSelectedIndex();
                if (index >= 0) {
                    model.removeElementAt(index);
                    liste.remove(index);
                    ManipulationFichier.ecrireListeDansLeFichier(fichier, liste);
                }

            }
        });

        //bouton Refresh
        //faire enregistrer touts les modifications: dans la memoire et fichier, que dans le model
        btnRefresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                model.clear();
                ajouterListeAuModel(liste);
                ManipulationFichier.ecrireListeDansLeFichier(fichier, liste);
            }
        });
        //fin jpList

        //panel Centre: add jpListe: 
        jpCentre = new JPanel();
        jpPanel.add(jpCentre, BorderLayout.CENTER);
        jpCentre.add(jpListe);

        //set fenetre
        setTitle("Liste utilisateurs");
        setContentPane(jpPanel);
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }//constructeur Fen_Liste

    //objet methodes 
    public void ajouterListeAuModel(ListeUtilisateurs liste) {

        for (Utilisateur utilisateur : liste) {
            model.addElement(utilisateur.getNom() + " " + utilisateur.getNiveau());
        }
    }

    public void ajouterUtilisateurAuModel(Utilisateur utilisateur) {

        model.addElement(utilisateur.getNom() + " " + utilisateur.getNiveau());
    }

}
