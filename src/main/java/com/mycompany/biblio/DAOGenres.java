package com.mycompany.biblio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOGenres extends DAO<Genres, String> {

    public List<Genres> getAllGenres() {

        List<Genres> genres = new ArrayList<>();
        String req = "SELECT id_genre, nom FROM genres";

        try (PreparedStatement ps = conn.prepareStatement(req)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Genres genretype = new Genres();
                    genretype.setIdGenre(rs.getInt(1));
                    genretype.setNom(rs.getString(2));
                    genres.add(genretype);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOLivres.class.getName()).log(Level.SEVERE, "Erreur lors de la récupération de liste des genres", e);
        }
        return genres;
    }

    @Override
    public boolean create(Genres obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Genres obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Genres findById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Genres> findAll(String String) throws SQLException {
        List<Genres> genres = new ArrayList<>();
        String req = "SELECT id_genre, nom FROM genres";

        try (PreparedStatement ps = conn.prepareStatement(req)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Genres genretype = new Genres();
                    genretype.setIdGenre(rs.getInt(1));
                    genretype.setNom(rs.getString(2));
                    genres.add(genretype);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOLivres.class.getName()).log(Level.SEVERE, "Erreur lors de la récupération de liste des genres", e);
        }
        return genres;
    }

    @Override
    public Genres rsToObj(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
