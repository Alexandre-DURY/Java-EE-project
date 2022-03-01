package com.octest.beans;

public class Transaction {
	
	private Voiture v;
	private int prixRetenue;
	private String nom_utilisateur;
	private String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Voiture getV() {
		return v;
	}
	public void setV(Voiture v) {
		this.v = v;
	}
	public int getPrixRetenue() {
		return prixRetenue;
	}
	public void setPrixRetenue(int prixRetenue) {
		this.prixRetenue = prixRetenue;
	}
	public String getNom_utilisateur() {
		return nom_utilisateur;
	}
	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}

}
