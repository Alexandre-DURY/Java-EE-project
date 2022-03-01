package com.octest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Transaction;
import com.octest.beans.Voiture;

public class VoitureDaoImpl implements VoitureDao {
	
	private DaoFactory daoFactory;

    VoitureDaoImpl(DaoFactory daoFactory) {
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

	
	public void ajouter(Voiture voiture, String nomUser) {
        Connection connexion = null;
        Statement statement = null;
        String req_id_utilisateur = "(SELECT id FROM utilisateurs WHERE pseudo='" +nomUser + "')";
        
        int ct;
        if(voiture.isControle_technique()) ct = 1;
        else ct = 0;
        
        try 
        {
            connexion = loadDatabase();
            String req = "INSERT INTO voiture(visuel, modele, categorie, etat, km, ct, couleur, annee, description, prix, id_utilisateur)  VALUES('"
                    + voiture.getVisuel() + "', '"
            		+ voiture.getModele() +"', '"
            		+ voiture.getCategorie()+ "', '"
                    + voiture.getEtat() + "', '"
                    + voiture.getKm() + "', '"
                    + ct + "', '"
                    + voiture.getCouleur() + "', '"
                    + voiture.getAnnee() + "', '"
            		+ voiture.getDescription() + "', '"
            		+ voiture.getPrix() + "', "
            		+ req_id_utilisateur +"  );";
            statement = connexion.createStatement();
            statement.executeUpdate(req);
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	@Override
	public List<Voiture> lister() {
		List<Voiture> voitures = new ArrayList<Voiture>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        String req = "SELECT v.id, v.visuel, v.modele, v.categorie, v.etat, v.km, v.ct, v.couleur, v.annee, v.description, v.prix, u.pseudo from voiture v JOIN utilisateurs u on u.id = v.id_utilisateur";
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            
            while (resultat.next()) 
            {
            	int id = resultat.getInt("id");
                String visuel = resultat.getString("visuel");
                String modele = resultat.getString("modele");
                String categorie = resultat.getString("categorie");
                String etat = resultat.getString("etat");
                int km = resultat.getInt("km");
                boolean ct = resultat.getBoolean("ct");
                String couleur = resultat.getString("couleur");
                int annee = resultat.getInt("annee");
                String description = resultat.getString("description");
                int prix = resultat.getInt("prix");
                String pseudo = resultat.getString("pseudo");
                
                Voiture voiture = new Voiture();
                voiture.setId(id);
                voiture.setVisuel(visuel);
                voiture.setModele(modele);
                voiture.setCategorie(categorie);
                voiture.setEtat(etat);
                voiture.setKm(km);
                voiture.setControle_technique(ct);
                voiture.setCouleur(couleur);
                voiture.setAnnee(annee);
                voiture.setDescription(description);
                voiture.setPrix(prix);
                voiture.setNomVendeur(pseudo);
                voitures.add(voiture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voitures;
	}
	
	@Override
	public Voiture getVoiture(String id) {
		Voiture voiture = new Voiture();
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		String req = "SELECT v.visuel, v.modele, v.categorie, v.etat, v.km, v.ct, v.couleur, v.annee, v.description, v.prix, v.id, u.pseudo from voiture v JOIN utilisateurs u on u.id = v.id_utilisateur WHERE v.id='"+ id + "';";
		try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) 
            {
            	String visuel = resultat.getString("visuel");
                String modele = resultat.getString("modele");
                String categorie = resultat.getString("categorie");
                String etat = resultat.getString("etat");
                int km = resultat.getInt("km");
                boolean ct = resultat.getBoolean("ct");
                String couleur = resultat.getString("couleur");
                int annee = resultat.getInt("annee");
                String description = resultat.getString("description");
                int prix = resultat.getInt("prix");
                int id_ = resultat.getInt("id");
                String pseudo = resultat.getString("pseudo");
                voiture.setVisuel(visuel);
                voiture.setModele(modele);
                voiture.setCategorie(categorie);
                voiture.setEtat(etat);
                voiture.setKm(km);
                voiture.setControle_technique(ct);
                voiture.setCouleur(couleur);
                voiture.setAnnee(annee);
                voiture.setDescription(description);
                voiture.setPrix(prix);
                voiture.setId(id_);
                voiture.setNomVendeur(pseudo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return voiture;
	}
	@Override
	public List<String> getModeles() {
		String req = "SELECT DISTINCT modele FROM voiture;";
		List<String> modeles = new ArrayList<String>();
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) {
            	modeles.add(resultat.getString("modele"));
            } 
		} catch (SQLException e) {
                e.printStackTrace();
		}
		return modeles;
	}
	@Override
	public List<String> getEtats() {
		String req = "SELECT DISTINCT etat FROM voiture;";
		List<String> etats = new ArrayList<String>();
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) {
            	etats.add(resultat.getString("etat"));
            } 
		} catch (SQLException e) {
                e.printStackTrace();
		}
		return etats;
	}
	
	@Override
	public List<String> getCouleurs() {
		String req = "SELECT DISTINCT couleur FROM voiture;";
		List<String> couleurs = new ArrayList<String>();
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) {
            	couleurs.add(resultat.getString("couleur"));
            } 
		} catch (SQLException e) {
                e.printStackTrace();
		}
		return couleurs;
	}
	
