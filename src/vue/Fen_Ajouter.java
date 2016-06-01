package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.ListeUtilisateurs;
import model.Utilisateur;
import model.UtilisateurDejaPresentException;
import utils.ManipulationFichier;

/**
 *
 * @author 1395945
 */
public class Fen_Ajouter extends JFrame {

    //attributs
    private JLabel lblTitre;
    private JLabel lblNom;
    private JTextField txtNom;

    private JButton btnAjouter;
    private JButton btnQuitter;

    private JPanel jpPanel;
    private JPanel jpCentre;
    private JPanel jpNord;
    private JPanel jpSud;

    //constructeur
    public Fen_Ajouter(final Fen_Liste fenetreListe, final String fichier, final ListeUtilisateurs listeUtilisateurs) {

        lblTitre = new JLabel("Ajouter nouvel utilisateur:");
        lblNom = new JLabel("Nom: ");
        txtNom = new JTextField(10);
        btnAjouter = new JButton("Ajouter");
        btnQuitter = new JButton("Quitter");

        jpPanel = new JPanel(new BorderLayout());

        jpNord = new JPanel();
        jpPanel.add(jpNord, BorderLayout.NORTH);
        jpNord.add(lblTitre);

        jpCentre = new JPanel();
        jpPanel.add(jpCentre, BorderLayout.CENTER);
        jpCentre.add(lblNom);
        jpCentre.add(txtNom);

        jpSud = new JPanel();
        jpPanel.add(jpSud, BorderLayout.SOUTH);
        jpSud.add(btnAjouter);
        jpSud.add(btnQuitter);

        //listeners
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtNom.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "SVP entrer le nom utilisateur!", "Erreur!",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Utilisateur nouvelUtilisateur = new Utilisateur(txtNom.getText(), 0);

                    //ajout nouvel utilisateur dans la liste utilisateurs
                    try {
                        listeUtilisateurs.ajouterUtilisateur(nouvelUtilisateur);
//                        JOptionPane.showMessageDialog(null, "Utilisateur: \n" + txtNom.getText() + " " + "\na été ajouté");
                        fenetreListe.revalidate();//pourquoi la fenetre ne se refresh pas???
                        fenetreListe.repaint();
                        
                        //si nouvel utilisateur a été bien ajouter, alors continue
                        
                        fenetreListe.setVisible(false);
                        setVisible(false);
                        
                        //instantion fenetre principal avec nouvel utilisateur créé
                        Fen_Principale fenPrincipal = new Fen_Principale(fichier, listeUtilisateurs, nouvelUtilisateur);
                        

                    } catch (UtilisateurDejaPresentException utilisateur) {
                        JOptionPane.showMessageDialog(null, nouvelUtilisateur + " existe déja!", "Doublon!",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    //sauvgare modification imediat dans le fichier
                    try {
                        ManipulationFichier.ecrireListeDansLeFichier(fichier, listeUtilisateurs);

                        

                    } catch (Exception ex) {
                        System.out.println("Ecriture fichier invalide");
                    }

                }
            }
        });

        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });

        //condiguration fenetre
        setTitle("Ajouter un nouveau utilisateur");
        setContentPane(jpPanel);
        setSize(300, 150);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);

    }

}
