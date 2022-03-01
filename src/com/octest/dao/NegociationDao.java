package com.octest.dao;

import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Negociation;
import com.octest.beans.Voiture;

public interface NegociationDao {
	public List<Integer> getListeOffre(String nomArticle, String user);
	public List<Negociation> initNegociation(List<Integer> ListOffres, Voiture v, VoitureDao voitureDao);
	public Negociation negocier(Negociation n);
	public ArrayList<String> negocierPrixInit(Negociation n);
	public int calculMoyenne(String modele);
	
	//Fonctions arguments = retourne une valeur pour savoir s'il faut argumenter sur un parametre.
	
	//Arguments de l'Acheteur
	public boolean argumentAPrixMoyen(Negociation n);//Retourne vrai si le prix du vendeur est supérieur au prix moyen
	public boolean argumentACouleur(Negociation n); // Retourne vrai si la couleur n'est pas celle recherchée
	public double argumentAEtat(Negociation n); //Retourne une valeur entre 0.90 et 1 selon l'état de la voiture
	public boolean argumentACT(Negociation n);//Retourne vrai s'il n'y a pas de controle technique
	public boolean argumentARecenteNonNeuf(Negociation n);//Retourne vrai si l'offre est recente, mais pas neuf
	public boolean argumentARecenteMoinsChere(Negociation n);//Retourne vrai s'il existe une voiture du même modele plus recente et moins chere
	public boolean argumentAMoinsChereMeilleurEtat(Negociation n);//Retourne vrai s'il existe au moins une offre moins chere avec un état meilleur (ou identique)
	
	//Arguments du vendeur
	public boolean argumentVPrixMoyen(Negociation n);//Retourne vrai si le prix du vendeur est inférieur au prix moyen
	public boolean argumentVModeleUnique(Negociation n);//Retourne vrai si c'est la seule voiture de ce modele en vente actuellement
	public boolean argumentVCouleurUnique(Negociation n);//Retourne vrai si c'est la seule offre de cette voiture avec la couleur recherchée
	public boolean argumentVMeilleurEtat(Negociation n);// Retourne vrai s'il n'y a pas de voiture de ce modele avec un meilleur etat en vente
	public boolean argumentVAgeeBonEtat(Negociation n);// Retourne vrai si une voiture est vieille et est en bon état
	public boolean argumentVNeufRecente(Negociation n);// Retourne vrai si une voiture est nouvelle et neuf
	
	public int argumentFaibleKilometrage(Negociation n);//Retourne 0 si jamais utilisé, 1 si km <= 10, 2 sinon
	//Vérification cohérence des offres   ---   TODO
	public List<Negociation> reajustementNegociations(List<Negociation> l); // Réajuste les prix finaux par rapport aux autres negociations
	
}
