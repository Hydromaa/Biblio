package View;

import com.mycompany.biblio.DAOLivres;
import com.mycompany.biblio.Livres;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class MainPanneau extends JPanel implements ActionListener {

    private JButton btn_retour_livre = new JButton("Retour livre(s)");
    private JButton btn_emprunt = new JButton("Emprunt livre(s)");
    private JButton btn_gestion_client = new JButton("Gestion Clients");
    private JButton btn_retard_livre = new JButton("Lise des retards");
    private JButton btn_rech = new JButton();
    private JButton btn_gestion_livre = new JButton("Gestion des livres");

    private JTextField text_rech = new JTextField();
    private JLabel lab_rech = new JLabel("Recherche :");

    private JTable tab_livres = new JTable();
    private JScrollPane scroll_tab = new JScrollPane();

    private JSeparator sep_menuV = new JSeparator(SwingConstants.VERTICAL);
    private JSeparator sep_menuH = new JSeparator(SwingConstants.HORIZONTAL);

    public MainPanneau() {

        this.setLayout(null);

        this.btn_emprunt.setBounds(65, 100, 180, 65);
        this.btn_emprunt.setBackground(new Color(58, 114, 175));
        this.btn_emprunt.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        this.btn_emprunt.addActionListener(this);
        this.add(btn_emprunt);

        this.btn_retour_livre.setBounds(65, 250, 180, 65);
        this.btn_retour_livre.setBackground(new Color(58, 114, 175));
        this.btn_retour_livre.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        this.add(btn_retour_livre);
        this.btn_retour_livre.addActionListener(this);


        this.btn_gestion_client.setBounds(65, 400, 180, 65);
        this.btn_gestion_client.setBackground(new Color(58, 114, 175));
        this.btn_gestion_client.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        this.add(btn_gestion_client);
        this.btn_gestion_client.addActionListener(this);

        this.btn_retard_livre.setBounds(65, 500, 180, 65);
        this.btn_retard_livre.setBackground(new Color(58, 114, 175));
        this.btn_retard_livre.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        this.add(btn_retard_livre);
        this.btn_retard_livre.addActionListener(this);

        this.btn_gestion_livre.setBounds(65, 600, 180, 65);
        this.btn_gestion_livre.setBackground(new Color(58, 114, 175));
        this.btn_gestion_livre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        this.add(btn_gestion_livre);
        this.btn_gestion_livre.setActionCommand("Gestion_Livres");
        this.btn_gestion_livre.addActionListener(this);

        this.btn_rech.setBounds(650, 100, 30, 30);
        this.btn_rech.addActionListener(this);
        this.btn_rech.setActionCommand("Recherche");
        this.add(btn_rech);

        this.sep_menuV.setBounds(300, 75, 5, 650);
        this.add(sep_menuV);

        this.sep_menuH.setBounds(65, 375, 200, 5);
        this.add(sep_menuH);

        this.lab_rech.setBounds(350, 65, 100, 100);
        this.add(lab_rech);

        this.text_rech.setBounds(450, 100, 200, 30);
        this.text_rech.addActionListener(this);
        //Jtextfiled + ActionListener -> Déclenche un event par défaut avec "Enter"
        this.text_rech.setActionCommand("Recherche");
        this.add(text_rech);

        String[] nom_col = {"ID", "Titre", "ISBN", "Publication"};
        DefaultTableModel model = new DefaultTableModel(nom_col, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.tab_livres.setModel(model);

        //Largeur colonne
        this.tab_livres.getColumnModel().getColumn(0).setPreferredWidth(0);
        this.tab_livres.getColumnModel().getColumn(1).setPreferredWidth(300);
        this.tab_livres.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.tab_livres.getColumnModel().getColumn(3).setPreferredWidth(80);

        tab_livres.getColumnModel().getColumn(0).setMinWidth(0);
        tab_livres.getColumnModel().getColumn(0).setMaxWidth(0);

        this.scroll_tab = new JScrollPane(tab_livres);
        this.scroll_tab.setBounds(350, 150, 800, 600);
        this.add(scroll_tab);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Retour livre(s)":
                RetourFen retourfen = new RetourFen((JFrame) SwingUtilities.getWindowAncestor(this));
                break;

            case "Emprunt livre(s)":
                EmpruntFen empruntfen = new EmpruntFen((JFrame) SwingUtilities.getWindowAncestor(this));
                break;
            case "Gestion_Livres":
                GestionLivreFen gestionLivreFen = new GestionLivreFen((JFrame) SwingUtilities.getWindowAncestor(this));
            case "Recherche":
                rechercheLivres();
                break;

            case "Gestion Clients":
                ClientsFen clientsFen = new ClientsFen((JFrame) SwingUtilities.getWindowAncestor(this));
        }
    }

    private void affichageRecherche(ArrayList<Livres> rechLivres) {

        DefaultTableModel model = (DefaultTableModel) this.tab_livres.getModel();
        model.setRowCount(0);

        for (Livres livre : rechLivres) {
            Object[] rowData = {
                livre.getId_livre(),
                livre.getTitre(),
                livre.getIsbn(),
                livre.getAnneePublication()
            };
            model.addRow(rowData);

        }

    }

    private void rechercheLivres() {

        if (!text_rech.getText().isBlank()) {

            DAOLivres daoLiv = new DAOLivres();
            String rech = text_rech.getText();
            ArrayList<Livres> rechLivres = daoLiv.findAllByString(rech);

            if (rechLivres.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun résultat trouvé.", "Recherche", JOptionPane.INFORMATION_MESSAGE);
            } else {
                affichageRecherche(rechLivres);
            }

        }
    }
}
