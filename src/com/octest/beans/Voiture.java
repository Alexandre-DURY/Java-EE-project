package com.octest.beans;

public class Voiture {

	private String visuel;
	private String modele;
	private String categorie;
	private String etat;
	private int km;
	private boolean controle_technique;
	private String couleur;
	private int annee;
	private String description;
	private int prix;
	private int id;
	private String nomVendeur;
	
	public String getVisuel() {
		return visuel;
	}
	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public boolean isControle_technique() {
		return controle_technique;
	}
	public void setControle_technique(boolean controle_technique) {
		this.controle_technique = controle_technique;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomVendeur() {
		return nomVendeur;
	}
	public void setNomVendeur(String nomVendeur) {
		this.nomVendeur = nomVendeur;
	}
}