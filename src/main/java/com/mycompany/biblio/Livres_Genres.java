package com.mycompany.biblio;

public class Livres_Genres {

    private int idLivre;
    private int idGenre;

    public Livres_Genres(int idLivre, int idGenre) {
        this.idLivre = idLivre;
        this.idGenre = idGenre;
    }

    public Livres_Genres() {
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
}
