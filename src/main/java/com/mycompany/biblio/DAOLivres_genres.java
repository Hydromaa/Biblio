package com.mycompany.biblio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOLivres_genres extends DAO<Livres_Genres, Object> {

    @Override
    public boolean create(Livres_Genres obj) {
        String req = "INSERT INTO livres_genres (id_livre, id_genre) "
                + "VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setInt(1, obj.getIdLivre());
            ps.setInt(2, obj.getIdGenre());

            int rowsAffected = ps.executeUpdate();
            //RowsAffected => Retour de la DB après update. 0 = 0 lignes changées.
            if (rowsAffected > 0) {
                return true;
            } else {
                Logger.getLogger(DAOLivres.class.getName()).log(Level.WARNING, "Aucune ligne insérée.");
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(DAOLivres.class.getName()).log(Level.SEVERE, "Erreur lors de l'exécution de la requête : " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(Livres_Genres obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Livres_Genres findById(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Livres_Genres> findAll(Object String) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Livres_Genres rsToObj(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
