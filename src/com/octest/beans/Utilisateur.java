package com.octest.beans;

public class Utilisateur {
	public int id;
	public String pseudo;
	public String mdp;
	public String email;
	public String date_creation;
	public int notification;
	public String photo;
	public String nom;
	public String prenom;
	public String description;
	public int nb_articles;
		
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(String date) {
		this.date_creation = date;
	}
	public int getNotification() {
		return notification;
	}
	public void setNotification(int notification) {
		this.notification = notification;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNb_articles() {
		return nb_articles;
	}
	public void setNb_articles(int nb_articles) {
		this.nb_articles = nb_articles;
	}
}
