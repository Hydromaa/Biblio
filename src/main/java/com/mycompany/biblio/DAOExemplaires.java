package com.mycompany.biblio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOExemplaires extends DAO<Exemplaire, String> {

    @Override
    public boolean create(Exemplaire obj) {
        String req = "INSERT INTO exemplaires (code_barre, id_livre, etat) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setString(1, obj.getCodeBarre());
            ps.setInt(2, obj.getIdLivre());
            ps.setString(3, obj.getEtat());

            int rowsAffected = ps.executeUpdate();
            //RowsAffected => Retour de la DB après create. 0 = 0 lignes changées.
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

    public void updateEtat(String codeBarre, String nouvelEtat) {

        String req = "UPDATE exemplaires SET etat = ? WHERE code_barre = ?";

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, nouvelEtat);
            ps.setString(2, codeBarre);
            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DAOExemplaires.class
                    .getName()).log(Level.SEVERE, "Erreur lors de la mise à jour de l'état de l'exemplaire.", e);
        }
    }

    @Override
    public boolean update(Exemplaire obj) throws SQLException {

        String req = "UPDATE exemplaires SET etat = ? WHERE code_barre = ?";

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, obj.getEtat());
            ps.setString(2, obj.getCodeBarre());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return true;

            } else {
                Logger.getLogger(DAOExemplaires.class
                        .getName()).log(Level.WARNING, "Aucune ligne mise à jour.");
                return false;

            }

        } catch (SQLException e) {
            Logger.getLogger(DAOExemplaires.class
                    .getName()).log(Level.SEVERE, "Erreur lors de l'exécution de la requête : " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Exemplaire findById(String id) throws SQLException {

        String req = "SELECT * FROM exemplaires WHERE code_barre = ?";

        Exemplaire exemplaire = null;

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exemplaire = rsToObj(rs);

                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOExemplaires.class
                    .getName()).log(Level.SEVERE, "Erreur lors de la récupération de l'exemplaire.", e);
        }

        return exemplaire;
    }

    @Override
    public List<Exemplaire> findAll(String String) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Exemplaire rsToObj(ResultSet rs) throws SQLException {

        Exemplaire exemplaire = new Exemplaire();

        exemplaire.setCodeBarre(rs.getString(1));
        exemplaire.setIdLivre(rs.getInt(2));
        exemplaire.setEtat(rs.getString(3));
        exemplaire.setRemarque(rs.getString(4));

        return exemplaire;

    }

}
