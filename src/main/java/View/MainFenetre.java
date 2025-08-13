package View;



import javax.swing.JFrame;

public class MainFenetre extends JFrame {

    public MainFenetre() {

        setTitle("Gestion bibliothèque");
        setSize(1300,900);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new MainPanneau());
        setVisible(true);

    }
}
