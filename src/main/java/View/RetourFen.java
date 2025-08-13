package View;

import java.awt.Dialog;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class RetourFen extends JDialog {

    private RetourPan rp = new RetourPan();

    public RetourFen(JFrame parent) {

        super(parent, "Retour des livres", true);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(rp);
        getRootPane().registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke("ESCAPE"),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        setVisible(true);

    }

}
