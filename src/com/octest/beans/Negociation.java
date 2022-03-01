package com.octest.beans;

import java.util.ArrayList;

public class Negociation {
	private Voiture vendeur;
	private Voiture acheteur;
	private int nouveauPrix;
	private ArrayList<String> discussion;
	private int id; // Numéro de négociation
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
		System.out.println("Affichage d'une seule négociation (pour l'instant)");
		System.out.println("Affichage de la voiture recherchée : ");
		System.out.println("Budget : " + acheteur.getPrix());
		System.out.println("Modele : " + acheteur.getModele());
		System.out.println("Couleur : " + acheteur.getCouleur());
		System.out.println("Affichage de la premiere offre : ");
		System.out.println("Affichage du modele : " + vendeur.getModele());
		System.out.println("Affichage de la categorie : " + vendeur.getCategorie());
		System.out.println("Affichage de la description : " + vendeur.getDescription());
		System.out.println("Affichage de la couleur : " + vendeur.getCouleur());
		System.out.println("Affichage du controle technique : " + vendeur.isControle_technique());
		System.out.println("Affichage de l'état : " + vendeur.getEtat());
		System.out.println("Affichage du prix Avant négociation : " + vendeur.getPrix());
		System.out.println("Affichage de la discussion de négociation : " + discussion);
		System.out.println("Affichage du prix Après négociation : " + nouveauPrix);
		
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
