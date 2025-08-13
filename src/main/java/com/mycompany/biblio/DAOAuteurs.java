package com.mycompany.biblio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOAuteurs extends DAO<Auteur, String> {

    public List<Auteur> findAllCombo() throws SQLException {
        //Liste des auteurs pour le combobox GestionLivrePan
        String req = "SELECT * FROM auteurs ORDER BY nom ASC;";

        List<Auteur> auteurs_list = new ArrayList<>();

        PreparedStatement ps = conn.prepareCall(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            auteurs_list.add(rsToObj(rs));
        }
        return auteurs_list;
    }

    public List<Auteur> findByName(String recherche) throws SQLException {

        String req = "SELECT id_auteur, nom, prenom FROM auteurs WHERE nom LIKE ? ORDER BY nom ASC;";

        List<Auteur> auteurs_list = new ArrayList<>();

        PreparedStatement ps = conn.prepareCall(req);
        ps.setString(1, recherche + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            auteurs_list.add(rsToObj(rs));
        }
        return auteurs_list;
    }

    @Override
    public boolean create(Auteur auteur) throws SQLException {

        String req = "INSERT INTO auteurs (nom, prenom) "
                + "VALUES (?, ?)";

        PreparedStatement ps = conn.prepareStatement(req);

        ps.setString(1, auteur.getNom());
        ps.setString(2, auteur.getPrenom());

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Auteur auteur) throws SQLException {

        String req = "UPDATE auteur SET nom = ?, "
                + "prenom = ?;";

        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, auteur.getNom());
        ps.setString(2, auteur.getPrenom());

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Auteur findById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Auteur> findAll(String recherche) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Auteur rsToObj(ResultSet rs) throws SQLException {

        Auteur auteur = new Auteur();

        auteur.setIdAuteur(rs.getInt(1));
        auteur.setNom(rs.getString(2));
        auteur.setPrenom(rs.getString(3));

        return auteur;

    }

    public int getLastInsertedId() {

        //ID à -1 pour vérification, si -1 => Erreur
        int lastId = -1;

        String req = "SELECT LAST_INSERT_ID()"; // Récupère l'ID du dernier INSERT

        try (PreparedStatement stmt = conn.prepareStatement(req)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastId;
    }

}
