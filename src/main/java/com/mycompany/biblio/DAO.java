package com.mycompany.biblio;


import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO<T, K> {

    public Connection conn = DAOFactory.getConnection();
    
    public abstract boolean create(T obj) throws SQLException;
    public abstract boolean update (T obj) throws SQLException;
    public abstract boolean delete(K id) throws SQLException;
    public abstract T findById(K id) throws SQLException;
    public abstract List<T> findAll(K String) throws SQLException;
    public abstract T rsToObj(ResultSet rs) throws SQLException;

}
