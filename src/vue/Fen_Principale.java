package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ListeUtilisateurs;
import model.Utilisateur;
import utils.ManipulationFichier;

//test1 
//test2
//test3
        
       

/**
 * ver 3.0
 * @author Dan-Ghenadie Roman
 */
public class Fen_Principale extends JFrame implements ActionListener {

    //attributs
    private Utilisateur utilisateur;
    private ListeUtilisateurs liste;
    private String fichier;

    private int MIN = 1;
    private int MAX = 10;
    private int nombreFauts = 0;
    private int nombreOpp = 0;
    private int compteurOpp = 0;

    //panels
    private JPanel jpPanel;
    private JPanel jpNord;
    private JPanel jpInfo;
    private JPanel jpSud;
    private JPanel jpOpperations;

    //boutons
    private JButton btnA = new JButton();
    private JButton btnSign = new JButton();
    private JButton btnB = new JButton();
    private JButton btnEgal = new JButton();
    private JButton btnResultat = new JButton();

    private JButton btnZero = new JButton("0");
    private JButton btnOk = new JButton("OK");
    private JButton btnAnnuler = new JButton("Annuler");

    private JButton btnRetour = new JButton("Retour");

    //labels
    private JLabel lblUtilisateur = new JLabel("Utilisateur: ", JLabel.CENTER);
    private JLabel lblNiveau = new JLabel("Niveau accumulé: ", JLabel.CENTER);
    private JLabel lblTemps = new JLabel("Temps: 00:00", JLabel.CENTER);
    private JLabel lblFautes = new JLabel("Fauts: 0", JLabel.CENTER);
    private JLabel lblNombreOpp = new JLabel("Opperation: 0", JLabel.CENTER);
    private JLabel lblInfo = new JLabel("", JLabel.CENTER);

    public Fen_Principale(String fichier, ListeUtilisateurs liste, Utilisateur utilisateur) {

        this.fichier = fichier;
        this.liste = liste;
        this.utilisateur = utilisateur;

        //les paneaux
        //panel principal
        jpPanel = new JPanel(new GridLayout(2, 1));

        //jpNord
        jpNord = new JPanel(new GridLayout(2, 1));
        jpPanel.add(jpNord);

        //jpInfo: paneau Info Utilisateur
        jpInfo = new JPanel(new GridLayout(2, 5));
        jpNord.add(jpInfo);
        jpInfo.add(lblUtilisateur);
        jpInfo.add(lblNiveau);
        jpInfo.add(lblNombreOpp);
        jpInfo.add(btnRetour);
        jpInfo.add(lblTemps);
        jpInfo.add(lblFautes);

        btnRetour.addActionListener(this);

        jpInfo.add(lblInfo);

        lblUtilisateur.setText("Utilisateur: " + utilisateur.getNom());
        lblNiveau.setText("Niveau: " + utilisateur.getNiveau());

        //jpOpperations
        jpOpperations = new JPanel(new GridLayout(1, 5, 2, 2));
        jpNord.add(jpOpperations);

        //generer les opperations
        btnA = new JButton("" + genererNombre(MIN, MAX));
        btnA.setFont(new Font(null, Font.PLAIN, 36));
        btnSign = new JButton("+");
        btnSign.setFont(new Font(null, Font.PLAIN, 36));
        btnB = new JButton("" + genererNombre(MIN, MAX));
        btnB.setFont(new Font(null, Font.PLAIN, 36));
        btnEgal = new JButton("=");
        btnEgal.setFont(new Font(null, Font.PLAIN, 36));

        btnResultat = new JButton("");
        btnResultat.setFont(new Font(null, Font.PLAIN, 36));

        jpOpperations.add(btnA);
        jpOpperations.add(btnSign);
        jpOpperations.add(btnB);
        jpOpperations.add(btnEgal);
        jpOpperations.add(btnResultat);

        //jpSud
        jpSud = new JPanel(new GridLayout(4, 3, 1, 1));
        jpPanel.add(jpSud);

        //générer une liste des entiers
        JButton[] nombres = new JButton[10];

        for (int i = 1; i < nombres.length; i++) {
            nombres[i] = new JButton("" + i);
            nombres[i].addActionListener(this);
            nombres[i].setFont(new Font(null, Font.BOLD, 20));
            jpSud.add(nombres[i]);
        }
        jpSud.add(btnZero);
        btnZero.setFont(new Font(null, Font.BOLD, 20));
        btnZero.addActionListener(this);

        jpSud.add(btnOk);
        btnOk.addActionListener(this);

        jpSud.add(btnAnnuler);
        btnAnnuler.addActionListener(this);

        //set fenetre
        setTitle("Kumon: Page principale");
        setContentPane(jpPanel);
        setSize(500, 350);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }//constructeur

