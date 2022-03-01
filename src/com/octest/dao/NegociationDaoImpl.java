package com.octest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.*;

public class NegociationDaoImpl implements NegociationDao {

	private DaoFactory daoFactory;

	public NegociationDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Integer> getListeOffre(String nomArticle, String user) {
		String req = "SELECT v.id FROM voiture v JOIN utilisateurs u ON v.id_utilisateur = u.id WHERE v.modele='"
				+ nomArticle + "' && u.pseudo!='" + user + "';";
		List<Integer> id_offres = new ArrayList<Integer>();
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) {
            	id_offres.add(resultat.getInt("id"));
            } 
		} catch (SQLException e) {
                e.printStackTrace();
		}
		return id_offres;
	}
	@Override
	public int calculMoyenne(String modele) {
		int moyenne = 0;
		String req = "SELECT AVG(prix) AS prix FROM voiture WHERE modele='" + modele + "';";
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) {
            	moyenne = resultat.getInt("prix");
            } 
		} catch (SQLException e) {
                e.printStackTrace();
		}
		return moyenne;
	}

	@Override
	public List<Negociation> initNegociation(List<Integer> ListOffres, Voiture v, VoitureDao voitureDao) {
		List<Negociation> listNegociation = new ArrayList<Negociation>();
		Negociation n = new Negociation(v);
		n.setVendeur(voitureDao.getVoiture(ListOffres.get(0).toString()));
		n = negocier(n);
		n.afficherConsole();
		for(int i=0; i<ListOffres.size(); i++) {
			Negociation n1 = new Negociation(v);
			n1.setVendeur(voitureDao.getVoiture(ListOffres.get(i).toString()));
			n1 = negocier(n1);
			n1.setId(i+1);
			listNegociation.add(n1);
		}
		return listNegociation;
	}

	@Override
	public ArrayList<String> negocierPrixInit(Negociation n) {
		ArrayList<String> infos = new ArrayList<String>();
		infos.add("Définition du prix initial de négociation");
		infos.add("Le client a un budget de " + n.getAcheteur().getPrix() + " euros");
		infos.add("Le vendeur propose une voiture a " + n.getVendeur().getPrix() + " euros");
		int prix_initial_acheteur = n.getAcheteur().getPrix();
		int prix_initial_vendeur = n.getVendeur().getPrix();
		int prix;
		if(prix_initial_vendeur > prix_initial_acheteur)
			prix = (int) (prix_initial_vendeur*0.3 + prix_initial_acheteur*0.7);
		else
			prix = prix_initial_vendeur;
		infos.add("Le client commence a reflechir a un prix autour de "+ prix + " euros");
		if(argumentAPrixMoyen(n)) {
			int moyenne = calculMoyenne(n.getAcheteur().getModele());
			infos.add("Le prix moyen de vente pour ce modèle de voiture est de " + moyenne + " euros");
			prix = Math.max(moyenne, prix*=0.98);
			infos.add("Le client demande un rabais à " + prix + " euros");
		}
		if(argumentACouleur(n)) {
			prix*=0.98;//On demande un prix plus bas car la couleur n'est pas celle souhaitée par l'utilisateur
			infos.add("Le client demande un rabais de 1% ( " + prix + " euros ) car il recherche une autre couleur");
		}
		if(argumentAEtat(n) < 1) {
			prix*=argumentAEtat(n);
			infos.add("Le client demande un rabais ( " + prix + " euros ) à cause de l'état de la voiture");
		}
		if(argumentACT(n)) {
			prix*=0.98;
			infos.add("Le client demande un rabais de 2% ( " + prix + " euros ) à cause de l'absence de contrôle technique");
		}
		infos.add("Le client souhaite commencer la négociation à " + prix + " euros");
		infos.add(0,String.valueOf(prix));
		return infos;
	}
	@Override
	public boolean argumentVPrixMoyen(Negociation n) {
		int prix = n.getVendeur().getPrix();
		return (prix < calculMoyenne(n.getVendeur().getModele()));
	}
	@Override
	public boolean argumentAPrixMoyen(Negociation n) {
		int prix = n.getVendeur().getPrix();
		return(prix > calculMoyenne(n.getVendeur().getModele()));
	}
	@Override
	public boolean argumentACouleur(Negociation n) {
		return !n.getVendeur().getCouleur().equals(n.getAcheteur().getCouleur());
	}
	@Override
	public double argumentAEtat(Negociation n) {
		double etat = 1;
		switch(n.getVendeur().getEtat()) {
		case "Neuf":
			etat = 1;
			break;
		case "Tres Bon":
			etat = 0.99;
			break;
		case "Bon":
			etat = 0.98;
			break;
		case "Moyen":
			etat = 0.95;
			break;
		case "Mauvais":
			etat = 0.94;
			break;
		case "Tres Mauvais":
			etat = 0.90;
			break;
		}
		return etat;
	}

	@Override
	public boolean argumentACT(Negociation n) {
		return !n.getVendeur().isControle_technique();
	}

	public int selectCountVoiture(String where) { // Permet de compter le nb d'offres avec une condition
		int res = 0;
		String req = "SELECT COUNT(*) AS nb FROM voiture WHERE " + where;
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(req);
            while (resultat.next()) {
            	res = resultat.getInt("nb");
            } 
		} catch (SQLException e) {
                e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public boolean argumentVModeleUnique(Negociation n) {
		String where = "modele = '" + n.getVendeur().getModele() + "';";
		int nbVoitures = selectCountVoiture(where);
		return nbVoitures == 1;
	}

	@Override
	public boolean argumentVCouleurUnique(Negociation n) {
		String where = "modele = '" + n.getVendeur().getModele() + "' AND couleur = '" + n.getVendeur().getCouleur() + "';";
		int nbCouleur = selectCountVoiture(where);
		return nbCouleur == 1 && !argumentVModeleUnique(n);
	}
	@Override
	public boolean argumentVMeilleurEtat(Negociation n) {
		if(argumentVModeleUnique(n)) {
			return false;
		}
		String where = "";
		switch(n.getVendeur().getEtat()) {
		case "Tres Mauvais":
			where = "modele = '" + n.getVendeur().getModele() + "' AND etat = 'Mauvais';";
			if(selectCountVoiture(where) > 0) {
				System.out.println("Mauvais");
				return false;
			}
		case "Mauvais":
			where = "modele = '" + n.getVendeur().getModele() + "' AND etat = 'Moyen';";
			if(selectCountVoiture(where) > 0) {
				System.out.println("Moyen");
				return false;
			}
				
		case "Moyen":
			where = "modele = '" + n.getVendeur().getModele() + "' AND etat = 'Bon';";
			if(selectCountVoiture(where) > 0) {
				System.out.println("Bon");
				return false;
			}
		case "Bon":
			where = "modele = '" + n.getVendeur().getModele() + "' AND etat = 'Tres Bon';";
			if(selectCountVoiture(where) > 0) {
				System.out.println("TB");
				return false;
			}
		case "Tres Bon":
			where = "modele = '" + n.getVendeur().getModele() + "' AND etat = 'Neuf';";
			if(selectCountVoiture(where) > 0) {
				System.out.println("Neuf");
				return false;
			}
			break;
		}
		return true;
	}
	
	@Override
	public boolean argumentVAgeeBonEtat(Negociation n) {
		boolean etat = n.getVendeur().getEtat().equals("Bon") 
				|| n.getVendeur().getEtat().equals("Tres Bon") 
				|| n.getVendeur().getEtat().equals("Neuf");
		return n.getVendeur().getAnnee() < 2000 && etat;
	}

	@Override
	public boolean argumentARecenteNonNeuf(Negociation n) {
		boolean etat = !n.getVendeur().getEtat().equals("Neuf");
		return n.getVendeur().getAnnee() > 2018 && etat;
	}

	@Override
	public boolean argumentARecenteMoinsChere(Negociation n) {
		String where = "modele='" + n.getVendeur().getModele() + "' AND annee > " + n.getVendeur().getAnnee() +  " AND prix < " + n.getVendeur().getPrix() + ";";
		int nbVoitures = selectCountVoiture(where);
		return nbVoitures > 0;
	}

	@Override
	public boolean argumentAMoinsChereMeilleurEtat(Negociation n) {
		if(n.getVendeur().getEtat().equals("Neuf"))
			return false;
		String etat = "(";
		switch(n.getVendeur().getEtat()) {
		case "Tres Mauvais":
			etat+="etat = 'Mauvais' OR ";
		case "Mauvais":
			etat+="etat = 'Moyen' OR ";
		case "Moyen":
			etat+="etat = 'Bon' OR ";
		case "Bon":
			etat+="etat = 'Tres Bon' OR ";
		case "Tres Bon":
			etat+="etat = 'Neuf' );";
			break;
		}
		String where = "modele='" + n.getVendeur().getModele() + "' AND prix < " + n.getVendeur().getPrix() +" AND " + etat;
		//System.out.println(where);
		int nbVoitures = selectCountVoiture(where);
		return nbVoitures > 0;
	}

	@Override
	public boolean argumentVNeufRecente(Negociation n) {
		return n.getVendeur().getAnnee() > 2015 && n.getVendeur().getEtat().equals("Neuf");
	}

	@Override
	public int argumentFaibleKilometrage(Negociation n) {
		if(n.getVendeur().getKm() == 0) {
			return 0;
		} else if (n.getVendeur().getKm() <= 10) {
			return 1;
		}
		return 2;
	}

	@Override
	public List<Negociation> reajustementNegociations(List<Negociation> l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Negociation negocier(Negociation n) {
		ArrayList<String> discussion = new ArrayList<String>();
		System.out.println("Affichage d'une négociation en cours de réalisation");
		int prixAcheteur = n.getAcheteur().getPrix();
		int prixVendeur = n.getVendeur().getPrix();
		if(prixAcheteur > prixVendeur)
			prixAcheteur = prixVendeur;
		if(prixAcheteur < prixVendeur*0.85)
			prixAcheteur = (int) (prixVendeur*0.85);
		int prix = prixAcheteur;
		if(argumentAPrixMoyen(n)) {
			prix*=0.95;
			discussion.add("Client : Le prix de l'offre est supérieur au prix Moyen pour ce modèle. \n Nouvelle proposition à  " + prix +" euros");
		}
		else if(argumentVPrixMoyen(n)) {
			prix*=1.05;
			discussion.add("Vendeur : Le prix de l'offre est inférieur au prix Moyen pour ce modèle. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentACouleur(n)) {
			prix*=0.95;
			discussion.add("Client : La couleur n'est pas celle recherchée. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentVModeleUnique(n)) {
			prix*=1.05;
			discussion.add("Vendeur : Il n'y a aucune autre offre de ce modèle en vente. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentAEtat(n) < 1) {
			prix*= argumentAEtat(n);
			discussion.add("Client : La voiture n'est pas neuve. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentVCouleurUnique(n)) {
			prix*=1.05;
			discussion.add("Vendeur : Il n'y a aucune autre offre de ce modèle avec cette couleur en vente. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentACT(n)) {
			prix*=0.95;
			discussion.add("Client : Il n'y a pas de controle technique. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentVMeilleurEtat(n)) {
			prix*=1.05;
			discussion.add("Vendeur : Il n'y a aucune autre offre de ce modèle avec un meilleur état en vente. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentARecenteNonNeuf(n)) {
			prix*=0.95;
			discussion.add("Client : La voiture est recente, mais pas neuve. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentVAgeeBonEtat(n)) {
			prix*=1.05;
			discussion.add("Vendeur : La voiture est agée, mais en bon état. \n  Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentARecenteMoinsChere(n)) {
			prix*=0.95;
			discussion.add("Client : Il existe une offre du même modele plus recente et moins chere. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentVNeufRecente(n)) {
			prix*=1.05;
			discussion.add("Vendeur : La voiture est neuve et recente. \n Nouvelle proposition à  " + prix +" euros");
		}
		if(argumentAMoinsChereMeilleurEtat(n)) {
			prix*=0.95;
			discussion.add("Client : Il existe au moins une offre moins chere avec un état meilleur ou identique. \n Nouvelle proposition à  " + prix +" euros");
		}
		switch(argumentFaibleKilometrage(n)) {
		case 0:
			prix*=1.05;
			discussion.add("Vendeur : La voiture n'a jamais été utilisée. \n Nouvelle proposition à  " + prix +" euros");
			break;
		case 1:
			prix*=1.02;
			discussion.add("Vendeur : La voiture a été peu utilisée. \n Nouvelle proposition à  " + prix +" euros");
			break;
		case 2:
			prix*=0.95;
			discussion.add("Client : La voiture a été utilisée. \n Nouvelle proposition à  " + prix +" euros");
			break;
		}
		n.setNouveauPrix(prix);
		n.setDiscussion(discussion);
		return n;
	}
	
}
