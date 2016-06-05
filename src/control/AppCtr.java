package control;

import model.ListeUtilisateurs;
import vue.Fen_Intro;

/**
 * ver 3.0
 *
 * @author Dan-Ghenadie Roman
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
