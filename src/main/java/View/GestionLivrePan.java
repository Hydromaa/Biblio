package View;

import com.mycompany.biblio.Auteur;
import com.mycompany.biblio.DAOAuteurs;
import com.mycompany.biblio.DAOExemplaires;
import com.mycompany.biblio.DAOGenres;
import com.mycompany.biblio.DAOLivres;
import com.mycompany.biblio.DAOLivres_auteurs;
import com.mycompany.biblio.DAOLivres_genres;
import com.mycompany.biblio.Exemplaire;
import com.mycompany.biblio.Genres;
import com.mycompany.biblio.Livres;
import com.mycompany.biblio.Livres_Auteurs;
import com.mycompany.biblio.Livres_Genres;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;

public class GestionLivrePan extends JPanel implements ActionListener {

    private JTabbedPane tabbedPane = new JTabbedPane();

    private JLabel lbl_titre_ajout = new JLabel("Titre : ");
    private JLabel lbl_isbn_ajout = new JLabel("ISBN : ");
    private JLabel lbl_annee_publication_ajout = new JLabel("Année de publication : ");
    private JLabel lbl_auteur_ajout = new JLabel("Auteur(s) (Nouveau): ");
    private JLabel lbl_auteur_ajout_existant = new JLabel("Déjà existant : ");
    private JLabel lbl_genre_ajout = new JLabel("Genre(s) : ");
    private JLabel lbl_nbre_exemplaire = new JLabel("Nombre d'exemplaires : ");
    private JLabel lbl_nom_auteur = new JLabel("Nom :");
    private JLabel lbl_prenom_auteur = new JLabel("Prénom :");
    private JSpinner spin_nbre_exemplaire = new JSpinner();

    private JTextField txt_titre_ajout = new JTextField();
    private JTextField txt_isbn_ajout = new JTextField();
    private JTextField txt_annee_publication_ajout = new JTextField();
    private JTextField txt_auteur_nom_ajout = new JTextField();
    private JTextField txt_auteur_prenom_ajout = new JTextField();

    private JTextField txt_genre_ajout = new JTextField();

    JPanel panelGenres = new JPanel();
    List<JCheckBox> checkboxesGenres = new ArrayList<>();

    private JButton btn_ajouter = new JButton("Ajouter");

    private JLabel lbl_titre_modif = new JLabel("Titre : ");
    private JLabel lbl_isbn_modif = new JLabel("ISBN : ");
    private JLabel lbl_annee_publication_modif = new JLabel("Année de publication : ");
    private JLabel lbl_auteur_modif = new JLabel("Auteur(s) : ");
    private JLabel lbl_genre_modif = new JLabel("Genre(s) : ");

    private JLabel lbl_id_modif = new JLabel();

    private JTextField txt_titre_modif = new JTextField();
    private JTextField txt_isbn_modif = new JTextField();
    private JTextField txt_annee_publication_modif = new JTextField();
    private JTextField txt_auteur_modif = new JTextField();
    private JTextField txt_genre_modif = new JTextField();

    private JButton btn_modifier = new JButton("Modifier");

    private JButton btn_annuler_ajout = new JButton("Annuler");
    private JButton btn_annuler = new JButton("Annuler");

    private JLabel lbl_recherche = new JLabel("Rechercher :");
    private JTextField txt_recherche = new JTextField();
    private JButton btn_recherche = new JButton("Rechercher");
    private JComboBox<String> combo_resultats = new JComboBox<>();

    private JComboBox<Auteur> combo_auteurs = new JComboBox<>();

