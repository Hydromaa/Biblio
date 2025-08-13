package View;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GestionLivreFen extends JDialog {

    private GestionLivrePan glp = new GestionLivrePan();

    public GestionLivreFen(JFrame parent) {

        super(parent, "Gestion des livres", true);
        setTitle("Gestion des livres");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(glp);
        getRootPane().registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke("ESCAPE"),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        setVisible(true);
    }
}
