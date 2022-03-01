package com.octest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private DaoFactory daoFactory;

    UtilisateurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private Connection loadDatabase() {
        // Chargement du driver
    	Connection connexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tds", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connexion;
    }
    
    
    @Override
    public void ajouter(Utilisateur utilisateur) {
        Connection connexion = null;
        Statement statement = null;
        if(utilisateur.getPseudo()!=null 
        		&& utilisateur.getMdp()!=null 
        		&& utilisateur.getEmail()!=null) {
	        try {
	            connexion = loadDatabase();
	            String req = "INSERT INTO utilisateurs(pseudo, mdp, email)  VALUES('"
	                    + utilisateur.getPseudo() + "', '"
	            		+ utilisateur.getMdp() +"', '"
	                    + utilisateur.getEmail() + "' );";
	            statement = connexion.createStatement();
	            statement.executeUpdate(req);
	            /*preparedStatement = connexion.prepareStatement("INSERT INTO utilisateurs(pseudo, mdp) VALUES(?, ?);");
	            preparedStatement.setString(1, utilisateur.getPseudo());
	            preparedStatement.setString(2, utilisateur.getMdp());
	            System.out.println(req);
	            int i = preparedStatement.executeUpdate();
	            System.out.println(i);*/
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
        }

    }

    @Override
    public List<Utilisateur> lister() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT"
            		+ "(SELECT COUNT(*) FROM voiture v "
            		+ "JOIN utilisateurs u ON v.id_utilisateur = u.id "
            		+ "WHERE u.id = util.id) "
            		+ "AS nb_articles, util.pseudo, util.date_creation, util.photo, util.description, util.id, util.nom, util.prenom "
            		+ "FROM utilisateurs util");

            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                int nb_articles = resultat.getInt("nb_articles");
                String date_crea = resultat.getString("date_creation");
                String photo = resultat.getString("photo");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String description = resultat.getString("description");
                int id = resultat.getInt("id");
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setPseudo(pseudo);
                //utilisateur.setMdp(mdp);
                utilisateur.setId(id);
                utilisateur.setDate_creation(date_crea);
                utilisateur.setPhoto(photo);
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                utilisateur.setNb_articles(nb_articles);
                utilisateur.setDescription(description);
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    
    @Override
    public List<Utilisateur> ListerVendeur() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT DISTINCT U.pseudo,U.mdp,U.id,U.date_creation,U.photo,U.nom,U.prenom,U.description FROM utilisateurs U join voiture V on U.id=V.id_utilisateur");

            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                String mdp = resultat.getString("mdp");
                int id = resultat.getInt("id");
                String date_crea = resultat.getString("date_creation");
                String photo = resultat.getString("photo");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String description = resultat.getString("description");
                
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setPseudo(pseudo);
                utilisateur.setMdp(mdp);
                utilisateur.setId(id);
                utilisateur.setDate_creation(date_crea);
                utilisateur.setPhoto(photo);
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                utilisateur.setDescription(description);
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    
    @Override
    public Utilisateur listerUtilisateur(int idUtil) {
        Utilisateur utilisateur = new Utilisateur();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM utilisateurs where id="+idUtil+";");

            while (resultat.next()) {
                String pseudo = resultat.getString("pseudo");
                String mdp = resultat.getString("mdp");
                int id = resultat.getInt("id");
                String date_crea = resultat.getString("date_creation");
                String photo = resultat.getString("photo");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String description = resultat.getString("description");
                
                utilisateur.setPseudo(pseudo);
                utilisateur.setMdp(mdp);
                utilisateur.setId(id);
                utilisateur.setDate_creation(date_crea);
                utilisateur.setPhoto(photo);
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                utilisateur.setDescription(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    @Override
    public boolean testLogin(String pseudo, String mdp) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        String pwd = "";
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT mdp FROM utilisateurs where pseudo = ? ;");
            preparedStatement.setString(1, pseudo);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()) {
            	pwd = resultat.getString("mdp");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pwd.equals(mdp);

    }

	@Override
	public boolean testregister(String pseudo, String mdp, String email) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public void modifier(String pseudo,String mdp ,String email, String nom, String prenom, String description, String ancienPseudo) {
        Connection connexion = null;
        Statement statement = null;
        int resultat = 0;

        try {
            connexion =loadDatabase();
            statement = connexion.createStatement();
            resultat = statement.executeUpdate("UPDATE utilisateurs SET pseudo = '"+pseudo+"',mdp ='"+mdp+"',email ='"+email+"',nom ='"+nom+"',prenom ='"+prenom+"',description ='"+ description + "'WHERE pseudo= '"+ancienPseudo+"';");
            if(resultat == 0) {
                System.out.println(" Pas de tuples modifier !");
            }
            else {
                System.out.println(" Modification effectué !");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}