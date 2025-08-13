package com.mycompany.biblio;

public class Exemplaire {

    private String codeBarre;
    private int idLivre;
    private String etat;
    private String remarque;

    // Constructeurs, getters et setters
    public Exemplaire(String codeBarre, int idLivre, String etat, String remarque) {
        this.codeBarre = codeBarre;
        this.idLivre = idLivre;
        this.etat = etat;
        this.remarque = remarque;
    }

    public Exemplaire() {
    }

    // Getters et setters ici
    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
