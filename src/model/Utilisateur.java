package model;

import java.util.Objects;

/**
 * ver 1.0
 *
 * @author 1395945
 */
public class Utilisateur {

    //attributs
    private String nom;
    private int niveau;

    //constructeur
    public Utilisateur() {
    }

    public Utilisateur(String nom, int niveau) {
        this.nom = nom;
        this.niveau = niveau;
    }

    //getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    //toString
    @Override
    public String toString() {
        return "Utilisateur {" + "nom: " + nom + ", niveau: " + niveau + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    //equals
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
//        if (this.niveau != other.niveau) {
//            return false;
//        }
        return true;
    }

}