    public GestionLivrePan() {

        this.setLayout(null);

        JPanel panelAjout = new JPanel(null);
        tabbedPane.addTab("Nouveau livre", panelAjout);

        lbl_titre_ajout.setBounds(70, 50, 100, 30);
        panelAjout.add(lbl_titre_ajout);

        txt_titre_ajout.setBounds(220, 50, 250, 30);
        panelAjout.add(txt_titre_ajout);

        lbl_isbn_ajout.setBounds(70, 100, 100, 30);
        panelAjout.add(lbl_isbn_ajout);

        txt_isbn_ajout.setBounds(220, 100, 250, 30);
        panelAjout.add(txt_isbn_ajout);

        lbl_annee_publication_ajout.setBounds(70, 150, 150, 30);
        panelAjout.add(lbl_annee_publication_ajout);

        txt_annee_publication_ajout.setBounds(220, 150, 250, 30);
        panelAjout.add(txt_annee_publication_ajout);

        lbl_auteur_ajout.setBounds(70, 200, 130, 30);
        panelAjout.add(lbl_auteur_ajout);

        lbl_nom_auteur.setBounds(220, 200, 100, 30);
        panelAjout.add(lbl_nom_auteur);

        lbl_prenom_auteur.setBounds(380, 200, 150, 30);
        panelAjout.add(lbl_prenom_auteur);

        lbl_auteur_ajout_existant.setBounds(70, 270, 150, 30);
        panelAjout.add(lbl_auteur_ajout_existant);

        txt_auteur_nom_ajout.setBounds(220, 230, 150, 30);
        txt_auteur_prenom_ajout.setBounds(380, 230, 150, 30);
        panelAjout.add(txt_auteur_nom_ajout);
        panelAjout.add(txt_auteur_prenom_ajout);

        combo_auteurs.setBounds(220, 270, 310, 30);
        remplissageComboAuteurs();
        panelAjout.add(combo_auteurs);

        lbl_genre_ajout.setBounds(480, 50, 150, 30);
        panelAjout.add(lbl_genre_ajout);

        panelGenres.setLayout(new javax.swing.BoxLayout(panelGenres, javax.swing.BoxLayout.Y_AXIS));
        panelGenres.setBounds(620, 50, 150, 200);
        panelAjout.add(panelGenres);
        recupListGenres();

        JScrollPane scrollGenres = new JScrollPane(panelGenres);
        scrollGenres.setBounds(550, 50, 200, 200);
        panelAjout.add(scrollGenres);

        lbl_nbre_exemplaire.setBounds(70, 320, 300, 30);
        panelAjout.add(lbl_nbre_exemplaire);

        spin_nbre_exemplaire.setBounds(220, 320, 50, 35);

        SpinnerNumberModel modelSpin = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        spin_nbre_exemplaire.setModel(modelSpin);
        panelAjout.add(spin_nbre_exemplaire);

        btn_ajouter.setBounds(250, 450, 100, 30);
        btn_ajouter.addActionListener(this);
        btn_ajouter.setActionCommand("Ajouter");
        panelAjout.add(btn_ajouter);

        btn_annuler_ajout.setBounds(570, 450, 100, 30);
        btn_annuler_ajout.addActionListener(this);
        btn_annuler_ajout.setActionCommand("Annuler");
        panelAjout.add(btn_annuler_ajout);

        //------------------------------------------------------------------------------------
        // Onglet Modification
        JPanel panelModification = new JPanel(null);
        tabbedPane.addTab("Modification livre", panelModification);

        // Placement des labels et champs de texte pour la modification
        lbl_recherche.setBounds(180, 20, 100, 30);
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

        lbl_titre_modif.setBounds(180, 100, 100, 30);
        panelModification.add(lbl_titre_modif);

        txt_titre_modif.setBounds(310, 100, 200, 30);
        panelModification.add(txt_titre_modif);

        lbl_isbn_modif.setBounds(180, 150, 100, 30);
        panelModification.add(lbl_isbn_modif);

        txt_isbn_modif.setBounds(310, 150, 200, 30);
        panelModification.add(txt_isbn_modif);

        lbl_annee_publication_modif.setBounds(180, 200, 150, 30);
        panelModification.add(lbl_annee_publication_modif);

        txt_annee_publication_modif.setBounds(310, 200, 200, 30);
        panelModification.add(txt_annee_publication_modif);

        lbl_id_modif.setBounds(200, 250, 100, 30);
        panelModification.add(lbl_id_modif);
        lbl_id_modif.setVisible(false);

        btn_modifier.setBounds(250, 400, 100, 30);
        btn_modifier.addActionListener(this);
        btn_modifier.setActionCommand("Modifier");
        panelModification.add(btn_modifier);

        btn_annuler.setBounds(570, 400, 100, 30);
        btn_annuler.addActionListener(this);
        btn_annuler.setActionCommand("Annuler");
        panelModification.add(btn_annuler);

        tabbedPane.setBounds(0, 0, 800, 600);
        this.add(tabbedPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Ajouter":
                AjouterLivresEtInfos();
                break;

            case "Annuler":
                SwingUtilities.getWindowAncestor(this).dispose();
                break;

        }

    }

