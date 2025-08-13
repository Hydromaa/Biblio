package View;

import com.mycompany.biblio.Clients;
import com.mycompany.biblio.DAOClients;
import com.mycompany.biblio.DAOEmprunt;
import com.mycompany.biblio.DAOExemplaires;
import com.mycompany.biblio.DAOLivres;
import com.mycompany.biblio.Emprunt;
import com.mycompany.biblio.Exemplaire;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class EmpruntPan extends JPanel implements ActionListener {

    private JTextField txt_client = new JTextField();

    private JLabel lbl_client = new JLabel("Client : ");
    private JLabel lbl_client_value = new JLabel("");

    private JLabel lbl_client_id = new JLabel();

    private JLabel lbl_scan = new JLabel("Code-barres : ");
    private JTextField txt_scan = new JTextField();

    private JButton btn_confirm = new JButton("Confirmer l'emprunt");
    private JButton btn_cancel = new JButton("Annuler");
    private JButton btn_search_client = new JButton("Rechercher client");
    private JButton btn_supp_livre = new JButton("Supprimer de la liste");

    private JTable tbl_livres_emprunt = new JTable();
    private DefaultTableModel model_livres;

    public EmpruntPan() {

        this.setLayout(null);

        // Label client (vide au début)
        this.lbl_client.setBounds(580, 50, 100, 30);
        this.add(lbl_client);

        this.lbl_client_value.setBounds(640, 50, 200, 30);
        this.add(lbl_client_value);

        this.lbl_client_id.setBounds(570, 20, 200, 30);
        this.add(lbl_client_id);
        this.lbl_client_id.setVisible(false);

        // Champ scan
        this.lbl_scan.setBounds(50, 20, 100, 30);
        this.add(lbl_scan);

        this.txt_scan.setBounds(130, 20, 250, 30);
        this.txt_scan.addActionListener(this);
        this.txt_scan.setActionCommand("Ajout emprunt");
        this.add(txt_scan);

        this.txt_client.setBounds(580, 20, 200, 30);
        this.txt_client.addActionListener(this);
        this.txt_client.setActionCommand("RechercherClient");
        this.add(txt_client);

        // Tableau des livres à emprunter + Modif impossible des lignes
        model_livres = new DefaultTableModel(new Object[]{"Titre", "Auteur", "Code barre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl_livres_emprunt.setModel(model_livres);

        JScrollPane jsp = new JScrollPane(tbl_livres_emprunt);
        jsp.setBounds(50, 90, 900, 370);
        this.add(jsp);

        // Boutons
        this.btn_confirm.setBounds(800, 480, 150, 50);
        this.btn_confirm.addActionListener(this);
        this.btn_confirm.setActionCommand("Confirmer");
        this.add(btn_confirm);

        this.btn_cancel.setBounds(50, 480, 150, 50);
        this.btn_cancel.addActionListener(this);
        this.btn_cancel.setActionCommand("Annuler");
        this.add(btn_cancel);

        this.btn_search_client.setBounds(800, 20, 150, 30);
        this.btn_search_client.addActionListener(this);
        this.btn_search_client.setActionCommand("RechercherClient");
        this.add(btn_search_client);

        this.btn_supp_livre.setBounds(375, 480, 150, 50);
        this.btn_supp_livre.addActionListener(this);
        this.btn_supp_livre.setActionCommand("RetireListe");
        this.add(btn_supp_livre);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Confirmer":
                confirmationEmprunt();
                model_livres.setRowCount(0);
                break;

            case "Annuler":
                SwingUtilities.getWindowAncestor(this).dispose();
                break;
            case "RetireListe":

                SuppExemplaireSelection();
                break;
            case "RechercherClient":

                if (!this.txt_client.getText().isBlank()) {
                    resultatRechercheClient(this);
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun résultat trouvé.", "Recherche", JOptionPane.INFORMATION_MESSAGE);

                }
                break;

            case "Ajout emprunt":

                if (!this.txt_client.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Aucun client renseigné.", "Client", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    String codeBarre = this.txt_scan.getText();
                    this.txt_scan.setText("");

                    // Vérifie si le livre a déjà été scanné
                    if (verifCodeBarreDejaPresent(tbl_livres_emprunt, codeBarre)) {
                        JOptionPane.showMessageDialog(null, "Ce livre est déjà dans la liste des emprunts.", "Duplication", JOptionPane.WARNING_MESSAGE);
                    } else if (verifLivreEmprunte(codeBarre)) {
                        // Si le livre est déjà emprunté, proposer de forcer l'emprunt
                        int choixUser = JOptionPane.showConfirmDialog(null, "Le livre est déjà emprunté. Voulez-vous forcer l'emprunt ?", "Emprunt déjà effectué", JOptionPane.YES_NO_OPTION);
                        if (choixUser == JOptionPane.YES_OPTION) {
                            modifEtatExemplaire(codeBarre, "Disponible");
                            suppEmpruntEnCours(codeBarre);
                            scanCodeBarreExemplaires(tbl_livres_emprunt, codeBarre);
                        }
                    } else {
                        // Sinon, scanner le livre normalement
                        scanCodeBarreExemplaires(tbl_livres_emprunt, codeBarre);
                    }
                    break;

                }
        }
    }

    private void resultatRechercheClient(EmpruntPan empruntPan) {

        String[] rechClient = {"ID", "Nom", "Prénom", "Adresse"};
        DefaultTableModel model_rech = new DefaultTableModel(rechClient, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DAOClients daoClients = new DAOClients();
        ArrayList<Clients> resultRech = new ArrayList();
        resultRech = daoClients.findAllByString(this.txt_client.getText());

        model_rech.setRowCount(0);

        for (Clients client : resultRech) {
            Object[] rowData = {
                client.getId_client(),
                client.getNom(),
                client.getPrenom(),
                client.getAdresse(),};
            model_rech.addRow(rowData);
        }

        JDialog dialog_rech = new JDialog();
        dialog_rech.setTitle("Recherche client");
        dialog_rech.setModal(true);

        JTable table = new JTable(model_rech);
        dialog_rech.add(new JScrollPane(table));
        dialog_rech.setSize(600, 300);
        dialog_rech.setLocationRelativeTo(null);

        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(440);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                int row = table.rowAtPoint(e.getPoint()); // Obtenir l'index de la ligne cliquée
                int col = table.columnAtPoint(e.getPoint()); // Obtenir l'index de la colonne cliquée
                System.out.println(row);
                if (row >= 0) {
                    if (e.getClickCount() == 2) { // Détecte un double-clic
                        int id = (int) model_rech.getValueAt(row, 0);
                        empruntPan.txt_client.setText("");
                        empruntPan.lbl_client_id.setText(String.valueOf(model_rech.getValueAt(row, 0)));
                        empruntPan.lbl_client_value.setText(model_rech.getValueAt(row, 1) + " " + model_rech.getValueAt(row, 2));
                        dialog_rech.dispose();
                    }
                }
            }
        });
        dialog_rech.setVisible(true);
    }

    private boolean verifCodeBarreDejaPresent(JTable tbl_livres_emprunt, String codeBarre) {

        DefaultTableModel model = (DefaultTableModel) tbl_livres_emprunt.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String codeBarreExistant = model.getValueAt(i, 2).toString(); // Colonne 2 = Code Barre
            if (codeBarre.equalsIgnoreCase(codeBarreExistant)) {
                return true;
            }
        }
        return false;
    }

    private boolean verifLivreEmprunte(String codeBarre) {

        DAOExemplaires daoexemplaires = new DAOExemplaires();
        Exemplaire exemplaire = new Exemplaire();

        try {
            exemplaire = daoexemplaires.findById(codeBarre);
        } catch (SQLException ex) {
            Logger.getLogger(EmpruntPan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exemplaire.getEtat().equals("Emprunté");

    }

    private void modifEtatExemplaire(String codeBarre, String disponible) {

        DAOExemplaires daoExemplaire = new DAOExemplaires();

        daoExemplaire.updateEtat(codeBarre, disponible);

    }

    private void suppEmpruntEnCours(String codeBarre) {

        DAOEmprunt daoEmprunt = new DAOEmprunt();
        daoEmprunt.delete(codeBarre);

    }

    private void scanCodeBarreExemplaires(JTable tbl_livres_emprunt1, String codeBarre) {

        DAOLivres daoLivres = new DAOLivres();
        String[] livre = daoLivres.findByCodeBarre(codeBarre);

        if (livre != null) {
            DefaultTableModel model = (DefaultTableModel) tbl_livres_emprunt1.getModel();
            model.addRow(livre);
        } else {
            JOptionPane.showMessageDialog(null, "Livre introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void confirmationEmprunt() {

        DAOEmprunt daoEmprunt = new DAOEmprunt();
        int rowCount = model_livres.getRowCount();

        for (int i = 0; i < rowCount; i++) {

            String codeBarres = (String) model_livres.getValueAt(i, 2);
            Emprunt emprunt = new Emprunt();

            DAOExemplaires daoExemplaires = new DAOExemplaires();
            Exemplaire exemplaire = new Exemplaire();

            exemplaire.setCodeBarre(codeBarres);
            exemplaire.setEtat("Emprunté");

            emprunt.setIdClient(Integer.parseInt(lbl_client_id.getText()));
            emprunt.setCodeBarre(codeBarres);
            emprunt.setDateEmprunt(LocalDate.now());
            emprunt.setDateRetourPrevu(LocalDate.now().plusDays(14));

            try {
                daoEmprunt.create(emprunt);
                daoExemplaires.update(exemplaire);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement de l'emprunt du livre : " + codeBarres, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(this, "Emprunt(s) enregistré(s) avec succès !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

    }

    private void SuppExemplaireSelection() {

        int selectedRow = tbl_livres_emprunt.getSelectedRow();

        if (selectedRow != -1) { // Vérifie qu'une ligne est bien sélectionnée
            DefaultTableModel model = (DefaultTableModel) tbl_livres_emprunt.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un exemplaire à supprimer.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
        }
    }
}
