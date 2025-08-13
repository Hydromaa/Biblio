package View;

import com.mycompany.biblio.Clients;
import com.mycompany.biblio.DAOClients;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

public class ClientsPan extends JPanel implements ActionListener {

    private JTabbedPane tabbedPane = new JTabbedPane();

    // Composants pour l'ajout d'un client
    private JLabel lbl_nom_ajout = new JLabel("Nom : ");
    private JLabel lbl_prenom_ajout = new JLabel("Prénom : ");
    private JLabel lbl_adresse_ajout = new JLabel("Adresse : ");
    private JLabel lbl_mail_ajout = new JLabel("Mail :");
    private JLabel lbl_tel_ajout = new JLabel("Téléphone :");

    private JTextField txt_nom_ajout = new JTextField();
    private JTextField txt_prenom_ajout = new JTextField();
    private JTextField txt_adresse_ajout = new JTextField();
    private JTextField txt_email_ajout = new JTextField();
    private JTextField txt_phone_ajout = new JTextField();

    private JButton btn_ajouter = new JButton("Ajouter");

    // Composants pour la modification d'un client
    private JLabel lbl_nom_modif = new JLabel("Nom : ");
    private JLabel lbl_prenom_modif = new JLabel("Prénom : ");
    private JLabel lbl_adresse_modif = new JLabel("Adresse : ");
    private JLabel lbl_mail_modif = new JLabel("Mail : ");
    private JLabel lbl_tel_modif = new JLabel("Téléphone : ");

    private JLabel lbl_id_modif = new JLabel();

    private JTextField txt_nom_modif = new JTextField();
    private JTextField txt_prenom_modif = new JTextField();
    private JTextField txt_adresse_modif = new JTextField();
    private JTextField txt_mail_modif = new JTextField();
    private JTextField txt_tel_modif = new JTextField();
    private JTextField txt_id_modif = new JTextField();

    private JButton btn_modifier = new JButton("Modifier");

    private JButton btn_annuler_ajout = new JButton("Annuler");
    private JButton btn_annuler = new JButton("Annuler");

    private JLabel lbl_recherche = new JLabel("Rechercher :");
    private JTextField txt_recherche = new JTextField();
    private JButton btn_recherche = new JButton("Rechercher");
    private JComboBox<String> combo_resultats = new JComboBox<>();

