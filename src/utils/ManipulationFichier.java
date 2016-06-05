package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.ListeUtilisateurs;
import model.Utilisateur;
import model.UtilisateurDejaPresentException;

/**
 * ver 1.0
 *
 * @author Dan-Ghenadie Roman
 */
public class ManipulationFichier {

    //lire données fichier et ecrire dans la liste utilisateur
    public static void lireFichierDansLaListe(String fichier, ListeUtilisateurs liste) {

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(new File(fichier)));

            String ligne;
            while ((ligne = br.readLine()) != null) {

                Utilisateur utilisateur = Utilitaire.ligneVersObjetUtilisateur(ligne);

                if (utilisateur == null) {//oui, s'execute si erreur format sur la ligne
                    JOptionPane.showMessageDialog(null, "Erreur format sur la ligne: \n" + ligne, "Manipulation fichier!",
                            JOptionPane.ERROR_MESSAGE);

                } else {
                    try {
                        liste.ajouterUtilisateur(utilisateur);
                    } catch (UtilisateurDejaPresentException eAjout) {
                        JOptionPane.showMessageDialog(null, ligne + "\nUtilisateur doublon trouvé dans le fichier."
                                + "\nL'utilisateur ne sera pas stocker dans la liste\n", 
                                "Manipulation fichier!", JOptionPane.ERROR_MESSAGE);

                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //transcrire les utilisateurs de la liste dans le fichier
    public static void ecrireListeDansLeFichier(String fichier, ListeUtilisateurs liste) {

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(new File(fichier)));

            for (Utilisateur utilisateur : liste) {

                bw.write(utilisateur.getNom() + " " + utilisateur.getNiveau() + "\n");
            }
//            JOptionPane.showMessageDialog(null, "Écriture fichier réussite", "Manipulation fichier",
//                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur fermeture buffer!", "Manipulation fichier",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
