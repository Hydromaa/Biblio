package com.mycompany.biblio;

public class Livres_Auteurs {

    private int idLivre;
    private int idAuteur;

    public Livres_Auteurs(int idLivre, int idAuteur) {
        this.idLivre = idLivre;
        this.idAuteur = idAuteur;
    }

    public Livres_Auteurs() {
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }
}
