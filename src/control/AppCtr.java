package control;

import model.ListeUtilisateurs;
import model.Utilisateur;
import utils.ManipulationFichier;
import vue.Fen_Intro;
import vue.Fen_Liste;
import vue.Fen_Principale;

/**
 * ver 1.0
 *
 * @author 1395945
 */
public class AppCtr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ListeUtilisateurs listeUtilisateurs = new ListeUtilisateurs();
        String fichier = "data.txt";
        ManipulationFichier.lireFichierDansLaListe(fichier, listeUtilisateurs);

       Fen_Principale f = new Fen_Principale(fichier, listeUtilisateurs, new Utilisateur("Dan", 0));
       
       
//        Fen_Liste fen_liste = new Fen_Liste(fichier, listeUtilisateurs);
//        Fen_Intro fen_intro = new Fen_Intro(fichier, listeUtilisateurs, fen_liste);
//        fen_liste.setVisible(false);


    }

}
