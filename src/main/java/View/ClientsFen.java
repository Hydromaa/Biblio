package View;

import View.ClientsPan;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.KeyStroke;

public class ClientsFen extends JDialog {

    private ClientsPan clientsPan = new ClientsPan();

    public ClientsFen(JFrame parent) {
        super(parent, "Gestion des clients", true);
        setTitle("Gestion des clients");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(clientsPan);
        getRootPane().registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke("ESCAPE"),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        setVisible(true);
    }
}
