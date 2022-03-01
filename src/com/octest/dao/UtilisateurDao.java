package com.octest.dao;

import java.util.List;

import com.octest.beans.Utilisateur;

public interface UtilisateurDao {
    void ajouter( Utilisateur utilisateur );
    List<Utilisateur> lister();
    List<Utilisateur> ListerVendeur();
    Utilisateur listerUtilisateur(int idUtil);
    void modifier(String pseudo,String mdp ,String email ,String nom, String prenom, String description,String ancienPseudo);
    boolean testLogin(String pseudo, String mdp);
    boolean testregister(String pseudo, String mdp, String email);
}