    //override methodes
    //ecouteurs pour les objets de la fenetre
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "0": {
                btnResultat.setText(btnResultat.getText() + "0");
                break;
            }
            case "1": {
                btnResultat.setText(btnResultat.getText() + "1");
                break;
            }
            case "2": {
                btnResultat.setText(btnResultat.getText() + "2");
                break;
            }
            case "3": {
                btnResultat.setText(btnResultat.getText() + "3");
                break;
            }
            case "4": {
                btnResultat.setText(btnResultat.getText() + "4");
                break;
            }
            case "5": {
                btnResultat.setText(btnResultat.getText() + "5");
                break;
            }
            case "6": {
                btnResultat.setText(btnResultat.getText() + "6");
                break;
            }
            case "7": {
                btnResultat.setText(btnResultat.getText() + "7");
                break;
            }
            case "8": {
                btnResultat.setText(btnResultat.getText() + "8");
                break;
            }
            case "9": {
                btnResultat.setText(btnResultat.getText() + "9");
                break;
            }
            case "OK": {
                verifierResultat();
                break;
            }
            case "Annuler": {
                btnResultat.setText("");
                break;
            }
            case "Retour": {
                setVisible(false);
                break;
            }
        }//switch
    }

    //object methodes
    public void verifierResultat() {

        if (!btnResultat.getText().isEmpty()) {

            if (verifierResultat(btnResultat, btnA, btnB, btnSign)) {

                lblInfo.setText("BRAVO");
                btnResultat.setText("");
                nombreOpp++;
                lblNombreOpp.setText("Opperation: " + nombreOpp);
                genererOpperation(utilisateur);

                if (nombreOpp == 3 && nombreFauts == 0) {
                    augmanterNiveauUtilisateur(utilisateur);
                    JOptionPane.showMessageDialog(null, "Bravo! On continue les exercices!", "Augmentation de niveau!",
                            JOptionPane.PLAIN_MESSAGE);
                    ManipulationFichier.ecrireListeDansLeFichier(fichier, liste);
                    metAJourFenetrePrincipale();

                } else {
                    if (nombreOpp == 3 && nombreFauts > 0) {
                        JOptionPane.showMessageDialog(null, "Désolée. On recommance les exercices!", "Pas augmentation de niveau!",
                                JOptionPane.PLAIN_MESSAGE);
                        metAJourFenetrePrincipale();
                    }
                }

            } else {
                lblInfo.setText("INCORRECT");
                btnResultat.setText("");
                nombreFauts++;
                lblFautes.setText("Fauts: " + nombreFauts);
                lblFautes.setForeground(Color.red);
            }
        }
    }

    public int determinerComplexiteOpperation(Utilisateur utilisateur) {

        return utilisateur.getNiveau();
        //ici il faut elaborer une algorithme de determination niveau opperation depandement de niveau utilisateur

    }

    public void metAJourFenetrePrincipale() {
        lblNiveau.setText("Niveau: " + utilisateur.getNiveau());
        nombreOpp = 0;
        lblNombreOpp.setText("Opperation: " + nombreOpp);
        nombreFauts = 0;
        lblFautes.setText("Fautes: " + nombreFauts);
    }

    public void genererOpperation(Utilisateur utilisateur) {
        //generer les opperations
        //algorithme temporaire pour faire la demonstration (Must Have)
        int max = determinerComplexiteOpperation(utilisateur);
        int min = 0;
        if (max > 20) {
            min = max - 20;
        }

        btnA.setText("" + genererNombre(0, 9));
        btnSign.setText("+");
        btnB.setText("" + genererNombre(min, max));
        btnEgal.setText("=");
        btnResultat.setText("");

    }

    //modifier niveau utilisateur
    public void augmanterNiveauUtilisateur(Utilisateur utilisateur) {

        utilisateur.setNiveau(utilisateur.getNiveau() + 1);
    }

    //verifier si resultat d'opperation est correct
    public boolean verifierResultat(JButton btnR, JButton btnA, JButton btnB, JButton btnSign) {

        int r = Integer.valueOf(btnR.getText());
        int a = Integer.valueOf(btnA.getText());
        int b = Integer.valueOf(btnB.getText());
        String sign = btnSign.getText();
        boolean resultat = true;

        switch (sign) {
            case "+": {
                resultat = r == a + b;
                break;
            }
            case "-": {
                resultat = r == a - b;
                break;
            }
            case "*": {
                resultat = r == a * b;
                break;
            }
            case ":": {
                resultat = r == a / b;
                break;
            }
            default: {
                resultat = false;
            }
        }
        return resultat;
    }

    //generer un nombre aléatoir entre min et max
    public int genererNombre(int minimum, int maximum) {

        return minimum + (int) (Math.random() * maximum);
    }

}
