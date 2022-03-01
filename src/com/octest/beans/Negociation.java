package com.octest.beans;

import java.util.ArrayList;

public class Negociation {
	private Voiture vendeur;
	private Voiture acheteur;
	private int nouveauPrix;
	private ArrayList<String> discussion;
	private int id; // Num�ro de n�gociation
	public Negociation(Voiture v) {
		acheteur = v;
		discussion = new ArrayList<String>();
		discussion.add("test");
	}
	public Voiture getVendeur() {
		return vendeur;
	}
	public void setVendeur(Voiture vendeur) {
		this.vendeur = vendeur;
	}
	public Voiture getAcheteur() {
		return acheteur;
	}
	public void setAcheteur(Voiture acheteur) {
		this.acheteur = acheteur;
	}
	public void afficherConsole() {
		System.out.println("Affichage d'une seule n�gociation (pour l'instant)");
		System.out.println("Affichage de la voiture recherch�e : ");
		System.out.println("Budget : " + acheteur.getPrix());
		System.out.println("Modele : " + acheteur.getModele());
		System.out.println("Couleur : " + acheteur.getCouleur());
		System.out.println("Affichage de la premiere offre : ");
		System.out.println("Affichage du modele : " + vendeur.getModele());
		System.out.println("Affichage de la categorie : " + vendeur.getCategorie());
		System.out.println("Affichage de la description : " + vendeur.getDescription());
		System.out.println("Affichage de la couleur : " + vendeur.getCouleur());
		System.out.println("Affichage du controle technique : " + vendeur.isControle_technique());
		System.out.println("Affichage de l'�tat : " + vendeur.getEtat());
		System.out.println("Affichage du prix Avant n�gociation : " + vendeur.getPrix());
		System.out.println("Affichage de la discussion de n�gociation : " + discussion);
		System.out.println("Affichage du prix Apr�s n�gociation : " + nouveauPrix);
		
	}
	public int getNouveauPrix() {
		return nouveauPrix;
	}
	public void setNouveauPrix(int nouveauPrix) {
		this.nouveauPrix = nouveauPrix;
	}
	public ArrayList<String> getDiscussion() {
		return discussion;
	}
	public void setDiscussion(ArrayList<String> discussion) {
		this.discussion = discussion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
