package model;

import java.util.ArrayList;

/**
 * ver 1.0
 *
 * @author 1395945
 */
public class ListeUtilisateurs extends ArrayList<Utilisateur> {

    //ajout nouveau utilisateur dans Registre Utilisateurs
    public void ajouterUtilisateur(Utilisateur utilisateur)
            throws UtilisateurDejaPresentException {

        if (verifierDoublonListeUtilisateurs(utilisateur)) {
            throw new UtilisateurDejaPresentException(utilisateur);

        } else {
            this.add(utilisateur);
        }
    }

    //affiche la liste des utilisateur
    public void afficherListeUtilisateur() {

        if (this.isEmpty()) {
            System.out.println("Liste est vide!");
        } else {

            for (Utilisateur utilisateur : this) {
                System.out.println(utilisateur.toString());
            }
        }
    }

    //verifie s'il existe un tel utilisateur dans le registre utilisateurs
    public boolean verifierDoublonListeUtilisateurs(Utilisateur utilisateur) {

        for (Utilisateur temp : this) {
            if (temp.equals(utilisateur)) {
                return true;
            }
        }
        return false;
    }

}
