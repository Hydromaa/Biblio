package com.mycompany.biblio;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFactory {

    static final String Driver = "com.mysql.cj.jdbc.Driver";
    static final String Url = "jdbc:mysql://localhost:3306/bibliotheque";
    static final String User = "root";
    static final String Pwd = "";

    private static Connection conn = null;

    private DAOFactory() {
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(Driver);
                conn = DriverManager.getConnection(Url, User, Pwd);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

}
