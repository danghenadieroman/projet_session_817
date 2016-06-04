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

        ListeUtilisateurs liste = new ListeUtilisateurs();
        String fichier = "data.txt";

        Fen_Intro fen_intro = new Fen_Intro(fichier, liste);
        
        

    }

}
