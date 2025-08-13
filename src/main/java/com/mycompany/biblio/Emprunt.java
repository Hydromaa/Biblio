package com.mycompany.biblio;

import java.time.LocalDate;

public class Emprunt {

    private int idEmprunt;
    private int idClient;
    private String codeBarre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;
    private boolean retardNotif;
    private LocalDate date_retour_reel;

    public Emprunt(int idEmprunt, int idClient, String codeBarre, LocalDate dateEmprunt, LocalDate dateRetourPrevu, boolean retardNotif, LocalDate date_retour_reel) {
        this.idEmprunt = idEmprunt;
        this.idClient = idClient;
        this.codeBarre = codeBarre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevu = dateRetourPrevu;
        this.retardNotif = retardNotif;
        this.date_retour_reel = date_retour_reel;
    }

    public Emprunt() {
    }

    public LocalDate getDate_retour_reel() {
        return date_retour_reel;
    }

    public void setDate_retour_reel(LocalDate date_retour_reel) {
        this.date_retour_reel = date_retour_reel;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public void setDateRetourPrevu(LocalDate dateRetourPrevu) {
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public boolean isRetardNotif() {
        return retardNotif;
    }

    public void setRetardNotif(boolean retardNotif) {
        this.retardNotif = retardNotif;
    }
}