    public ClientsPan() {
        this.setLayout(null);

        // Onglet "Nouveau client"
        JPanel panelAjout = new JPanel(null);
        tabbedPane.addTab("Nouveau client", panelAjout);

        // Onglet Ajout
        lbl_nom_ajout.setBounds(200, 50, 100, 30);
        panelAjout.add(lbl_nom_ajout);

        txt_nom_ajout.setBounds(310, 50, 200, 30);
        panelAjout.add(txt_nom_ajout);

        lbl_prenom_ajout.setBounds(200, 100, 100, 30);
        panelAjout.add(lbl_prenom_ajout);

        txt_prenom_ajout.setBounds(310, 100, 200, 30);
        panelAjout.add(txt_prenom_ajout);

        lbl_adresse_ajout.setBounds(200, 150, 100, 30);
        panelAjout.add(lbl_adresse_ajout);

        txt_adresse_ajout.setBounds(310, 150, 200, 30);
        panelAjout.add(txt_adresse_ajout);

        lbl_mail_ajout.setBounds(200, 200, 100, 30);
        panelAjout.add(lbl_mail_ajout);

        txt_email_ajout.setBounds(310, 200, 200, 30);
        panelAjout.add(txt_email_ajout);

        lbl_tel_ajout.setBounds(200, 250, 200, 30);
        panelAjout.add(lbl_tel_ajout);

        txt_phone_ajout.setBounds(310, 250, 200, 30);
        panelAjout.add(txt_phone_ajout);

        btn_ajouter.setBounds(250, 400, 100, 30);
        btn_ajouter.addActionListener(this);
        btn_ajouter.setActionCommand("Ajouter");
        panelAjout.add(btn_ajouter);

        btn_annuler_ajout.setBounds(370, 400, 100, 30);
        btn_annuler_ajout.addActionListener(this);
        btn_annuler_ajout.setActionCommand("Annuler");
        panelAjout.add(btn_annuler_ajout);

        // Onglet Modification
        JPanel panelModification = new JPanel(null); // Layout absolu pour le placement précis
        tabbedPane.addTab("Modification client", panelModification);

        // Placement des labels et champs de texte pour la modification
        lbl_recherche.setBounds(200, 20, 100, 30);
        panelModification.add(lbl_recherche);

        txt_recherche.setBounds(310, 20, 200, 30);
        panelModification.add(txt_recherche);

        btn_recherche.setBounds(550, 20, 120, 30);
        btn_recherche.addActionListener(this);
        btn_recherche.setActionCommand("Rechercher");
        panelModification.add(btn_recherche);

        combo_resultats.setBounds(160, 60, 200, 30);
        combo_resultats.setVisible(false);
        combo_resultats.addActionListener(this);
        panelModification.add(combo_resultats);

        lbl_nom_modif.setBounds(200, 100, 100, 30);
        panelModification.add(lbl_nom_modif);

        txt_nom_modif.setBounds(310, 100, 200, 30);
        panelModification.add(txt_nom_modif);

        lbl_prenom_modif.setBounds(200, 150, 100, 30);
        panelModification.add(lbl_prenom_modif);

        txt_prenom_modif.setBounds(310, 150, 200, 30);
        panelModification.add(txt_prenom_modif);

        lbl_adresse_modif.setBounds(200, 200, 100, 30);
        panelModification.add(lbl_adresse_modif);

        txt_adresse_modif.setBounds(310, 200, 200, 30);
        panelModification.add(txt_adresse_modif);

        lbl_id_modif.setBounds(200, 250, 100, 30);
        panelModification.add(lbl_id_modif);
        lbl_id_modif.setVisible(false);

        txt_id_modif.setBounds(310, 250, 200, 30);
        panelModification.add(txt_id_modif);
        txt_id_modif.setVisible(false);

        lbl_mail_modif.setBounds(200, 250, 100, 30);
        panelModification.add(lbl_mail_modif);

        txt_mail_modif.setBounds(310, 250, 200, 30);
        panelModification.add(txt_mail_modif);

        lbl_tel_modif.setBounds(200, 300, 200, 30);
        panelModification.add(lbl_tel_modif);

        txt_tel_modif.setBounds(310, 300, 200, 30);
        panelModification.add(txt_tel_modif);

        btn_modifier.setBounds(250, 400, 100, 30);
        btn_modifier.addActionListener(this);
        btn_modifier.setActionCommand("Modifier");
        panelModification.add(btn_modifier);

        btn_annuler.setBounds(370, 400, 100, 30);
        btn_annuler.addActionListener(this);
        btn_annuler.setActionCommand("Annuler");
        panelModification.add(btn_annuler);

        // Ajout du JTabbedPane
        tabbedPane.setBounds(0, 0, 800, 600);
        this.add(tabbedPane);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ajouter":
                ajouterClient();
                break;
            case "Modifier":
                modifierClient();
                break;
            case "Annuler":
                SwingUtilities.getWindowAncestor(this).dispose();
                break;
            case "Rechercher":
                if (!this.txt_recherche.getText().isBlank()) {
                    resultatRechercheClient(this);
                    this.txt_recherche.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun résultat trouvé.", "Recherche", JOptionPane.INFORMATION_MESSAGE);

                }
                break;
        }
    }

    private void ajouterClient() {

        Clients client = new Clients();
        DAOClients daoClient = new DAOClients();

        client.setNom(this.txt_nom_ajout.getText());
        client.setPrenom(this.txt_prenom_ajout.getText());
        client.setPhone(this.txt_phone_ajout.getText());
        client.setEmail(this.txt_email_ajout.getText());
        client.setAdresse(this.txt_adresse_ajout.getText());

        if (validerClient(client)) {
            daoClient.create(client);
            JOptionPane.showMessageDialog(null, "Client ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du client.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void modifierClient() {

        Clients client = new Clients();
        DAOClients daoClient = new DAOClients();

        client.setId_client(Integer.parseInt(this.txt_id_modif.getText()));
        client.setNom(this.txt_prenom_modif.getText());
        client.setPrenom(this.txt_nom_modif.getText());
        client.setEmail(this.txt_mail_modif.getText());
        client.setPhone(this.txt_tel_modif.getText());
        client.setAdresse(this.txt_adresse_modif.getText());

        if (!validerClient(client)) { // Vérif des champs
            return;
        }

        if (daoClient.update(client)) {
            JOptionPane.showMessageDialog(null, "Client modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            viderChamps();
        } else {
            JOptionPane.showMessageDialog(null, "La fiche client n'a pas été modifié", "Erreur", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private void resultatRechercheClient(ClientsPan clientsPan) {

        String[] rechClient = {"ID", "Nom", "Prénom", "Mail", "Adresse", "Téléphone"};
        DefaultTableModel model_rech = new DefaultTableModel(rechClient, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DAOClients daoClients = new DAOClients();
        ArrayList<Clients> resultRech = new ArrayList();
        resultRech = daoClients.findAllByString(this.txt_recherche.getText());

        model_rech.setRowCount(0);

        for (Clients client : resultRech) {
            Object[] rowData = {
                client.getId_client(),
                client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getAdresse(),
                client.getPhone()};
            model_rech.addRow(rowData);
        }

        JDialog dialog_rech = new JDialog();
        dialog_rech.setTitle("Recherche client");
        dialog_rech.setModal(true);

        JTable table = new JTable(model_rech);
        dialog_rech.add(new JScrollPane(table));
        dialog_rech.setSize(800, 300);
        dialog_rech.setLocationRelativeTo(null);

        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);

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
                        clientsPan.txt_id_modif.setText(String.valueOf(model_rech.getValueAt(row, 0)));
                        clientsPan.txt_nom_modif.setText(String.valueOf(model_rech.getValueAt(row, 1)));
                        clientsPan.txt_prenom_modif.setText(String.valueOf(model_rech.getValueAt(row, 2)));
                        clientsPan.txt_mail_modif.setText(String.valueOf(model_rech.getValueAt(row, 3)));
                        clientsPan.txt_adresse_modif.setText(String.valueOf(model_rech.getValueAt(row, 4)));
                        clientsPan.txt_tel_modif.setText(String.valueOf(model_rech.getValueAt(row, 5)));
                        dialog_rech.dispose();
                    }
                }
            }
        });
        dialog_rech.setVisible(true);
    }

    private boolean validerClient(Clients client) {

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Clients>> violations = validator.validate(client);

            if (!violations.isEmpty()) {
                StringBuilder message = new StringBuilder("Erreurs de validation :\n");
                for (ConstraintViolation<Clients> violation : violations) {
                    message.append("- ").append(violation.getMessage()).append("\n");
                }
                JOptionPane.showMessageDialog(null, message.toString(), "Erreur de validation", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void viderChamps() {

        this.txt_adresse_modif.setText("");
        this.txt_id_modif.setText("");
        this.txt_mail_modif.setText("");
        this.txt_nom_modif.setText("");
        this.txt_prenom_modif.setText("");
        this.txt_tel_modif.setText("");

    }
}
