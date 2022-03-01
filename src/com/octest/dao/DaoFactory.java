package com.octest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String pseudo;
    private String mdp;

    DaoFactory(String url, String pseudo, String mdp) {
        this.url = url;
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:mysql://localhost:3306/tds", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connexion = DriverManager.getConnection(url, pseudo, mdp);
        connexion.setAutoCommit(false);
        return connexion; 
    }

    // Récupération du Dao
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDaoImpl(this);
    }

	public NegociationDao getNegociateurDao() {
		// TODO Auto-generated method stub
		return new NegociationDaoImpl(this);
	}

	public VoitureDao getVoitureDao() {
		// TODO Auto-generated method stub
		return new VoitureDaoImpl(this);
	}
}