    private void AjouterLivresEtInfos() {
        try {

            if (txt_isbn_ajout.getText().isEmpty() || txt_titre_ajout.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer le nom et l'ISBN.");
            } else {
                //Vérif si champ nom ou prenom contient du texte et si l'index selectionné est un autre que celui par défaut qu'on a ajouté
                if ((!txt_auteur_nom_ajout.getText().isEmpty() || !this.txt_auteur_prenom_ajout.getText().isEmpty()) && combo_auteurs.getSelectedIndex() > 0) {
                    this.combo_auteurs.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null, "Veuillez choisir un auteur existant, soit en créer un nouveau mais pas les deux.");
                } else {

                    //gère l'ajout livre/Exemplaire/Genre/Auteur
                    Livres livre = new Livres();
                    DAOLivres daoLivre = new DAOLivres();

                    livre.setTitre(this.txt_titre_ajout.getText());
                    livre.setIsbn(this.txt_isbn_ajout.getText());

                    livre.setAnneePublication(Integer.parseInt(this.txt_annee_publication_ajout.getText()));

                    boolean ajoutOK;

                    ajoutOK = daoLivre.create(livre);

                    if (ajoutOK) {
                        //Récupération de l'ID ajouter en dernier
                        int idLivre = daoLivre.getLastInsertedId();

                        associerGenreLivre(idLivre);
                        associerAuteurLivre(idLivre);
                        creerExemplaires(idLivre, livre.getTitre());

                        this.txt_titre_ajout.setText("");
                        this.txt_isbn_ajout.setText("");
                        this.txt_annee_publication_ajout.setText("");
                        this.combo_auteurs.setSelectedIndex(0);
                        this.txt_auteur_nom_ajout.setText("");
                        this.txt_auteur_prenom_ajout.setText("");
                        cleanGenreSelection();

                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du livre.", "Nouveau livre", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer une année valide (en chiffres).");
            this.txt_annee_publication_ajout.setText("");
        }
    }

    private void associerGenreLivre(int idLivre) {

        DAOLivres daoLivre = new DAOLivres();

        List<Integer> idsGenresSelectionnes = new ArrayList<>();

        //On parcourt les genres selectionné
        for (JCheckBox cb : checkboxesGenres) {
            if (cb.isSelected()) {
                idsGenresSelectionnes.add(Integer.parseInt(cb.getActionCommand()));
            }
        }

        Livres_Genres livreGenre = new Livres_Genres();
        DAOLivres_genres daoLivreGenre = new DAOLivres_genres();

        for (int idGenre : idsGenresSelectionnes) {
            livreGenre.setIdLivre(idLivre);
            livreGenre.setIdGenre(idGenre);
            daoLivreGenre.create(livreGenre);

        }
    }

    private void recupListGenres() {

        DAOGenres daoGenres = new DAOGenres();
        List<Genres> genres = daoGenres.getAllGenres();

        panelGenres.removeAll(); //Clean du panel
        checkboxesGenres.clear(); //Clean des checkbox

        for (Genres genre : genres) {
            //Création checkBox avec le nom du genre
            JCheckBox checkbox = new JCheckBox(genre.getNom());
            //Affectation de l'ID au genre avec setAction (id devient un string)
            checkbox.setActionCommand(String.valueOf(genre.getIdGenre()));
            //Ajout d'une checkbox
            panelGenres.add(checkbox);
            //Ajout d'un genre à une checkbox
            checkboxesGenres.add(checkbox);
        }

        panelGenres.revalidate(); //Recalcul de la disposition suite au clean
        panelGenres.repaint(); //Actualisation du composant suite au clear
    }

    private void associerAuteurLivre(int idLivre) {

        Livres_Auteurs liv_aut = new Livres_Auteurs();
        DAOLivres_auteurs dao_liv_aut = new DAOLivres_auteurs();
        DAOAuteurs daoAuteur = new DAOAuteurs();
        //Si id égal a -1 -> Pas existant dans la DB
        int idAuteur = -1;
        try {
            //Si un auteur est selectionné dans la combobox -Déjà existant- (>0)
            if (combo_auteurs.getSelectedIndex() > 0) {

                Auteur selectionCombo = (Auteur) combo_auteurs.getSelectedItem();
                idAuteur = selectionCombo.getIdAuteur();

            } else {

                Auteur nouvelAuteur = new Auteur();

                nouvelAuteur.setNom(txt_auteur_nom_ajout.getText());
                nouvelAuteur.setPrenom(txt_auteur_prenom_ajout.getText());

                daoAuteur.create(nouvelAuteur);
                idAuteur = daoAuteur.getLastInsertedId();

            }

            if (idAuteur > 0) {
                System.out.println(idAuteur);
                liv_aut.setIdAuteur(idAuteur);
                liv_aut.setIdLivre(idLivre);

                dao_liv_aut.create(liv_aut);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivrePan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void creerExemplaires(int idLivre, String titre) {

        DAOExemplaires daoExemplaires = new DAOExemplaires();
        //List des images de code barre
        List<BufferedImage> etiquettes = new ArrayList<>();
        //list des noms de fichiers
        List<String> nomsFichiers = new ArrayList<>();

        int nombreExemplaires = (int) this.spin_nbre_exemplaire.getValue();

        //Boucle pour gérer la création d'exemplaires
        for (int i = 0; i < nombreExemplaires; i++) {
            Exemplaire ex = new Exemplaire();
            ex.setIdLivre(idLivre);
            ex.setEtat("Disponible");
            //Pour chaque exemplaire, on crée un code barre unique via la méthode
            String codeBarreUnique = genererCodeBarres(idLivre);
            ex.setCodeBarre(codeBarreUnique);
            daoExemplaires.create(ex);

            //Génération de l'image du code barre
            BufferedImage imageCodeBarre = genererImageCodeBarres(codeBarreUnique, titre);
            etiquettes.add(imageCodeBarre);
            nomsFichiers.add(codeBarreUnique + ".png");
        }
        // Save des étiquettes si besoin plus tard
        if (!etiquettes.isEmpty()) {
            int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer toutes les étiquettes générées ?", "Enregistrer ?", JOptionPane.YES_NO_OPTION);
            if (reponse == JOptionPane.YES_OPTION) {
                //Fenêtre choix dossier
                JFileChooser fileChooser = new JFileChooser();
                //Choix dossier uniquement (Pas de fichier)
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //Choix => OK ou Annuler
                int retour = fileChooser.showSaveDialog(null);
                //If OK == ...
                if (retour == JFileChooser.APPROVE_OPTION) {
                    File dossier = fileChooser.getSelectedFile();
                    for (int j = 0; j < etiquettes.size(); j++) {
                        File fichier = new File(dossier, nomsFichiers.get(j));
                        sauvegarderImage(etiquettes.get(j), fichier.getAbsolutePath());
                    }

                }
                JOptionPane.showMessageDialog(null, "Toutes les étiquettes ont été enregistrées !");
            }
        }

    }

    private String genererCodeBarres(int idLivre) {

        //UUID = Universally Unique Identifier (Code unique de 36 caractères)
        String idUnique = UUID.randomUUID().toString().substring(0, 8);
        String codeBarre = "EX-" + idLivre + "-" + idUnique;
        return codeBarre;

    }

    private BufferedImage genererImageCodeBarres(String codeBarreUnique, String titre) {

        try {
            Barcode barcode = BarcodeFactory.createCode128(codeBarreUnique);
            barcode.setBarHeight(60);
            barcode.setBarWidth(2);
            barcode.setDrawingText(false);

            int padding = 10;
            int textHeight = 20;

            int width = barcode.getWidth() + padding * 2;
            int height = barcode.getHeight() + padding * 3 + textHeight;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            // Fond blanc
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);

            // Titre en haut
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(titre);
            int xText = (width - textWidth) / 2;
            g2d.drawString(titre, xText, padding + fm.getAscent());

            // Code-barres en dessous
            int yBarcode = padding * 2 + textHeight;
            barcode.draw(g2d, padding, yBarcode);

            g2d.dispose();

            return image;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private void sauvegarderImage(BufferedImage image, String nomFichier) {
        try {
            File outputfile = new File(nomFichier);
            ImageIO.write(image, "png", outputfile);
            JOptionPane.showMessageDialog(null, "Image enregistrée : " + outputfile.getAbsolutePath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de l'image.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remplissageComboAuteurs() {

        DAOAuteurs daoauteurs = new DAOAuteurs();

        List<Auteur> listAuteur;
        try {
            listAuteur = daoauteurs.findAllCombo();
            //Ajout d'une ligne "par défaut" en index 0 (Première place)
            listAuteur.add(0, new Auteur(-1, "-- Sélectionner un auteur --", ""));

            for (Auteur a : listAuteur) {
                combo_auteurs.addItem(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionLivrePan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cleanGenreSelection() {

        for (JCheckBox checkBox : checkboxesGenres) {
            checkBox.setSelected(false);
        }
    }

}