	@Override
	public List<String> getCategories() {
		String req = "SELECT DISTINCT categorie FROM voiture;";
		List<String> categories = new ArrayList<String>();
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) {
            	categories.add(resultat.getString("categorie"));
            } 
		} catch (SQLException e) {
                e.printStackTrace();
		}
		return categories;
	}
	
	@Override
	public List<Voiture> ListerOffres(String pseudo){
		
		 List<Voiture> articles = new ArrayList<Voiture>();
	        Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;

	        try {
	            connexion = daoFactory.getConnection();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT * FROM voiture V join utilisateurs U on V.id_utilisateur=U.id "
	            		+ " where U.pseudo ='" +pseudo+"';");

	            while (resultat.next()) {
	            	int id = resultat.getInt("V.id");
	                String nom = resultat.getString("modele");
	                String description = resultat.getString("description");
	                int prix_min = resultat.getInt("prix");
	                String photo = resultat.getString("visuel");
	                
	                Voiture article = new Voiture();
	                article.setVisuel(photo);
	                article.setModele(nom);
	                article.setDescription(description);
	                article.setPrix(prix_min);
	                article.setId(id);
	                articles.add(article);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return articles;
	}
	
	@Override
	public List<Voiture> listerOffresUtil(int idVendeur){
		
		List<Voiture> voitures = new ArrayList<Voiture>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        String req = "SELECT * from voiture v JOIN utilisateurs u on u.id = v.id_utilisateur WHERE u.id = "+ idVendeur + ";";
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            
            while (resultat.next()) 
            {
            	
                String visuel = resultat.getString("visuel");
                String modele = resultat.getString("modele");
                int annee = resultat.getInt("annee");
                String description = resultat.getString("description");
                int prix = resultat.getInt("prix");
                String pseudo = resultat.getString("pseudo");
                int id = resultat.getInt("V.id");
                
                Voiture voiture = new Voiture();
                voiture.setId(id);
                voiture.setVisuel(visuel);
                voiture.setModele(modele);
                voiture.setAnnee(annee);
                voiture.setDescription(description);
                voiture.setPrix(prix);
                voiture.setNomVendeur(pseudo);
                voitures.add(voiture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voitures;
	}
	
	public List<Transaction> listerTransactions(String utilCO,boolean Achat){
		
		List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        String req = "SELECT * FROM transaction where nom_vendeur = '"+utilCO+"'";
        if(Achat) {
        	req = "SELECT * FROM transaction where nom_utilisateur = '"+utilCO+"'";
        }
       
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) 
            {
                String modele = resultat.getString("modele");
                String categorie = resultat.getString("Categorie");
                int annee = resultat.getInt("annee");
                String description = resultat.getString("description");
                int prix_offre = resultat.getInt("prix_offre");
                int prix_vente = resultat.getInt("prix_vente");
                String etat = resultat.getString("Etat");
                int km = resultat.getInt("km");
                int ct = resultat.getInt("ct");
                String date = resultat.getString("date");
                String nom_vendeur =resultat.getString("nom_vendeur");
                String nom_utilisateur =resultat.getString("nom_utilisateur");
                
                
                
                Voiture voiture = new Voiture();
                voiture.setModele(modele);
                voiture.setAnnee(annee);
                voiture.setDescription(description);
                voiture.setPrix(prix_offre);
                voiture.setCategorie(categorie);
                voiture.setEtat(etat);
                voiture.setNomVendeur(nom_vendeur);
                
                
                Transaction tran = new Transaction();
                tran.setV(voiture);
                tran.setDate(date);
                tran.setPrixRetenue(prix_vente);
                tran.setNom_utilisateur(nom_utilisateur);
                System.out.print(nom_utilisateur);
                System.out.print("VENDEUR ::::");
                System.out.println(nom_vendeur);
                transactions.add(tran);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
		
		
	}
	
	@Override
	public List<Voiture> ListerMesAchats(String pseudo){
		
		 List<Voiture> articles = new ArrayList<Voiture>();
	        Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;

	        try {
	            connexion = daoFactory.getConnection();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT * FROM transaction T join utilisateurs U on T.nom_utilisateur=U.pseudo "
	            		+ " where T.nom_utilisateur ='" +pseudo+"';");

	            while (resultat.next()) {
	                String nom = resultat.getString("modele");
	                String description = resultat.getString("description");
	                int prix_min = resultat.getInt("prix_vente");
	                String photo = resultat.getString("T.visuel");
	                
	                Voiture article = new Voiture();
	                article.setVisuel(photo);
	                article.setModele(nom);
	                article.setDescription(description);
	                article.setPrix(prix_min);
	                articles.add(article);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return articles;
	}

	@Override
	public void addtransaction(String id, String prixRentenue,String userCo) {
		
		  Voiture voiture = new Voiture();
		  voiture = getVoiture(id);
		  Connection connexion = null;
	      Statement statement = null;
		  try {
		            connexion = loadDatabase();
		            String req = "INSERT INTO transaction (Modele, Categorie, Description, Etat, km, ct, annee, nom_vendeur, nom_utilisateur, prix_offre, prix_vente,visuel)"
		            		+ " VALUES ('"+voiture.getModele()+"','"+voiture.getCategorie()+"','"+voiture.getDescription()+"','"+voiture.getEtat()+"',"+voiture.getKm()
		            		+","+voiture.isControle_technique()+","+voiture.getAnnee()+",'"+voiture.getNomVendeur()+"','"+userCo+"',"+voiture.getPrix()+","+prixRentenue+",'"+voiture.getVisuel()+"');";
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
	
	@Override
	public void supprimerVoiture(String idVoiture) {

		  Connection connexion = null;
	      Statement statement = null;
		 try {
	            connexion = loadDatabase();
	            String req = "DELETE FROM voiture WHERE id="+idVoiture+";";
	            statement = connexion.createStatement();
	            statement.executeUpdate(req);
		 } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	@Override
	public void afficherConsole(Voiture v) {
		System.out.println("Visuel = " + v.getVisuel());
		System.out.println("Modele = " + v.getModele());
		System.out.println("Catégorie = " + v.getCategorie());
		System.out.println("Etat = " + v.getEtat());
		System.out.println("km = " + v.getKm());
		System.out.println("Controle technique = " + v.isControle_technique());
		System.out.println("Couleur = " + v.getCouleur());
		System.out.println("Année = " + v.getAnnee());
		System.out.println("Description = " + v.getDescription());
		System.out.println("Prix = " + v.getPrix());
	}

	@Override
    public int getLastId() {
        String req = "SELECT MAX(id) AS id FROM voiture";
        int id = 0;
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) {
                id = resultat.getInt("id");
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return id;
    }
}


