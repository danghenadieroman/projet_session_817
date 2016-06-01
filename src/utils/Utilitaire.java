package utils;

import model.Utilisateur;

/**
 * ver 1.0
 *
 * @author 1395945
 */
public class Utilitaire {

    //validation si entrée est numerique
    public static boolean isNumerique(String string) {

        try {
            int niveau = Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //converte une ligne fichier vers un objet de type Utilisateur
    public static Utilisateur ligneVersObjetUtilisateur(String ligne) {

        String[] tokens = new String[2];

        if (ligne != null) {
            
            try {
                tokens = ligne.split(" ");
                String nom = tokens[0];
                int niveau = Integer.parseInt(tokens[1]);
                return new Utilisateur(nom, niveau);
            } catch (Exception e) {
            }
        }
        return null;
    }
    
    
}
