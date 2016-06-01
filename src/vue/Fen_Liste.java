package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ListeUtilisateurs;
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


    //constructeur
    public Fen_Liste(String fichier, ListeUtilisateurs liste) {

        //labes

        //les paneaux
        jpPanel = new JPanel();
        jpPanel.setLayout(new BorderLayout());
        jpPanel.setBackground(Color.white);

        jpNord = new JPanel();
        jpPanel.add(jpNord, BorderLayout.NORTH);
        jpNord.setBackground(Color.yellow);

        //list utilisateur pour affichage
        Pan_ListeModel jpListeModel = new Pan_ListeModel(this, fichier, liste);
        
        //cree panel menu
        Pan_Menu jpMenu = new Pan_Menu();

        jpCentre = new JPanel();
        jpPanel.add(jpCentre, BorderLayout.CENTER);
        jpCentre.add(jpListeModel);
        jpNord.add(jpMenu);

        //les ecouteurs
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
