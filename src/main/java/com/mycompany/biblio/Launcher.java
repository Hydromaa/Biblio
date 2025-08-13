package com.mycompany.biblio;


import View.MainFenetre;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher {

    public static void main(String[] args) {

        //Thème FlatLaf (Swing c'est bien mais c'est pas très joli)
        //https://www.formdev.com/flatlaf/
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }

        MainFenetre mainf = new MainFenetre();

    }
}
