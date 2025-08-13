package com.mycompany.biblio;

public class Auteur {

    private int idAuteur;
    private String nom;
    private String prenom;

    // Constructeurs, getters et setters
    public Auteur(int idAuteur, String nom, String prenom) {
        this.idAuteur = idAuteur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Auteur() {
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
    
}
