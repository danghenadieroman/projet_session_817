package vue;

import java.awt.BorderLayout;
import java.awt.Color;
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
 *
 * @author Irina Meghi-Roman
 */
public class Fen_Liste extends JFrame {

    //attributs
    private JPanel jpPanel;
    private JPanel jpNord;
    private JPanel jpCentre;
    private JPanel jpSud;
    private JPanel jpListe;

    private JButton btnRefresh;
    //
    private JList list;
    private DefaultListModel model;

    private JButton btnCommancer;
    private JButton btnAjouter;
    private JButton btnSupprimer;

    //constructeur
    public Fen_Liste(final String fichier, final ListeUtilisateurs liste) {

        //labes
        //les paneaux
        jpPanel = new JPanel();
        jpPanel.setLayout(new BorderLayout());
        jpPanel.setBackground(Color.white);

        jpNord = new JPanel();
        jpPanel.add(jpNord, BorderLayout.NORTH);
        jpNord.setBackground(Color.yellow);

        //panel Liste Utilisateurs
        jpListe = new JPanel(new BorderLayout());
        jpListe.setLayout(new BorderLayout());
        model = new DefaultListModel();
        list = new JList(model);

        JScrollPane pane = new JScrollPane(list);

        btnAjouter = new JButton("Ajouter");
        btnCommancer = new JButton("Commancer");
        btnSupprimer = new JButton("Supprimer");

        jpListe.add(pane, BorderLayout.NORTH);
        jpListe.add(btnCommancer, BorderLayout.CENTER);
        jpListe.add(btnAjouter, BorderLayout.WEST);
        jpListe.add(btnSupprimer, BorderLayout.EAST);

        //parcourir la liste utilisateurs pour afficher 
        for (Utilisateur utilisateur : liste) {
            model.addElement(utilisateur.getNom() + " " + utilisateur.getNiveau());
        }

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
//                setVisible(false);
                if (list.getSelectedIndex() >= 0) {
                    int index = list.getSelectedIndex();
                    Utilisateur utilisateur = liste.get(index);
                    Fen_Principale fenPrincipal = new Fen_Principale(fichier, liste, utilisateur);
                } else {
                    JOptionPane.showMessageDialog(null, " Selectioner l<utilisateur SVP", "Erreur!",
                                JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        //bouton supprimer
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int index = list.getSelectedIndex();
                if (index >= 0) { //Remove only if a particular item is selected
                    model.removeElementAt(index);
                    liste.remove(index);
                    ManipulationFichier.ecrireListeDansLeFichier(fichier, liste);
                }
            }
        });
        //fin jpList

        jpCentre = new JPanel();
        jpPanel.add(jpCentre, BorderLayout.CENTER);
        jpCentre.add(jpListe);

        //set fenetre
        setTitle("Kumon: Liste utilisateurs");
        setContentPane(jpPanel);
        setSize(700, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

}
