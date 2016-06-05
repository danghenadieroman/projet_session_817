package model;

/**
 * ver 1.0
 *
 * @author Dan-Ghenadie Roman
 */
public class UtilisateurDejaPresentException extends Exception {

    //attributs
    private Utilisateur utilisateur;

    //constructeurs
    public UtilisateurDejaPresentException() {
    }

    public UtilisateurDejaPresentException(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    //getters et setters
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
