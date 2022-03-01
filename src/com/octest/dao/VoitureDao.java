package com.octest.dao;

import java.util.List;

import com.octest.beans.Transaction;
import com.octest.beans.Voiture;

public interface VoitureDao 
{
	void ajouter(Voiture voiture, String nomUser);
	List<Voiture> lister();
	Voiture getVoiture(String id);
	List<String> getModeles();
	List<String> getEtats();
	List<String> getCategories();
	List<String> getCouleurs();
	List<Voiture>ListerOffres(String pseudo);
	List<Voiture> listerOffresUtil(int idVendeur);
	List<Voiture> ListerMesAchats(String pseudo);
	List<Transaction> listerTransactions(String utilCO,boolean Achat);
	void addtransaction(String id, String prixRentenue, String userCo);
	void supprimerVoiture (String idVoiture);
	void afficherConsole(Voiture v);
	public int getLastId();
}
