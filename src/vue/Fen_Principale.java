package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jdk.nashorn.internal.ir.ContinueNode;
import model.ListeUtilisateurs;
import model.Utilisateur;

/**
 *
 * @author Irina Meghi-Roman
 */
public class Fen_Principale extends JFrame {

    private int MIN = 1;
    private int MAX = 10;
    private int nombreFauts = 0;

    //attributs
    private JPanel jpPanel;
    private JPanel jpNord;
    private JPanel jpInfo;
    private JPanel jpSud;
    private JPanel jpOpperations;

    private JButton btnA = new JButton();
    private JButton btnSign = new JButton();
    private JButton btnB = new JButton();
    private JButton btnEgal = new JButton();
    private JButton btnResultat = new JButton();

    private JButton btnOk = new JButton();
    private JButton btnAnnuler = new JButton();

    private JLabel lblUtilisateur = new JLabel("Utilisateur: ", JLabel.CENTER);
    private JLabel lblNiveau = new JLabel("Niveau accumulé: ", JLabel.CENTER);
    private JLabel lblTemps = new JLabel("Temps: 00:00", JLabel.CENTER);
    private JLabel lblFautes = new JLabel("Fauts: 0", JLabel.CENTER);
    private JLabel lblInfo = new JLabel("Info", JLabel.CENTER);

    public Fen_Principale(final String fichier, final ListeUtilisateurs liste, final Utilisateur utilisateurCourant) {

        //les paneaux
        //panel principal
        jpPanel = new JPanel(new GridLayout(2, 1));

        //jpNord
        jpNord = new JPanel(new GridLayout(2, 1));
        jpPanel.add(jpNord);

        //jpInfo
        jpInfo = new JPanel(new GridLayout(1, 5));
        jpNord.add(jpInfo);
        jpInfo.add(lblUtilisateur);
        jpInfo.add(lblNiveau);
        jpInfo.add(lblTemps);
        jpInfo.add(lblFautes);
        jpInfo.add(lblInfo);

        lblUtilisateur.setText("Utilisateur: " + utilisateurCourant.getNom());
        lblNiveau.setText("Niveau: " + utilisateurCourant.getNiveau());

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

        JButton[] nombres = new JButton[10];

        //générer une liste des entiers
        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = new JButton("" + i);
            nombres[i].addActionListener(new ButtonClickListener());
            nombres[i].setFont(new Font(null, Font.BOLD, 20));
            jpSud.add(nombres[i]);
        }
        jpSud.add(btnOk);
        btnOk.setText("OK");
        btnOk.addActionListener(new ButtonClickListener());

        jpSud.add(btnAnnuler);
        btnAnnuler.setText("Annuler");
        btnAnnuler.addActionListener(new ButtonClickListener());

        //set fenetre
        setTitle("Kumon: Page principale");
        setContentPane(jpPanel);
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent windowEvent) {
//                System.exit(0);
//            }
//        });

    }//constructeur

    private class ButtonClickListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String command = e.getActionCommand();
            switch (command) {
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
            }
        }
    }//private class ButtonClickListener

    //methodes
    public void verifierResultat() {

        if (btnResultat.getText().isEmpty()) {

        } else if (verifierResultat(btnResultat, btnA, btnB, btnSign)) {
            lblInfo.setText("BRAVO");
            genererOpperation();
        } else {
            lblInfo.setText("INCORRECT");
            btnResultat.setText("");
            nombreFauts++;
            lblFautes.setText("Fauts: " + nombreFauts);
            lblFautes.setForeground(Color.red);
        }
    }

    public void genererOpperation() {
        //generer les opperations
        btnA.setText("" + genererNombre(MIN, MAX));
        btnSign.setText("+");
        btnB.setText("" + genererNombre(MIN, MAX));
        btnEgal.setText("=");
        btnResultat.setText("");

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
