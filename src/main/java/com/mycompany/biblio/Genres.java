package com.mycompany.biblio;

public class Genres {

    private int idGenre;
    private String nom;
    private String description;

    // Constructeurs, getters et setters
    public Genres(int idGenre, String nom, String description) {
        this.idGenre = idGenre;
        this.nom = nom;
        this.description = description;
    }

    public Genres() {
    }

    // Getters et setters ici
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.nom;
    }

}
