package View;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class EmpruntFen extends JDialog {

    private EmpruntPan ep = new EmpruntPan();

    public EmpruntFen(JFrame parent) {

        super(parent, "Retour des livres", true);
        setTitle("Emprunt de livres");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(ep);
        getRootPane().registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke("ESCAPE"),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        setVisible(true);

    }

}
