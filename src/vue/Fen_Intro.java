package vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ListeUtilisateurs;
import model.Utilisateur;

/**
 * ver 3.0
 * @author Dan-Ghenadie Roman
 */
public class Fen_Intro extends JFrame {

    //attributs
    private Utilisateur utilisateur;
    private ListeUtilisateurs liste;
    private String fichier;

    private JLabel lblImageLogo;
    private JLabel lblTexteTitre;
    private JLabel lblImagePlay;

    private JPanel jpPanel;
    private JPanel jpLogo;
    private JPanel jpCentre;
    private JPanel jpSud;

    private JButton btnStart;

    //constructeur
    public Fen_Intro(String fichierRecu, ListeUtilisateurs listeRecu) {

        this.fichier = fichierRecu;
        this.liste = listeRecu;

        lblImageLogo = new JLabel();
        lblImageLogo.setIcon(new ImageIcon(getClass().getResource("/images/logo.jpg")));

        btnStart = new JButton(new ImageIcon(getClass().getResource("/images/play40px.png")));
        btnStart.setBackground(Color.white);
        btnStart.setBorderPainted(false);

        //les paneaux
        jpPanel = new JPanel();
        jpPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpPanel.setBackground(Color.white);

        jpLogo = new JPanel();
        jpPanel.add(jpLogo);
        jpLogo.add(lblImageLogo);

        jpCentre = new JPanel();
        jpPanel.add(jpCentre);
        jpCentre.add(btnStart);
        jpCentre.setBackground(Color.white);

        //les ecouteurs
        btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Fen_Liste fen_liste = new Fen_Liste(fichier, liste);
                fen_liste.setVisible(true);
                setVisible(false);
            }
        });

        //set fenetre
        setTitle("Kumon: Bienvenue");
        setContentPane(jpPanel);
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
