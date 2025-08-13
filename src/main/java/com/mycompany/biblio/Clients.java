package com.mycompany.biblio;
import jakarta.validation.constraints.*;

public class Clients {

    //@NotBlanc - @Email - ... -> Contrainte sur l'attribut
    
    private int id_client;
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;
    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String prenom;
    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "Veuillez entrer un email valide au format exemple@domaine.com")
    private String email;
    private String phone;
    private String adresse;

    public Clients(int id_client, String nom, String prenom, String email, String phone, String adresse) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.adresse = adresse;
    }

    public Clients() {
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
