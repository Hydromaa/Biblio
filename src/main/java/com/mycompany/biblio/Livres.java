package com.mycompany.biblio;


public class Livres {

    private int id_livre;
    private String titre;
    private String isbn;
    private int anneePublication;

    public Livres(int id_livre, String titre, String isbn, int anneePublication) {
        this.id_livre = id_livre;
        this.titre = titre;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
    }

    public Livres() {
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

}
