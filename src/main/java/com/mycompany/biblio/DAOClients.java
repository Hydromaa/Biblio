package com.mycompany.biblio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOClients extends DAO<Clients, String> {

    public ArrayList<Clients> findAllByString(String string) {

        ArrayList<Clients> clientsList = new ArrayList<>();
        string = "%" + string + "%";

        String req = "SELECT * "
                + "FROM clients "
                + "WHERE nom LIKE ? "
                + "OR prenom LIKE ? "
                + "OR adresse LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setString(1, string);
            ps.setString(2, string);
            ps.setString(3, string);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Clients client = rsToObj(rs);
                    clientsList.add(client);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOLivres.class.getName()).log(Level.SEVERE, "Erreur lors de l'exécution de la requête ou de la préparation", e);
        }
        return clientsList;
    }

    @Override
    public boolean create(Clients client) {

        String req = "INSERT INTO clients (nom, prenom, email, telephone, adresse) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getAdresse());

            int rowsAffected = ps.executeUpdate();
            //RowsAffected => Retour de la DB après update. 0 = 0 lignes changées.
            if (rowsAffected > 0) {
                return true;
            } else {
                Logger.getLogger(DAOClients.class.getName()).log(Level.WARNING, "Aucune ligne insérée.");
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(DAOClients.class.getName()).log(Level.SEVERE, "Erreur lors de l'exécution de la requête : " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(Clients client) {
        String req = "UPDATE clients SET nom = ?,"
                + "prenom = ?, "
                + "email = ?, "
                + "telephone = ?, "
                + "adresse = ? "
                + "WHERE id_client = ?;";

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, client.getPrenom());
            ps.setString(2, client.getNom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getAdresse());
            ps.setInt(6, client.getId_client());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                Logger.getLogger(DAOExemplaires.class.getName()).log(Level.WARNING, "Aucune ligne mise à jour.");
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(DAOExemplaires.class.getName()).log(Level.SEVERE, "Erreur lors de l'exécution de la requête : " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Clients findById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Clients> findAll(String String) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Clients rsToObj(ResultSet rs) throws SQLException {

        Clients client = new Clients();

        client.setId_client(rs.getInt(1));
        client.setNom(rs.getString(2));
        client.setPrenom(rs.getString(3));
        client.setEmail(rs.getString(4));
        client.setPhone(rs.getString(5));
        client.setAdresse(rs.getString(6));

        return client;
    }
}
