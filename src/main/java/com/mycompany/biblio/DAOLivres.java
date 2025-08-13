package com.mycompany.biblio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOLivres extends DAO<Livres, String> {

    public String[] findByCodeBarre(String codeBarre) {

        String req = "SELECT livres.titre, GROUP_CONCAT(auteurs.prenom, ' ', "
                + "auteurs.nom SEPARATOR ', ') AS auteurs, exemplaires.code_barre "
                + "FROM livres "
                + "INNER JOIN livres_auteurs ON livres.id_livre = livres_auteurs.id_livre "
                + "INNER JOIN auteurs ON livres_auteurs.id_auteur = auteurs.id_auteur "
                + "INNER JOIN exemplaires ON livres.id_livre = exemplaires.id_livre "
                + "WHERE exemplaires.code_barre = ? "
                + "GROUP BY livres.id_livre, exemplaires.code_barre";

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, codeBarre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Création du tableau avec les trois informations demandées
                String infoLivre[] = {rs.getString(1), rs.getString(2), rs.getString(3)};
                return infoLivre;
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOLivres.class.getName()).log(Level.SEVERE, "Erreur lors de l'exécution de la requête : " + e.getMessage(), e);
        }

        return null; // Retourne null si aucun résultat trouvé
    }

    public ArrayList<Livres> findAllByString(String string) {

        ArrayList<Livres> livresList = new ArrayList<>();

        String req = "SELECT livres.id_livre, livres.titre, livres.isbn, livres.annee_publication, "
                + "GROUP_CONCAT(DISTINCT CONCAT(auteurs.prenom, ' ', auteurs.nom) SEPARATOR ', ') AS auteurs, "
                + "GROUP_CONCAT(DISTINCT genres.nom SEPARATOR ', ') AS genres, "
                + "exemplaires.code_barre "
                + "FROM livres "
                + "LEFT JOIN livres_auteurs ON livres.id_livre = livres_auteurs.id_livre "
                + "LEFT JOIN auteurs ON livres_auteurs.id_auteur = auteurs.id_auteur "
                + "LEFT JOIN livres_genres ON livres.id_livre = livres_genres.id_livre "
                + "LEFT JOIN genres ON livres_genres.id_genre = genres.id_genre "
                + "LEFT JOIN exemplaires ON livres.id_livre = exemplaires.id_livre "
                + "WHERE livres.titre LIKE ? "
                + "OR livres.isbn LIKE ? "
                + "OR livres.annee_publication LIKE ? "
                + "OR auteurs.nom LIKE ? "
                + "OR auteurs.prenom LIKE ? "
                + "OR genres.nom LIKE ? "
                + "GROUP BY livres.id_livre";

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, "%" + string + "%");
            ps.setString(2, "%" + string + "%");
            ps.setString(3, "%" + string + "%");
            ps.setString(4, "%" + string + "%");
            ps.setString(5, "%" + string + "%");
            ps.setString(6, "%" + string + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Livres livre = new Livres();

                    livre.setTitre(rs.getString("titre"));
                    livre.setIsbn(rs.getString("isbn"));
                    livre.setAnneePublication(rs.getInt("annee_publication"));
                    livresList.add(livre);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOLivres.class.getName()).log(Level.SEVERE, "Erreur lors de l'exécution de la requête ou de la préparation", e);
        }

        return livresList;
    }

    @Override
    public boolean create(Livres livre) {
        String req = "INSERT INTO livres (titre, isbn, annee_publication ) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getIsbn());
            ps.setInt(3, livre.getAnneePublication());

            //Retour de la DB. 0 = Pas de create. 1 = create OK.
            int rowsAffected = ps.executeUpdate();

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

    @Override
    public boolean update(Livres obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Livres findById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public List<Livres> findAll(String String) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Livres rsToObj(ResultSet rs) throws SQLException {

        Livres livre = new Livres();

        livre.setId_livre(rs.getInt(1));
        livre.setTitre(rs.getString(2));
        livre.setIsbn(rs.getString(3));
        livre.setAnneePublication(rs.getInt(4));

        return livre;
    }

}
