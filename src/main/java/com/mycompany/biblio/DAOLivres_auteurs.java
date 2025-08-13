package com.mycompany.biblio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOLivres_auteurs extends DAO<Livres_Auteurs, Object> {

    @Override
    public boolean create(Livres_Auteurs obj) throws SQLException {

        String req = "INSERT INTO livres_auteurs (id_livre, id_auteur) "
                + "VALUES (?, ?)";

        PreparedStatement ps = conn.prepareStatement(req);

        ps.setInt(1, obj.getIdLivre());
        ps.setInt(2, obj.getIdAuteur());

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean update(Livres_Auteurs obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Livres_Auteurs findById(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Livres_Auteurs> findAll(Object String) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Livres_Auteurs rsToObj(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
