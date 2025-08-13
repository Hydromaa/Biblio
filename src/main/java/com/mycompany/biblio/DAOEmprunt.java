package com.mycompany.biblio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmprunt extends DAO<Emprunt, String> {

    @Override
    public boolean create(Emprunt emprunt) throws SQLException {

        String req = "INSERT INTO emprunts (id_client, code_barre, date_emprunt, date_retour_prevu) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(req)) {

            ps.setInt(1, emprunt.getIdClient());
            ps.setString(2, emprunt.getCodeBarre());
            ps.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            ps.setDate(4, Date.valueOf(emprunt.getDateRetourPrevu()));

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
    public boolean update(Emprunt emprunt) {

        String req = "UPDATE emprunts "
                + "SET date_retour_reel = ? "
                + "WHERE id_client = ? "
                + "AND code_barre = ? ";

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setDate(1, Date.valueOf(emprunt.getDate_retour_reel()));
            ps.setInt(2, emprunt.getIdClient());
            ps.setString(3, emprunt.getCodeBarre());
            ps.executeUpdate();

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                Logger.getLogger(DAOExemplaires.class.getName()).log(Level.WARNING, "Aucune ligne mise à jour pour id_client: {0}, code_barre: {1}", new Object[]{emprunt.getIdClient(), emprunt.getCodeBarre()});
                return false;
            }
            return true;

        } catch (SQLException e) {
            Logger.getLogger(DAOExemplaires.class.getName()).log(Level.SEVERE, "Erreur lors du retour.", e);
            return false;
        }
    }

    @Override
    public boolean delete(String codeBarre) {

        String req = "DELETE FROM Emprunts WHERE code_barre = ? AND date_retour_reel IS NULL";

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, codeBarre);

            int rowsAffected = ps.executeUpdate();
            //RowsAffected => Retour de la DB après update. 0 = 0 lignes changées.
            if (rowsAffected > 0) {
                return true;
            } else {
                Logger.getLogger(DAOLivres.class.getName()).log(Level.WARNING, "Aucune ligne supprimée.");
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(DAOLivres.class.getName()).log(Level.SEVERE, "Erreur lors de l'exécution de la requête : " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Emprunt findById(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Emprunt> findAll(String String) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<RapportRetard> findEmpruntRetard() {

        List<RapportRetard> retards = new ArrayList<>();

        String req = "SELECT livres.titre, clients.nom, clients.prenom,"
                + "clients.email, clients.telephone, emprunts.date_retour_prevu "
                + "FROM emprunts "
                + "JOIN exemplaires ON emprunts.code_barre = exemplaires.code_barre "
                + "JOIN livres ON exemplaires.id_livre = livres.id_livre "
                + "JOIN clients ON emprunts.id_client = clients.id_client "
                + "WHERE emprunts.date_retour_prevu < CURDATE() "
                + "AND emprunts.date_retour_reel IS NULL;";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RapportRetard rapport = new RapportRetard();

                rapport.setTitre_Livre(rs.getString(1));
                rapport.setNom_Client(rs.getString(2));
                rapport.setPrenom_Client(rs.getString(3));
                rapport.setEmail_Client(rs.getString(4));
                rapport.setTel_Client(rs.getString(5));
                rapport.setDateRetourPrevue(rs.getDate(6));
                retards.add(rapport);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retards;
    }

    @Override
    public Emprunt rsToObj(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
