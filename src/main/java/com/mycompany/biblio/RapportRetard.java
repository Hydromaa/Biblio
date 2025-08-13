package com.mycompany.biblio;

import java.sql.Date;

public class RapportRetard {

    private String titre_Livre;
    private String nom_Client;
    private String prenom_Client;
    private String email_Client;
    private String tel_Client;
    private Date dateRetourPrevue;

    public RapportRetard(String titre_Livre, String nom_Client, String prenom_Client, String email_Client, String tel_Client, Date dateRetourPrevue) {
        this.titre_Livre = titre_Livre;
        this.nom_Client = nom_Client;
        this.prenom_Client = prenom_Client;
        this.email_Client = email_Client;
        this.tel_Client = tel_Client;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public RapportRetard() {
    }

    public String getTitre_Livre() {
        return titre_Livre;
    }

    public void setTitre_Livre(String titre_Livre) {
        this.titre_Livre = titre_Livre;
    }

    public String getNom_Client() {
        return nom_Client;
    }

    public void setNom_Client(String nom_Client) {
        this.nom_Client = nom_Client;
    }

    public String getPrenom_Client() {
        return prenom_Client;
    }

    public void setPrenom_Client(String prenom_Client) {
        this.prenom_Client = prenom_Client;
    }

    public String getEmail_Client() {
        return email_Client;
    }

    public void setEmail_Client(String email_Client) {
        this.email_Client = email_Client;
    }

    public String getTel_Client() {
        return tel_Client;
    }

    public void setTel_Client(String tel_Client) {
        this.tel_Client = tel_Client;
    }

    public Date getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(Date dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

}
